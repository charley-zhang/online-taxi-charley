package com.charley.apipassenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/26 23:59
 * @ClassName: ApiPassengerApplication
 * @Version 1.0
 * @Description: api-passenger 启动服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApiPassengerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPassengerApplication.class, args);
    }

}
