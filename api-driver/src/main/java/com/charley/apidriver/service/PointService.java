package com.charley.apidriver.service;

import com.charley.apidriver.remote.ServiceDriverUserClient;
import com.charley.apidriver.remote.ServiceMapClient;
import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.ApiDriverPointRequest;
import com.charley.internalcommon.request.PointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:43
 * @ClassName: PointService
 * @Version 1.0
 * @Description: 车辆位置服务
 */
@Service
public class PointService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceMapClient serviceMapClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: upload
     * @param: apiDriverPointRequest
     * @paramType [com.charley.internalcommon.request.ApiDriverPointRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/26 23:43
     * @Description: 长传车辆经纬度信息
     */
    public ResponseResult upload(ApiDriverPointRequest apiDriverPointRequest) {
        // 获取caiId
        Long carId = apiDriverPointRequest.getCarId();

        // 通过carId 获取 tid  trid， 调用 service-driver-user的接口
        ResponseResult<Car> carById = serviceDriverUserClient.getCarById(carId);
        Car car = carById.getData();
        String tid = car.getTid();
        String trid = car.getTrid();

        // 调用地图上传
        PointRequest pointRequest = new PointRequest();
        pointRequest.setTid(tid);
        pointRequest.setTrid(trid);
        pointRequest.setPoints(apiDriverPointRequest.getPoints());

        return serviceMapClient.upload(pointRequest);
    }
}
