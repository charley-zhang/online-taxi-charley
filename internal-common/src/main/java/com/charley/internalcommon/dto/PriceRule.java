package com.charley.internalcommon.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (PriceRule)实体类
 *
 * @author makejava
 * @since 2023-01-31 14:26:26
 */
@Data
@Accessors(chain = true)
public class PriceRule implements Serializable {
    private static final long serialVersionUID = 122774746281575318L;
    /**
     * 城市代码
     */
    private String cityCode;
    /**
     * 车辆类型
     */
    private String vehicleType;
    /**
     * 起步价
     */
    private Double startFare;
    
    private Integer startMile;
    
    private Double unitPricePerMile;
    
    private Double unitPricePerMinute;
    
    private Integer fareVersion;
    
    private String fareType;



}

