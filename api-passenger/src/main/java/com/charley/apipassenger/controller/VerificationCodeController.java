package com.charley.apipassenger.controller;

import com.charley.internalcommon.request.VerificationCodeDTO;
import com.charley.apipassenger.service.VerificationCodeService;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:51
 * @ClassName: VerificationCodeController
 * @Version 1.0
 * @Description: 用户信息控制
 */
@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: verificationCode
     * @param: verificationCodeDTO
     * @paramType [com.charley.internalcommon.request.VerificationCodeDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:51
     * @Description: 用户获取验证码
     */
    @GetMapping(value = "/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号码" + passengerPhone);

        return verificationCodeService.generatorCode(passengerPhone);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: checkVerificationCode
     * @param: verificationCodeDTO
     * @paramType [com.charley.internalcommon.request.VerificationCodeDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:51
     * @Description: 用户校验验证码
     */
    @PostMapping(value = "/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        System.out.println("phoneNumber--->" + passengerPhone + "=====checkCode---->" + verificationCode);


        return verificationCodeService.checkCode(passengerPhone, verificationCode);
    }
}