package com.smart.device.message.parse.fire.smoke.huaqiang.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.parse.fire.smoke.huaqiang.entity.DeviceEntityVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * USER: gll
 * DATE: 2020/5/12
 * TIME: 14:05
 * Describe:
 */
@Component
@Slf4j
public class HQSmokeStandar {

    /**
     * DeviceEntityVo(type=通用数据包, index=0A, ver=4.2, dev=烟雾监测设备（W308）,
     * manu=华强技术, initiator=00, opt=中国电信, attri=0000000000011011, dataLength=27,
     * encryptType=消息体不加密, time=2020-04-11 10:17:16, imei=860803031745975, cmd=2048,
     * alert=0, devCode=设备正常, devMode=正常模式, devVolt=3.03, devVoltPer=100, devTemp=19,
     * cellId=39154002, rsrp=-89, smokeTime=2020-04-09 18:03:44, selfDetectTime=2020-04-11 10:17:16,
     * selfDetectRet=0, reserve=0)
     */
    public TAlarmSmoke changeToAlarm(DeviceEntityVo vo) {
        TAlarmSmoke alarm = new TAlarmSmoke();
        alarm.setImei(Long.parseLong(vo.getImei()));
        // 报警时间
        alarm.setEventTime(DateUtils.covertTimeStringToLocalDateTime(vo.getTime()));

        alarm.setType(vo.getAlert());
        //设备故障码
        alarm.setContent(vo.getDevCode());

        // 初始化值
        // 未处理
        alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
        alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
        // 报警等级 1 正常 2 一般
        alarm.setLevel(DeviceConstant.LEVEL_JUST);
        alarm.setCreateTime(LocalDateTime.now());
        log.info("ctwing华强NB烟感-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        return alarm;
    }

    public THeartSmoke changeToHeart(DeviceEntityVo vo) {
        THeartSmoke heart = new THeartSmoke();
        /**
         * DeviceEntityVo(type=通用数据包, index=0A, ver=4.2, dev=烟雾监测设备（W308）,
         * manu=华强技术, initiator=00, opt=中国电信, attri=0000000000011011, dataLength=27,
         * encryptType=消息体不加密, time=2020-04-11 10:17:16, imei=860803031745975, cmd=2048,
         * alert=0, devCode=设备正常, devMode=正常模式, devVolt=3.03, devVoltPer=100, devTemp=19,
         * cellId=39154002, rsrp=-89, smokeTime=2020-04-09 18:03:44, selfDetectTime=2020-04-11 10:17:16,
         * selfDetectRet=0, reserve=0)
         */

        heart.setImei(Long.parseLong(vo.getImei()));
        heart.setHeartTime(DateUtils.covertTimeStringToLocalDateTime(vo.getTime()));

        //硬件版本
        heart.setHardVersion(vo.getVer());

        //生产厂商 manu
        //设备电池电压
        heart.setDeviceVolt(vo.getDevVolt());
        //电池电量剩余--百分比
        heart.setElectricQuantity(Float.parseFloat(String.valueOf(vo.getDevVoltPer())));
        if(vo.getDevTemp() != null){
            heart.setTemperature(vo.getDevTemp().floatValue());
        }
        heart.setSignalStrength(vo.getRsrp());
        heart.setSelfDetectTime(DateUtils.covertTimeStringToLocalDateTime(vo.getSelfDetectTime()));
        heart.setSelfDetectRet(String.valueOf(vo.getSelfDetectRet()));
        heart.setCreateTime(LocalDateTime.now());
        log.info("ctwing华强NB烟感-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
        return heart;

    }
}
