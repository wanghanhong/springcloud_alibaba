package com.smart.device.message.parse.fire.electric.wanlin.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmElectric;
import com.smart.device.common.message.entity.THeartElectric;
import com.smart.device.message.common.utils.DataUtils;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.parse.fire.electric.wanlin.constant.ElectricWanlinEnum;
import com.smart.device.message.parse.fire.electric.wanlin.constant.ElectricWanlinMapConstant;
import com.smart.device.message.parse.fire.electric.wanlin.entity.ElectricWanlinEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class ElectricStandar {

    /**
     * /**
     * 将 万林对接数据对接为标准数据
     *
     * @param
     * @return
     */
    public TAlarmElectric changeToAlarm(ElectricWanlinEntity vo) {
        // 设备号
        TAlarmElectric alarm = new TAlarmElectric();
        if (ElectricWanlinMapConstant.TYPE_EVENT.equals(vo.getType())) {
            alarm.setImei(Long.parseLong(vo.getChipcode()));
            // 报警时间
            alarm.setEventTime(DateUtils.covertLongToLocalDateTime(vo.getEventdate()));
            alarm.setEventCode(vo.getEvent());
            // 报警内容
            alarm.setContent(ElectricWanlinMapConstant.WANLIN_MAP.get(ElectricWanlinEnum.EVENT).get(vo.getEvent()));
            // 初始化值// 未处理
            alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
            alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
            // 报警等级 1 一般 2 严重
            alarm.setLevel(DeviceConstant.LEVEL_JUST);
            alarm.setCreateTime(LocalDateTime.now());
            log.info("万林智慧用电-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        }
        return alarm;
    }

    public THeartElectric changeToHeart(ElectricWanlinEntity vo) {
        THeartElectric heart = new THeartElectric();
        if (ElectricWanlinMapConstant.TYPE_HEARTBEAT.equals(vo.getType())) {
            heart.setImei(Long.parseLong(vo.getChipcode()));
            heart.setSignalStrength(vo.getSignalIntensity());
            // 报警时间
            heart.setHeartTime(DateUtils.covertLongToLocalDateTime(vo.getEventdate()));
            if(DataUtils.isDouble(vo.getCurrent1())){
                heart.setCurrentA(Float.parseFloat(vo.getCurrent1()));
            }
            if(DataUtils.isDouble(vo.getCurrent2())){
                heart.setCurrentB(Float.parseFloat(vo.getCurrent2()));
            }
            if(DataUtils.isDouble(vo.getCurrent3())){
                heart.setCurrentC(Float.parseFloat(vo.getCurrent3()));
            }
            if(DataUtils.isDouble(vo.getCurrent4())){
                heart.setCurrentN(Float.parseFloat(vo.getCurrent4()));
            }
            if(DataUtils.isDouble(vo.getTemperature1())){
                heart.setTemperatureA(Float.parseFloat(vo.getTemperature1()));
            }
            if(DataUtils.isDouble(vo.getTemperature2())){
                heart.setTemperatureB(Float.parseFloat(vo.getTemperature2()));
            }
            if(DataUtils.isDouble(vo.getTemperature3())){
                heart.setTemperatureC(Float.parseFloat(vo.getTemperature3()));
            }
            if(DataUtils.isDouble(vo.getTemperature4())){
                heart.setTemperatureN(Float.parseFloat(vo.getTemperature4()));
            }
            heart.setCreateTime(LocalDateTime.now());
            log.info("万林智慧用电-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
        }
        return heart;
    }

    //***********将获取到的设备基础信息复制给报警的表********开始***********
    public void changeAlarmAttri(TAlarmElectric alarm, DeviceBaseVo vo) {
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
    public void changeHeartAttri(THeartElectric heart, DeviceBaseVo vo) {
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
