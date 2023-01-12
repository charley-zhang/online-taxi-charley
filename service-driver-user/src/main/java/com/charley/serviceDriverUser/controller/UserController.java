package com.charley.serviceDriverUser.controller;

import com.charley.internalcommon.dto.DriverUser;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceDriverUser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping(value = "/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){
        log.info(driverUser.toString());

        return driverUserService.addDriverUser(driverUser);
    }
}
