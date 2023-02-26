package com.charley.apipassenger.remote;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:53
 * @ClassName: ServiceOrderClient
 * @Version 1.0
 * @Description: 用户订单服务远程调用客户端
 */
@FeignClient(value = "service-order")
public interface ServiceOrderClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:54
     * @Description: 新建订单 / 用户下单
     */
    @RequestMapping(method = RequestMethod.POST, value = "/order/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest);

    /**
     * @Author: Charley_Zhang
     * @MethodName: dispatchRealTimeOrder
     * @param: orderId
     * @paramType [long]
     * @return: java.lang.String
     * @Date: 2023/2/26 23:55
     * @Description: 派单
     */
    @RequestMapping(value = "/test-real-time-order/{orderId}", method = RequestMethod.GET)
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId);
}
