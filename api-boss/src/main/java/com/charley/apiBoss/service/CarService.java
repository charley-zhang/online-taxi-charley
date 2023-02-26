package com.charley.apiBoss.service;

import com.charley.apiBoss.remote.ServiceDriverUserClient;
import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:25
 * @ClassName: CarService
 * @Version 1.0
 * @Description: 车辆服务
 */
@Service
public class CarService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: addCar
     * @param: car
     * @paramType [com.charley.internalcommon.dto.Car]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:25
     * @Description: 添加车辆信息
     */
    public ResponseResult addCar(Car car) {
        return serviceDriverUserClient.addCar(car);
    }
}
