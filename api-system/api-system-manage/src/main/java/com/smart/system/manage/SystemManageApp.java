package com.smart.system.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 三多
 * @Time 2020/6/2
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.smart"})
public class SystemManageApp {
    public static void main(String[] args) {
        SpringApplication.run(SystemManageApp.class,args);
    }

}
