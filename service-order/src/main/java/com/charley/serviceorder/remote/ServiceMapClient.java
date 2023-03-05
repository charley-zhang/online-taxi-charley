package com.charley.serviceorder.remote;

import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TerminalResponse;
import com.charley.internalcommon.reponese.TrsearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:46
 * @ClassName: ServiceMapClient
 * @Version 1.0
 * @Description: 本地地图服务远程调用客户端
 */
@FeignClient(value = "service-map")
public interface ServiceMapClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: terminalAroundSearch
     * @param: center
     * @param: radius
     * @paramType [java.lang.String, java.lang.Integer]
     * @return: com.charley.internalcommon.dto.ResponseResult<java.util.List < com.charley.internalcommon.reponese.TerminalResponse>>
     * @Date: 2023/2/27 0:46
     * @Description: 终端搜索
     */
    @RequestMapping(method = RequestMethod.POST, value = "/terminal/aroundsearch")
    public ResponseResult<List<TerminalResponse>> terminalAroundSearch(@RequestParam String center, @RequestParam Integer radius);


    /**
     * @Author: Charley_Zhang
     * @MethodName: trsearch
     * @param: tid
     * @param: startTime
     * @param: endTime
     * @paramType [java.lang.String, java.lang.Long, java.lang.Long]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.TrsearchResponse>
     * @Date: 2023/3/5 16:49
     * @Description: 终端轨迹查询
     */
    @RequestMapping(method = RequestMethod.POST, value = "/terminal/trsearch")
    public ResponseResult<TrsearchResponse> trsearch(@RequestParam String tid, @RequestParam Long startTime, @RequestParam Long endTime);
}
