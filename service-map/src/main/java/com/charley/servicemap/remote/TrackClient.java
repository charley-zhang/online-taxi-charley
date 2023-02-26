package com.charley.servicemap.remote;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.charley.internalcommon.constant.AmapConfigConstants;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TrackResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:33
 * @ClassName: TrackClient
 * @Version 1.0
 * @Description: 远程调用轨迹服务客户端
 */
@Service
@Slf4j
public class TrackClient {

    @Value(value = "${amap.key}")
    private String amapKey;

    @Value(value = "${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: tid
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.reponese.TrackResponse>
     * @Date: 2023/2/27 0:34
     * @Description: 创建轨迹
     */
    public ResponseResult<TrackResponse> add(String tid) {

        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TRACK_ADD);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&sid=" + amapSid);
        url.append("&tid=" + tid);

        log.info("创建轨迹的请求： " + url.toString());

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url.toString(), null, String.class);

        String body = stringResponseEntity.getBody();
        log.info("创建轨迹的响应： " + body);
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        // 轨迹 id 轨迹名称
        String trid = data.getString("trid");

        String trname = "";
        if (data.has("trname")) trname = StringUtils.isEmpty(data.getString("trname")) ? "" : data.getString("trname");

        TrackResponse trackResponse = new TrackResponse();
        trackResponse.setTrid(trid);
        trackResponse.setTrname(trname);


        return ResponseResult.success(trackResponse);
    }
}
