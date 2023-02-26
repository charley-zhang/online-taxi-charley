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

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:41
 * @ClassName: OrderInfoController
 * @Version 1.0
 * @Description: 订单服务控制
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;


    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: orderRequest
     * @param: httpServletRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest, javax.servlet.http.HttpServletRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 22:23
     * @Description: 乘客创建订单
     */
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest, HttpServletRequest httpServletRequest) {
        log.info(orderRequest.getAddress());

//        String deviceCode = httpServletRequest.getHeader(HeadPrarmConstants.DEVICE_CODE);
//        orderRequest.setDeviceCode(deviceCode);

        return orderInfoService.add(orderRequest);
    }


    /**
       * @Author: Charley_Zhang
       * @MethodName: changeStatus
     * @param: orderRequest
       * @paramType  [com.charley.internalcommon.request.OrderRequest]
       * @return:  com.charley.internalcommon.dto.ResponseResult
       * @Date: 2023/2/27 0:41
       * @Description:   更新订单状态
       */
    @PostMapping(value = "/to-pick-up-passenger")
    public ResponseResult changeStatus(@RequestBody OrderRequest orderRequest){
        return orderInfoService.toPickUpPassenger(orderRequest);
    }
}
