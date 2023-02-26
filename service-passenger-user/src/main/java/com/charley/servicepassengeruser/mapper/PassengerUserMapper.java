package com.charley.servicepassengeruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charley.internalcommon.dto.PassengerUser;
import org.springframework.stereotype.Repository;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:52
 * @ClassName: PassengerUserMapper
 * @Version 1.0
 * @Description: 与 passenger_user 链接的工具
 */
@Repository
public interface PassengerUserMapper extends BaseMapper<PassengerUser> {
}
