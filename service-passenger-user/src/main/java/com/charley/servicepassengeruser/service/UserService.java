package com.charley.servicepassengeruser.service;

import com.charley.internalcommon.constant.CommonStatusEnum;
import com.charley.internalcommon.dto.PassengerUser;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.VerificationCodeDTO;
import com.charley.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:54
 * @ClassName: UserService
 * @Version 1.0
 * @Description: 用户信息服务
 */
@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    /**
     * @Author: Charley_Zhang
     * @MethodName: loginOrRegister
     * @param: passengerPhone
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:54
     * @Description: 根据手机号插入用户
     */
    public ResponseResult loginOrRegister(String passengerPhone) {
        System.out.println("loginOrRegister(String passengerPhone) --- 被调用");
        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers);
        System.out.println(passengerUsers.size() == 0 ? "无记录" : passengerUsers.get(0).getPassengerName());


        // 判断用户信息是否存在
        if (passengerUsers.size() == 0) {
            // 如果不存在，插入用户信息
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte) 0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);

            passengerUserMapper.insert(passengerUser);

        }
        return ResponseResult.success();
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: getUserByPhone
     * @param: passengerPhone
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:55
     * @Description: 根据手机号查询用户信息
     */
    public ResponseResult getUserByPhone(String passengerPhone) {

        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers);

        if (passengerUsers.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(), CommonStatusEnum.USER_NOT_EXISTS.getValue());
        } else {
            PassengerUser passengerUser = passengerUsers.get(0);
            return ResponseResult.success(passengerUser);
        }
    }

}
