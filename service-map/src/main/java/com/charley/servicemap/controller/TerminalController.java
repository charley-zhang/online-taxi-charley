package com.charley.servicemap.controller;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TerminalResponse;
import com.charley.internalcommon.reponese.TrsearchResponse;
import com.charley.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:24
 * @ClassName: TerminalController
 * @Version 1.0
 * @Description: 终端控制  车辆控制
 */
@RestController
@RequestMapping(value = "/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: name
     * @param: desc
     * @paramType [java.lang.String, java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.TerminalResponse>
     * @Date: 2023/2/27 0:24
     * @Description: 创建终端
     */
    @PostMapping(value = "/add")
    public ResponseResult<TerminalResponse> add(String name, String desc) {
        return terminalService.add(name, desc);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: aroundsearch
     * @param: center
     * @param: radius
     * @paramType [java.lang.String, java.lang.Integer]
     * @return: com.charley.internalcommon.dto.ResponseResult<java.util.List < com.charley.internalcommon.reponese.TerminalResponse>>
     * @Date: 2023/2/27 0:24
     * @Description: 终端搜索
     */
    @PostMapping(value = "/aroundsearch")
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius) {
        return terminalService.aroundsearch(center, radius);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: trsearch
     * @param: tid
     * @param: startTime
     * @param: endTime
     * @paramType [java.lang.String, java.lang.Long, java.lang.Long]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/3/5 0:28
     * @Description: 轨迹查询
     */
    @PostMapping(value = "/trsearch")
    public ResponseResult<TrsearchResponse> trsearch(String tid, Long startTime, Long endTime) {
        return terminalService.trsearch(tid, startTime, endTime);
    }

}
