package com.charley.apiBoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:27
 * @ClassName: ApiBossApplication
 * @Version 1.0
 * @Description: api-boss 启动服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApiBossApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiBossApplication.class, args);
    }
}
