package com.charley.serviceorder.remote;

import com.charley.internalcommon.dto.PriceRule;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.request.PriceRuleIsNewRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:46
 * @ClassName: ServicePriceClient
 * @Version 1.0
 * @Description: 预估价格服务远程调用给客户端
 */
@FeignClient(value = "service-price")
public interface ServicePriceClient {

    /**
     * @Author: Charley_Zhang
     * @MethodName: isNew
     * @param: priceRuleIsNewRequest
     * @paramType [com.charley.internalcommon.request.PriceRuleIsNewRequest]
     * @return: com.charley.internalcommon.dto.ResponseResult<java.lang.Boolean>
     * @Date: 2023/2/27 0:47
     * @Description: 查询计价规则是否是最新版本
     */
    @PostMapping(value = "/price-rule/is-new")
    public ResponseResult<Boolean> isNew(@RequestBody PriceRuleIsNewRequest priceRuleIsNewRequest);

    /**
     * @Author: Charley_Zhang
     * @MethodName: ifPriceExists
     * @param: priceRule
     * @paramType [com.charley.internalcommon.dto.PriceRule]
     * @return: com.charley.internalcommon.dto.ResponseResult<java.lang.Boolean>
     * @Date: 2023/2/27 0:47
     * @Description: 判断计价规则是否存在
     */
    @GetMapping(value = "/price-rule/if-exists")
    public ResponseResult<Boolean> ifPriceExists(@RequestBody PriceRule priceRule);
}
