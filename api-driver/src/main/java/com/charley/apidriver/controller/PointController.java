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

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:29
 * @ClassName: PointController
 * @Version 1.0
 * @Description: 司机位置上传控制
 */
@RestController
@RequestMapping(value = "/point")
@Slf4j
public class PointController {

    @Autowired
    private PointService pointService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: upload
     * @param: apiDriverPointRequest
     * @paramType [com.charley.internalcommon.request.ApiDriverPointRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:29
     * @Description: 上传车辆经纬度信息
     */
    @PostMapping(value = "/upload")
    public ResponseResult upload(@RequestBody ApiDriverPointRequest apiDriverPointRequest) {
        log.info(apiDriverPointRequest.toString());
        return pointService.upload(apiDriverPointRequest);
    }
}
