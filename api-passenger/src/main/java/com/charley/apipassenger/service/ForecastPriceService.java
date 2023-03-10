package com.charley.apipassenger.service;

import com.charley.apipassenger.remote.ServicePriceClient;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.ForecastPriceResponse;
import com.charley.internalcommon.request.ForecastPriceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServicePriceClient servicePriceClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: forecastPrice
     * @param: depLongitude
     * @param: depLatiude
     * @param: destLongitude
     * @param: destLatiude
     * @param: cityCode
     * @param: vehicleType
     * @paramType [java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:55
     * @Description: 根据出发和目的地经纬度，计算预估价格
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatiude, String destLongitude, String destLatiude, String cityCode, String vehicleType) {

        log.info("调用计价服务，计算价格");

        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatiude(depLatiude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatiude(destLatiude);
        forecastPriceDTO.setCityCode(cityCode);
        forecastPriceDTO.setVehicleType(vehicleType);

        log.info("ForecastPriceDTO: " + forecastPriceDTO.toString());

        ResponseResult<ForecastPriceResponse> forecast = servicePriceClient.forecast(forecastPriceDTO);

        return ResponseResult.success(forecast.getData());
    }

}
