package com.charley.servicemap.controller;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.servicemap.service.ServiceFromMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:23
 * @ClassName: ServiceController
 * @Version 1.0
 * @Description: 地图服务控制
 */
@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceFromMapService serviceFromMapService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: name
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:23
     * @Description: 创建服务
     */
    @PostMapping(value = "/add")
    public ResponseResult add(String name) {

        return serviceFromMapService.add(name);
    }
}
