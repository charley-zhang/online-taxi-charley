package com.charley.apidriver.service;

import com.charley.apidriver.remote.ServiceDriverUserClient;
import com.charley.internalcommon.dto.DriverUser;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:44
 * @ClassName: UserService
 * @Version 1.0
 * @Description: 司机用户服务
 */
@Service
public class UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: updateUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:44
     * @Description: 维护司机信息
     */
    public ResponseResult updateUser(DriverUser driverUser) {
        return serviceDriverUserClient.updateUser(driverUser);
    }
}
