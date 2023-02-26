package com.charley.apidriver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:46
 * @ClassName: ApiDriverApplication
 * @Version 1.0
 * @Description: api-driver 启动服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApiDriverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiDriverApplication.class, args);
    }
}
