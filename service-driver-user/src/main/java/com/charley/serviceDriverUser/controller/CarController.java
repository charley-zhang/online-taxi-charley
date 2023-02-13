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
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Charlry
 * @since 2023-01-13
 */
@RestController
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 插入车辆信息
     * @param car
     * @return
     */
    @PostMapping(value = "/car")
    public ResponseResult addCar(@RequestBody Car car){
        log.info(car.toString());
        return carService.addCar(car);
    }


    /**
     * 根据车辆 id 查询车辆信息
     * @param carId
     * @return
     */
    @GetMapping(value = "/car")
    public ResponseResult<Car> getCarById(Long carId){
        log.info(carId.toString());
        return carService.getCarById(carId);
    }

}
