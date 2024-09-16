package com.smart.device.message.parse.fire.gas.wanlin.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.parse.fire.gas.wanlin.entity.GasEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Slf4j
public class WanlinGasStandard {

    /**
     * 将 万林对接数据对接为标准数据
     *
     * @param
     * @return
     */
    public TAlarmSmoke changeToAlarm(GasEntity vo) {
        TAlarmSmoke alarm = new TAlarmSmoke();

        alarm.setImei(Long.parseLong(vo.getDeviceCode()));
        // 设备号
        alarm.setEventTime(DateUtils.covertLongToLocalDateTime(vo.getEventdate()));
        // 报警时间
        alarm.setEventCode(vo.getEvent());
        // 报警内容
        alarm.setContent(vo.getEventString());
        // 初始化值
        alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
        alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
        alarm.setLevel(DeviceConstant.LEVEL_JUST);
        alarm.setCreateTime(LocalDateTime.now());
        log.info("万林NB气感-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        return alarm;
    }

    public THeartSmoke changeToHeart(GasEntity vo) {
        THeartSmoke heart = new THeartSmoke();

        heart.setImei(Long.parseLong(vo.getDeviceCode()));
        try {
            heart.setHandState(vo.getHandState());
            heart.setSignalStrength(Integer.parseInt(vo.getSignalIntensity()));
            // 报警内容
            heart.setContent(vo.getEventString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        heart.setHeartTime(DateUtils.covertLongToLocalDateTime(vo.getEventdate()));
        heart.setCreateTime(LocalDateTime.now());
        log.info("万林NB气感-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
        return heart;
    }

    public static void main(String[] args) {

    }


}
