package com.charley.servicessepush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:51
 * @ClassName: ServiceSsePushApplication
 * @Version 1.0
 * @Description:  service-sse-push 启动服务
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceSsePushApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSsePushApplication.class, args);
    }
}
