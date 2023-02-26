package com.charley.apipassenger.remote;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.ForecastPriceResponse;
import com.charley.internalcommon.request.ForecastPriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:56
 * @ClassName: ServicePriceClient
 * @Version 1.0
 * @Description: 价格服务远程调用客户端
 */
@FeignClient(value = "service-price")
public interface ServicePriceClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: forecast
     * @param: forecastPriceDTO
     * @paramType [com.charley.internalcommon.request.ForecastPriceDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.ForecastPriceResponse>
     * @Date: 2023/2/26 23:56
     * @Description: 计算预估价格
     */
    @RequestMapping(method = RequestMethod.POST, value = "/forecast-price")
    public ResponseResult<ForecastPriceResponse> forecast(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
