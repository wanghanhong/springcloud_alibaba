package com.smart.device.message.parse.fire.firehost.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmFirehost;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.entity.THeartFirehost;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.parse.fire.firehost.entity.FirehostEntity;
import com.smart.device.message.parse.fire.gas.constant.GasMapConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Slf4j
public class FirehostStandard {

    /**
     * 将 万林对接数据对接为标准数据
     *
     * @param
     * @return
     */
    public TAlarmSmoke changeToAlarm(FirehostEntity vo) {
        TAlarmSmoke alarm = new TAlarmSmoke();
        alarm.setImei(Long.parseLong(vo.getDeviceCode())); // 设备号
        alarm.setEventTime(DateUtils.covertLongToLocalDateTime(vo.getEventdate()));

        alarm.setEventCode(vo.getEvent());
        alarm.setType(Integer.parseInt(vo.getEvent()));
        // 报警内容
        alarm.setContent(vo.getContent());
        // 初始化值
        // 未处理
        alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
        alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
        // 报警等级 1 正常 2 一般
        alarm.setLevel(DeviceConstant.LEVEL_JUST);
        alarm.setCreateTime(LocalDateTime.now());
        log.info("万林LORA消防物理网主机-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        return alarm;
    }

    public THeartSmoke changeToHeart(FirehostEntity vo) {
        THeartSmoke heart = new THeartSmoke();
        heart.setImei(Long.parseLong(vo.getDeviceCode()));
        heart.setHeartTime(DateUtils.covertLongToLocalDateTime(vo.getEventdate()));
        heart.setContent(vo.getContent());
        heart.setCreateTime(LocalDateTime.now());
        log.info("万林LORA消防物理网主机-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
        return heart;
    }

    public static void main(String[] args) {
        String data = "{\"iccid\":\"\",\"client_id\":\"7f0000010c1f00000176\",\"dcode\":\"868000000001003196\",\"event\":\"0003\",\"loop\":\"000\",\"address\":\"000\",\"eventdate\":1589871942,\"content\":\"报警_000防区主机防拆\"}";

        FirehostParseStrategy strategy = new FirehostParseStrategy();
        FirehostEntity e = strategy.assemblyData(data);
        FirehostStandard pressureStandar = new FirehostStandard();

    }


}
