package com.charley.serviceDriverUser.controller;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.dto.DriverUserWorkStatus;
import com.charley.serviceDriverUser.service.DriverUserWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:06
 * @ClassName: DriverUserWorkStatusController
 * @Version 1.0
 * @Description: 司机工作状态控制
 */
@RestController
public class DriverUserWorkStatusController {

    @Autowired
    private DriverUserWorkStatusService driverUserWorkStatusService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: changeWorkStatus
     * @param: driverUserWorkStatus
     * @paramType [com.charley.internalcommon.dto.DriverUserWorkStatus]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:06
     * @Description: 更新司机工作状态
     */
    @RequestMapping("/driver-user-work-status")
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus) {

        return driverUserWorkStatusService.changeWorkStatus(driverUserWorkStatus.getDriverId(), driverUserWorkStatus.getWorkStatus());
    }

}
