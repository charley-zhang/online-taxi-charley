package com.charley.servicemap.controller;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.servicemap.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:21
 * @ClassName: DicDistrictController
 * @Version 1.0
 * @Description: 地区控制
 */
@RestController
public class DicDistrictController {

    @Autowired
    private DicDistrictService dicDistrictService;


    /**
     * @Author: Charley_Zhang
     * @MethodName: initDicDistrict
     * @param: keywords
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:21
     * @Description: 获取地区字典
     */
    @GetMapping(value = "/dic-district")
    public ResponseResult initDicDistrict(String keywords) {


        return dicDistrictService.initDicDistrict(keywords);
    }


}
