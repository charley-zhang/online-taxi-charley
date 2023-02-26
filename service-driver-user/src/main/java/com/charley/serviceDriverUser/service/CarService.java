package com.charley.serviceDriverUser.service;

import com.charley.internalcommon.dto.Car;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.TerminalResponse;
import com.charley.internalcommon.reponese.TrackResponse;
import com.charley.serviceDriverUser.mapper.CarMapper;
import com.charley.serviceDriverUser.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:13
 * @ClassName: CarService
 * @Version 1.0
 * @Description: 车辆信息服务
 */
@Service
@Slf4j
public class CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    /**
     * @Author: Charley_Zhang
     * @MethodName: addCar
     * @param: car
     * @paramType [com.charley.internalcommon.dto.Car]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:14
     * @Description: 添加车辆
     */
    public ResponseResult addCar(Car car) {
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);

        carMapper.insert(car);

        // 获得车辆 终端id tid
        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo(), car.getId() + "");
        String tid = responseResult.getData().getTid();
        car.setTid(tid);

        // 获得此车辆轨迹id trid
        ResponseResult<TrackResponse> trackResponseResponseResult = serviceMapClient.addTrack(tid);
        String trid = trackResponseResponseResult.getData().getTrid();
        String trname = trackResponseResponseResult.getData().getTrname();

        car.setTrid(trid);
        car.setTrname(trname);

        carMapper.updateById(car);

        return ResponseResult.success("");
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: getCarById
     * @param: id
     * @paramType [java.lang.Long]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.dto.Car>
     * @Date: 2023/2/27 0:14
     * @Description: 根据 carId获取车辆信息
     */
    public ResponseResult<Car> getCarById(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        List<Car> cars = carMapper.selectByMap(map);
        log.info(cars.toString());
        return ResponseResult.success(cars.get(0));
    }
}
