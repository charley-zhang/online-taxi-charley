package com.charley.servicemap.service;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.DirectionResponse;
import com.charley.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    /**
     * 根据起点经纬度和终点经纬度获取距离 （米）和时长 （分钟）
     * @param depLongitude
     * @param depLatiude
     * @param destLongitude
     * @param destLatiude
     * @return
     */
    public ResponseResult driving(String depLongitude, String depLatiude, String destLongitude, String destLatiude){

        log.info("DirectionService 的 driving 执行");

        // 调用第三方地图接口
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatiude, destLongitude, destLatiude);
        log.info("direction: "+ direction);

        return ResponseResult.success(direction);
    }

}
