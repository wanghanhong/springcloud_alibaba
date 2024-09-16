package com.smart.message.manage;


import com.smart.message.manage.mq.consumer.BroadcastConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 消息模块
 * @author 三多
 * @Time 2020/4/28
 */


@SpringBootApplication(scanBasePackages = {"com.smart"})
@EnableDiscoveryClient
@EnableFeignClients
public class MessageApplication implements CommandLineRunner {

    @Autowired
    BroadcastConsumer broadcastConsumer;

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class,args);
    }

    //实现CommandLineRunner 重写run方法
    @Override
    public void run(String... args) throws Exception {
        broadcastConsumer.start();
    }

}
