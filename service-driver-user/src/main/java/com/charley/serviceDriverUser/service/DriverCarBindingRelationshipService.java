package com.charley.serviceDriverUser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.charley.internalcommon.constant.CommonStatusEnum;
import com.charley.internalcommon.constant.DriverCarConstants;
import com.charley.internalcommon.dto.DriverCarBindingRelationship;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceDriverUser.mapper.DriverCarBindingRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:16
 * @ClassName: DriverCarBindingRelationshipService
 * @Version 1.0
 * @Description: 司机车辆绑定关系服务
 */
@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    /**
     * @Author: Charley_Zhang
     * @MethodName: bind
     * @param: driverCarBindingRelationship
     * @paramType [com.charley.internalcommon.dto.DriverCarBindingRelationship]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:16
     * @Description: 司机 & 车辆  绑定
     */
    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
        // 判断 如果车辆中的车辆和司机 ，已经做过绑定， 则不允许再次绑定
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id", driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state", DriverCarConstants.DRIVER_CAR_BIND);

        Long integer = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (integer.intValue() > 0) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getCode(), CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getValue());
        }

        // 司机被绑定了
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("bind_state", DriverCarConstants.DRIVER_CAR_BIND);
        integer = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (integer.intValue() > 0) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_BIND_EXISTS.getCode(), CommonStatusEnum.DRIVER_BIND_EXISTS.getValue());
        }

        // 车辆被绑定了
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id", driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state", DriverCarConstants.DRIVER_CAR_BIND);
        integer = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (integer.intValue() > 0) {
            return ResponseResult.fail(CommonStatusEnum.CAR_BIND_EXISTS.getCode(), CommonStatusEnum.CAR_BIND_EXISTS.getValue());
        }

        LocalDateTime now = LocalDateTime.now();
        driverCarBindingRelationship.setBindingTime(now);
        driverCarBindingRelationship.setBindState(DriverCarConstants.DRIVER_CAR_BIND);
        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
        return ResponseResult.success("");
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: unbind
     * @param: driverCarBindingRelationship
     * @paramType [com.charley.internalcommon.dto.DriverCarBindingRelationship]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:16
     * @Description: 司机 & 车辆  解绑
     */
    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {

        Map<String, Object> map = new HashMap<>();
        map.put("driver_id", driverCarBindingRelationship.getDriverId());
        map.put("car_id", driverCarBindingRelationship.getCarId());
        map.put("bind_state", DriverCarConstants.DRIVER_CAR_BIND);

        List<DriverCarBindingRelationship> driverCarBindingRelationships = driverCarBindingRelationshipMapper.selectByMap(map);
        if (driverCarBindingRelationships.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getValue());
        }

        DriverCarBindingRelationship relationship = driverCarBindingRelationships.get(0);
        relationship.setBindState(DriverCarConstants.DRIVER_CAR_UNBING);
        relationship.setUnBindingTime(LocalDateTime.now());

        driverCarBindingRelationshipMapper.updateById(relationship);

        return ResponseResult.success("");

    }
}
