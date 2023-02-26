package com.charley.apipassenger.controller;

import com.charley.apipassenger.service.UserService;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:50
 * @ClassName: UserController
 * @Version 1.0
 * @Description: 用户订单控制
 */
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: getUser
     * @param: request
     * @paramType [javax.servlet.http.HttpServletRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:50
     * @Description: 查询乘客用户信息
     */
    @GetMapping(value = "/users")
    public ResponseResult getUser(HttpServletRequest request) {

        //从 http 请求中获取 accessToken
        String accessToken = request.getHeader("Authorization");


        return userService.getUserByAccessToken(accessToken);

    }
}
