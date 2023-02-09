package com.charley.serviceprice.controller;




import com.charley.internalcommon.dto.PriceRule;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceprice.service.PriceRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (PriceRule)表控制层
 *
 * @author makejava
 * @since 2023-01-31 14:26:24
 */
@RestController
@RequestMapping("/price-rule")
@Slf4j
public class PriceRuleController {

    @Autowired
    private PriceRuleService priceRuleService;

    /**
     * 添加计价规则
     * @param priceRule
     * @return
     */
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody PriceRule priceRule){
        log.info("priceRule : "+priceRule.toString());
        return priceRuleService.add(priceRule);
    }

    /**
     * 修改计价规则
     * @param priceRule
     * @return
     */
    @PostMapping(value = "/edit")
    public ResponseResult edit(@RequestBody PriceRule priceRule){
        log.info("priceRule : "+priceRule.toString());
        return priceRuleService.edit(priceRule);
    }

    /**
     * 查询最新版本的计价规则
     * @param fareType
     * @return
     */
    @GetMapping(value = "/get-newest-version")
    public ResponseResult<PriceRule> getNewestVersion(@RequestParam String fareType){
        return priceRuleService.getNewestVersion(fareType);
    }

    /**
     * 判断当前的计价规则是否是最新的
     * @param fareType
     * @param fareVersion
     * @return
     */
    @GetMapping(value = "/is-new")
    public ResponseResult<Boolean> isNew(@RequestParam String fareType, @RequestParam Integer fareVersion){
        return priceRuleService.isNew(fareType, fareVersion);
    }

    /**
     * 根据城市编码和车型查询计价规则是否存在
     * @param priceRule
     * @return
     */
    @PostMapping(value = "/if-exists")
    public ResponseResult<Boolean> ifExists(@RequestBody PriceRule priceRule){
        return priceRuleService.ifExists(priceRule);
    }
}

