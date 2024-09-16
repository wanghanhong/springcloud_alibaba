package com.smart.video.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.smart.video","com.smart.common"})
public class VideoManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoManageApplication.class, args);
    }

}
