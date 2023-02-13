package com.charley.servicepassengeruser.controller;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.VerificationCodeDTO;
import com.charley.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据手机号插入用户
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping(value = "/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println(passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }


    /**
     * 根据手机号查询用户信息
     * @param passengerPhone
     * @return
     */
    @GetMapping(value = "/user/{phone}")
    public ResponseResult getUser(@PathVariable("phone") String passengerPhone){
        System.out.println("service-passenger-user : phone" + passengerPhone);
        return userService.getUserByPhone(passengerPhone);
    }
}
