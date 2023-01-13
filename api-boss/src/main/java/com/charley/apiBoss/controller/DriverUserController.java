package com.charley.apiBoss.controller;


import com.charley.apiBoss.service.CarService;
import com.charley.apiBoss.service.DriverUserService;
import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.DriverUser;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * 添加司机信息
     * @param driverUser
     * @return
     */
    @PostMapping(value = "/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    @PutMapping(value = "/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.updateDriverUser(driverUser);
    }

    @Autowired
    private CarService carService;

    @PostMapping(value = "/car")
    public ResponseResult car(@RequestBody Car car){
        return carService.addCar(car);
    }
}