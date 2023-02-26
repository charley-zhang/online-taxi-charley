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

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:21
 * @ClassName: DriverUserController
 * @Version 1.0
 * @Description: 司机用户控制
 */
@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @Autowired
    private CarService carService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: addDriverUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:21
     * @Description: 添加司机信息
     */
    @PostMapping(value = "/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser) {
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: updateDriverUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:21
     * @Description: 修改司机信息
     */
    @PutMapping(value = "/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser) {
        return driverUserService.updateDriverUser(driverUser);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: car
     * @param: car
     * @paramType [com.charley.internalcommon.dto.Car]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:30
     * @Description: 添加车辆信息
     */
    @PostMapping(value = "/car")
    public ResponseResult car(@RequestBody Car car) {
        return carService.addCar(car);
    }
}
