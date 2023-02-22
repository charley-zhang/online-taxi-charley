package com.charley.apipassenger.controller;

import com.charley.apipassenger.remote.ServiceOrderClient;
import com.charley.internalcommon.dto.OrderInfo;
import com.charley.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

    /**
     * 需要有token
     * @return
     */
    @GetMapping(value = "/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");
    }

    /**
     * 没有token也能正常返回
     * @return
     */
    @GetMapping(value = "noauthTest")
    public ResponseResult noauthTest(){
        return ResponseResult.success("noauth test");
    }

    @Autowired
    private ServiceOrderClient serviceOrderClient;


    /**
     * 测试派单逻辑
     * @param orderId
     * @return
     */
    @GetMapping(value = "/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId){
        log.info("并发测试： orderId ：" + String.valueOf(orderId));
        serviceOrderClient.dispatchRealTimeOrder(orderId);
        return "test-real-time-order    success";
    }
}
