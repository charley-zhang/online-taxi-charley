package com.charley.serviceprice.controller;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.ForecastPriceDTO;
import com.charley.serviceprice.service.ForecastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:56
 * @ClassName: ForecastPriceController
 * @Version 1.0
 * @Description: 预估价格控制
 */
@RestController
public class ForecastPriceController {

    @Autowired
    private ForecastPriceService forecastPriceService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: forecastPrice
     * @param: forecastPriceDTO
     * @paramType [com.charley.internalcommon.request.ForecastPriceDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:56
     * @Description: 计算预估价格
     */
    @PostMapping(value = "/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {

        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatiude = forecastPriceDTO.getDepLatiude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatiude = forecastPriceDTO.getDestLatiude();
        String cityCode = forecastPriceDTO.getCityCode();
        String vehicleType = forecastPriceDTO.getVehicleType();

        return forecastPriceService.forecastPrice(depLongitude, depLatiude, destLongitude, destLatiude, cityCode, vehicleType);
    }
}
