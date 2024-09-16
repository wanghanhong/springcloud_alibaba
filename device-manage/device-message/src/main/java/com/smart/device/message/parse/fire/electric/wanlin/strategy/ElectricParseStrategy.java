package com.smart.device.message.parse.fire.electric.wanlin.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.message.entity.TAlarmElectric;
import com.smart.device.common.message.entity.THeartElectric;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.electric.wanlin.constant.ElectricWanlinMapConstant;
import com.smart.device.message.parse.fire.electric.wanlin.entity.ElectricWanlinEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 万林 智慧用电
 */
@Component
@Slf4j
public class ElectricParseStrategy implements ParseStrategy<ElectricWanlinEntity> {

    /**
     * String param = "{\"type\":\"0302\",\"chipcode\":\"701904012751507255\",\"signal_intensity\":17,\"current1\":\"0\",\"current2\":\"0_02\",\"current3\":\"0_01\",\"current4\":\"0_04\",\"temperature1\":\"0\",\"temperature2\":\"0\",\"temperature3\":\"0\",\"temperature4\":\"0\",\"client_id\":\"7f0000010bba00001ba9\",\"eventdate\":1589944430,\"event\":1361}" ;
     *
     * @param
     * @return 返回值
     */
    @Override
    public ElectricWanlinEntity assemblyData(String param) {
        JSONObject obj = JSONObject.parseObject(param);

        String type = obj.getString(ElectricWanlinMapConstant.TYPE);
        String chipcode = obj.getString(ElectricWanlinMapConstant.CHIPCODE);

        Integer signalIntensity = obj.getInteger(ElectricWanlinMapConstant.SIGNAL_INTENSITY);
        String current1 = obj.getString(ElectricWanlinMapConstant.CURRENT1);
        String current2 = obj.getString(ElectricWanlinMapConstant.CURRENT2);
        String current3 = obj.getString(ElectricWanlinMapConstant.CURRENT3);
        String current4 = obj.getString(ElectricWanlinMapConstant.CURRENT4);

        String temperature1 = obj.getString(ElectricWanlinMapConstant.TEMPERATURE1);
        String temperature2 = obj.getString(ElectricWanlinMapConstant.TEMPERATURE2);
        String temperature3 = obj.getString(ElectricWanlinMapConstant.TEMPERATURE3);
        String temperature4 = obj.getString(ElectricWanlinMapConstant.TEMPERATURE4);

//      String clientId; //  系统 ID
        Long eventdate = obj.getLong(ElectricWanlinMapConstant.EVENTDATE);
        String event = obj.getString(ElectricWanlinMapConstant.EVENT);

        ElectricWanlinEntity entity = new ElectricWanlinEntity();
        // 报警时间
        entity.setEventdate(eventdate);
        entity.setType(type);
        entity.setChipcode(chipcode);

        entity.setSignalIntensity(signalIntensity);
        entity.setCurrent1(current1);
        entity.setCurrent2(current2);
        entity.setCurrent3(current3);
        entity.setCurrent4(current4);

        entity.setTemperature1(temperature1);
        entity.setTemperature2(temperature2);
        entity.setTemperature3(temperature3);
        entity.setTemperature4(temperature4);

        entity.setEvent(event);
        log.info("万林智慧用电-参数接收" + JSONObject.toJSONString(entity));
        return entity;
    }

    public static void main(String[] args) {
        String data = "{\"type\":\"0301\",\"chipcode\":\"701904012751507255\",\"signal_intensity\":19,\"current1\":\"0\",\"current2\":\"0_02\",\"current3\":\"0_01\",\"current4\":\"0_04\",\"temperature1\":\"0\",\"temperature2\":\"0\",\"temperature3\":\"0\",\"temperature4\":\"0\",\"client_id\":\"7f0000010bba00001ba9\",\"eventdate\":1589944732,\"event\":\"1361\"}\n";

        ElectricParseStrategy strategy = new ElectricParseStrategy();
        ElectricWanlinEntity e = strategy.assemblyData(data);
        ElectricStandar pressureStandar = new ElectricStandar();
        TAlarmElectric a = pressureStandar.changeToAlarm(e);
        THeartElectric log = pressureStandar.changeToHeart(e);
    }

}
