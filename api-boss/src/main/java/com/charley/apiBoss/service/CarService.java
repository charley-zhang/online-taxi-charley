package com.charley.apiBoss.service;

import com.charley.apiBoss.remote.ServiceDriverUserClient;
import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addCar(Car car){
        return serviceDriverUserClient.addCar(car);
    }
}
