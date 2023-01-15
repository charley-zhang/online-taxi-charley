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

@Service
@Slf4j
public class PointClient {

    @Value(value = "${amap.key}")
    private String amapKey;

    @Value(value = "${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult upload(PointRequest pointRequest){

        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.POINT_UPLOAD);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&sid="+amapSid);
        url.append("&tid="+pointRequest.getTid());
        url.append("&trid="+pointRequest.getTrid());
        url.append("&points=");
        PointDTO[] points = pointRequest.getPoints();
        url.append("%5B");
        for (PointDTO p : points) {
            url.append("%7B");
            url.append("%22location%22%3A%22"+p.getLocation()+"%22%2C");
            url.append("%22locatetime%22%3A"+p.getLocatetime());
            url.append("%7D");
        }
        url.append("%5D");

        log.info("高德地图请求url :  "+url.toString());

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(URI.create(url.toString()), null, String.class);

        log.info("高德地图响应 ：  "+ stringResponseEntity.getBody());

        String body = stringResponseEntity.getBody();


        return ResponseResult.success("");
    }
}
