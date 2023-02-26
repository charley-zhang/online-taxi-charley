package com.charley.servicemap.service;


import com.charley.internalcommon.constant.AmapConfigConstants;
import com.charley.internalcommon.constant.CommonStatusEnum;
import com.charley.internalcommon.dto.DicDistrict;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.servicemap.mapper.DicDistrictMapper;
import com.charley.servicemap.remote.MapDicDistractClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:31
 * @ClassName: DicDistrictService
 * @Version 1.0
 * @Description: 地区字典服务
 */
@Service
@Slf4j
public class DicDistrictService {

    @Autowired
    private MapDicDistractClient mapDicDistractClient;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;


    /**
     * @Author: Charley_Zhang
     * @MethodName: initDicDistrict
     * @param: keywords
     * @paramType [java.lang.String]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 0:31
     * @Description: 获取地区字典
     */
    public ResponseResult initDicDistrict(String keywords) {

        // 请求地图
        String dicDistrictResult = mapDicDistractClient.dicDistrict(keywords);
//        log.info(dicDistrictResult);

        // 解析结果
        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJsonObject.getInt(AmapConfigConstants.STATUS);
        if (status != 1) {
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(), CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }

        JSONArray countryJsonArray = dicDistrictJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        for (int c = 0; c < countryJsonArray.size(); c++) {
            JSONObject countryJsonObject = countryJsonArray.getJSONObject(c);

            String countryAddressCode = countryJsonObject.getString(AmapConfigConstants.ADCODE);
            String countryAddressName = countryJsonObject.getString(AmapConfigConstants.NAME);
            String countryParentAddressCode = "0";
            String countryLevel = countryJsonObject.getString(AmapConfigConstants.LEVEL);
            if (countryLevel.equals(AmapConfigConstants.STREET)) {
                continue;
            }

            insertDicDistrict(countryAddressCode, countryAddressName, countryLevel, countryParentAddressCode);

            JSONArray provinceJsonArray = countryJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int p = 0; p < provinceJsonArray.size(); p++) {

                JSONObject provinceJsonObject = provinceJsonArray.getJSONObject(p);

                String provinceAddressCode = provinceJsonObject.getString(AmapConfigConstants.ADCODE);
                String provinceAddressName = provinceJsonObject.getString(AmapConfigConstants.NAME);
                String provinceParentAddressCode = countryAddressCode;
                String provinceLevel = provinceJsonObject.getString(AmapConfigConstants.LEVEL);
                if (provinceLevel.equals(AmapConfigConstants.STREET)) {
                    continue;
                }

                insertDicDistrict(provinceAddressCode, provinceAddressName, provinceLevel, provinceParentAddressCode);

                JSONArray cityArray = provinceJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);

                for (int city = 0; city < cityArray.size(); city++) {

                    JSONObject cityJsonObject = cityArray.getJSONObject(city);

                    String cityAddressCode = cityJsonObject.getString(AmapConfigConstants.ADCODE);
                    String cityAddressName = cityJsonObject.getString(AmapConfigConstants.NAME);
                    String cityParentAddressCode = provinceAddressCode;
                    String cityLevel = cityJsonObject.getString(AmapConfigConstants.LEVEL);

                    if (cityLevel.equals(AmapConfigConstants.STREET)) {
                        continue;
                    }

                    insertDicDistrict(cityAddressCode, cityAddressName, cityLevel, cityParentAddressCode);

                    JSONArray districtArray = cityJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);

                    for (int d = 0; d < districtArray.size(); d++) {

                        JSONObject districtJsonObject = districtArray.getJSONObject(d);

                        String districtAddressCode = districtJsonObject.getString(AmapConfigConstants.ADCODE);
                        String districtAddressName = districtJsonObject.getString(AmapConfigConstants.NAME);
                        String districtParentAddressCode = cityAddressCode;
                        String districtLevel = districtJsonObject.getString(AmapConfigConstants.LEVEL);
                        if (districtLevel.equals(AmapConfigConstants.STREET)) {
                            continue;
                        }

                        insertDicDistrict(districtAddressCode, districtAddressName, districtLevel, districtParentAddressCode);
                    }
                }
            }
        }


        return ResponseResult.success("");
    }


    /**
     * @Author: Charley_Zhang
     * @MethodName: insertDicDistrict
     * @param: addressCode
     * @param: addressName
     * @param: level
     * @param: parentAddressCode
     * @paramType [java.lang.String, java.lang.String, java.lang.String, java.lang.String]
     * @return: void
     * @Date: 2023/2/27 0:32
     * @Description: 插入数据信息 dic_district
     */
    private void insertDicDistrict(String addressCode, String addressName, String level, String parentAddressCode) {
        // 数据库对象
        DicDistrict district = new DicDistrict();
        district.setAddressCode(addressCode);
        district.setAddressName(addressName);
        district.setLevel(generateLevel(level));
        district.setParentAddressCode(parentAddressCode);

        log.info(district.toString());

        // 插入数据库
        dicDistrictMapper.insert(district);
    }

    private int generateLevel(String level) {
        int levelInt = 0;

        switch (level.trim()) {
            case "province":
                levelInt = 1;
                break;
            case "city":
                levelInt = 2;
                break;
            case "district":
                levelInt = 3;
                break;
        }

        return levelInt;
    }


//    public static void main(String[] args) {
//        System.out.println(new DicDistrictService().generateLevel("country"));
//    }

}
