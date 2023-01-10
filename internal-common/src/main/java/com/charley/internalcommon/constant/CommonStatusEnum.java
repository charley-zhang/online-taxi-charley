package com.charley.internalcommon.constant;

public enum CommonStatusEnum {

    /**
     * 验证码错误提示  1000-1099
     */
    VERIFICATION_CODE_ERROR(1099, "验证码不正确"),

    /**
     * Token 类提示  1100  -  1199
     */
    TOKEN_ERROR(1199,"token错误"),

    /**
     * 用户提示：1200 - 1299
     */
    USER_NOT_EXISTS(1200,"当前用户不存在"),

    /**
     *  计价规则不存在 1300 - 1399
     */
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),

    /**
     * 成功
     */
    SUCCESS(1,"success"),

    /**
     * 失败
     */
    FAIL(0,"fail")



    ;

    private int code;
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
