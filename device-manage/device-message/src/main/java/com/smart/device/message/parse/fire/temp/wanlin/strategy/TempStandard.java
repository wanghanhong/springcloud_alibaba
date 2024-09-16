package com.smart.device.message.parse.fire.temp.wanlin.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.message.entity.TAlarmTemp;
import com.smart.device.common.message.entity.THeartTemp;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.factory.fire.type.ParseTypeEnum;
import com.smart.device.message.parse.fire.temp.constants.WanlinTempConstants;
import com.smart.device.message.parse.fire.temp.constants.WanlinTempEnum;
import com.smart.device.message.parse.fire.temp.wanlin.entity.TempEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * @author jungle
 * @Time 2020/4/10
 */
@Component
@Slf4j
public class TempStandard {
    /**
     * 将 万林对接数据对接为标准数据
     * @param vo vo
     * @return alarm
     */
    public TAlarmTemp changeToAlarm(TempEntity vo){
        TAlarmTemp alarm = new TAlarmTemp();

        alarm.setImei(Long.parseLong(vo.getMac()));
        alarm.setDeviceCode(Long.parseLong(vo.getMac()));
        alarm.setEventTime(DateUtils.covertToLocalDateTime(vo.getEventDate()));
        alarm.setEventCode(WanlinTempConstants.WANLIN_MAP.get(WanlinTempEnum.CONTENT).get(vo.getEvent()));
        alarm.setType(Integer.parseInt(WanlinTempConstants.WANLIN_MAP.get(WanlinTempEnum.CONTENT).get(vo.getEvent())));
        // 初始化值// 未处理
        alarm.setState(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL);
        alarm.setStateName(DeviceConstant.DEVICE_DEAL_STATUS_NOHANDEL_NAME);
        // 报警等级 1 正常 2 一般
        alarm.setLevel(DeviceConstant.LEVEL_JUST);
        alarm.setDeviceType(Integer.parseInt(ParseTypeEnum.WL_TEMP_WET.getCode()));
        alarm.setCreateTime(LocalDateTime.now());
        log.info("万林温湿度计-标准化为数据库报警表" + JSONObject.toJSONString(alarm));
        return alarm;
    }

    /**
     * 将 万林对接数据对接为标准数据
     * @param vo vo
     * @return heart
     */
    public THeartTemp changeToHeart(TempEntity vo){
        THeartTemp heart = new THeartTemp();

        heart.setImei(Long.parseLong(vo.getMac()));
        heart.setHumidity(vo.getHumidity());
        heart.setIccid(vo.getIccid());
        heart.setHeartTime(DateUtils.covertToLocalDateTime(vo.getEventDate()));
        heart.setType(Integer.parseInt(WanlinTempConstants.WANLIN_MAP.get(WanlinTempEnum.CONTENT).get(vo.getEvent())));
        //设备类型 20烟感
        heart.setDeviceType(Integer.parseInt(ParseTypeEnum.WL_TEMP_WET.getCode()));
        heart.setCreateTime(LocalDateTime.now());
        log.info("万林温湿度计-标准化为数据库心跳表" + JSONObject.toJSONString(heart));
        return heart;
    }

    //***********将获取到的设备基础信息复制给报警的表********开始***********
    public void changeAlarmAttri(TAlarmTemp alarm, DeviceBaseVo vo) {
        alarm.setDeviceId(vo.getId());
        alarm.setDeviceName(vo.getDeviceName());
        alarm.setDeviceCode(vo.getDeviceCode());
        alarm.setDeviceType(vo.getDeviceType());
        alarm.setDeviceTypeName(vo.getDeviceTypeName());
        alarm.setParentType(vo.getParentType());
        alarm.setDeviceModel(vo.getDeviceModel());

        alarm.setProductId(vo.getProductId());
        alarm.setProductName(vo.getProductName());
    }
    public void changeHeartAttri(THeartTemp heart, DeviceBaseVo vo) {
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
    }
    //********结束*******************************************************

}
