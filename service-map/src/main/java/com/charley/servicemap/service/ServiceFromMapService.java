package com.charley.servicemap.service;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.servicemap.remote.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:29
 * @ClassName: ServiceFromMapService
 * @Version 1.0
 * @Description: 本地地图服务
 */
@Service
public class ServiceFromMapService {

    @Autowired
    private ServiceClient serviceClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: name
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:29
     * @Description: 创建服务
     */
    public ResponseResult add(String name) {
        return serviceClient.add(name);
    }
}
