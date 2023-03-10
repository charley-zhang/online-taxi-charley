package com.charley.apidriver.interceptor;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.charley.internalcommon.constant.TokenConstants;
import com.charley.internalcommon.dto.ResponseResult;
import com.charley.internalcommon.dto.TokenResult;
import com.charley.internalcommon.util.JwtUtils;
import com.charley.internalcommon.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:36
 * @ClassName: JWTInterceptor
 * @Version 1.0
 * @Description: token校验
 */
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @Author: Charley_Zhang
     * @MethodName: preHandle
     * @param: request
     * @param: response
     * @param: handler
     * @paramType [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object]
     * @return: boolean
     * @Date: 2023/2/26 23:36  根据用户请求所携带的token进行校验
     * @Description:
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;

        String resutltString = "";

        String token = request.getHeader("Authorization");

        // 解析token
        TokenResult tokenResult = JwtUtils.checkToken(token);

        if (tokenResult == null) {
            resutltString = "access token invalid";
            result = false;
        } else {
            // 拼接 key
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();

            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);

            // 从redis中取出token
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);

            if ((StringUtils.isBlank(tokenRedis)) || (!token.trim().equals(tokenRedis.trim()))) {
                resutltString = "access token invalid";
                result = false;
            }
        }


        if (!result) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resutltString)).toString());
        }

        return true;
    }
}
