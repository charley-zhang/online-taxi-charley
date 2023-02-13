package com.charley.servicemap.controller;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TrackResponse;
import com.charley.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/track")
public class TrackController {

    @Autowired
    private TrackService trackService;


    /**
     * 创建轨迹
     * @param tid
     * @return
     */
    @PostMapping(value = "/add")
    public ResponseResult<TrackResponse> add(String tid){
        return trackService.add(tid);
    }
}
