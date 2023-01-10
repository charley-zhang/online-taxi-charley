package com.charley.internalcommon.dto;


import lombok.Data;

@Data
public class PriceRule {


    private String cityCode;

    private String vehicleType;

    private Double startFare;

    private int startMile;

    private Double unitPricePerMile;

    private Double unitPricePerMinute;
}
