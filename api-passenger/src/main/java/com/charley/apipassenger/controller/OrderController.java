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

@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单/下单
     * @return
     */
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        log.info(orderRequest.toString());

        return orderService.add(orderRequest);
    }
}
