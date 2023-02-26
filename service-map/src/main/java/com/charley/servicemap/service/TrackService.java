package com.charley.servicemap.service;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TerminalResponse;
import com.charley.internalcommon.reponese.TrackResponse;
import com.charley.servicemap.remote.TrackClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:30
 * @ClassName: TrackService
 * @Version 1.0
 * @Description: 轨迹服务
 */
@Service
@Slf4j
public class TrackService {

    @Autowired
    private TrackClient trackClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: tid
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.TrackResponse>
     * @Date: 2023/2/27 0:31
     * @Description: 创建轨迹
     */
    public ResponseResult<TrackResponse> add(String tid) {
        return trackClient.add(tid);
    }
}
