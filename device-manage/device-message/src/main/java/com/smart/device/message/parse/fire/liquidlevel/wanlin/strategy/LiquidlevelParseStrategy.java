package com.smart.device.message.parse.fire.liquidlevel.wanlin.strategy;

import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.waterpress.pressure.wanlin.entity.PressureEntity;
import com.smart.device.message.parse.fire.waterpress.pressure.wanlin.strategy.PressureParseStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 万林 压力表
 */
@Component
@Slf4j
public class LiquidlevelParseStrategy implements ParseStrategy<PressureEntity> {

    @Override
    public PressureEntity assemblyData(String dataStr) {
        PressureParseStrategy pressureParseStrategy = new PressureParseStrategy();
        PressureEntity vo = pressureParseStrategy.assemblyData(dataStr);
        return vo;
    }


    public static void main(String[] args) {
        String data = "{\"notifyType\":\"deviceDataChanged\",\"deviceId\":\"b3831fd6-a33d-4a13-b36c-fdf8b5c6333a\",\"gatewayId\":\"b3831fd6-a33d-4a13-b36c-fdf8b5c6333a\",\"requestId\":null,\"service\":{\"serviceId\":\"ReceivePiezoMeter\",\"serviceType\":\"ReceivePiezoMeter\",\"data\":{\"ReceivePiezoMeter\":\"NB900390386772603140168204001000300630NC\"},\"eventTime\":\"20200518T071659Z\"}}";

        LiquidlevelParseStrategy strategy = new LiquidlevelParseStrategy();
        PressureEntity e = strategy.assemblyData(data);
        System.out.println(12);
        LiquidlevelStandar pressureStandar = new LiquidlevelStandar();

        System.out.println(125);
    }
}
