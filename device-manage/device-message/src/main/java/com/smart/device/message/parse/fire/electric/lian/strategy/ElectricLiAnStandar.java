package com.smart.device.message.parse.fire.electric.lian.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.common.utils.StringUtils;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmElectric;
import com.smart.device.common.message.entity.THeartElectric;
import com.smart.device.message.common.utils.DataUtils;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.parse.fire.electric.lian.constant.ElectricLiAnEnum;
import com.smart.device.message.parse.fire.electric.lian.constant.ElectricLiAnMapConstant;
import com.smart.device.message.parse.fire.electric.lian.entity.ElectricLiAnEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Slf4j
public class ElectricLiAnStandar {

    /**
     * /**
     * 将 lian对接数据对接为标准数据
     *
     * @param
     * @return
     */
    public TAlarmElectric changeToAlarm(ElectricLiAnEntity vo) {
        // 设备号
        TAlarmElectric alarm = new TAlarmElectric();
        if (ElectricLiAnMapConstant.TYPE_ALARM.equals(String.valueOf(vo.getType()))) {
            alarm.setImei(Long.parseLong(vo.getDeviceId()));
            // 报警时间
            alarm.setEventTime(DateUtils.covertToLocalDateTime(Long.parseLong(vo.getTime())));
            alarm.setEventCode(String.valueOf(vo.getAlarmType()));
            // 报警内容
            alarm.setContent(ElectricLiAnMapConstant.MAP.get(ElectricLiAnEnum.ALARM_TYPE).get(vo.getAlarmType()));
            // 初始化值// 未处理
            alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
            alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
            // 报警等级 1 一般 2 严重
            alarm.setLevel(DeviceConstant.LEVEL_JUST);
            alarm.setCreateTime(LocalDateTime.now());
            log.info("lian智慧用电-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        }
        return alarm;
    }

    public THeartElectric changeToHeart(ElectricLiAnEntity vo) {
        THeartElectric heart = new THeartElectric();
        if (ElectricLiAnMapConstant.TYPE_DATA.equals(String.valueOf(vo.getType()))  ||
                ElectricLiAnMapConstant.TYPE_ONLINE.equals(String.valueOf(vo.getType()))  ) {
            heart.setImei(Long.parseLong(vo.getDeviceId()));
            heart.setHeartTime(DateUtils.covertToLocalDateTime(Long.parseLong(vo.getTime())));
            heart.setEventCode(String.valueOf(vo.getType()));
            // 报警内容
            heart.setContent(ElectricLiAnMapConstant.MAP.get(ElectricLiAnEnum.ALARM_TYPE).get(vo.getType()));
            heart.setCreateTime(LocalDateTime.now());

            if(StringUtils.isNotBlank(vo.getValue())){
                String pointId = vo.getPointId();
                String value = vo.getValue();
                //  10901	漏电电流，单位：mA
                if(pointId.equals("10901")){
                    heart.setCurrentN(Float.parseFloat(value));
                }
                if(pointId.equals("10301")){
                    heart.setVoltageA(Float.parseFloat(value));
                }
                if(pointId.equals("10302")){
                    heart.setVoltageB(Float.parseFloat(value));
                }
                if(pointId.equals("10303")){
                    heart.setVoltageC(Float.parseFloat(value));
                }
                if(pointId.equals("10201")){
                    heart.setCurrentA(Float.parseFloat(value));
                }
                if(pointId.equals("10202")){
                    heart.setCurrentB(Float.parseFloat(value));
                }
                if(pointId.equals("10203")){
                    heart.setCurrentC(Float.parseFloat(value));
                }
                if(pointId.equals("10101")){
                    heart.setTemperatureA(Float.parseFloat(value));
                }
                if(pointId.equals("10102")){
                    heart.setTemperatureB(Float.parseFloat(value));
                }
                if(pointId.equals("10103")){
                    heart.setTemperatureC(Float.parseFloat(value));
                }
                if(pointId.equals("10104")){
                    heart.setTemperatureN(Float.parseFloat(value));
                }
            }
            log.info("lian智慧用电-标准化为数据库心跳日志表" + JSONObject.toJSONString(heart));
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
