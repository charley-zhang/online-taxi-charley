package com.charley.sseDriverClientWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class SseDriverClientWeb {
    public static void main(String[] args) {
        SpringApplication.run(SseDriverClientWeb.class, args);
    }
}
