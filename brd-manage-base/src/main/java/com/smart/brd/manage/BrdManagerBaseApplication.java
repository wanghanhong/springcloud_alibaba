package com.smart.brd.manage;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
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
@SpringBootApplication(scanBasePackages = {"com.smart.brd","com.smart.common"})
public class BrdManagerBaseApplication implements CommandLineRunner{

    private static final Logger logger = LoggerFactory.getLogger(BrdManagerBaseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BrdManagerBaseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("项目启动了，执行了方法");
    }
}
