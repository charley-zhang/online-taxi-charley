package com.charley.apipassenger.controller;


import com.charley.apipassenger.service.ForecastPriceService;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.ForecastPriceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:47
 * @ClassName: ForecastPriceController
 * @Version 1.0
 * @Description: 乘客预估价格控制
 */
@RestController
@Slf4j
public class ForecastPriceController {

    @Autowired
    private ForecastPriceService forecastPriceService;


    /**
     * @Author: Charley_Zhang
     * @MethodName: forecastPrice
     * @param: forecastPriceDTO
     * @paramType [com.charley.internalcommon.request.ForecastPriceDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:47
     * @Description: 预估价格
     */
    @PostMapping(value = "/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {

        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatiude = forecastPriceDTO.getDepLatiude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatiude = forecastPriceDTO.getDestLatiude();
        String cityCode = forecastPriceDTO.getCityCode();
        String vehicleType = forecastPriceDTO.getVehicleType();

        log.info("出发地经度：" + depLongitude);
        log.info("出发地纬度：" + depLatiude);
        log.info("目的地经度：" + destLongitude);
        log.info("目的地纬度：" + destLatiude);


        return forecastPriceService.forecastPrice(depLongitude, depLatiude, destLongitude, destLatiude, cityCode, vehicleType);
    }
}
