package com.smart.device.message.parse.fire.waterpress.pressure.wanlin.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmWaterpress;
import com.smart.device.common.message.entity.THeartWaterpress;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.parse.fire.waterpress.pressure.constant.PressureEnum;
import com.smart.device.message.parse.fire.waterpress.pressure.constant.PressureMapConstant;
import com.smart.device.message.parse.fire.waterpress.pressure.wanlin.entity.PressureEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@Slf4j
public class PressureStandar {

    /**
     * {"notifyType":"deviceDataChanged","deviceId":"e9d7261b-e1df-4adf-9ad7-0b7c59bc66b2",
     * "gatewayId":"e9d7261b-e1df-4adf-9ad7-0b7c59bc66b2",
     * "requestId":null,"service":{"serviceId":"ReceivePiezoMeter","serviceType":"ReceivePiezoMeter",
     * "data":{"ReceivePiezoMeter":"NB900190186772603140297903000100400000NC"},"eventTime":"20200515T023353Z"}}
     */
    /**
     * 将万林对接数据对接为标准数据
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
        log.info("万林NB无线远传报警数显压力计-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
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
        log.info("万林NB无线远传报警数显压力计-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
        return heart;
    }

    //***********将获取到的设备基础信息复制给报警的表********开始***********
    public void changeAlarmAttri(TAlarmWaterpress alarm, DeviceBaseVo vo) {
        alarm.setDeviceId(vo.getId());
        alarm.setDeviceName(vo.getDeviceName());
        alarm.setDeviceCode(vo.getDeviceCode());
        alarm.setDeviceType(vo.getDeviceType());
        alarm.setDeviceTypeName(vo.getDeviceTypeName());
        alarm.setParentType(vo.getParentType());
        alarm.setDeviceModel(vo.getDeviceModel());

        alarm.setImsi(vo.getImsi());
        alarm.setProductId(vo.getProductId());
        alarm.setProductName(vo.getProductName());
        alarm.setProtocol(vo.getProtocol());
        alarm.setOutFactoryTime(vo.getOutFactoryTime());

        alarm.setScrapTime(vo.getScrapTime());
        alarm.setHardVersion(vo.getHardVersion());
        alarm.setSoftVersion(vo.getSoftVersion());

    }
    public void changeHeartAttri(THeartWaterpress heart, DeviceBaseVo vo) {
        heart.setDeviceId(vo.getId());
        heart.setDeviceName(vo.getDeviceName());
        heart.setDeviceCode(vo.getDeviceCode());
        heart.setDeviceType(vo.getDeviceType());
        heart.setDeviceTypeName(vo.getDeviceTypeName());
        heart.setParentType(vo.getParentType());
        heart.setDeviceModel(vo.getDeviceModel());

        heart.setImsi(vo.getImsi());
        heart.setProductId(vo.getProductId());
        heart.setProductName(vo.getProductName());
        heart.setProtocol(vo.getProtocol());
        heart.setOutFactoryTime(vo.getOutFactoryTime());

        heart.setScrapTime(vo.getScrapTime());
        heart.setHardVersion(vo.getHardVersion());
        heart.setSoftVersion(vo.getSoftVersion());
    }
    //********结束*******************************************************

}
