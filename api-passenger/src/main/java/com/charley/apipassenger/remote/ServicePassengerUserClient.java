package com.charley.apipassenger.remote;

import com.charley.internalcommon.dto.PassengerUser;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:02
 * @ClassName: ServicePassengerUserClient
 * @Version 1.0
 * @Description: 乘客用户远程调用客户端
 */
@FeignClient(value = "service-passenger-user")
public interface ServicePassengerUserClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: loginOrgister
     * @param: verificationCodeDTO
     * @paramType [com.charley.internalcommon.request.VerificationCodeDTO]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:01
     * @Description: 判断原来是否有用户
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseResult loginOrgister(@RequestBody VerificationCodeDTO verificationCodeDTO);

    /**
     * @Author: Charley_Zhang
     * @MethodName: getUserByPhone
     * @param: phone
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.dto.PassengerUser>
     * @Date: 2023/2/27 0:02
     * @Description: 根据手机号查询用户信息
     */
    @RequestMapping(value = "/user/{phone}", method = RequestMethod.GET)
    public ResponseResult<PassengerUser> getUserByPhone(@PathVariable String phone);
}
