package com.charley.apiBoss.controller;


import com.charley.apiBoss.service.DriverCarBindingRelationshipService;
import com.charley.internalcommon.dto.DriverCarBindingRelationship;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:19
 * @ClassName: DriverCarBindingRelationshipController
 * @Version 1.0
 * @Description: 司机车辆绑定关系控制器
 */
@RestController
@RequestMapping(value = "/driver-car-binding-relationship")
public class DriverCarBindingRelationshipController {

    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: bind
     * @param: driverCarBindingRelationship
     * @paramType [com.charley.internalcommon.dto.DriverCarBindingRelationship]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:20
     * @Description: 绑定司机和车辆
     */
    @PostMapping(value = "/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {
        return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: unbind
     * @param: driverCarBindingRelationship
     * @paramType [com.charley.internalcommon.dto.DriverCarBindingRelationship]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:20
     * @Description: 解绑司机和车辆
     */
    @PostMapping(value = "/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {
        return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
    }
}
