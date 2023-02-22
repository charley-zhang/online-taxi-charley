package com.charley.apipassenger.remote;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-order")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/order/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest);

    @RequestMapping(value = "/test-real-time-order/{orderId}", method = RequestMethod.GET)
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId);
}
