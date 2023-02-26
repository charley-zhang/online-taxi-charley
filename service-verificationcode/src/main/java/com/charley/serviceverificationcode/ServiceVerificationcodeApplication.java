package com.charley.serviceverificationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 1:06
 * @ClassName: ServiceVerificationcodeApplication
 * @Version 1.0
 * @Description: service-verificationcode 服务启动
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVerificationcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVerificationcodeApplication.class, args);
    }

}
