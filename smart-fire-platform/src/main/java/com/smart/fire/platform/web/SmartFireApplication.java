package com.smart.fire.platform.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 智慧消防应用
 * @author 三多
 * @Time 2020/6/8
 */

@RefreshScope
@SpringBootApplication(scanBasePackages = {"com.smart"})
@EnableFeignClients
@EnableDiscoveryClient
public class SmartFireApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartFireApplication.class,args);
    }
}
