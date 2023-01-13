package com.charley.internalcommon.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Charlry
 * @since 2023-01-13
 */
@Data
public class DriverCarBindingRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long driverId;

    private Long carId;

    /**
     * 绑定状态：1  绑定 ， 2  解绑
     */
    private Integer bindState;

    private LocalDateTime bindingTime;

    private LocalDateTime unBindingTime;

}
