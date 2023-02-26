package com.charley.apipassenger.service;


import com.charley.apipassenger.remote.ServicePassengerUserClient;
import com.charley.apipassenger.remote.ServiceVefificationCodeClient;
import com.charley.internalcommon.dto.PassengerUser;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.dto.TokenResult;
import com.charley.internalcommon.request.VerificationCodeDTO;
import com.charley.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:58
 * @ClassName: UserService
 * @Version 1.0
 * @Description: 用户服务
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: getUserByAccessToken
     * @param: accessToken
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:58
     * @Description: 查询乘客用户信息
     */
    public ResponseResult getUserByAccessToken(String accessToken) {

        log.info("accessToken: " + accessToken);

        // 解析 accessToken 拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();

        log.info("手机号：" + phone);

        // 根据手机号查询用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);

        return ResponseResult.success(userByPhone.getData());
    }
}
