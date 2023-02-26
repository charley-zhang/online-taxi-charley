package com.charley.servicepassengeruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:55
 * @ClassName: ServicePassengerUserApplication
 * @Version 1.0
 * @Description: service-passenger-user 启动服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.charley.servicepassengeruser.mapper")
public class ServicePassengerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePassengerUserApplication.class, args);
    }
}
