package com.charley.serviceDriverUser.controller;


import com.charley.internalcommon.dto.DriverCarBindingRelationship;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceDriverUser.service.DriverCarBindingRelationshipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:05
 * @ClassName: DriverCarBindingRelationshipController
 * @Version 1.0
 * @Description: 司机车辆绑定关系控制
 */
@RestController
@RequestMapping("/driver-car-binding-relationship")
@Slf4j
public class DriverCarBindingRelationshipController {

    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: bing
     * @param: driverCarBindingRelationship
     * @paramType [com.charley.internalcommon.dto.DriverCarBindingRelationship]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:05
     * @Description: 司机 & 车辆  绑定
     */
    @PostMapping(value = "/bind")
    public ResponseResult bing(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {
        log.info(driverCarBindingRelationship.toString());
        return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: unbind
     * @param: driverCarBindingRelationship
     * @paramType [com.charley.internalcommon.dto.DriverCarBindingRelationship]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:06
     * @Description: 司机 & 车辆 解绑
     */
    @PostMapping(value = "/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {
        log.info(driverCarBindingRelationship.toString());
        return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
    }
}
