package com.smart.message.manage;

import com.smart.message.BaseTestService;
import com.smart.message.manage.common.domain.FebsResponse;
import com.smart.message.manage.entity.YunBaseModel;
import com.smart.message.manage.strategy.SmsPhoneStrategy;
import com.smart.message.manage.strategy.service.SmsStrategyImp;
import org.junit.Test;

import javax.annotation.Resource;

public class DeviceGasApplicationTests extends BaseTestService{

    @Resource
    public SmsPhoneStrategy smsPhoneStrategy;
    @Resource
    public SmsStrategyImp smsStrategyImp;


    @Test
    public void test2(){
        String param = "{\"client_id\":\"7f0000010c8200001a37\",\"dcode\":\"361000000030000088\",\"event\":\"0009\",\"state\":1,\"eventdate\":1594085798,\"content\":\"机械手状态_已打开\",\"Signal_Intensity\":\"13\"}";
        try {
            YunBaseModel yun = new YunBaseModel();
            yun.setPhone("19991932716");
            yun.setSmsParam("黄;点心厨房;11楼");
            yun.setPhoneParam("");
            yun.setStrategyType(1);
            yun.setDeviceCode("19991932716");

            FebsResponse res = smsStrategyImp.yunSmsSend(yun);

        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(12);
    }


}
