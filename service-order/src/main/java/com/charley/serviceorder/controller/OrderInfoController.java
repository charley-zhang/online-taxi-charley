package com.charley.serviceorder.controller;

import com.charley.internalcommon.constant.HeadPrarmConstants;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.OrderRequest;
import com.charley.internalcommon.dto.OrderInfo;
import com.charley.serviceorder.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;


    /**
     * 创建订单
     * @param orderRequest
     * @return
     */
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest, HttpServletRequest httpServletRequest){
        log.info(orderRequest.getAddress());

//        String deviceCode = httpServletRequest.getHeader(HeadPrarmConstants.DEVICE_CODE);
//        orderRequest.setDeviceCode(deviceCode);

        return orderInfoService.add(orderRequest);
    }
}
