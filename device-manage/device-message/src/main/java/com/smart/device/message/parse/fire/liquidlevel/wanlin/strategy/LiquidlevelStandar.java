package com.smart.device.message.parse.fire.liquidlevel.wanlin.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmWaterpress;
import com.smart.device.common.message.entity.THeartWaterpress;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.factory.fire.type.ParseTypeEnum;
import com.smart.device.message.parse.fire.liquidlevel.constant.LiquidlevelEnum;
import com.smart.device.message.parse.fire.liquidlevel.constant.LiquidlevelMapConstant;
import com.smart.device.message.parse.fire.waterpress.pressure.constant.PressureEnum;
import com.smart.device.message.parse.fire.waterpress.pressure.constant.PressureMapConstant;
import com.smart.device.message.parse.fire.waterpress.pressure.wanlin.entity.PressureEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Component
@Slf4j
public class LiquidlevelStandar{

    /**
     * 将 万林对接数据对接为标准数据
     *
     * @param
     * @return
     */
    public TAlarmWaterpress changeToAlarm(PressureEntity vo) {
        // 设备号
        TAlarmWaterpress alarm = new TAlarmWaterpress();
        alarm.setImei(Long.parseLong(vo.getImei()));
        // 报警时间
        alarm.setEventTime(DateUtils.covertToLocalDateTime(vo.getEventTime()));
        alarm.setEventCode(vo.getType());
        // 报警内容
        alarm.setContent(PressureMapConstant.WANLIN_MAP.get(PressureEnum.CONTENT).get(vo.getType()));
        // 初始化值// 未处理
        alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
        alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
        // 报警等级 1 一般 2 严重
        alarm.setLevel(DeviceConstant.LEVEL_JUST);
        alarm.setCreateTime(LocalDateTime.now());
        // 报警内容
        alarm.setContent(LiquidlevelMapConstant.WANLIN_MAP.get(LiquidlevelEnum.CONTENT).get(vo.getType()));
        log.info("万林NB无线远传报警数显液位计-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        return alarm;
    }

    public THeartWaterpress changeToTHeart(PressureEntity vo) {
        THeartWaterpress heart = new THeartWaterpress();

        heart.setImei(Long.parseLong(vo.getImei()));
        if (vo.getEventTime() != null) {
            // 报警时间
            heart.setHeartTime(vo.getEventTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }
        try {
            heart.setHighValue(Float.parseFloat(vo.getHightValue())/100);
            heart.setLowValue(Float.parseFloat(vo.getLowValue())/100);
            heart.setPresentValue(Float.parseFloat(vo.getPresentValue())/100);
        }catch (Exception e){
            e.printStackTrace();
        }
        heart.setCreateTime(LocalDateTime.now());
        //设备类型
        heart.setDeviceType(Integer.parseInt(ParseTypeEnum.WL_NB_LIQUIDLEVEL.getCode()));
        log.info("万林NB无线远传报警数显液位计-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
        return heart;
    }

}
