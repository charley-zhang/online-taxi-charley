package com.charley.internalcommon.util;

public class RedisPrefixUtils {

    // 乘客验证码的前缀
    public static String verificationCodePrefix = "passenger-verification-code-";

    // token 前缀
    public static String tokenPrefix = "token-";

    public static String generatorKeyByPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
    }

    // 根据手机号和身份标识生成token
    public static String generatorTokenKey(String phone, String identity, String tokenType){
        return tokenPrefix + phone + "-" + identity + "-" +tokenType;
    }
}
