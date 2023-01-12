package com.charley.servicemap.remote;


import com.charley.internalcommon.constant.AmapConfigConstants;
import com.charley.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MapDicDistractClient {

    @Value("${amap.key}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;


    public String dicDistrict(String keywords){

        // https://restapi.amap.com/v3/config/district?
        // keywords=北京&
        // subdistrict=2&
        // key=<用户的key>

        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL);
        url.append("?");
        url.append("keywords="+keywords+"&");
        url.append("subdistrict=3&");
        url.append("key="+key);

        log.info(url.toString());

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);

        return forEntity.getBody();
    }
}
