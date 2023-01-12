package com.charley.apiBoss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public String test(){
        return "<h1>this is api-boss testing</h1>";
    }
}
