package com.charley.apidriver.remote;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Charley_Zhang
 * @Date 2023/3/5 17:38
 * @PackageName:com.charley.apidriver.remote
 * @ClassName: ServiceOrderCClient
 * @Version 1.0
 * @Description:
 */
@FeignClient(value = "service-order")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/order/to-pick-up-passenger")
    public ResponseResult toPickUpPassenger(@RequestBody OrderRequest orderRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/order/arrived-departure")
    public ResponseResult arrivedDeparture(@RequestBody OrderRequest orderRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/order/pick-up-passenger")
    public ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/order/passenger-getoff")
    public ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest);
}
