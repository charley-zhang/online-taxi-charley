package com.charley.serviceorder.remote;

import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-driver-user")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/city-driver/is-available-driver")
    public ResponseResult isAvailableDriver(@RequestParam String cityCode);
}
