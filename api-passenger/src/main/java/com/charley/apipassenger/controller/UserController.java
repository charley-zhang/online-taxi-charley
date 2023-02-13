package com.charley.apipassenger.controller;

import com.charley.apipassenger.service.UserService;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 查询乘客用户信息
     * @param request
     * @return
     */
    @GetMapping(value = "/users")
    public ResponseResult getUser(HttpServletRequest request){

        //从 http 请求中获取 accessToken
        String accessToken = request.getHeader("Authorization");


        return userService.getUserByAccessToken(accessToken);

    }
}
