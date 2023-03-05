package com.charley.serviceDriverUser.controller;

import com.charley.internalcommon.constant.DriverCarConstants;
import com.charley.internalcommon.dto.DriverUser;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.DriverUserExistsResponse;
import com.charley.internalcommon.reponese.OrderDriverResponse;
import com.charley.serviceDriverUser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:07
 * @ClassName: UserController
 * @Version 1.0
 * @Description: 司机信息控制
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: addUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:07
     * @Description: 新增司机信息
     */
    @PostMapping(value = "/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser) {
        log.info(JSONObject.fromObject(driverUser).toString());

        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: updateUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:07
     * @Description: 修改司机信息
     */
    @PutMapping(value = "/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser) {
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: getUser
     * @param: driverPhone
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.DriverUserExistsResponse>
     * @Date: 2023/2/27 0:08
     * @Description: 查询司机
     * 检查手机号对应司机是否存在
     * 如果需要按照司机的多个条件做查询，那么此处用  /user
     */
    @GetMapping(value = "/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> getUser(@PathVariable("driverPhone") String driverPhone) {

        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDB = driverUserByPhone.getData();
        int ifExists = DriverCarConstants.DRIVER_EXISTS;
        DriverUserExistsResponse response = new DriverUserExistsResponse();
        if (driverUserDB == null) {
            ifExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            response.setDriverPhone(driverPhone);
            response.setIfExists(ifExists);
        } else {
            response.setDriverPhone(driverUserDB.getDriverPhone());
            response.setIfExists(ifExists);
        }


        return ResponseResult.success(response);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: getAvailableDriver
     * @param: carId
     * @paramType [java.lang.Long]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.OrderDriverResponse>
     * @Date: 2023/2/27 0:08
     * @Description: 根据车辆 ID，查询可以派单的司机的信息
     */
    @GetMapping(value = "/get-available-driver/{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId) {
        return driverUserService.getAvailableDriver(carId);
    }


}
