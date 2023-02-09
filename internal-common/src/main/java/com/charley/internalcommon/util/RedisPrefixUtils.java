package com.charley.internalcommon.util;

public class RedisPrefixUtils {

    // 乘客验证码的前缀
    public static String verificationCodePrefix = "passenger-verification-code-";

    // token 前缀
    public static String tokenPrefix = "token-";

    /**
     * 黑名单设备号
     */
    public static String blackDeviceCodePrefix = "black-device-";

    /**
     * 根据手机号生成key
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorKeyByPhone(String phone, String identity){
        return verificationCodePrefix + identity+ "-" + phone;
    }

    // 根据手机号和身份标识生成token
    public static String generatorTokenKey(String phone, String identity, String tokenType){
        return tokenPrefix + phone + "-" + identity + "-" +tokenType;
    }
}
