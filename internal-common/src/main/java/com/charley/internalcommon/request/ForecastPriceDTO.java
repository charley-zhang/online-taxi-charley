package com.charley.internalcommon.request;


import lombok.Data;

@Data
public class ForecastPriceDTO {

    /**
     * 出发的经度
     */
    private String depLongitude;

    /**
     * 出发的纬度
     */
    private String depLatiude;

    /**
     * 目的地的经度
     */
    private String destLongitude;

    /**
     * 目的地的维度
     */
    private String destLatiude;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 车辆类型
     */
    private String vehicleType;
}
