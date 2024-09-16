package com.smart.publicize.manage;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 17:38
 * Describe:
 *
 * @author l
 */
@SpringBootApplication(scanBasePackages = {"com.smart"})
@MapperScan({"com.smart.publicize.manage.mapper"})
@Import(FdfsClientConfig.class)
@EnableDiscoveryClient
public class PublicizeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PublicizeApplication.class, args);
    }
}
