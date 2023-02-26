package com.charley.serviceverificationcode.controller;


import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.reponese.NumberCodeReponese;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 1:05
 * @ClassName: NumberCodeController
 * @Version 1.0
 * @Description: 验证码控制
 */
@RestController
public class NumberCodeController {


    /**
     * @Author: Charley_Zhang
     * @MethodName: numberCode
     * @param: size
     * @paramType [int]
     * @return: com.charley.internalcommon.dto.ResponseResult
     * @Date: 2023/2/27 1:06
     * @Description: 生成数字验证码
     */
    @GetMapping(value = "/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size) {
        System.out.println("size" + size);
        // 生成验证码
        double mathRandom = (Math.random() * 9 + 1) * (Math.pow(10, size - 1));
        System.out.println((int) mathRandom);

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("code",1);
//        jsonObject.put("message","success");
//        JSONObject object = new JSONObject();
//        object.put("numberCode", (int)mathRandom);
//        jsonObject.put("data", object);
//        return jsonObject.toString();


        // 定义返回值
        NumberCodeReponese numberCodeResponse = new NumberCodeReponese();
        numberCodeResponse.setNumberCode((int) mathRandom);

        return ResponseResult.success(numberCodeResponse);
    }

    public static void main(String[] args) {
        // 获取随机数


    }

}
