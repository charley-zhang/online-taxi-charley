package com.charley.servicemap.controller;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.PointRequest;
import com.charley.servicemap.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/point")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping(value = "/upload")
    public ResponseResult upload(@RequestBody PointRequest pointRequest){
        return pointService.upload(pointRequest);
    }
}
