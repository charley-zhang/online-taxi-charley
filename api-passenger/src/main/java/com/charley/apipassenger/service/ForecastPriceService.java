package com.charley.apipassenger.service;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {

    /**
     * 根据出发和目的地经纬度，计算预估价格
     * @param depLongitude
     * @param depLatiude
     * @param destLongitude
     * @param destLatiude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatiude, String destLongitude, String destLatiude){



        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);
        return ResponseResult.success(forecastPriceResponse);
    }

}
