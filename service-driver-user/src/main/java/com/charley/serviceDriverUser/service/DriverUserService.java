package com.charley.serviceDriverUser.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.IFill;
import com.charley.internalcommon.constant.CommonStatusEnum;
import com.charley.internalcommon.constant.DriverCarConstants;
import com.charley.internalcommon.dto.*;
import com.charley.internalcommon.reponese.OrderDriverResponse;
import com.charley.serviceDriverUser.mapper.CarMapper;
import com.charley.serviceDriverUser.mapper.DriverCarBindingRelationshipMapper;
import com.charley.serviceDriverUser.mapper.DriverUserMapper;
import com.charley.serviceDriverUser.mapper.DriverUserWorkStatusMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:17
 * @ClassName: DriverUserService
 * @Version 1.0
 * @Description: 用户信息服务
 */
@Service
@Slf4j
public class DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    @Autowired
    private CarMapper carMapper;

    public ResponseResult testGetDriverUser() {
        DriverUser driverUser = driverUserMapper.selectById(1);
        log.info(driverUser.toString());
        return ResponseResult.success(driverUser);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: addDriverUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:17
     * @Description: 新增司机用户信息
     */
    public ResponseResult addDriverUser(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);

        log.info(driverUser.toString());

        driverUserMapper.insert(driverUser);

        // 初始化司机工作状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarConstants.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setGmtCreate(now);
        driverUserWorkStatus.setGmtModified(now);

        driverUserWorkStatusMapper.insert(driverUserWorkStatus);

        return ResponseResult.success("");
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: updateDriverUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:17
     * @Description: 修改司机用户信息
     */
    public ResponseResult updateDriverUser(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtModified(now);
        driverUserMapper.updateById(driverUser);
        return ResponseResult.success("");
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: getDriverUserByPhone
     * @param: driverPhone
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.dto.DriverUser>
     * @Date: 2023/2/27 0:18
     * @Description: 根据司机用户手机号获取用户信息
     */
    public ResponseResult<DriverUser> getDriverUserByPhone(String driverPhone) {
        Map<String, Object> map = new HashMap<>();
        map.put("driver_phone", driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);

        if (driverUsers.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }

        DriverUser driverUser = driverUsers.get(0);
        return ResponseResult.success(driverUser);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: getAvailableDriver
     * @param: carId
     * @paramType [java.lang.Long]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.OrderDriverResponse>
     * @Date: 2023/2/27 0:19
     * @Description: 根据车辆 ID，查询可以派单的司机的信息
     */
    public ResponseResult<OrderDriverResponse> getAvailableDriver(Long carId) {
        // 车辆和司机绑定关系的查询
        QueryWrapper<DriverCarBindingRelationship> driverCarBindingRelationshipQueryWrapper = new QueryWrapper<>();
        driverCarBindingRelationshipQueryWrapper.eq("car_id", carId);
        driverCarBindingRelationshipQueryWrapper.eq("bind_state", DriverCarConstants.DRIVER_CAR_BIND);
        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMapper.selectOne(driverCarBindingRelationshipQueryWrapper);
        log.info(driverCarBindingRelationship.toString());
        Long driverId = driverCarBindingRelationship.getDriverId();

        // 司机工作状态的查询
        QueryWrapper<DriverUserWorkStatus> driverUserWorkStatusQueryWrapper = new QueryWrapper<>();
        driverUserWorkStatusQueryWrapper.eq("driver_id", driverId);
        driverUserWorkStatusQueryWrapper.eq("work_status", DriverCarConstants.DRIVER_WORK_STATUS_START);

        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectOne(driverUserWorkStatusQueryWrapper);
        if (driverUserWorkStatus == null) {
            return ResponseResult.fail(CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode(), CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getValue());
        } else {
            // 查询司机信息
            QueryWrapper<DriverUser> driverUserQueryWrapper = new QueryWrapper<>();
            driverUserQueryWrapper.eq("id", driverId);
            DriverUser driverUser = driverUserMapper.selectOne(driverUserQueryWrapper);
            // 查询车辆信息
            QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
            carQueryWrapper.eq("id", carId);
            Car car = carMapper.selectOne(carQueryWrapper);

            OrderDriverResponse orderDriverResponse = new OrderDriverResponse();
            orderDriverResponse.setCarId(carId);
            orderDriverResponse.setDriverId(driverId);
            orderDriverResponse.setDriverPhone(driverUser.getDriverPhone());
            orderDriverResponse.setLicenseId(driverUser.getLicenseId());
            orderDriverResponse.setVehicleNo(car.getVehicleNo());

            return ResponseResult.success(orderDriverResponse);
        }


    }
}
