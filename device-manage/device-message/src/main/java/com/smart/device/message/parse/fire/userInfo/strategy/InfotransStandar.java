package com.smart.device.message.parse.fire.userInfo.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.message.parse.fire.userInfo.constant.InfotransMapConstant;
import com.smart.device.message.parse.fire.userInfo.entity.InfotransEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class InfotransStandar {

    public TAlarmSmoke changeToAlarm(InfotransEntity vo) {
        TAlarmSmoke alarm = new TAlarmSmoke();
        alarm.setImei(vo.getImei());
        alarm.setDeviceId(Long.parseLong(vo.getDeviceId()));
        //报警时间
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(vo.getTm()));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(format,df);
        alarm.setEventTime(ldt);

        if(vo.getC() != null){
            // 报警
            if(InfotransMapConstant.event_21.equals(vo.getC()) ||
                     InfotransMapConstant.event_22.equals(vo.getC())){
                alarm.setEventCode(InfotransMapConstant.event_code_1);
                alarm.setContent(InfotransMapConstant.event_Map.get(InfotransMapConstant.event_code_1));
            }
            // 故障
            if(InfotransMapConstant.event_23.equals(vo.getC()) ){
                alarm.setEventCode(InfotransMapConstant.event_code_3);
                alarm.setContent(InfotransMapConstant.event_Map.get(InfotransMapConstant.event_code_3));
            }
        }
        // 未处理
        alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
        alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
        // 报警等级 1 正常 2 一般
        alarm.setLevel(DeviceConstant.LEVEL_JUST);
        alarm.setCreateTime(LocalDateTime.now());
        log.info("ctwing力安NB烟感-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        return alarm;
    }

    public THeartSmoke changeToHeart(InfotransEntity vo) {
        THeartSmoke heart = new THeartSmoke();
        heart.setImei(vo.getImei());
        heart.setDeviceId(Long.parseLong(vo.getDeviceId()));
        //报警时间
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(vo.getTm()));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(format,df);
        heart.setHeartTime(ldt);
        heart.setCreateTime(LocalDateTime.now());
        log.info("ctwing力安NB烟感-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
        return heart;

    }
}
