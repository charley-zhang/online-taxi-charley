package com.charley.servicemap.controller;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.ForecastPriceDTO;
import com.charley.servicemap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:22
 * @ClassName: DriectionController
 * @Version 1.0
 * @Description: 路径规划控制
 */
@RestController
@RequestMapping(value = "/direction")
public class DriectionController {

    @Autowired
    private DirectionService directionService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: driving
     * @param: forecastPriceDTO
     * @paramType [com.charley.internalcommon.request.ForecastPriceDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:22
     * @Description: 根据起点和终点的经纬度获取距离和时长
     */
    @GetMapping(value = "/driving")
    public ResponseResult driving(@RequestBody ForecastPriceDTO forecastPriceDTO) {

        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatiude = forecastPriceDTO.getDepLatiude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatiude = forecastPriceDTO.getDestLatiude();

        return directionService.driving(depLongitude, depLatiude, destLongitude, destLatiude);
    }
}
