package com.charley.servicemap.controller;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TerminalResponse;
import com.charley.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @PostMapping(value = "/add")
    public ResponseResult<TerminalResponse> add(String name, String desc){
        return terminalService.add(name, desc);
    }


    @PostMapping(value = "/aroundsearch")
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius){
        return terminalService.aroundsearch(center, radius);
    }
}
