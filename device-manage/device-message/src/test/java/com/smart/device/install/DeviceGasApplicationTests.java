package com.smart.device.install;

import com.smart.device.message.DeviceMessageApplication;
import com.smart.device.message.factory.fire.analysis.service.impl.GasDeviceParse;
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
public class DeviceGasApplicationTests {

    @Resource
    private GasDeviceParse gasDeviceParse;


    @Test
    public void test2(){
        String param = "{\"client_id\":\"7f0000010c8200001a37\",\"dcode\":\"361000000030000088\",\"event\":\"0003\",\"state\":1,\"eventdate\":1594085798,\"content\":\"机械手状态_已打开\",\"Signal_Intensity\":\"13\"}";

        gasDeviceParse.parse(param);

        System.out.println(12);
    }


}
