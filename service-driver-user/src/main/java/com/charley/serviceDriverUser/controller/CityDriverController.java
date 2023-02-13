package com.charley.serviceDriverUser.controller;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceDriverUser.service.CityDriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/city-driver")
public class CityDriverController {

    @Autowired
    private CityDriverUserService cityDriverUserService;

    /**
     * 根据城市码查询当前城市是否有可用司机
     * @param cityCode
     * @return
     */
    @GetMapping(value = "/is-available-driver")
    public ResponseResult isAvailableDriver(String cityCode){
        return cityDriverUserService.isAvailableDriver(cityCode);
    }
}
