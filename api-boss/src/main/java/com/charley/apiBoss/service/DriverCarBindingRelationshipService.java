package com.charley.apiBoss.service;

import com.charley.apiBoss.remote.ServiceDriverUserClient;
import com.charley.internalcommon.dto.DriverCarBindingRelationship;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:25
 * @ClassName: DriverCarBindingRelationshipService
 * @Version 1.0
 * @Description: 司机车辆绑定关系服务
 */
@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: bind
     * @param: driverCarBindingRelationship
     * @paramType [com.charley.internalcommon.dto.DriverCarBindingRelationship]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:26
     * @Description: 司机 & 车辆绑定
     */
    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.bind(driverCarBindingRelationship);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: unbind
     * @param: driverCarBindingRelationship
     * @paramType [com.charley.internalcommon.dto.DriverCarBindingRelationship]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:26
     * @Description: 司机 & 车辆解绑
     */
    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.unbind(driverCarBindingRelationship);
    }
}
