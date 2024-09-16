package com.smart.device.message.parse.fire.firehost.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.firehost.constant.FirehostEnum;
import com.smart.device.message.parse.fire.firehost.entity.FirehostEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FirehostParseStrategy implements ParseStrategy<FirehostEntity> {

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
    public FirehostEntity assemblyData(String param) {
        JSONObject obj = JSONObject.parseObject(param);
        FirehostEntity entity = new FirehostEntity();

        entity.setClientId(obj.getString(FirehostEnum.client_id.name()));
        entity.setAddress(obj.getString(FirehostEnum.address.name()));

        entity.setDeviceCode(obj.getString(FirehostEnum.deviceCode.name()));

        entity.setEventdate(obj.getLongValue(FirehostEnum.eventdate.name()));
        entity.setContent(obj.getString(FirehostEnum.content.name()));

        JSONObject detail = obj.getJSONObject(FirehostEnum.detail.name());

        entity.setEvent(detail.getString(FirehostEnum.event.name()));

        log.info("万林气感-参数接收" + JSONObject.toJSONString(entity));
        return entity;
    }

    public static void main(String[] args) {
        String key = FirehostEnum.event.name();

        System.out.println(11);
    }

}
