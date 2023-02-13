package com.charley.serviceDriverUser.service;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceDriverUser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.DeclareRoles;

@Service
public class CityDriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    public ResponseResult<Boolean> isAvailableDriver(String cityCode){
        int i = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        if (i > 0) {
            return ResponseResult.success(true);
        }
        return ResponseResult.success(false);
    }
}
