package com.charley.serviceorder.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.charley.internalcommon.constant.CommonStatusEnum;
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
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * (OrderInfo)表服务接口
 *
 * @author makejava
 * @since 2023-01-30 16:57:42
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


    public ResponseResult add(OrderRequest orderRequest) {
        log.info("orderRequest: "+orderRequest.toString());

        //查询城市是否有可用的司机
        ResponseResult<Boolean> availableDriver = serviceDriverUserClient.isAvailableDriver(orderRequest.getAddress());
        log.info("城市是否有司机结果" + availableDriver);
        if (!availableDriver.getData()){
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
        if (!isPriceRuleExists(orderRequest)){
            return ResponseResult.fail(CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getCode(), CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getValue());
        }


        // 判断乘客 是否有进行中的订单
        if (isPassengerOrderGoingOn(orderRequest.getPassengerId()) > 0) {
            return ResponseResult.fail(CommonStatusEnum.ORDER_GOING_ON.getCode(), CommonStatusEnum.ORDER_GOING_ON.getValue(), String.valueOf(false));
        }

        // 创建订单
        OrderInfo orderInfo = new OrderInfo();

        BeanUtils.copyProperties(orderRequest,orderInfo);
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);

        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);

        orderInfoMapper.insert(orderInfo);

        // 派单 dispatchRealTimeOrder
        dispatchRealTimeOrder(orderInfo);

        return ResponseResult.success("");
    }

    /**
     * 实时订单派单逻辑
     * @param orderInfo
     */
    public synchronized void dispatchRealTimeOrder(OrderInfo orderInfo) {

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
        radius :
        for (int i = 0; i < radiusList.size(); i++) {

            listResponseResult = serviceMapClient.terminalAroundSearch(center, radiusList.get(i));
            log.info("在半径为" + radiusList.get(i) + "米的范围内, 寻找车辆, 结果：" + JSONArray.fromObject(listResponseResult.getData()).toString());

            // 获得终端

            // 解析终端
            List<TerminalResponse> data = listResponseResult.getData();
            for (int j = 0; j < data.size(); j++){
                TerminalResponse terminalResponse = data.get(j);
                Long carId = terminalResponse.getCarId();
                String longitude = terminalResponse.getLongitude();
                String latitude = terminalResponse.getLatitude();

                // 查询是否有多余的可派单司机
                ResponseResult<OrderDriverResponse> availableDriver = serviceDriverUserClient.getAvailableDriver(carId);
                if (availableDriver.getCode() == CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode()) {
                    log.info("没有车辆ID：" + carId + ", 对应的司机");
                    continue;
                }else {
                    log.info("找到了正在出车的司机，他的车辆ID： " + carId);

                    OrderDriverResponse orderDriverResponse = availableDriver.getData();
                    Long driverId = orderDriverResponse.getDriverId();
                    String driverPhone = orderDriverResponse.getDriverPhone();
                    String licenseId = orderDriverResponse.getLicenseId();
                    String vehicleNo = orderDriverResponse.getVehicleNo();

                    String lockKey = (driverId+"").intern();
                    RLock lock = redissonClient.getLock(lockKey);
                    lock.lock();

//                    isDriverOrderGoingOn
                    // 判断司机 是否有进行中的订单
                    if (isDriverOrderGoingOn(driverId) > 0) {
                        lock.unlock();
                        continue ;
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

                    lock.unlock();

                    // 退出，不再进行司机的查找
                    break radius;

                }
            }





        // 根据解析出来的终端，查询车辆信息

        // 找到符合的车辆，进行派单

        // 如过派单成功，则退出循环
        }

    }


    private  boolean isPriceRuleExists(OrderRequest orderRequest){
        String fareType = orderRequest.getFareType();
        int index = fareType.indexOf("$");
        String cityCode = fareType.substring(0, index);
        String vehicleType = fareType.substring(index + 1);

        PriceRule priceRule = new PriceRule().setCityCode(cityCode).setVehicleType(vehicleType);

        return servicePriceClient.ifPriceExists(priceRule).getData();

    }

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
            }else {
                stringRedisTemplate.opsForValue().increment(deviceCodeKey);
            }
        }else {
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey, "1", 1L, TimeUnit.MINUTES);
        }
        return false;
    }


    /**
     * 判断是否有 业务中的订单
     * @param passengerId
     * @return
     */
    private int isPassengerOrderGoingOn(Long passengerId){
        //判断有正在进行的订单，不允许下单
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passenger_id", passengerId);
        queryWrapper.and(wrapper->wrapper.eq("order_status", OrderConstants.ORDER_START)
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
     * 判断是否有 业务中的订单
     * @param driverId
     * @return
     */
    private int isDriverOrderGoingOn(Long driverId){
        //判断有正在进行的订单，不允许下单
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverId);
        queryWrapper.and(wrapper->wrapper
                .eq("order_status", OrderConstants.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status", OrderConstants.DRIVER_TO_PICK_UP_PASSENGER)
                .or().eq("order_status", OrderConstants.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status", OrderConstants.PICK_UP_PASSENGER)
        );

        Integer validOrderNumber = orderInfoMapper.selectCount(queryWrapper);
        log.info("司机 ID：" + driverId + ", 正在进行的订单的数量： " + validOrderNumber);

        return validOrderNumber;

    }
}
