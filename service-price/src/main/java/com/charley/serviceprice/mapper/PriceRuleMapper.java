package com.charley.serviceprice.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charley.internalcommon.dto.PriceRule;
import org.springframework.stereotype.Repository;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:58
 * @ClassName: PriceRuleMapper
 * @Version 1.0
 * @Description:  与 price_rule 的交互
 */
@Repository
public interface PriceRuleMapper extends BaseMapper<PriceRule> {
}
