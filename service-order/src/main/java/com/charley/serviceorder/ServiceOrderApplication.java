package com.charley.serviceorder;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:49
 * @ClassName: ServiceOrderApplication
 * @Version 1.0
 * @Description: service-order 启动服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.charley.serviceorder.mapper")
@EnableFeignClients
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}
