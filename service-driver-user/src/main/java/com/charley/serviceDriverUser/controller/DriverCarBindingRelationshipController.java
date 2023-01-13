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
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Charlry
 * @since 2023-01-13
 */
@RestController
@RequestMapping("/driver-car-binding-relationship")
@Slf4j
public class DriverCarBindingRelationshipController {

    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    /**
     * 司机 车辆  绑定
     * @param driverCarBindingRelationship
     * @return
     */
    @PostMapping(value = "/bind")
    public ResponseResult bing(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        log.info(driverCarBindingRelationship.toString());
        return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
    }


    /**
     * 司机 车辆 解绑
     * @param driverCarBindingRelationship
     * @return
     */
    @PostMapping(value = "/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        log.info(driverCarBindingRelationship.toString());
        return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
    }
}
