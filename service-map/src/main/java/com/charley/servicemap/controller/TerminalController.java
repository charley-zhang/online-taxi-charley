package com.charley.servicemap.controller;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TerminalResponse;
import com.charley.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @PostMapping(value = "/add")
    public ResponseResult<TerminalResponse> add(String name){
        return terminalService.add(name);
    }
}
