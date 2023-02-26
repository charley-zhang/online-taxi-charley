package com.charley.servicemap.service;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.PointRequest;
import com.charley.servicemap.remote.PointClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:28
 * @ClassName: PointService
 * @Version 1.0
 * @Description: 车辆轨迹点 位置服务
 */
@Service
public class PointService {

    @Autowired
    private PointClient pointClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: upload
     * @param: pointRequest
     * @paramType [com.charley.internalcommon.request.PointRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:29
     * @Description: 车辆 轨迹点位置 上传
     */
    public ResponseResult upload(PointRequest pointRequest) {
        return pointClient.upload(pointRequest);
    }

}
