package com.charley.serviceorder.controller;

import com.charley.internalcommon.dto.OrderInfo;
import com.charley.serviceorder.mapper.OrderInfoMapper;
import com.charley.serviceorder.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @GetMapping(value = "/test")
    public String test(){
        return "<h1>This is testing service-order</h1>";
    }

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Value("${server.port}")
    String port;

    @GetMapping(value = "/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId){
        log.info("service-order 端口: "+ port +", 并发测试： orderId ：" + String.valueOf(orderId));
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        orderInfoService.dispatchRealTimeOrder(orderInfo);
        return "test-real-time-order    success";
    }
}
