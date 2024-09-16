package com.smart.test.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 框架demo
 *
 * @author 三多
 * @Time 2020/4/29
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = {"com.smart"})
@EnableDiscoveryClient
@EnableBinding({Source.class, Sink.class})
@EnableAsync
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
   /* @Override
    public void run(String... args) throws Exception {
        BaseEventPublish.recordLog("1","127.0.0.1","登录");
    }

    @Bean
    public BaseEventListener logListener(){
        return new BaseEventListener();
    }*/
}
