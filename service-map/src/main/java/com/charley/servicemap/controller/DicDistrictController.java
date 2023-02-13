package com.charley.servicemap.controller;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.servicemap.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DicDistrictController {

    @Autowired
    private DicDistrictService dicDistrictService;


    /**
     * 获取地区字典
     * @param keywords
     * @return
     */
    @GetMapping(value = "/dic-district")
    public ResponseResult initDicDistrict(String keywords){



        return dicDistrictService.initDicDistrict(keywords);
    }



}
