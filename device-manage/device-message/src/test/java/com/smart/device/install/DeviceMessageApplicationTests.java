package com.smart.device.install;

import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.message.DeviceMessageApplication;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.data.service.TStrategyAlarmService;
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
public class DeviceMessageApplicationTests {

    @Resource
    private TStrategyAlarmService tStrategyAlarmService;


//    @Test
//    public void contextLoads() {
//        tStrategyAlarmService.queryStrategyByTypeAndCode(17,"0301");
//    }


    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;

    @Test
    public void test2(){

        DeviceBaseVo vo = new DeviceBaseVo();vo.setImei(786543289734562L);
        DeviceBaseVo result = deviceBaseFeignClient.selectDeviceSmokeByImei(786543289734562L);

        System.out.println(12);
    }


}
