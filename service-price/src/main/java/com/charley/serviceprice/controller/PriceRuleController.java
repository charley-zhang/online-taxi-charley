package com.charley.serviceprice.controller;


import com.charley.internalcommon.dto.PriceRule;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.PriceRuleIsNewRequest;
import com.charley.serviceprice.service.PriceRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:56
 * @ClassName: PriceRuleController
 * @Version 1.0
 * @Description: 计价规则控制
 */
@RestController
@RequestMapping("/price-rule")
@Slf4j
public class PriceRuleController {

    @Autowired
    private PriceRuleService priceRuleService;

    /**
     * @Author: Charley_Zhang
     * @MethodName: add
     * @param: priceRule
     * @paramType [com.charley.internalcommon.dto.PriceRule]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:56
     * @Description: 添加计价规则
     */
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody PriceRule priceRule) {
        log.info("priceRule : " + priceRule.toString());
        return priceRuleService.add(priceRule);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: edit
     * @param: priceRule
     * @paramType [com.charley.internalcommon.dto.PriceRule]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:57
     * @Description: 修改计价规则
     */
    @PostMapping(value = "/edit")
    public ResponseResult edit(@RequestBody PriceRule priceRule) {
        log.info("priceRule : " + priceRule.toString());
        return priceRuleService.edit(priceRule);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: getNewestVersion
     * @param: fareType
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult<com.charley.internalcommon.dto.PriceRule>
     * @Date: 2023/2/27 0:57
     * @Description: 查询最新版本的计价规则
     */
    @GetMapping(value = "/get-newest-version")
    public ResponseResult<PriceRule> getNewestVersion(@RequestParam String fareType) {
        return priceRuleService.getNewestVersion(fareType);
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: isNew
     * @param: priceRuleIsNewRequest
     * @paramType [com.charley.internalcommon.request.PriceRuleIsNewRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult<java.lang.Boolean>
     * @Date: 2023/2/27 0:58
     * @Description: 判断当前的计价规则是否是最新的
     */
    @PostMapping(value = "/is-new")
    public ResponseResult<Boolean> isNew(@RequestBody PriceRuleIsNewRequest priceRuleIsNewRequest) {
        return priceRuleService.isNew(priceRuleIsNewRequest.getFareType(), priceRuleIsNewRequest.getFareVersion());
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: ifExists
     * @param: priceRule
     * @paramType [com.charley.internalcommon.dto.PriceRule]
     * @return: com.charley.internalcommon.dto.ResponseResult<java.lang.Boolean>
     * @Date: 2023/2/27 0:58
     * @Description: 根据城市编码和车型查询计价规则是否存在
     */
    @PostMapping(value = "/if-exists")
    public ResponseResult<Boolean> ifExists(@RequestBody PriceRule priceRule) {
        return priceRuleService.ifExists(priceRule);
    }
}

