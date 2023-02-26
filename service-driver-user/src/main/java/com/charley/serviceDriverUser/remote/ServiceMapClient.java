package com.charley.serviceDriverUser.remote;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TerminalResponse;
import com.charley.internalcommon.reponese.TrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:12
 * @ClassName: ServiceMapClient
 * @Version 1.0
 * @Description: 地图服务远程调用客户端
 */
@FeignClient(value = "service-map")
public interface ServiceMapClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: addTerminal
     * @param: name
     * @param: desc
     * @paramType [java.lang.String, java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.TerminalResponse>
     * @Date: 2023/2/27 0:12
     * @Description: 添加终端
     */
    @RequestMapping(method = RequestMethod.POST, value = "/terminal/add")
    public ResponseResult<TerminalResponse> addTerminal(@RequestParam String name, @RequestParam String desc);

    /**
     * @Author: Charley_Zhang
     * @MethodName: addTrack
     * @param: tid
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.TrackResponse>
     * @Date: 2023/2/27 0:13
     * @Description: 添加车辆轨迹
     */
    @RequestMapping(method = RequestMethod.POST, value = "/track/add")
    public ResponseResult<TrackResponse> addTrack(@RequestParam String tid);
}
