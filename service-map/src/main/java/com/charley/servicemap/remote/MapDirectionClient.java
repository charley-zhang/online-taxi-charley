package com.charley.servicemap.remote;


import com.charley.internalcommon.constant.AmapConfigConstants;
import com.charley.internalcommon.reponese.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:38
 * @ClassName: MapDirectionClient
 * @Version 1.0
 * @Description: 路径规划远程调用客户端
 */
@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * @Author: Charley_Zhang
     * @MethodName: direction
     * @param: depLongitude
     * @param: depLatiude
     * @param: destLongitude
     * @param: destLatiude
     * @paramType [java.lang.String, java.lang.String, java.lang.String, java.lang.String]
     * @return: com.charley.internalcommon.reponese.DirectionResponse
     * @Date: 2023/2/27 0:39
     * @Description: 根据起点经纬度和终点经纬度获取距离 （米）和时长 （分钟）
     */
    public DirectionResponse direction(String depLongitude, String depLatiude, String destLongitude, String destLatiude) {

        /**
         *  组装请求调用url
         *
         *  https://restapi.amap.com/v3/direction/driving ?
         *  origin=116.481028,39.989643 & destination=116.465302,40.004717 & extensions=all & output=json & key=855fdd30e0365567e41e840f2a05c967
         */

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.DIRECTION_URL);
        urlBuilder.append("?");
        urlBuilder.append("origin=" + depLongitude + "," + depLatiude);
        urlBuilder.append("&");
        urlBuilder.append("destination=" + destLongitude + "," + destLatiude);
        urlBuilder.append("&");
        urlBuilder.append("extensions=base&output=json");
        urlBuilder.append("&");
        urlBuilder.append("key=" + amapKey);

        log.info(urlBuilder.toString());

        // 调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionString = directionEntity.getBody();
        log.info("高德地图路径规划返回信息： " + directionString);

        // 解析接口
        DirectionResponse directionResponse = parseDirectionEntity(directionString);


        return directionResponse;
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: parseDirectionEntity
     * @param: directionString
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.reponese.DirectionResponse
     * @Date: 2023/2/27 0:39
     * @Description: 返回信息的解析
     */
    private DirectionResponse parseDirectionEntity(String directionString) {

        DirectionResponse directionResponse = null;

        try {

            // 最外层
            JSONObject result = JSONObject.fromObject(directionString);

            if (result.has(AmapConfigConstants.STATUS)) {
                int status = result.getInt(AmapConfigConstants.STATUS);
                if (status == 1) {
                    if (result.has(AmapConfigConstants.ROUTE)) {
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray pathArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathObject = pathArray.getJSONObject(0);

                        directionResponse = new DirectionResponse();

                        if (pathObject.has(AmapConfigConstants.DISTANCE)) {
                            int distance = pathObject.getInt(AmapConfigConstants.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if (pathObject.has(AmapConfigConstants.DURATION)) {
                            int duration = pathObject.getInt(AmapConfigConstants.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }

        } catch (Exception e) {

        }

        return directionResponse;
    }
}
