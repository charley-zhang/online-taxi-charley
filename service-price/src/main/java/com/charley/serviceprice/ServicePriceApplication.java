package com.charley.serviceprice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 1:05
 * @ClassName: ServicePriceApplication
 * @Version 1.0
 * @Description: service-price  启动服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(value = "com.charley.serviceprice.mapper")
public class ServicePriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePriceApplication.class, args);
    }

}
