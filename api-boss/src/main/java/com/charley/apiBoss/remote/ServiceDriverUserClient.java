package com.charley.apiBoss.remote;

import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.DriverCarBindingRelationship;
import com.charley.internalcommon.dto.DriverUser;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:22
 * @ClassName: ServiceDriverUserClient
 * @Version 1.0
 * @Description: 司机用户远程调用客户端
 */
@FeignClient(value = "service-driver-user")
public interface ServiceDriverUserClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: addDriverUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:23
     * @Description: 添加司机信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    /**
     * @Author: Charley_Zhang
     * @MethodName: updateDriverUser
     * @param: driverUser
     * @paramType [com.charley.internalcommon.dto.DriverUser]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:23
     * @Description: 修改司机信息
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);

    /**
     * @Author: Charley_Zhang
     * @MethodName: addCar
     * @param: car
     * @paramType [com.charley.internalcommon.dto.Car]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:23
     * @Description: 添加车辆信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/car")
    public ResponseResult addCar(@RequestBody Car car);

    /**
     * @Author: Charley_Zhang
     * @MethodName: bind
     * @param: driverCarBindingRelationship
     * @paramType [com.charley.internalcommon.dto.DriverCarBindingRelationship]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:24
     * @Description: 司机 & 车辆 绑定
     */
    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);

    /**
     * @Author: Charley_Zhang
     * @MethodName: unbind
     * @param: driverCarBindingRelationship
     * @paramType [com.charley.internalcommon.dto.DriverCarBindingRelationship]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:24
     * @Description: 司机 & 车辆 解绑
     */
    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship/unbind")
    ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship);
}
