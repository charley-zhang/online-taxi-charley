package com.charley.apipassenger.service;

import com.charley.apipassenger.remote.ServiceOrderClient;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:56
 * @ClassName: OrderService
 * @Version 1.0
 * @Description: 用户订单服务
 */
@Service
public class OrderService {

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:56
     * @Description: 创建订单/下单
     */
    public ResponseResult add(OrderRequest orderRequest) {
        return serviceOrderClient.add(orderRequest);
    }
}
