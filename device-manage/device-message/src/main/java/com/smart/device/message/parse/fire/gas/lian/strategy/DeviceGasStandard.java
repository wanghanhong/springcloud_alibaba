package com.smart.device.message.parse.fire.gas.lian.strategy;

import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.entity.THeartSmoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeviceGasStandard {

    //***********将获取到的设备基础信息复制给报警的表********开始***********
    public void changeAlarmAttri(TAlarmSmoke alarm, DeviceBaseVo vo) {
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
    public void changeHeartAttri(THeartSmoke heart, DeviceBaseVo vo) {
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
