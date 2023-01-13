package com.charley.serviceDriverUser.service;

import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceDriverUser.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.time.LocalDateTime;

@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public ResponseResult addCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        carMapper.insert(car);
        return ResponseResult.success("");
    }

}
