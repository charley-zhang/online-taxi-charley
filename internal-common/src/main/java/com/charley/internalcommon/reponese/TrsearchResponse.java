package com.charley.internalcommon.reponese;

import lombok.Data;

/**
 * @Author Charley_Zhang
 * @Date 2023/3/5 16:40
 * @PackageName:com.charley.internalcommon.reponese
 * @ClassName: TrsearchResponse
 * @Version 1.0
 * @Description:
 */
@Data
public class TrsearchResponse {

    /**
     * 距离 ： 米
     */
    private Long driveMile;


    /**
     * 时长 ： 分
     */
    private Long driveTime;
}
