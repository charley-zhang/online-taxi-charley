package com.charley.servicemap.controller;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.ForecastPriceDTO;
import com.charley.servicemap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/direction")
public class DriectionController {

    @Autowired
    private DirectionService directionService;

    /**
     * 根据起点和终点的经纬度获取距离和时长
     * @param forecastPriceDTO
     * @return
     */
    @GetMapping(value = "/driving")
    public ResponseResult driving(@RequestBody ForecastPriceDTO forecastPriceDTO){

        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatiude = forecastPriceDTO.getDepLatiude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatiude = forecastPriceDTO.getDestLatiude();

        return directionService.driving(depLongitude, depLatiude, destLongitude, destLatiude);
    }
}
