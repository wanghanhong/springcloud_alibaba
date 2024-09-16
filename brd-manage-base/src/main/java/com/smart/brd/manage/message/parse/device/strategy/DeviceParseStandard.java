package com.smart.brd.manage.message.parse.device.strategy;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.entity.TDevice;
import com.smart.brd.manage.base.entity.THeartDevice;
import com.smart.brd.manage.base.mapper.TDeviceMapper;
import com.smart.brd.manage.message.parse.device.entity.DeviceTags;
import com.smart.brd.manage.message.parse.device.entity.payload;
import com.smart.common.utils.bean.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author junglelocal
 */
@Component
@Slf4j
public class DeviceParseStandard {

    @Resource
    private TDeviceMapper tDeviceService;

    /**
     * {"GPS":"guangzhou22","key":"c2a52609245843f08c8d4cf3fc314022","tagsId":"09001583",
     * "eventTime":1616383564,"batteryPower":2.9,"temperature":22.6,"envTemperature":23.0,
     * "stationId":"210304010","closingFlag":0,"Damage":5,"Bat":2.9,"RSSI":0,"Intervals":240,
     * "mac":"88124c101fdc","netmode":"wifi","lterssi":22}
     */
    public THeartDevice changeToTags(DeviceTags vo) {
        THeartDevice tag = new THeartDevice();
        BeanUtils.copyBeanProp(tag, vo);
        tag.setEventTime(LocalDateTime.ofEpochSecond(vo.getEventTime(), 0, ZoneOffset.ofHours(8)));
        tag.setDeviceCode(vo.getTagsId());
        tag.setServerKey(vo.getKey());
        TDevice device = new TDevice();
        device.setDeviceCode(vo.getTagsId());
        QueryWrapper<TDevice> wrapper = new QueryWrapper<>();
        wrapper.eq("device_code", tag.getDeviceCode());
        device = tDeviceService.selectOne(wrapper);
        if (device != null) {
            tag.setDeviceId(device.getDeviceId());
        }
        log.info("接收到数据" + JSONObject.toJSONString(vo));
        return tag;
    }

    public THeartDevice changeToTagsNew(payload vo) {
        THeartDevice tag = new THeartDevice();
        if(vo != null) {
            BeanUtils.copyBeanProp(tag, vo);
            double temperature = vo.getTemperature();
            tag.setTemperature((float) temperature);
            tag.setDeviceCode(vo.getAaid());
            double voltage = vo.getVoltage();
            tag.setBat((float) voltage);
            //tag.setServerKey(vo.getKey());
            TDevice device = new TDevice();
            device.setDeviceCode(vo.getAaid());
            QueryWrapper<TDevice> wrapper = new QueryWrapper<>();
            wrapper.eq("device_code", tag.getDeviceCode());
            device = tDeviceService.selectOne(wrapper);
            if (device != null) {
                tag.setDeviceId(device.getDeviceId());
            }
            log.info("接收到数据" + JSONObject.toJSONString(vo));
        }else{
            return null;
        }
        return tag;
    }

}
