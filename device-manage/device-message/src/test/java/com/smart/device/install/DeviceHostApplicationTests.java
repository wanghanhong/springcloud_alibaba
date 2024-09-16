package com.smart.device.install;

import com.smart.device.message.DeviceMessageApplication;
import com.smart.device.message.factory.fire.analysis.service.impl.FirehostParse;
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
public class DeviceHostApplicationTests {

    @Resource
    private FirehostParse firehostParse;


    @Test
    public void test2(){
        String param = "{\"iccid\":\"\",\"client_id\":\"7f0000010c1f00000011\",\"dcode\":\"868000000001003196\",\"event\":\"0003\",\"loop\":\"000\",\"address\":\"001\",\"eventdate\":1596165936,\"content\":\"报警_001防区烟感报警\"}";

        firehostParse.parse(param);

        System.out.println(12);
    }


}
