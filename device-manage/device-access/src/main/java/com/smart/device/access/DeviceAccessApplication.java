package com.smart.device.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 开启定时任务功能
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.smart"})
public class DeviceAccessApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceAccessApplication.class, args);
    }


}
