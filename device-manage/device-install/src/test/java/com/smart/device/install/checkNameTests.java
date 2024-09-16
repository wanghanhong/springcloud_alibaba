package com.smart.device.install;

import com.smart.device.install.feign.SmokeFirePlatformFeignClient;
import com.smart.device.install.service.inspection.impl.TBaseFirefighterServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableDiscoveryClient
@EnableFeignClients
@ContextConfiguration(classes = DeviceInstallApplication.class)
public class checkNameTests {

    @Resource
    private TBaseFirefighterServiceImpl tBaseFirefighterService;
    @Resource
    private SmokeFirePlatformFeignClient smokeFirePlatformFeignClient;

    @Test
    public void contextLoads() {
        Map<String,Object> map = smokeFirePlatformFeignClient.checkUserName("15029669393");
        String result = String.valueOf(map.get("Data"));
        if("true".equals(result)){

        }
    }

    public static void main(String [] args){

        System.out.println(11);
    }
}
