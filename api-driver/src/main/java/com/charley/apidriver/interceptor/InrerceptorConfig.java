package com.charley.apidriver.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InrerceptorConfig implements WebMvcConfigurer {

    @Bean
    public JWTInterceptor jwtInterceptor(){
        return new JWTInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())

        // 拦截的路径
                .addPathPatterns("/**")
        // 不拦截的路径
                .excludePathPatterns("/noauth")
                .excludePathPatterns("/verification-code")
                .excludePathPatterns("/verification-code-check");

    }
}
