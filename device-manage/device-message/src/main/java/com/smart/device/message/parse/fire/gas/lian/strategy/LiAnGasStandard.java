package com.smart.device.message.parse.fire.gas.lian.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmGas;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.entity.THeartGas;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.parse.fire.gas.constant.GasEnum;
import com.smart.device.message.parse.fire.gas.constant.GasMapConstant;
import com.smart.device.message.parse.fire.gas.lian.entity.GasLiAnEntity;
import com.smart.device.message.parse.fire.smoke.lian.constant.LiAnSmokeEnum;
import com.smart.device.message.parse.fire.smoke.lian.constant.LiAnSmokeMapConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class LiAnGasStandard {

    public TAlarmSmoke changeToAlarm(GasLiAnEntity vo) {
        TAlarmSmoke alarm = new TAlarmSmoke();
        String deviceCode = vo.getDeviceCode();
        alarm.setImei(Long.parseLong(deviceCode));
        alarm.setDeviceCode(Long.parseLong(deviceCode));
        // 设备号
        LocalDateTime dateTime = LocalDateTime.now();
        alarm.setEventTime(dateTime);
        // 报警时间
        String event = vo.getEvent();
        alarm.setEventCode(event);
        // 报警内容
        String s = String.valueOf(GasMapConstant.GAS_MAP.get(GasEnum.EVENT).get(event));
        alarm.setContent(s);
        // 初始化值
        if(Integer.parseInt(event) != Integer.parseInt(LiAnGasParseStrategy.normal)){
            //报警状态
            alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
            alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
            alarm.setLevel(DeviceConstant.LEVEL_JUST);
            alarm.setCreateTime(LocalDateTime.now());
        }else{
            //正常状态
            alarm.setState(DeviceConstant.DEVICE_STATE_ONLINE);
            alarm.setStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
            alarm.setLevel(DeviceConstant.LEVEL_NORMAL);
            alarm.setCreateTime(LocalDateTime.now());
        }
        log.info("力安NB气感-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        return alarm;
    }

    public THeartSmoke changeToHear(GasLiAnEntity vo) {
        THeartSmoke heart = new THeartSmoke();
        String deviceCode = vo.getDeviceCode();
        heart.setImei(Long.parseLong(deviceCode));
        heart.setDeviceCode(Long.parseLong(deviceCode));
        try {
            String s = String.valueOf(GasMapConstant.GAS_MAP.get(GasEnum.EVENT).get(vo.getEvent()));
            heart.setContent(s);
            // 报警内容
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime dateTime = LocalDateTime.now();
        heart.setHeartTime(dateTime);
        heart.setCreateTime(dateTime);
        log.info("力安NB气感-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
        return heart;
    }
}
