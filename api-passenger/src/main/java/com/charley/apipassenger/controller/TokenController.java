package com.charley.apipassenger.controller;

import com.charley.apipassenger.service.TokenService;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:50
 * @ClassName: TokenController
 * @Version 1.0
 * @Description: token控制
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: refreshToken
     * @param: tokenResponse
     * @paramType [com.charley.internalcommon.reponese.TokenResponse]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:50
     * @Description: 双token刷新
     */
    @PostMapping(value = "/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {

        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的refreshToken：" + refreshTokenSrc);

        return tokenService.refreshToken(refreshTokenSrc);
    }
}
