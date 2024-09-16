package com.smart.device.install;

import com.smart.device.message.DeviceMessageApplication;
import com.smart.device.message.factory.fire.analysis.service.impl.ElectricDeviceParse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableDiscoveryClient
@EnableFeignClients
@ContextConfiguration(classes = DeviceMessageApplication.class)
public class DeviceElectricApplicationTests {

    @Resource
    private ElectricDeviceParse electricDeviceParse;


    @Test
    public void test2(){
        String param = "{\"type\":\"0302\",\"chipcode\":\"701904012751507255\",\"event\":\"1361\",\"event_loop\":\"0001\",\"alarmvalue\":\"0\",\"client_id\":\"7f0000010bba00000369\",\"eventdate\":1595988464}\t" ;


        electricDeviceParse.parse(param);

        System.out.println(12);
    }


}
