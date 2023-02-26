package com.charley.serviceorder.remote;

import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.OrderDriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:43
 * @ClassName: ServiceDriverUserClient
 * @Version 1.0
 * @Description: 远程调用司机用户服务客户端
 */
@FeignClient(value = "service-driver-user")
public interface ServiceDriverUserClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: isAvailableDriver
     * @param: cityCode
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:43
     * @Description: 根据城市码查询城市是否有可用的司机
     */
    @RequestMapping(method = RequestMethod.GET, value = "/city-driver/is-available-driver")
    public ResponseResult isAvailableDriver(@RequestParam String cityCode);


    /**
     * @Author: Charley_Zhang
     * @MethodName: getAvailableDriver
     * @param: carId
     * @paramType [java.lang.Long]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.OrderDriverResponse>
     * @Date: 2023/2/27 0:43
     * @Description: 根据 carId 查询是否有多余的可派单司机
     */
    @GetMapping(value = "/get-available-driver{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId);

    /**
     * @Author: Charley_Zhang
     * @MethodName: getCarById
     * @param: carId
     * @paramType [java.lang.Long]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.dto.Car>
     * @Date: 2023/2/27 0:44
     * @Description: 根据 carId 查询车辆信息
     */
    @GetMapping(value = "/car")
    public ResponseResult<Car> getCarById(@RequestParam Long carId);
}
