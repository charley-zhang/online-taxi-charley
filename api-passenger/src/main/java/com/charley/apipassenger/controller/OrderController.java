package com.charley.apipassenger.controller;

import com.charley.apipassenger.service.OrderService;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:48
 * @ClassName: OrderController
 * @Version 1.0
 * @Description: 用户订单控制
 */
@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:48
     * @Description: 创建订单/下单
     */
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest) {
        log.info(orderRequest.toString());

        return orderService.add(orderRequest);
    }
}
