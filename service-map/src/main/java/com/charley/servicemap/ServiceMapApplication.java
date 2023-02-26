package com.charley.servicemap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:49
 * @ClassName: ServiceMapApplication
 * @Version 1.0
 * @Description: service-map 启动服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.charley.servicemap.mapper")
public class ServiceMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMapApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
