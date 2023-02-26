package com.charley.apidriver.service;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.charley.apidriver.remote.ServiceDriverUserClient;
import com.charley.apidriver.remote.ServiceVerificationCodeClient;
import com.charley.internalcommon.constant.CommonStatusEnum;
import com.charley.internalcommon.constant.DriverCarConstants;
import com.charley.internalcommon.constant.IdentityConstant;
import com.charley.internalcommon.constant.TokenConstants;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.DriverUserExistsResponse;
import com.charley.internalcommon.reponese.NumberCodeReponese;
import com.charley.internalcommon.reponese.TokenResponse;
import com.charley.internalcommon.request.VerificationCodeDTO;
import com.charley.internalcommon.util.JwtUtils;
import com.charley.internalcommon.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:44
 * @ClassName: VerificationCodeService
 * @Version 1.0
 * @Description: 验证码服务
 */
@Service
@Slf4j
public class VerificationCodeService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @Author: Charley_Zhang
     * @MethodName: checkAndSendVerificationCode
     * @param: driverPhone
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:45
     * @Description: 司机用户获取验证码
     */
    public ResponseResult checkAndSendVerificationCode(String driverPhone) {
        // 查询 service-driver-user ，该手机号的司机是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriver(driverPhone);
        DriverUserExistsResponse data = driverUserExistsResponseResponseResult.getData();
        int ifExists = data.getIfExists();
        if (ifExists == DriverCarConstants.DRIVER_NOT_EXISTS) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        log.info(driverPhone + "的司机存在");

        // 获取验证码
        ResponseResult<NumberCodeReponese> numberCodeResult = serviceVerificationCodeClient.getNumberCode(6);
        NumberCodeReponese numberCodeReponese = numberCodeResult.getData();
        int numberCode = numberCodeReponese.getNumberCode();
        log.info("验证码：" + numberCode);

        // 调用第三方发送验证码

        // 存入 redis  1 key  2 存入value
        String key = RedisPrefixUtils.generatorKeyByPhone(driverPhone, IdentityConstant.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);

        return ResponseResult.success("");
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: checkCode
     * @param: driverPhone      手机号
     * @param: verificationCode     验证码
     * @paramType [java.lang.String, java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:45
     * @Description: 司机用户校验验证码
     */
    public ResponseResult checkCode(String driverPhone, String verificationCode) {

        // 根据手机号去redis读取验证码  1 生成key   2 根据key获取value
        String key = RedisPrefixUtils.generatorKeyByPhone(driverPhone, IdentityConstant.DRIVER_IDENTITY);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println(key + "--->" + codeRedis);

        // 校验验证码
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        // 颁发令牌  不应该用魔法值   用常量
        String accessToken = JwtUtils.generatorToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);

        // 将 token 存入 redis
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);


        // 响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        // 删除验证码  防止二次验证
        stringRedisTemplate.delete(key);

        return ResponseResult.success(tokenResponse);
    }
}
