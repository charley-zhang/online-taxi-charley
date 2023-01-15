package com.charley.apidriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * 需要授权的接口
     * @return
     */
    @GetMapping(value = "/auth")
    public String testAuth(){
        return "suth";
    }

    /**
     * 不需要授权的接口
     * @return
     */
    @GetMapping(value = "/noauth")
    public String testNoAuth(){
        return "nosuth";
    }
}
