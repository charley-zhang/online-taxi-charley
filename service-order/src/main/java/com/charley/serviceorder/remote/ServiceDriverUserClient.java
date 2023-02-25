package com.charley.serviceorder.remote;

import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.OrderDriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-driver-user")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/city-driver/is-available-driver")
    public ResponseResult isAvailableDriver(@RequestParam String cityCode);


    @GetMapping(value = "/get-available-driver{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId);

    @GetMapping(value = "/car")
    public ResponseResult<Car> getCarById(@RequestParam Long carId);
}
