package com.charley.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:34
 * @ClassName: InrerceptorConfig
 * @Version 1.0
 * @Description: 根据token进行拦截
 */
@Configuration
public class InrerceptorConfig implements WebMvcConfigurer {

    @Bean
    public JWTInterceptor jwtInterceptor() {
        return new JWTInterceptor();
    }

    /**
     * @Author: Charley_Zhang
     * @MethodName: addInterceptors
     * @param: registry
     * @paramType [org.springframework.web.servlet.config.annotation.InterceptorRegistry]
     * @return: void
     * @Date: 2023/2/26 23:35
     * @Description: 添加拦截路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())

                // 拦截的路径
                .addPathPatterns("/**")
                // 不拦截的路径
                .excludePathPatterns("/noauthTest")
                .excludePathPatterns("/verification-code")
                .excludePathPatterns("/verification-code-check")
                .excludePathPatterns("/token-refresh")
                .excludePathPatterns("/forecast-price")
                .excludePathPatterns("/test-real-time-order/**");

    }
}
