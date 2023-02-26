package com.charley.serviceDriverUser.service;

import com.charley.internalcommon.dto.DriverUserWorkStatus;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceDriverUser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:19
 * @ClassName: DriverUserWorkStatusService
 * @Version 1.0
 * @Description: 司机工作状态服务
 */
@Service
public class DriverUserWorkStatusService {

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    /**
     * @Author: Charley_Zhang
     * @MethodName: changeWorkStatus
     * @param: driverId
     * @param: workStatus
     * @paramType [java.lang.Long, java.lang.Integer]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:20
     * @Description: 更新司机工作状态
     */
    public ResponseResult changeWorkStatus(Long driverId, Integer workStatus) {

        Map<String, Object> map = new HashMap<>();
        map.put("driver_id", driverId);
        List<DriverUserWorkStatus> driverUserWorkStatuses = driverUserWorkStatusMapper.selectByMap(map);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatuses.get(0);

        driverUserWorkStatus.setWorkStatus(workStatus);
        driverUserWorkStatusMapper.updateById(driverUserWorkStatus);

        return ResponseResult.success("");
    }
}
