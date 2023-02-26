package com.charley.serviceprice.remote;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.DirectionResponse;
import com.charley.internalcommon.request.ForecastPriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:59
 * @ClassName: ServiceMapClient
 * @Version 1.0
 * @Description: 远程调用本地地图服务客户端
 */
@FeignClient(value = "service-map")
public interface ServiceMapClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: direction
     * @param: forecastPriceDTO
     * @paramType [com.charley.internalcommon.request.ForecastPriceDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.DirectionResponse>
     * @Date: 2023/2/27 1:00
     * @Description: 预估 起始地 -> 目的地的 距离和时长
     */
    @RequestMapping(method = RequestMethod.GET, value = "/direction/driving")
    public ResponseResult<DirectionResponse> direction(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
