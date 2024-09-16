package com.smart.device.install;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.smart.device.install",
        "com.smart.device.common",
        "com.smart.common"})
public class DeviceInstallApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceInstallApplication.class, args);
    }

}
