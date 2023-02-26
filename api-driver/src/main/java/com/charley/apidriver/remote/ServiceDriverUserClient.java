package com.charley.apidriver.remote;

import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.DriverUser;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:37
 * @ClassName: ServiceDriverUserClient
 * @Version 1.0
 * @Description: 司机用户服务远程调用客户端
 */
@FeignClient(value = "service-driver-user")
public interface ServiceDriverUserClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: updateUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:38
     * @Description: 维护司机信息
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser);

    /**
     * @Author: Charley_Zhang
     * @MethodName: checkDriver
     * @param: driverPhone
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.DriverUserExistsResponse>
     * @Date: 2023/2/26 23:38
     * @Description: 校验司机用户是否存在
     */
    @RequestMapping(method = RequestMethod.GET, value = "/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> checkDriver(@PathVariable("driverPhone") String driverPhone);

    /**
     * @Author: Charley_Zhang
     * @MethodName: getCarById
     * @param: carId
     * @paramType [java.lang.Long]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.dto.Car>
     * @Date: 2023/2/26 23:38
     * @Description: 根据id获取车辆信息
     */
    @RequestMapping(method = RequestMethod.GET, value = "/car")
    public ResponseResult<Car> getCarById(@RequestParam Long carId);
}
