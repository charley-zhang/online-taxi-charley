package com.charley.apidriver.controller;

import com.charley.apidriver.service.PointService;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.ApiDriverPointRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/point")
@Slf4j
public class PointController {

    @Autowired
    private PointService pointService;

    /**
     * 上传车辆经纬度信息
     * @param apiDriverPointRequest
     * @return
     */
    @PostMapping(value = "/upload")
    public ResponseResult upload(@RequestBody ApiDriverPointRequest apiDriverPointRequest){
        log.info(apiDriverPointRequest.toString());
        return pointService.upload(apiDriverPointRequest);
    }
}
