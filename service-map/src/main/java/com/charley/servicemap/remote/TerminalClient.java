package com.charley.servicemap.remote;


import com.charley.internalcommon.constant.AmapConfigConstants;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TerminalResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:34
 * @ClassName: TerminalClient
 * @Version 1.0
 * @Description: 远程调用终端服务客户端
 */
@Service
@Slf4j
public class TerminalClient {

    @Value(value = "${amap.key}")
    private String amapKey;

    @Value(value = "${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: name
     * @param: desc
     * @paramType [java.lang.String, java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.TerminalResponse>
     * @Date: 2023/2/27 0:35
     * @Description: 新建终端  远程请求  https://tsapi.amap.com/v1/track/terminal/add
     */
    public ResponseResult<TerminalResponse> add(String name, String desc) {

        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_ADD);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&sid=" + amapSid);
        url.append("&name=" + name);
        url.append("&desc=" + desc);

        log.info(url.toString());

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        log.info(stringResponseEntity.toString());
        String body = stringResponseEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String tid = data.getString("tid");
        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setTid(tid);

        return ResponseResult.success(terminalResponse);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: aroundsearch
     * @param: center
     * @param: radius
     * @paramType [java.lang.String, java.lang.Integer]
     * @return: com.charley.internalcommon.dto.ResponseResult<java.util.List < com.charley.internalcommon.reponese.TerminalResponse>>
     * @Date: 2023/2/27 0:35
     * @Description: 终端搜索
     */
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius) {

        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_AROUNDSEARCH);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&sid=" + amapSid);
        url.append("&center=" + center);
        url.append("&radius=" + radius);

        log.info("终端搜索请求url： " + url.toString());

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url.toString(), null, String.class);

        log.info("终端搜索请求结果： " + stringResponseEntity.getBody());

        // 解析终端搜索结果
        String body = stringResponseEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);

        List<TerminalResponse> terminalResponsesList = new ArrayList<>();

        JSONArray results = result.getJSONObject("data").getJSONArray("results");
        for (int i = 0; i < results.size(); i++) {
            TerminalResponse terminalResponse = new TerminalResponse();

            JSONObject jsonObject = results.getJSONObject(i);
            // desc 就是carId
            Long carId = Long.parseLong(jsonObject.getString("desc"));
            String tid = jsonObject.getString("tid");

            JSONObject location = jsonObject.getJSONObject("location");
            String longitude = location.getString("longitude");
            String latitude = location.getString("latitude");

            terminalResponse.setCarId(carId);
            terminalResponse.setTid(tid);
            terminalResponse.setLongitude(longitude);
            terminalResponse.setLatitude(latitude);

            terminalResponsesList.add(terminalResponse);
        }

        log.info(body);

        return ResponseResult.success(terminalResponsesList);
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: trsearch
     * @param: tid
     * @param: startTime
     * @param: endTime
     * @paramType [java.lang.String, java.lang.Long, java.lang.Long]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/3/5 0:30
     * @Description: 轨迹查询
     */
    public ResponseResult trsearch(String tid, Long startTime, Long endTime) {
        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_TRSEARCH);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&sid=" + amapSid);
        url.append("&tid=" + tid);
        url.append("&starttime=" + startTime);
        url.append("&endtime=" + endTime);

        log.info("高德地图查询轨迹请求url：" + url.toString());
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);
        log.info("高德地图查询轨迹结果响应：" + forEntity.toString());


        return null;

    }
}
