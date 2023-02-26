package com.charley.servicemap.remote;

import com.charley.internalcommon.constant.AmapConfigConstants;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.PointDTO;
import com.charley.internalcommon.request.PointRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:37
 * @ClassName: PointClient
 * @Version 1.0
 * @Description: 远程调用轨迹点服务客户端
 */
@Service
@Slf4j
public class PointClient {

    @Value(value = "${amap.key}")
    private String amapKey;

    @Value(value = "${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @Author: Charley_Zhang
     * @MethodName: upload
     * @param: pointRequest
     * @paramType [com.charley.internalcommon.request.PointRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:37
     * @Description: 轨迹点上传
     */
    public ResponseResult upload(PointRequest pointRequest) {

        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.POINT_UPLOAD);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&sid=" + amapSid);
        url.append("&tid=" + pointRequest.getTid());
        url.append("&trid=" + pointRequest.getTrid());
        url.append("&points=");
        PointDTO[] points = pointRequest.getPoints();
        url.append("%5B");
        for (PointDTO p : points) {
            url.append("%7B");
            url.append("%22location%22%3A%22" + p.getLocation() + "%22%2C");
            url.append("%22locatetime%22%3A" + p.getLocatetime());
            url.append("%7D");
        }
        url.append("%5D");

        log.info("上传位置请求url :  " + url.toString());
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(URI.create(url.toString()), null, String.class);
        log.info("上传位置响应 ：  " + stringResponseEntity.getBody());

        return ResponseResult.success("");
    }
}
