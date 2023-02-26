package com.charley.servicepassengeruser.controller;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.VerificationCodeDTO;
import com.charley.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:52
 * @ClassName: UserController
 * @Version 1.0
 * @Description: 用户信息控制
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: loginOrRegister
     * @param: verificationCodeDTO
     * @paramType [com.charley.internalcommon.request.VerificationCodeDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:52
     * @Description: 根据手机号插入用户
     */
    @PostMapping(value = "/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println(passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: getUser
     * @param: passengerPhone
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:52
     * @Description: 根据手机号查询用户信息
     */
    @GetMapping(value = "/user/{phone}")
    public ResponseResult getUser(@PathVariable("phone") String passengerPhone) {
        System.out.println("service-passenger-user : phone" + passengerPhone);
        return userService.getUserByPhone(passengerPhone);
    }
}
