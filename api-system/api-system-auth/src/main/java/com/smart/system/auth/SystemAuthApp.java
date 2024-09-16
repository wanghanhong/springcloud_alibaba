package com.smart.system.auth;

import com.smart.system.common.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 系统授权中心
 * @author 三多
 * @Time 2020/5/27
 */
@EnableRyFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,scanBasePackages = {"com.smart"})
public class SystemAuthApp {
    public static void main(String[] args) {
        SpringApplication.run(SystemAuthApp.class,args);
    }
}
