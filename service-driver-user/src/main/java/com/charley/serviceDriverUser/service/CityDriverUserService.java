package com.charley.serviceDriverUser.service;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceDriverUser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.DeclareRoles;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:14
 * @ClassName: CityDriverUserService
 * @Version 1.0
 * @Description: 查询可用司机服务
 */
@Service
public class CityDriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    /**
     * @Author: Charley_Zhang
     * @MethodName: isAvailableDriver
     * @param: cityCode
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<java.lang.Boolean>
     * @Date: 2023/2/27 0:15
     * @Description: 根据城市码查询城市是否有可用司机
     */
    public ResponseResult<Boolean> isAvailableDriver(String cityCode) {
        int i = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        if (i > 0) {
            return ResponseResult.success(true);
        }
        return ResponseResult.success(false);
    }
}
