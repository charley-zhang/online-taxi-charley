package com.charley.apidriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:21
 * @ClassName: TestController
 * @Version 1.0
 * @Description: 测试类
 */
@RestController
public class TestController {

    /**
     * @Author: Charley_Zhang
     * @MethodName: testAuth
     * @paramType []
     * @return: java.lang.String
     * @Date: 2023/2/26 23:31
     * @Description: 需要授权的接口
     */
    @GetMapping(value = "/auth")
    public String testAuth() {
        return "suth";
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: testNoAuth
     * @paramType []
     * @return: java.lang.String
     * @Date: 2023/2/26 23:31
     * @Description: 不需要授权的接口
     */
    @GetMapping(value = "/noauth")
    public String testNoAuth() {
        return "nosuth";
    }
}
