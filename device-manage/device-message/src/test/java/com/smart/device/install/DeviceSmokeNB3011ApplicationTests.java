package com.smart.device.install;

import com.smart.device.message.DeviceMessageApplication;
import com.smart.device.message.factory.fire.analysis.service.impl.WLSmokeNB301DeviceParse;
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
public class DeviceSmokeNB3011ApplicationTests {

    @Resource
    private WLSmokeNB301DeviceParse wlSmokeNB301DeviceParse;


    @Test
    public void test2(){
        String param = "{\"notifyType\":\"deviceDataChanged\",\"deviceId\":\"10c60373-7a77-4712-883b-b1aaa31d18cc\",\"gatewayId\":\"10c60373-7a77-4712-883b-b1aaa31d18cc\",\"requestId\":null,\"service\":{\"serviceId\":\"WPS\",\"serviceType\":\"WPS\",\"data\":{\"Data\":\"N311V31I865815040244829H89860317452041182488X10T26U99S25\"},\"eventTime\":\"20200706T055534Z\"}}\n" ;

        wlSmokeNB301DeviceParse.parse(param);

        System.out.println(12);
    }


}
