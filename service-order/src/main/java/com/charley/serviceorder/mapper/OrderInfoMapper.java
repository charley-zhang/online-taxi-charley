package com.charley.serviceorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charley.internalcommon.dto.OrderInfo;
import org.springframework.stereotype.Repository;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:42
 * @ClassName: OrderInfoMapper
 * @Version 1.0
 * @Description:  与 order_info 链接工具
 */
@Repository
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
}
