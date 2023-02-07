package com.charley.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.charley.internalcommon.dto.PriceRule;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceprice.mapper.PriceRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (PriceRule)表服务接口
 *
 * @author makejava
 * @since 2023-01-31 14:26:27
 */
@Service
public class PriceRuleService {

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    public ResponseResult add(PriceRule priceRule) {
        // 拼接 fareType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + vehicleType;
        priceRule.setFareType(fareType);

        // 添加版本号
        Map<String, Object> map = new HashMap<>();
        map.put("city_code", cityCode);
        map.put("vehicle_type", vehicleType);

        /**
         * 问题1： 增加了版本号，前面两个字段无法唯一的确定一条记录
         * 问题2： 找最大的版本号，需要排序
         */
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);

        Integer fareVersion = 0;
        if (priceRules.size() > 0){
            fareVersion = priceRules.get(0).getFareVersion();
        }

        priceRule.setFareVersion(++fareVersion);

        priceRuleMapper.insert(priceRule);
        return ResponseResult.success("");
    }
}
