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
    FAIL(0,"fail"),

    /**
     * 地图信息  1400 - 1499
     */
    MAP_DISTRICT_ERROR(1400, "请求地图错误"),

    /**
     * 司机和车辆  1500 - 1599
     */
    DRIVER_CAR_BIND_NOT_EXISTS(1500, "司机和车辆绑定关系不存在"),

    DRIVER_NOT_EXISTS(1501, "司机不存在"),

    DRIVER_CAR_BIND_EXISTS(1502, "司机和车辆绑定关系已存在，请勿重复绑定"),

    DRIVER_BIND_EXISTS(1503, "司机已经被绑定，请勿重复绑定"),

    CAR_BIND_EXISTS(1504, "车辆已经被绑定，请勿重复绑定"),




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
