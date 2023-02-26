package com.charley.servicemap.controller;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TrackResponse;
import com.charley.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:25
 * @ClassName: TrackController
 * @Version 1.0
 * @Description: 车辆轨迹控制
 */
@RestController
@RequestMapping(value = "/track")
public class TrackController {

    @Autowired
    private TrackService trackService;


    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: tid
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.TrackResponse>
     * @Date: 2023/2/27 0:25
     * @Description: 创建轨迹
     */
    @PostMapping(value = "/add")
    public ResponseResult<TrackResponse> add(String tid) {
        return trackService.add(tid);
    }
}
