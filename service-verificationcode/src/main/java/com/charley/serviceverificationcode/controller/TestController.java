package com.charley.serviceverificationcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public String test(){
        return "<h1>this is h1</h1>------------"+"<h5>this is h5</h5>";
    }
}
