package com.charley.serviceDriverUser.mapper;

import com.charley.internalcommon.dto.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:09
 * @ClassName: CarMapper
 * @Version 1.0
 * @Description: 与 数据库car 链接的工具类
 */
@Repository
public interface CarMapper extends BaseMapper<Car> {

}
