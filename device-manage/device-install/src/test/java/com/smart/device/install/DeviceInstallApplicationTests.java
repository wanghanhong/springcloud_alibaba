package com.smart.device.install;

import com.smart.device.install.service.inspection.ITBaseFirefighterService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableDiscoveryClient
@EnableFeignClients
@ContextConfiguration(classes = DeviceInstallApplication.class)
public class DeviceInstallApplicationTests {

    @Resource
    private TBaseFirefighterServiceImpl tBaseFirefighterService;


    @Test
    public void contextLoads() {
//        tBaseFirefighterService.checkUserAndSave("19991932716");
        System.out.println(12);
    }

    public static void main(String [] args){
        float  percentNum = (float)2/3;
        BigDecimal bg = new BigDecimal(percentNum);
        double f1 = bg.setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
        System.out.println(f1);
    }
}
