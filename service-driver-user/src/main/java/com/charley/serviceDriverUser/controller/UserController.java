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

@RestController
@Slf4j
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * 新增司机信息
     * @param driverUser
     * @return
     */
    @PostMapping(value = "/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());

        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    @PutMapping(value = "/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 查询司机
     * 检查手机号对应司机是否存在
     * 如果需要按照司机的多个条件做查询，那么此处用  /user
     * @param driverPhone
     * @return
     */
    @GetMapping(value = "/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> getUser(@PathVariable("driverPhone") String driverPhone){

        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDB = driverUserByPhone.getData();
        int ifExists = DriverCarConstants.DRIVER_EXISTS;
        DriverUserExistsResponse response = new DriverUserExistsResponse();
        if (driverUserDB == null){
            ifExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            response.setDriverPhone(driverPhone);
            response.setIfExists(ifExists);
        }else {
            response.setDriverPhone(driverUserDB.getDriverPhone());
            response.setIfExists(ifExists);
        }


        return ResponseResult.success(response);
    }


    /**
     * 根据车辆 ID，查询可以派单的司机的信息
     * @param carId
     * @return
     */
    @GetMapping(value = "/get-available-driver{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId){
        return driverUserService.getAvailableDriver(carId);
    }



}
