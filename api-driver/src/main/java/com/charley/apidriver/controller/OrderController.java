package com.charley.apidriver.controller;

import com.charley.apidriver.service.ApiDriverOrderInfoService;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/3/5 17:36
 * @PackageName:com.charley.apidriver.controller
 * @ClassName: OrderController
 * @Version 1.0
 * @Description: 订单状态控制
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private ApiDriverOrderInfoService apiDriverOrderInfoService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: toPickUpPassenger
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/3/5 17:44
     * @Description: 订单状态改变 --- 去接乘客
     */
    @PostMapping(value = "/to-pick-up-passenger")
    public ResponseResult toPickUpPassenger(@RequestBody OrderRequest orderRequest) {
        return apiDriverOrderInfoService.toPickUpPassenger(orderRequest);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: arrivedDeparture
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/3/4 22:27
     * @Description: 更新订单状态 ---  到达乘客上车点
     */
    @PostMapping(value = "/arrived-departure")
    public ResponseResult arrivedDeparture(@RequestBody OrderRequest orderRequest) {
        return apiDriverOrderInfoService.arrivedDeparture(orderRequest);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: pickUpPassenger
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/3/4 22:42
     * @Description: 更新订单状态 ---  司机接到乘客
     */
    @PostMapping(value = "/pick-up-passenger")
    public ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest) {
        return apiDriverOrderInfoService.pickUpPassenger(orderRequest);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: passengerGetoff
     * @param: orderRequest
     * @paramType [com.charley.internalcommon.request.OrderRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/3/4 23:11
     * @Description: 更新订单状态 ---  司机行程结束, 乘客到达目的地
     */
    @PostMapping(value = "/passenger-getoff")
    public ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest) {
        return apiDriverOrderInfoService.passengerGetoff(orderRequest);
    }


}
