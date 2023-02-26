package com.charley.serviceDriverUser;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:20
 * @ClassName: ServiceDriverUserApplication
 * @Version 1.0
 * @Description: service-driver-user 启动服务
 */
@SpringBootApplication
@MapperScan(value = "com.charley.serviceDriverUser.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceDriverUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDriverUserApplication.class, args);
    }
}
