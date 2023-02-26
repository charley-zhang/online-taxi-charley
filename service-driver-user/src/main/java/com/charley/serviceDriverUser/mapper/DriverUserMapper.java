package com.charley.serviceDriverUser.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charley.internalcommon.dto.DriverUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:11
 * @ClassName: DriverUserMapper
 * @Version 1.0
 * @Description:  与 数据库driver_user 链接的工具类
 */
@Repository
public interface DriverUserMapper extends BaseMapper<DriverUser> {

    public int selectDriverUserCountByCityCode(@Param("cityCode") String cityCode);
}
