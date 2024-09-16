package com.smart.brd.manage.message.parse.device.strategy;

import com.alibaba.fastjson.JSON;
import com.smart.brd.manage.message.parse.ParseStrategy;
import com.smart.brd.manage.message.parse.device.entity.DeviceTags;
import com.smart.brd.manage.message.parse.device.entity.DeviceTagsVo;
import com.smart.brd.manage.message.parse.device.entity.payload;
import com.smart.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author junglelocal
 */
@Component
public class DeviceParseStrategyNew implements ParseStrategy<DeviceTags> {


    @Override
    public DeviceTags assemblyData(String dataStrNew) {
        DeviceTags tagsNew;
        tagsNew = JSON.parseObject(dataStrNew, DeviceTags.class);
        payload payload = tagsNew.getPayload();
        if(payload != null) {
            DeviceTagsVo vo = new DeviceTagsVo();
            BeanUtils.copyBeanProp(vo, payload);
            vo.setTemperature(payload.getTemperature() + "℃");
            vo.setBat(payload.getVoltage());
            vo.setWalk(payload.getWalk());
            vo.setDistance(payload.getDistance());
            vo.setTagsId(payload.getAaid());
            tagsNew.setBat(payload.getVoltage());
        }
        return tagsNew;
    }
}
