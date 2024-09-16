package com.smart.device.message.parse.fire.smoke.wanlinnb301.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.message.factory.fire.type.ParseTypeEnum;
import com.smart.device.message.parse.fire.smoke.wanlinnb301.constant.NB301Enum;
import com.smart.device.message.parse.fire.smoke.wanlinnb301.constant.WanlinNB301MapConstant;
import com.smart.device.message.parse.fire.smoke.wanlinnb301.entity.WanlinNbEntityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class WanlinNb301Standard {
    /**
     * 将 万林对接数据对接为标准数据
     *
     * @param vo vo
     * @return
     */
    public TAlarmSmoke changeToAlarm(WanlinNbEntityVO vo) {
        // 设备号
        TAlarmSmoke alarm = new TAlarmSmoke();
        alarm.setImei(Long.parseLong(vo.getImei()));
        // 报警时间
        alarm.setEventTime(DateUtils.covertToLocalDateTime(vo.getEventTime()));
        // 报警代码
        alarm.setEventCode(vo.getType());
         // 报警内容
        alarm.setContent(WanlinNB301MapConstant.WANLIN_MAP.get(NB301Enum.TYPE).get(vo.getType()));

        // 初始化值 未处理
        alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
        alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
        // 报警等级 1 一般 2 严重
        alarm.setLevel(DeviceConstant.LEVEL_JUST);
        alarm.setCreateTime(LocalDateTime.now());
        log.info("万林NB烟感-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        return alarm;
    }

    public THeartSmoke changeToHeart(WanlinNbEntityVO vo) {
        THeartSmoke heart = new THeartSmoke();

        heart.setImei(Long.parseLong(vo.getImei()));
        heart.setDeviceVolt(Float.parseFloat(vo.getElectricQuantity()));
//        heart.setDeviceState(vo.getStatus());
        if(vo.getTemperature() != null ){
            heart.setTemperature(Float.parseFloat(vo.getTemperature()));
        }
        heart.setSignalStrength(Integer.parseInt(vo.getSignal()));
        heart.setSmokeScope(null);
        if (vo.getEventTime() != null) {
            // 报警时间
            heart.setHeartTime(DateUtils.covertToLocalDateTime(vo.getEventTime()));
        }
        heart.setContent(WanlinNB301MapConstant.WANLIN_MAP.get(NB301Enum.TYPE).get(vo.getType()));
        heart.setIccid(vo.getIccid());
        heart.setCreateTime(LocalDateTime.now());
        log.info("万林NB烟感-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
        return heart;
    }
}
