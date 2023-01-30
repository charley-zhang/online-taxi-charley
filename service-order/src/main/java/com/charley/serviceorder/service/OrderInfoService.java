package com.charley.serviceorder.service;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceorder.entity.OrderInfo;
import com.charley.serviceorder.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

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
    private OrderMapper orderMapper;

    public ResponseResult testMapper(OrderInfo orderInfo) {
        log.info(orderInfo.toString());
        orderMapper.insert(orderInfo);
        return ResponseResult.success("");
    }
}
