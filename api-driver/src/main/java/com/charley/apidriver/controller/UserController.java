package com.charley.apidriver.controller;

import com.charley.apidriver.service.UserService;
import com.charley.internalcommon.dto.DriverUser;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:32
 * @ClassName: UserController
 * @Version 1.0
 * @Description: 司机用户信息控制
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: updateUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:32
     * @Description: 维护司机信息
     */
    @PutMapping(value = "/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser) {
        return userService.updateUser(driverUser);
    }
}
