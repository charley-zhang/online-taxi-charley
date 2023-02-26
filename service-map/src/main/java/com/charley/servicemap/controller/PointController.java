package com.charley.servicemap.controller;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.PointRequest;
import com.charley.servicemap.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:23
 * @ClassName: PointController
 * @Version 1.0
 * @Description: 车辆位置控制
 */
@RestController
@RequestMapping(value = "/point")
public class PointController {

    @Autowired
    private PointService pointService;


    /**
     * @Author: Charley_Zhang
     * @MethodName: upload
     * @param: pointRequest
     * @paramType [com.charley.internalcommon.request.PointRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:23
     * @Description: 轨迹点位置上传
     */
    @PostMapping(value = "/upload")
    public ResponseResult upload(@RequestBody PointRequest pointRequest) {
        return pointService.upload(pointRequest);
    }
}
