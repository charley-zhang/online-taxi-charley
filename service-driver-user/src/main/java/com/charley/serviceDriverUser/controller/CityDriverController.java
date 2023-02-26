package com.charley.serviceDriverUser.controller;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceDriverUser.service.CityDriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:04
 * @ClassName: CityDriverController
 * @Version 1.0
 * @Description: 城市是否有可用司机控制
 */
@RestController
@RequestMapping(value = "/city-driver")
public class CityDriverController {

    @Autowired
    private CityDriverUserService cityDriverUserService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: isAvailableDriver
     * @param: cityCode
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:05
     * @Description: 根据城市码查询当前城市是否有可用司机
     */
    @GetMapping(value = "/is-available-driver")
    public ResponseResult isAvailableDriver(String cityCode) {
        return cityDriverUserService.isAvailableDriver(cityCode);
    }
}
