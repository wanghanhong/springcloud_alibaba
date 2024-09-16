package com.smart.device.message.parse.fire.smoke.wanlinnb3011.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.gas.constant.GasMapConstant;
import com.smart.device.message.parse.fire.smoke.wanlinnb3011.Wlnb3011Entity;
import com.smart.device.message.parse.fire.smoke.wanlinnb3011.Wlnb3011Enum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Wlnb3011ParseStrategy implements ParseStrategy<Wlnb3011Entity> {

    /**
     * /**
     * {"iccid":"","client_id":"7f0000010c1f00000176","dcode":"868000000001003196","event":"0003","loop":"000","address":"000","eventdate":1589871913,"content":"报警_000防区主机防拆"}
     * {"iccid":"","client_id":"7f0000010c1f00000176","dcode":"868000000001003196","event":"0003","loop":"000","address":"251","eventdate":1589871907,"content":"报警_251防区主机SOS"}
     * 将万林数据 整合为万林对象
     *
     * @param param
     * @return
     */
    @Override
    public Wlnb3011Entity assemblyData(String param) {
        JSONObject obj = JSONObject.parseObject(param);
        Wlnb3011Entity entity = new Wlnb3011Entity();

        entity.setClientId(obj.getString(Wlnb3011Enum.client_id.name()));

        String deviceCode = obj.getString(GasMapConstant.deviceCode);
        if(deviceCode != null){
            entity.setDeviceCode(deviceCode.substring(3,deviceCode.length()));
        }

        entity.setEventdate(obj.getLongValue(Wlnb3011Enum.eventdate.name()));
        entity.setContent(obj.getString(Wlnb3011Enum.content.name()));

        JSONObject detail = obj.getJSONObject(Wlnb3011Enum.detail.name());

        entity.setEvent(detail.getString(Wlnb3011Enum.event.name()));


        log.info("万林nb3011-参数接收" + JSONObject.toJSONString(entity));
        return entity;
    }

    public static void main(String[] args) {
        String key = Wlnb3011Enum.event.name();

        System.out.println(11);
    }

}
