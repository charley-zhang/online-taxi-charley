package com.charley.apiBoss.service;


import com.charley.apiBoss.remote.ServiceDriverUserClient;
import com.charley.internalcommon.dto.DriverUser;
import com.charley.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:26
 * @ClassName: DriverUserService
 * @Version 1.0
 * @Description: 司机用户服务
 */
@Service
@Slf4j
public class DriverUserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: addDriverUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:27
     * @Description: 添加司机信息
     */
    public ResponseResult addDriverUser(DriverUser driverUser) {
        return serviceDriverUserClient.addDriverUser(driverUser);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: updateDriverUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:27
     * @Description: 修改司机信息
     */
    public ResponseResult updateDriverUser(DriverUser driverUser) {
        return serviceDriverUserClient.updateDriverUser(driverUser);
    }

}
