package com.charley.serviceorder.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:50
 * @ClassName: ServiceSsePushClient
 * @Version 1.0
 * @Description:  通知服务远程调用客户端
 */
@FeignClient("service-sse-push")
public interface ServiceSsePushClient {

    /**
       * @Author: Charley_Zhang
       * @MethodName: push
     * @param: userId
     * @param: identity
     * @param: content
       * @paramType  [java.lang.Long, java.lang.String, java.lang.String]
       * @return:  java.lang.String
       * @Date: 2023/2/27 0:50
       * @Description:   发送消息
       */
    @RequestMapping(method = RequestMethod.GET, value = "/push")
    public String push(@RequestParam Long userId, @RequestParam String identity, @RequestParam String content);
}
