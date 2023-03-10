package com.charley.apidriver.controller;

import com.charley.apidriver.service.VerificationCodeService;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:33
 * @ClassName: VerificationCodeController
 * @Version 1.0
 * @Description: 司机用户获取验证码控制
 */
@RestController
@Slf4j
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: verificationcode
     * @param: verificationCodeDTO
     * @paramType [com.charley.internalcommon.request.VerificationCodeDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:33
     * @Description: 司机获取验证码
     */
    @GetMapping(value = "/verification-code")
    public ResponseResult verificationcode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String driverPhone = verificationCodeDTO.getDriverPhone();
        log.info("司机的号码：" + driverPhone);
        return verificationCodeService.checkAndSendVerificationCode(driverPhone);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: checkVerificationCode
     * @param: verificationCodeDTO
     * @paramType [com.charley.internalcommon.request.VerificationCodeDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:33
     * @Description: 司机校验验证码
     */
    @PostMapping(value = "/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String driverPhone = verificationCodeDTO.getDriverPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        System.out.println("phoneNumber--->" + driverPhone + "=====checkCode---->" + verificationCode);


        return verificationCodeService.checkCode(driverPhone, verificationCode);
    }
}
