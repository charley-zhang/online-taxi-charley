package com.charley.serviceorder.controller;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.OrderRequest;
import com.charley.serviceorder.entity.OrderInfo;
import com.charley.serviceorder.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping(value = "/testMapper")
    public ResponseResult testMapper(@RequestBody OrderInfo orderInfo){
        return orderInfoService.testMapper(orderInfo);
    }


    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        log.info(orderRequest.getAddress());

        return ResponseResult.success("");
    }
}
