package com.charley.servicemap.service;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.DirectionResponse;
import com.charley.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:28
 * @ClassName: DirectionService
 * @Version 1.0
 * @Description: 路径规划服务
 */
@Service
@Slf4j
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: driving
     * @param: depLongitude
     * @param: depLatiude
     * @param: destLongitude
     * @param: destLatiude
     * @paramType [java.lang.String, java.lang.String, java.lang.String, java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:28
     * @Description: 根据起点经纬度和终点经纬度获取距离 （米）和时长 （分钟）
     */
    public ResponseResult driving(String depLongitude, String depLatiude, String destLongitude, String destLatiude) {

        log.info("DirectionService 的 driving 执行");

        // 调用第三方地图接口
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatiude, destLongitude, destLatiude);
        log.info("direction: " + direction);

        return ResponseResult.success(direction);
    }

}
