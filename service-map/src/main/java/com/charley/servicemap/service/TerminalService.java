package com.charley.servicemap.service;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TerminalResponse;
import com.charley.servicemap.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:30
 * @ClassName: TerminalService
 * @Version 1.0
 * @Description: 终端服务
 */
@Service
public class TerminalService {

    @Autowired
    private TerminalClient terminalClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: name
     * @param: desc
     * @paramType [java.lang.String, java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.TerminalResponse>
     * @Date: 2023/2/27 0:30
     * @Description: 新建终端
     */
    public ResponseResult<TerminalResponse> add(String name, String desc) {
        return terminalClient.add(name, desc);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: aroundsearch
     * @param: center
     * @param: radius
     * @paramType [java.lang.String, java.lang.Integer]
     * @return: com.charley.internalcommon.dto.ResponseResult<java.util.List < com.charley.internalcommon.reponese.TerminalResponse>>
     * @Date: 2023/2/27 0:30
     * @Description: 搜索终端
     */
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius) {
        return terminalClient.aroundsearch(center, radius);
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
    public ResponseResult trsearch(String tid, Long startTime, Long endTime) {
        return terminalClient.trsearch(tid, startTime, endTime);
    }
}
