package com.charley.servicemap.remote;

import com.charley.internalcommon.constant.AmapConfigConstants;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.ServiceReponese;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:36
 * @ClassName: ServiceClient
 * @Version 1.0
 * @Description: 远程调用高德服务客户端
 */
@Service
@Slf4j
public class ServiceClient {

    @Value("${amap.key}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: name
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:36
     * @Description: 创建服务
     */
    public ResponseResult add(String name) {

        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.SERVICE_ADD_URL);
        url.append("?");
        url.append("key=" + key);
        url.append("&name=" + name);


        log.info(url.toString());

        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = forEntity.getBody();

        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String sid = data.getString("sid");

        ServiceReponese serviceReponese = new ServiceReponese();
        serviceReponese.setSid(sid);

        return ResponseResult.success(serviceReponese);
    }
}
