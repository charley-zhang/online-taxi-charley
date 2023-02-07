package com.charley.serviceprice.controller;




import com.charley.internalcommon.dto.PriceRule;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.serviceprice.service.PriceRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody PriceRule priceRule){
        log.info("priceRule : "+priceRule.toString());
        return priceRuleService.add(priceRule);
    }

    @PostMapping(value = "/edit")
    public ResponseResult edit(@RequestBody PriceRule priceRule){
        log.info("priceRule : "+priceRule.toString());
        return priceRuleService.edit(priceRule);
    }
}

