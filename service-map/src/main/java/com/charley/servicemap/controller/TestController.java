package com.charley.servicemap.controller;


import com.charley.internalcommon.dto.DicDistrict;
import com.charley.servicemap.mapper.DicDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping(value = "test")
    public String test(){
        return "this is testing service-map moudle";
    }

    @Autowired
    DicDistrictMapper mapper;

    @GetMapping(value = "test-map")
    public String testMap(){

        Map<String, Object> map = new HashMap<>();
        map.put("address_code", "110000");
        List<DicDistrict> dicDistricts = mapper.selectByMap(map);
        System.out.println(dicDistricts);

        return "test-map";
    }
}
