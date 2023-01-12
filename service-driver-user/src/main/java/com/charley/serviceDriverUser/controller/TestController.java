package com.charley.serviceDriverUser.controller;

import com.charley.serviceDriverUser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DriverUserService driverUserService;

    @GetMapping(value = "test")
    public String test(){
        return driverUserService.testGetDriverUser().toString();
    }
}
