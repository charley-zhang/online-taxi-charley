package com.charley.serviceorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charley.serviceorder.entity.OrderInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<OrderInfo> {
}
