package com.smart.device.message;

import com.smart.device.message.ws.server.WSServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author l
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.smart.device.message",
        "com.smart.device.common",
        "com.smart.common"})
public class DeviceMessageApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(DeviceMessageApplication.class, args);
    }

    //实现CommandLineRunner 重写run方法
    @Override
    public void run(String... args) throws Exception {
        WSServer.WSServer();
    }

}
