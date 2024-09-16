package com.smart.device.message.parse.fire.gas.wanlin.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.firehost.constant.FirehostEnum;
import com.smart.device.message.parse.fire.gas.constant.GasEnum;
import com.smart.device.message.parse.fire.gas.constant.GasMapConstant;
import com.smart.device.message.parse.fire.gas.wanlin.entity.GasEntity;
import com.smart.device.message.parse.fire.smoke.wanlinnb3011.Wlnb3011Enum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WanlinGasParseStrategy implements ParseStrategy<GasEntity> {

    /**
     * /**
     *
     * {"deviceCode":"361000000030000088","deviceType":"361","deviceStatus":"2","netStatus":"1","time":1604486520,"content":"机械手状态 已关闭","detail":{"event":"0009","state":"2","signal_intensity":"5","iccid":"","client_id":"7f0000011070000010da"}}
     *
     * 将万林数据 整合为万林对象
     *
     * @param param
     * @return
     */
    @Override
    public GasEntity assemblyData(String param) {
        JSONObject obj = JSONObject.parseObject(param);
        GasEntity entity = new GasEntity();

        String deviceCode = obj.getString(GasMapConstant.deviceCode);
        entity.setDeviceCode(deviceCode);

        entity.setHandState(obj.getInteger(GasMapConstant.STATE));
        entity.setEventdate(obj.getLongValue(GasMapConstant.EVENTDATE));
        //entity.setContent(obj.getString(GasMapConstant.CONTENT));

        try {
            JSONObject detail = obj.getJSONObject(FirehostEnum.detail.name());

            entity.setEvent(detail.getString(GasMapConstant.EVENT));
            //entity.setSignalIntensity(detail.getString(GasMapConstant.SIGNAL_INTENSITY));
            if(detail.get(GasMapConstant.EVENT) != null){
                entity.setEventString(String.valueOf(GasMapConstant.GAS_MAP.get(GasEnum.EVENT).get(detail.get(GasMapConstant.EVENT))));
            }
            if(detail.get(GasMapConstant.STATE) != null){
                entity.setEventString(String.valueOf(GasMapConstant.GAS_MAP.get(GasEnum.STATE).get(detail.get(GasMapConstant.STATE))));
            }
            //entity.setClientId(detail.getString(GasMapConstant.CLIENT_ID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("万林气感-参数接收" + JSONObject.toJSONString(entity));
        return entity;
    }

    public static void main(String[] args) {
        String deviceCode = "311865815040244829";

        String str = deviceCode.substring(3,deviceCode.length());

        String key = GasMapConstant.EVENT;
        Object valueOf = GasMapConstant.GAS_MAP.get(GasEnum.EVENT).get("0009");

        System.out.println(valueOf);
    }

}
