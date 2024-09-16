package com.smart.device.message.parse.fire.smoke.wanlinnb306.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.smoke.wanlinnb306.constant.WanlinMapConstant;
import com.smart.device.message.parse.fire.smoke.wanlinnb306.entity.DeviceWanlinEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WanlinSmokeParseStrategy implements ParseStrategy<DeviceWanlinEntity> {

    /**
     * {"notifyType":"deviceDataChanged","deviceId":"e917e013-044b-4f32-8b53-588ef68c185a",
     * "gatewayId":"e917e013-044b-4f32-8b53-588ef68c185a"
     * "service":{"serviceType":"Report","eventTime":"20200401T182505Z","serviceId":"Report"
     * "data":{"devId":"10009305","electricQuantity":100,"devState":0,"temperature":219,"
     * signal":12,"cmdValue":"10","smokescope":0}}}
     * 将万林数据 整合为万林对象
     *
     * @param param
     * @return
     */
    @Override
    public DeviceWanlinEntity assemblyData(String param) {
        JSONObject obj = JSONObject.parseObject(param);

        JSONObject service = obj.getJSONObject(WanlinMapConstant.SERVICE);
        JSONObject data = service.getJSONObject(WanlinMapConstant.DATA);

        DeviceWanlinEntity entity = new DeviceWanlinEntity();

        entity.setDeviceId(String.valueOf(obj.get(WanlinMapConstant.DEVICE_ID)));
        // 报警时间
        entity.setEventTime(String.valueOf(service.get(WanlinMapConstant.EVENT_TIME)));
        entity.setServiceType(String.valueOf(service.get(WanlinMapConstant.SERVICE_TYPE)));

        entity.setElectricQuantity(String.valueOf(data.get(WanlinMapConstant.ELECTRIC_QUANTITY)));
        entity.setDevState(String.valueOf(data.get(WanlinMapConstant.DEV_STATE)));
        // 真正的设备账号
        entity.setDevId(String.valueOf(data.get(WanlinMapConstant.DEV_ID)));

        entity.setTemperature(String.valueOf(data.get(WanlinMapConstant.TEMPERATURE)));
        entity.setSignal(String.valueOf(data.get(WanlinMapConstant.SIGNAL)));
        entity.setCmdValue(String.valueOf(data.get(WanlinMapConstant.CMDVALUE)));
        entity.setSmokeScope(String.valueOf(data.get(WanlinMapConstant.SMOKE_SCOPE)));

        log.info("万林NB烟感-参数接收" + JSONObject.toJSONString(entity));
        return entity;
    }


    public static void main(String[] args) {
        String data = "{\"notifyType\":\"deviceDataChanged\",\"service\":{\"serviceType\":\"Report\",\"data\":{\"devId\":\"10009305\",\"electricQuantity\":100,\"devState\":0,\"temperature\":219,\"signal\":12,\"cmdValue\":\"10\",\"smokescope\":0},\"eventTime\":\"20200401T182505Z\",\"serviceId\":\"Report\"},\"deviceId\":\"e917e013-044b-4f32-8b53-588ef68c185a\",\"gatewayId\":\"e917e013-044b-4f32-8b53-588ef68c185a\"}";

        WanlinSmokeParseStrategy strategy = new WanlinSmokeParseStrategy();
        DeviceWanlinEntity deviceEntity = strategy.assemblyData(data);

        WanlinStandard pressureStandar = new WanlinStandard();

    }

}
