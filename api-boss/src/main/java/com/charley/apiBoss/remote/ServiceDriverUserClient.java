package com.charley.apiBoss.remote;

import com.charley.apiBoss.controller.DriverCarBindingRelationshipController;
import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.DriverCarBindingRelationship;
import com.charley.internalcommon.dto.DriverUser;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-driver-user")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.POST, value = "/car")
    public ResponseResult addCar(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);

    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship/unbind")
    ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship);
}
