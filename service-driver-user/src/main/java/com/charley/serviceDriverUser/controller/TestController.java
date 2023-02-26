package com.charley.serviceDriverUser.controller;

import com.charley.serviceDriverUser.mapper.DriverUserMapper;
import com.charley.serviceDriverUser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:07
 * @ClassName: TestController
 * @Version 1.0
 * @Description: 测试控制
 */
@RestController
public class TestController {

    @Autowired
    private DriverUserService driverUserService;

    @Autowired
    private DriverUserMapper driverUserMapper;

    @GetMapping(value = "/test")
    public String test(){
        return driverUserService.testGetDriverUser().toString();
    }




    @GetMapping(value = "/testXml")
    public int testXml(@RequestParam String cityCode){
        return driverUserMapper.selectDriverUserCountByCityCode(cityCode);
    }



}
