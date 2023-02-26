package com.charley.apipassenger.remote;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.NumberCodeReponese;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:59
 * @ClassName: ServiceVefificationCodeClient
 * @Version 1.0
 * @Description: 验证码服务远程调用客户端
 */
@FeignClient(value = "service-verificationcode")
public interface ServiceVefificationCodeClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: getNumberCode
     * @param: size
     * @paramType [int]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.NumberCodeReponese>
     * @Date: 2023/2/27 0:00
     * @Description: 获取验证码
     */
    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    public ResponseResult<NumberCodeReponese> getNumberCode(@PathVariable("size") int size);
}
