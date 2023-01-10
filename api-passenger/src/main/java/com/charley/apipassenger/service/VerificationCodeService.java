package com.charley.apipassenger.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.charley.apipassenger.remote.ServicePassengerUserClient;
import com.charley.apipassenger.remote.ServiceVefificationCodeClient;
import com.charley.internalcommon.constant.CommonStatusEnum;
import com.charley.internalcommon.constant.IdentityConstant;
import com.charley.internalcommon.constant.TokenConstants;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.NumberCodeReponese;
import com.charley.internalcommon.reponese.TokenResponse;
import com.charley.internalcommon.request.VerificationCodeDTO;
import com.charley.internalcommon.util.JwtUtils;
import com.charley.internalcommon.util.RedisPrefixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {


    @Autowired
    private ServiceVefificationCodeClient serviceVefificationCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    /**
     * 生成验证码
     * @param passengerPhone  手机号
     * @return
     */
    public ResponseResult generatorCode(String passengerPhone){
        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeReponese> numberCodeReponeseResponseResult = serviceVefificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeReponeseResponseResult.getData().getNumberCode();
        System.out.println("remote number code : " + numberCode);


        // 存入redis
        System.out.println("存入redis");
        // key value 过期事件
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        // 存入redis
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到手机上   ：  阿里--短信服务    腾讯--短信通    华信    容联

        return ResponseResult.success();
    }


    /**
     * 校验验证码
     * @param passengerPhone  手机号
     * @param verificationCode  验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode){

        // 根据手机号去redis读取验证码  1 生成key   2 根据key获取value
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println(key+"--->"+codeRedis);

        // 校验验证码
        if (StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        // 判断原来是否有用户，并进行相应处理
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrgister(verificationCodeDTO);

        // 颁发令牌  不应该用魔法值   用常量
        String accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);

        // 将 token 存入 redis
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
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