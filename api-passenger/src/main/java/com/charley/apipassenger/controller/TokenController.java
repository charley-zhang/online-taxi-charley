package com.charley.apipassenger.controller;

import com.charley.apipassenger.service.TokenService;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){

        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的refreshToken："+ refreshTokenSrc);

        return tokenService.refreshToken(refreshTokenSrc);
    }
}
