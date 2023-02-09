package com.charley.internalcommon.reponese;

import lombok.Data;

@Data
public class ForecastPriceResponse {

    private double price;


    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 车辆类型
     */
    private String vehicleType;

    private String fareType;

    private Integer fareVersion;
}
