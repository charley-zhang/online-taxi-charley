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


    PRICE_RULE_EXISTS(1301, "计价规则已存在，不允许添加"),

    PRICE_RULE_NOT_EDIT(1302, "计价规则没有变化"),

    PRICE_RULE_CHANGED(1303, "计价规则有变化"),

    /**
     * 订单：1600~1699
     */
    ORDER_GOING_ON(1600, "有正在进行的订单"),

    /**
     * 下单异常
     */
    DEVICE_IS_BLACK(1601, "该设备超过下单次数"),

    CITY_SERVICE_NOT_SERVICE(1602, "当前城市不提供叫车服务"),

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

    CITY_DRIVER_EMPTY(1505, "当前城市没有可用的司机"),

    AVAILABLE_DRIVER_EMPTY(1506, "可用的司机为空"),



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
