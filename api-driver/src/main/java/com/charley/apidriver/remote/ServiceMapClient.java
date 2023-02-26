package com.charley.apidriver.remote;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.PointRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:40
 * @ClassName: ServiceMapClient
 * @Version 1.0
 * @Description: 地图服务远程调用客户端
 */
@FeignClient(value = "service-map")
public interface ServiceMapClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: upload
     * @param: pointRequest
     * @paramType [com.charley.internalcommon.request.PointRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:41
     * @Description: 上传车辆经纬度信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/point/upload")
    public ResponseResult upload(@RequestBody PointRequest pointRequest);
}
