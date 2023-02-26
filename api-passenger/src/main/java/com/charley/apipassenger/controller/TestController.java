package com.charley.apipassenger.controller;

import com.charley.apipassenger.remote.ServiceOrderClient;
import com.charley.internalcommon.dto.OrderInfo;
import com.charley.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:21
 * @ClassName: TestController
 * @Version 1.0
 * @Description: 测试类
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: authTest
     * @paramType []
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:49
     * @Description: 需要有token
     */
    @GetMapping(value = "/authTest")
    public ResponseResult authTest() {
        return ResponseResult.success("auth test");
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: noauthTest
     * @paramType []
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:49
     * @Description: 没有token也能正常返回
     */
    @GetMapping(value = "noauthTest")
    public ResponseResult noauthTest() {
        return ResponseResult.success("noauth test");
    }

    @Autowired
    private ServiceOrderClient serviceOrderClient;


    /**
     * @Author: Charley_Zhang
     * @MethodName: dispatchRealTimeOrder
     * @param: orderId
     * @paramType [long]
     * @return: java.lang.String
     * @Date: 2023/2/26 23:49
     * @Description: 测试派单逻辑
     */
    @GetMapping(value = "/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId) {
        log.info("并发测试： orderId ：" + String.valueOf(orderId));
        serviceOrderClient.dispatchRealTimeOrder(orderId);
        return "test-real-time-order    success";
    }
}
