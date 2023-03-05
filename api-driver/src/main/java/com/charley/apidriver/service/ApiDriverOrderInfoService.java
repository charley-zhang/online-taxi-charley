package com.charley.apidriver.service;


import com.charley.apidriver.remote.ServiceOrderClient;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/3/5 17:37
 * @PackageName:com.charley.apidriver.service
 * @ClassName: OrderInfoService
 * @Version 1.0
 * @Description:
 */
@Service
public class ApiDriverOrderInfoService {

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    public ResponseResult toPickUpPassenger(OrderRequest orderRequest) {
        return serviceOrderClient.toPickUpPassenger(orderRequest);
    }

    public ResponseResult arrivedDeparture(OrderRequest orderRequest) {
        return serviceOrderClient.arrivedDeparture(orderRequest);
    }

    public ResponseResult pickUpPassenger(OrderRequest orderRequest) {
        return serviceOrderClient.pickUpPassenger(orderRequest);
    }

    public ResponseResult passengerGetoff(OrderRequest orderRequest) {
        return serviceOrderClient.passengerGetoff(orderRequest);
    }
}
