package com.charley.serviceorder.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.charley.internalcommon.constant.CommonStatusEnum;
import com.charley.internalcommon.constant.IdentityConstant;
import com.charley.internalcommon.constant.OrderConstants;
import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.OrderInfo;
import com.charley.internalcommon.dto.PriceRule;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.OrderDriverResponse;
import com.charley.internalcommon.reponese.TerminalResponse;
import com.charley.internalcommon.request.OrderRequest;
import com.charley.internalcommon.request.PriceRuleIsNewRequest;
import com.charley.internalcommon.util.RedisPrefixUtils;
import com.charley.serviceorder.mapper.OrderInfoMapper;
import com.charley.serviceorder.remote.ServiceDriverUserClient;
import com.charley.serviceorder.remote.ServiceMapClient;
import com.charley.serviceorder.remote.ServicePriceClient;
import com.charley.serviceorder.remote.ServiceSsePushClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:48
 * @ClassName: OrderInfoService
 * @Version 1.0
 * @Description: 订单服务
 */
@Service
@Slf4j
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private ServicePriceClient servicePriceClient;

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ServiceSsePushClient serviceSsePushClient;


    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 22:44
     * @Description: 新建订单
     */
    public ResponseResult add(OrderRequest orderRequest) {
        log.info("orderRequest: " + orderRequest.toString());

        //查询城市是否有可用的司机
        ResponseResult<Boolean> availableDriver = serviceDriverUserClient.isAvailableDriver(orderRequest.getAddress());
        log.info("城市是否有司机结果" + availableDriver);
        if (!availableDriver.getData()) {
            return ResponseResult.fail(CommonStatusEnum.CITY_DRIVER_EMPTY.getCode(), CommonStatusEnum.CITY_DRIVER_EMPTY.getValue(), String.valueOf(false));
        }


        // 需要判断计价规则的版本是否是最新
        String fareType = orderRequest.getFareType();
        log.info("fareType : " + fareType);
        PriceRuleIsNewRequest priceRuleIsNewRequest = new PriceRuleIsNewRequest();
        priceRuleIsNewRequest.setFareType(orderRequest.getFareType());
        priceRuleIsNewRequest.setFareVersion(orderRequest.getFareVersion());
        ResponseResult<Boolean> aNew = servicePriceClient.isNew(priceRuleIsNewRequest);
        if (!aNew.getData()) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_CHANGED.getCode(), CommonStatusEnum.PRICE_RULE_CHANGED.getValue());
        }

        // 需要判断下单的设备是否是黑名单设备
        if (isBlackDevice(orderRequest)) {
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(), CommonStatusEnum.DEVICE_IS_BLACK.getValue());
        }

        // 判断下单的城市和计价规则是否正常
        if (!isPriceRuleExists(orderRequest)) {
            return ResponseResult.fail(CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getCode(), CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getValue());
        }


        // 判断乘客 是否有进行中的订单
        if (isPassengerOrderGoingOn(orderRequest.getPassengerId()) > 0) {
            return ResponseResult.fail(CommonStatusEnum.ORDER_GOING_ON.getCode(), CommonStatusEnum.ORDER_GOING_ON.getValue(), String.valueOf(false));
        }

        // 创建订单
        OrderInfo orderInfo = new OrderInfo();

        BeanUtils.copyProperties(orderRequest, orderInfo);
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);

        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);

        orderInfoMapper.insert(orderInfo);


        for (int i = 0; i < 6; i++) {
            // 派单 dispatchRealTimeOrder
            int result = dispatchRealTimeOrder(orderInfo);
            if (result == 1) {
                break;
            }
            // 等待20s
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return ResponseResult.success("");
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: dispatchRealTimeOrder
     * @param: orderInfo
     * @paramType [com.charley.internalcommon.dto.OrderInfo]
     * @return: int
     * @Date: 2023/2/26 16:09
     * @Description: 实时订单派单逻辑  如果返回 1 ，就派单成功
     */
    public synchronized int dispatchRealTimeOrder(OrderInfo orderInfo) {
        log.info("循环一次");
        int result = 0;
        // 2km
        String depLatitude = orderInfo.getDepLatitude();
        String depLongitude = orderInfo.getDepLongitude();
        String center = depLatitude + "," + depLongitude;

        List<Integer> radiusList = new ArrayList<>();
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);

        // 搜索结果
        ResponseResult<List<TerminalResponse>> listResponseResult = null;
        radius:
        for (int i = 0; i < radiusList.size(); i++) {

            listResponseResult = serviceMapClient.terminalAroundSearch(center, radiusList.get(i));
            log.info("在半径为" + radiusList.get(i) + "米的范围内, 寻找车辆, 结果：" + JSONArray.fromObject(listResponseResult.getData()).toString());

            // 获得终端

            // 解析终端
            List<TerminalResponse> data = listResponseResult.getData();

            // 为了测试是否从地图中获取到司机
//            List<TerminalResponse> data = new ArrayList<>();

            for (int j = 0; j < data.size(); j++) {
                TerminalResponse terminalResponse = data.get(j);
                Long carId = terminalResponse.getCarId();
                String longitude = terminalResponse.getLongitude();
                String latitude = terminalResponse.getLatitude();

                // 查询是否有多余的可派单司机
                ResponseResult<OrderDriverResponse> availableDriver = serviceDriverUserClient.getAvailableDriver(carId);
                if (availableDriver.getCode() == CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode()) {
                    log.info("没有车辆ID：" + carId + ", 对应的司机");
                    continue;
                } else {
                    log.info("找到了正在出车的司机，他的车辆ID： " + carId);

                    OrderDriverResponse orderDriverResponse = availableDriver.getData();
                    Long driverId = orderDriverResponse.getDriverId();
                    String driverPhone = orderDriverResponse.getDriverPhone();
                    String licenseId = orderDriverResponse.getLicenseId();
                    String vehicleNo = orderDriverResponse.getVehicleNo();

                    String lockKey = (driverId + "").intern();
                    RLock lock = redissonClient.getLock(lockKey);
                    lock.lock();

//                    isDriverOrderGoingOn
                    // 判断司机 是否有进行中的订单
                    if (isDriverOrderGoingOn(driverId) > 0) {
                        lock.unlock();
                        continue;
                    }
                    // 订单直接匹配司机
                    // 查询当前车辆信息
                    QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
                    carQueryWrapper.eq("id", carId);

                    /*
                        设置订单中和司机车辆相关的信息
                     */
                    // 查询当前司机信息
                    orderInfo.setDriverId(driverId);
                    orderInfo.setDriverPhone(driverPhone);
                    orderInfo.setCarId(carId);
                    // 从地图中来
                    orderInfo.setReceiveOrderCarLongitude(longitude);
                    orderInfo.setReceiveOrderCarLatitude(latitude);
                    orderInfo.setReceiveOrderTime(LocalDateTime.now());
                    // 从司机来
                    orderInfo.setLicenseId(licenseId);
                    // 从车辆来
                    orderInfo.setVehicleNo(vehicleNo);
                    orderInfo.setOrderStatus(OrderConstants.DRIVER_RECEIVE_ORDER);

                    orderInfoMapper.updateById(orderInfo);


                    // 通知司机
                    JSONObject driverContent = new JSONObject();
                    driverContent.put("passengerId", orderInfo.getPassengerId());
                    driverContent.put("passengerPhone", orderInfo.getPassengerPhone());
                    driverContent.put("departure", orderInfo.getDeparture());
                    driverContent.put("depLongitude", orderInfo.getDepLongitude());
                    driverContent.put("depLatitude", orderInfo.getDepLatitude());

                    driverContent.put("destination", orderInfo.getDestination());
                    driverContent.put("destLongitude", orderInfo.getDestLongitude());
                    driverContent.put("destLatitude", orderInfo.getDestLatitude());

                    serviceSsePushClient.push(driverId, IdentityConstant.DRIVER_IDENTITY, driverContent.toString());

                    // 通知乘客
                    JSONObject passengerContent = new JSONObject();
                    passengerContent.put("driverId", orderInfo.getDriverId());
                    passengerContent.put("driverPhone", orderInfo.getDriverPhone());
                    passengerContent.put("vehicleNo", orderInfo.getVehicleNo());

                    // 车辆信息  调用车辆服务查询
                    Car carRemote = serviceDriverUserClient.getCarById(carId).getData();
                    passengerContent.put("brand", carRemote.getBrand());
                    passengerContent.put("model", carRemote.getModel());
                    passengerContent.put("vehicleColor", carRemote.getVehicleColor());

                    passengerContent.put("receiveOrderCarLongitude", orderInfo.getReceiveOrderCarLongitude());
                    passengerContent.put("receiveOrderCarLatitude", orderInfo.getReceiveOrderCarLatitude());

                    serviceSsePushClient.push(orderInfo.getPassengerId(), IdentityConstant.PASSENGER_IDENTITY, passengerContent.toString());

                    result = 1;
                    lock.unlock();

                    // 退出，不再进行司机的查找，如果派单成功，则退出循环
                    break radius;

                }

            }

        }
        return result;
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: isPriceRuleExists
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: boolean
     * @Date: 2023/2/26 22:44
     * @Description: 判断计价规则是否存在
     */
    private boolean isPriceRuleExists(OrderRequest orderRequest) {
        String fareType = orderRequest.getFareType();
        int index = fareType.indexOf("$");
        String cityCode = fareType.substring(0, index);
        String vehicleType = fareType.substring(index + 1);

        PriceRule priceRule = new PriceRule().setCityCode(cityCode).setVehicleType(vehicleType);

        return servicePriceClient.ifPriceExists(priceRule).getData();

    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: isBlackDevice
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: boolean
     * @Date: 2023/2/26 22:45
     * @Description: 判断是否是黑名单
     */
    private boolean isBlackDevice(OrderRequest orderRequest) {


        String deviceCode = orderRequest.getDeviceCode();
        // 生成key
        String deviceCodeKey = RedisPrefixUtils.blackDeviceCodePrefix + deviceCode;

        // 设置key  看原来有没有key
        Boolean aBoolean = stringRedisTemplate.hasKey(deviceCodeKey);
        if (aBoolean) {
            String s = stringRedisTemplate.opsForValue().get(deviceCodeKey);
            int i = Integer.parseInt(s);
            if (i >= 2) {
                // 当前设备超过下单次数
                return true;
            } else {
                stringRedisTemplate.opsForValue().increment(deviceCodeKey);
            }
        } else {
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey, "1", 1L, TimeUnit.MINUTES);
        }
        return false;
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: isPassengerOrderGoingOn
     * @param: passengerId
     * @paramType [java.lang.Long]
     * @return: int
     * @Date: 2023/2/26 22:45
     * @Description: 判断是否有 业务中的订单
     */
    private int isPassengerOrderGoingOn(Long passengerId) {
        //判断有正在进行的订单，不允许下单
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passenger_id", passengerId);
        queryWrapper.and(wrapper -> wrapper.eq("order_status", OrderConstants.ORDER_START)
                .or().eq("order_status", OrderConstants.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status", OrderConstants.DRIVER_TO_PICK_UP_PASSENGER)
                .or().eq("order_status", OrderConstants.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status", OrderConstants.PICK_UP_PASSENGER)
                .or().eq("order_status", OrderConstants.PASSENGER_GETOFF)
                .or().eq("order_status", OrderConstants.TO_START_PAY)
        );

        return orderInfoMapper.selectCount(queryWrapper);

    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: isDriverOrderGoingOn
     * @param: driverId
     * @paramType [java.lang.Long]
     * @return: int
     * @Date: 2023/2/26 22:46
     * @Description: 判断是否有 业务中的订单
     */
    private int isDriverOrderGoingOn(Long driverId) {
        //判断有正在进行的订单，不允许下单
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverId);
        queryWrapper.and(wrapper -> wrapper
                .eq("order_status", OrderConstants.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status", OrderConstants.DRIVER_TO_PICK_UP_PASSENGER)
                .or().eq("order_status", OrderConstants.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status", OrderConstants.PICK_UP_PASSENGER)
        );

        Integer validOrderNumber = orderInfoMapper.selectCount(queryWrapper);
        log.info("司机 ID：" + driverId + ", 正在进行的订单的数量： " + validOrderNumber);

        return validOrderNumber;

    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: toPickUpPassenger
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 22:47
     * @Description: 更新订单状态 ---  去接乘客
     */
    public ResponseResult toPickUpPassenger(OrderRequest orderRequest) {
        Long orderId = orderRequest.getOrderId();
        LocalDateTime toPickUpPassengerTime = orderRequest.getToPickUpPassengerTime();
        String toPickUpPassengerLongitude = orderRequest.getToPickUpPassengerLongitude();
        String toPickUpPassengerLatitude = orderRequest.getToPickUpPassengerLatitude();
        String toPickUpPassengerAddress = orderRequest.getToPickUpPassengerAddress();

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);

        orderInfo.setToPickUpPassengerAddress(toPickUpPassengerAddress);
        orderInfo.setPickUpPassengerLongitude(toPickUpPassengerLongitude);
        orderInfo.setPickUpPassengerLatitude(toPickUpPassengerLatitude);
        orderInfo.setToPickUpPassengerTime(LocalDateTime.now());
        orderInfo.setOrderStatus(OrderConstants.DRIVER_TO_PICK_UP_PASSENGER);

        orderInfoMapper.updateById(orderInfo);

        return ResponseResult.success("");
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: arrivedDeparture
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/3/4 22:28
     * @Description: 更新订单状态 ---  到达乘客目的地
     */
    public ResponseResult arrivedDeparture(OrderRequest orderRequest) {
        Long orderId = orderRequest.getOrderId();

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);

        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);
        orderInfo.setOrderStatus(OrderConstants.DRIVER_ARRIVED_DEPARTURE);

        orderInfo.setDriverArrivedDepartureTime(LocalDateTime.now());
        orderInfoMapper.updateById(orderInfo);

        return ResponseResult.success("");
    }

    /**
       * @Author: Charley_Zhang
       * @MethodName: pickUpPassenger
     * @param: orderRequest
       * @paramType  [com.charley.internalcommon.request.OrderRequest]
       * @return:  com.charley.internalcommon.dto.ResponseResult
       * @Date: 2023/3/4 22:41
       * @Description:   更新订单状态 ---  司机接到乘客
       */
    public ResponseResult pickUpPassenger(OrderRequest orderRequest) {
        Long orderId = orderRequest.getOrderId();

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);

        orderInfo.setPickUpPassengerLongitude(orderRequest.getPickUpPassengerLongitude());
        orderInfo.setPickUpPassengerLatitude(orderRequest.getPickUpPassengerLatitude());
        orderInfo.setPickUpPassengerTime(LocalDateTime.now());
        orderInfo.setOrderStatus(OrderConstants.PICK_UP_PASSENGER);

        orderInfoMapper.updateById(orderInfo);

        return ResponseResult.success("");
    }


    /**
       * @Author: Charley_Zhang
       * @MethodName: passengerGetoff
     * @param: orderRequest
       * @paramType  [com.charley.internalcommon.request.OrderRequest]
       * @return:  com.charley.internalcommon.dto.ResponseResult
       * @Date: 2023/3/4 23:12
       * @Description:   更新订单状态 ---  司机行程结束，到达目的地
       */
    public ResponseResult passengerGetoff(OrderRequest orderRequest) {
        Long orderId = orderRequest.getOrderId();

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);

        orderInfo.setPassengerGetoffLongitude(orderRequest.getPassengerGetoffLongitude());
        orderInfo.setPassengerGetoffLatitude(orderRequest.getPassengerGetoffLatitude());
        orderInfo.setPassengerGetoffTime(LocalDateTime.now());
        orderInfo.setOrderStatus(OrderConstants.PASSENGER_GETOFF);

        // 订单行驶路程和时间
        orderInfoMapper.updateById(orderInfo);

        return ResponseResult.success("");

    }
}
