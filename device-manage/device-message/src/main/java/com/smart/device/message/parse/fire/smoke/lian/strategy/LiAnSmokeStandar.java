package com.smart.device.message.parse.fire.smoke.lian.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.parse.fire.smoke.lian.constant.LiAnSmokeEnum;
import com.smart.device.message.parse.fire.smoke.lian.constant.LiAnSmokeMapConstant;
import com.smart.device.message.parse.fire.smoke.lian.entity.LiAnSmokeEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class LiAnSmokeStandar {

    /**
     * {"upPacketSN":-1,"upDataSN":-1,"topic":"v1/up/ad19prof","timestamp":1606982320277,"tenantId":"10461180","serviceId":"","protocol":"lwm2m","productId":"15014772","payload":{"serviceId":"notification","serviceData":{"TData":30,"SNR":1,"RSRP":114,"IMEI":"860374051096202","Cell_ID_Length":8,"Cell_ID":"38848339","CSQ":5,"Battery_Level":100,"Alarm_Status":2,"AlarmLevel":100}},"messageType":"dataReport","deviceType":"","deviceId":"acf7baaee65e431eadfb8f5fe50e8b81","assocAssetId":"","IMSI":"undefined","IMEI":"860374051096202"}
     */
    public TAlarmSmoke changeToAlarm(LiAnSmokeEntity vo) {
        TAlarmSmoke alarm = new TAlarmSmoke();
        alarm.setImei(Long.parseLong(vo.getIMEI()));
        // 报警时间
        try {
            alarm.setEventTime(DateUtils.covertToLocalDateTime(Long.parseLong(vo.getTimestamp())));
        }catch (Exception e){
            e.printStackTrace();
        }
        alarm.setEventCode(vo.getAlarm_Status());
        if(vo.getAlarm_Status() != null){
            alarm.setType(Integer.parseInt(vo.getAlarm_Status()));
        }
        // 报警内容
        ConcurrentHashMap<String, String> map = LiAnSmokeMapConstant.DICT_MAP.get(LiAnSmokeEnum.Alarm_Status);
        if(map != null && vo.getAlarm_Status() != null){
            alarm.setContent(map.get(vo.getAlarm_Status()));
        }
        // 初始化值
        // 未处理
        alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
        alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
        // 报警等级 1 正常 2 一般
        alarm.setLevel(DeviceConstant.LEVEL_JUST);
        alarm.setCreateTime(LocalDateTime.now());
        log.info("ctwing-力安NB烟感-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        return alarm;
    }

    public THeartSmoke changeToHeart(LiAnSmokeEntity vo) {
        THeartSmoke heart = new THeartSmoke();
        /**
         * "payload":{"serviceId":"notification","serviceData":
         * {"TData":30,"SNR":1,"RSRP":114,"IMEI":"860374051096202",
         * "Cell_ID_Length":8,"Cell_ID":"38848339","CSQ":5,"Battery_Level":100,
         * "Alarm_Status":2,"AlarmLevel":100}}
         */
        heart.setImei(Long.parseLong(vo.getIMEI()));
        heart.setHeartTime(LocalDateTime.now());
        if(StringUtils.isNotBlank(vo.getRSRP())){
            heart.setSignalStrength(Integer.parseInt(vo.getRSRP()));
        }
        heart.setProtocol(vo.getProtocol());
        if(StringUtils.isNotBlank(vo.getBattery_Level())){
            heart.setElectricQuantity(Float.parseFloat(vo.getBattery_Level()));
        }
        heart.setEventCode(vo.getAlarm_Status());
        if(vo.getAlarm_Status() != null){
            heart.setType(Integer.parseInt(vo.getAlarm_Status()));
        }
        // 报警内容
        ConcurrentHashMap<String, String> map = LiAnSmokeMapConstant.DICT_MAP.get(LiAnSmokeEnum.Alarm_Status);
        if(map != null && vo.getAlarm_Status() != null ){
            heart.setContent(map.get(vo.getAlarm_Status()));
        }

        heart.setCreateTime(LocalDateTime.now());
        log.info("ctwing-力安NB烟感-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
        return heart;

    }
}
