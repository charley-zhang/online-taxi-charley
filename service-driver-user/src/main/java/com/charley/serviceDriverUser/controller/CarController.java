package com.charley.serviceDriverUser.controller;


import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceDriverUser.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:03
 * @ClassName: CarController
 * @Version 1.0
 * @Description: 车辆信息控制
 */
@RestController
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: addCar
     * @param: car
     * @paramType [com.charley.internalcommon.dto.Car]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:04
     * @Description: 插入车辆信息
     */
    @PostMapping(value = "/car")
    public ResponseResult addCar(@RequestBody Car car) {
        log.info(car.toString());
        return carService.addCar(car);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: getCarById
     * @param: carId
     * @paramType [java.lang.Long]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.dto.Car>
     * @Date: 2023/2/27 0:04
     * @Description: 根据车辆 id 查询车辆信息
     */
    @GetMapping(value = "/car")
    public ResponseResult<Car> getCarById(Long carId) {
        log.info(carId.toString());
        return carService.getCarById(carId);
    }

}
