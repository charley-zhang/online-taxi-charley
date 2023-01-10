package com.charley.apipassenger.remote;

import com.charley.internalcommon.dto.PassengerUser;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-passenger-user")
public interface ServicePassengerUserClient {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseResult loginOrgister(@RequestBody VerificationCodeDTO verificationCodeDTO);

    @RequestMapping(value = "/user/{phone}", method = RequestMethod.GET)
    public ResponseResult<PassengerUser> getUserByPhone(@PathVariable String phone);
}
