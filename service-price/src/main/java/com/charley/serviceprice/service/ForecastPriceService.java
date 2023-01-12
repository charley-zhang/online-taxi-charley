package com.charley.serviceprice.service;


import com.charley.internalcommon.constant.CommonStatusEnum;
import com.charley.internalcommon.dto.PriceRule;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.DirectionResponse;
import com.charley.internalcommon.reponese.ForecastPriceResponse;
import com.charley.internalcommon.request.ForecastPriceDTO;
import com.charley.internalcommon.util.BigDecimalUtils;
import com.charley.serviceprice.mapper.PriceRuleMapper;
import com.charley.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;


    public ResponseResult forecastPrice(String depLongitude, String depLatiude, String destLongitude, String destLatiude){

        log.info("调用地图服务，查询距离和时长");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatiude(depLatiude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatiude(destLatiude);

        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离: "+ distance + " 时长: " + duration);

        log.info("读取计价规则");
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("city_code","110000");
        queryMap.put("vehicle_type","1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);

        if (priceRules.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }

        PriceRule priceRule = priceRules.get(0);

        log.info("根据距离和时长和计价规则计算价格");

        double price = getPrice(distance, duration, priceRule);

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);
    }


    /**
     * 根据距离和时长计算最终价格
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return
     */
    private double getPrice(Integer distance, Integer duration, PriceRule priceRule){

        double price = 0;

        // 起步价
        Double startFare = priceRule.getStartFare();
        price = BigDecimalUtils.add(price, startFare);

        // 里程费
        // 总里程  单位: 米
        double distanceMile = BigDecimalUtils.divide(distance, 1000);

        // 起步里程
        double startMile = (double)priceRule.getStartMile();
        double distanceSubtract = BigDecimalUtils.substract(distanceMile, startMile);

        // 最终收费的里程费  km
        double mile = distanceSubtract < 0 ? 0 : distanceSubtract;

        // 计程单价  元/km
        double unitPricePerMile = priceRule.getUnitPricePerMile();

        // 里程价格
        double mileFare = BigDecimalUtils.multiply(mile, unitPricePerMile);
        price = BigDecimalUtils.add(price, mileFare);

        // 时长费
        // 时长的分钟数
        double timeMinute = BigDecimalUtils.divide(duration, 60);
        // 计时单价
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();

        double timeFare = BigDecimalUtils.multiply(timeMinute, unitPricePerMinute);

        price = BigDecimalUtils.add(price, timeFare);

        BigDecimal priceBigDecimal = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);

        return priceBigDecimal.doubleValue();
    }

//    public static void main(String[] args) {
//        PriceRule priceRule = new PriceRule();
//        priceRule.setUnitPricePerMile(1.8);
//        priceRule.setUnitPricePerMinute(0.5);
//        priceRule.setStartFare(10.0);
//        priceRule.setStartMile(3);
//
//        System.out.println(getPrice(6500, 1800, priceRule));
//    }
}
