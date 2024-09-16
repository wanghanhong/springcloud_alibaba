package com.smart.brd.manage.message.parse.device.strategy;

import com.alibaba.fastjson.JSON;
import com.smart.brd.manage.message.parse.ParseStrategy;
import com.smart.brd.manage.message.parse.device.constant.DeviceEnum;
import com.smart.brd.manage.message.parse.device.constant.DeviceMapConstant;
import com.smart.brd.manage.message.parse.device.entity.DeviceTags;
import com.smart.brd.manage.message.parse.device.entity.DeviceTagsVo;
import com.smart.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

/**
 * @author junglelocal
 */
@Component
public class DeviceParseStrategy implements ParseStrategy<DeviceTags> {

    @Override
    public DeviceTags assemblyData(String dataStr) {
        DeviceTags tags;
        tags = JSON.parseObject(dataStr, DeviceTags.class);
        DeviceTagsVo vo = new DeviceTagsVo();
        BeanUtils.copyBeanProp(vo, tags);

        vo.setBatteryPower(tags.getBatteryPower() + "V");
        vo.setTemperature(tags.getTemperature() + "℃");
        vo.setEnvTemperature(tags.getEnvTemperature() + "℃");
        vo.setRssi(DeviceMapConstant.DEVICE_MAP.get(DeviceEnum.RSSI).get(tags.getRssi()));
        vo.setClosingFlag(DeviceMapConstant.DEVICE_MAP.get(DeviceEnum.CLOSING).get(tags.getClosingFlag()));
        vo.setEventTime(Date.from(Instant.ofEpochSecond(tags.getEventTime())));
        return tags;
    }
}
