package com.charley.serviceorder.service;


import com.charley.internalcommon.constant.OrderConstants;
import com.charley.internalcommon.dto.OrderInfo;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.OrderRequest;
import com.charley.serviceorder.mapper.OrderInfoMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * (OrderInfo)表服务接口
 *
 * @author makejava
 * @since 2023-01-30 16:57:42
 */
@Service
@Slf4j
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public ResponseResult add(OrderRequest orderRequest) {
        log.info("orderRequest: "+orderRequest.toString());

        OrderInfo orderInfo = new OrderInfo();

        BeanUtils.copyProperties(orderRequest,orderInfo);
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);

        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);

        orderInfoMapper.insert(orderInfo);

        return ResponseResult.success("");
    }
}
