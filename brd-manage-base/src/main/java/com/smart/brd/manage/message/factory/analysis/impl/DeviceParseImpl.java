package com.smart.brd.manage.message.factory.analysis.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.entity.TAlarmInfo;
import com.smart.brd.manage.base.entity.TAlarmRules;
import com.smart.brd.manage.base.entity.THeartDevice;
import com.smart.brd.manage.base.entity.TLivestock;
import com.smart.brd.manage.base.mapper.TAlarmInfoMapper;
import com.smart.brd.manage.base.mapper.TAlarmRulesMapper;
import com.smart.brd.manage.base.mapper.THeartDeviceMapper;
import com.smart.brd.manage.base.mapper.TLivestockMapper;
import com.smart.brd.manage.message.factory.analysis.DeviceParse;
import com.smart.brd.manage.message.parse.ParseContext;
import com.smart.brd.manage.message.parse.device.entity.DeviceTags;
import com.smart.brd.manage.message.parse.device.entity.payload;
import com.smart.brd.manage.message.parse.device.strategy.DeviceParseStandard;
import com.smart.brd.manage.message.parse.device.strategy.DeviceParseStrategy;
import com.smart.brd.manage.message.parse.device.strategy.DeviceParseStrategyNew;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author junglelocal
 */
@Service
public class DeviceParseImpl implements DeviceParse {

    @Resource
    private DeviceParseStrategy deviceParseStrategy;
    @Resource
    private DeviceParseStrategyNew deviceParseStrategyNew;
    @Resource
    private DeviceParseStandard deviceParseStandard;
    @Resource
    private TAlarmInfoMapper tAlarmInfoMapper;
    @Resource
    private THeartDeviceMapper tHeartDeviceMapper;
    @Resource
    private TLivestockMapper tLivestockMapper;
    @Resource
    private TAlarmRulesMapper tAlarmRulesMapper;

    @Override
    public String parse(String data) {
        ParseContext<DeviceTags> context = new ParseContext<>(deviceParseStrategy);
        String s = "delete_flag";
        DeviceTags entity = context.parseEntity(data);
        THeartDevice tags = deviceParseStandard.changeToTags(entity);
        tags.setCreateTime(LocalDateTime.now());
        tags.setDeviceType(76);
        tags.setDeviceTypeName("耳标");
        tags.setDeleteFlag(0);
        QueryWrapper<TLivestock> stockWrapper = new QueryWrapper<>();
        String device = tags.getDeviceCode();
        stockWrapper.eq("device_code", device);
        stockWrapper.eq(s,0);
        TLivestock vo = tLivestockMapper.selectOne(stockWrapper);
        if (vo != null) {
            QueryWrapper<TAlarmRules> wrapper = new QueryWrapper<>();
            wrapper.eq("equipment_type", "76");
            wrapper.like("suitable", vo.getSuitable());
            wrapper.eq("dept_id",vo.getDeptId());
            wrapper.eq(s, 0);
            wrapper.eq("status", "143");
            wrapper.eq("rule_type", "146");
            TAlarmRules rule = tAlarmRulesMapper.selectOne(wrapper);
            //按规则内的阈值进行判断
            if (rule != null) {
                if (tags.getTemperature() <= rule.getThresholdMin()
                        || tags.getTemperature() >= rule.getThresholdMax()) {
                    TAlarmInfo info = new TAlarmInfo();
                    info.setCreateTime(LocalDateTime.now());
                    info.setAlarmTime(LocalDateTime.now());
                    info.setEquipmentType("76");
                    info.setEventStatus(88);
                    info.setAlarmCategory("15");
                    info.setAlarmContent(tags.getTemperature() +"℃");
                    info.setEquipmentNumber(tags.getDeviceCode());
                    info.setType(vo.getType());
                    info.setShedId(vo.getShedId());
                    info.setBedId(vo.getBedId());
                    info.setShedName(vo.getShedName());
                    info.setBedName(vo.getBedName());
                    info.setLivestockId(vo.getLivestockId());
                    if (vo.getDeptId() != null) {
                        info.setDeptId(vo.getDeptId());
                    }
                    QueryWrapper<TAlarmInfo> infoWrapper = new QueryWrapper<>();
                    infoWrapper.eq("equipment_number", vo.getDeviceCode());
                    infoWrapper.eq(s, 0);
                    infoWrapper.eq("event_status", 88);
                    vo.setStatus("151");
                    tLivestockMapper.updateById(vo);
                    if (tAlarmInfoMapper.selectCount(infoWrapper) <= 0) {
                        tAlarmInfoMapper.insert(info);
                    }
                    tHeartDeviceMapper.insert(tags);
                    return info.toString();
                }
            }
        }
        tHeartDeviceMapper.insert(tags);
        return tags.toString();
    }

    @Override
    public String parseNew(String data) {
        ParseContext<DeviceTags> context = new ParseContext<>(deviceParseStrategyNew);
        String s = "delete_flag";
        DeviceTags entity = context.parseEntity(data);
        THeartDevice tags = deviceParseStandard.changeToTagsNew(entity.getPayload());
        if(tags != null) {
            payload payload = entity.getPayload();
            if (payload != null) {
                LocalDateTime dateTime = LocalDateTime.ofEpochSecond(entity.getPayload().getUptime(), 0, ZoneOffset.ofHours(8));
                tags.setCreateTime(dateTime);
            }
            tags.setDeviceType(78);
            tags.setDeviceTypeName("耳标");
            tags.setDeleteFlag(0);
            tags.setManufacturers("123");
            QueryWrapper<TLivestock> stockWrapper = new QueryWrapper<>();
            String device = tags.getDeviceCode();
            stockWrapper.eq("device_code", device);
            stockWrapper.eq(s, 0);
            TLivestock vo = tLivestockMapper.selectOne(stockWrapper);
            if (vo != null) {
                QueryWrapper<TAlarmRules> wrapper = new QueryWrapper<>();
                wrapper.eq("equipment_type", "76");
                wrapper.like("suitable", vo.getSuitable());
                wrapper.eq("dept_id", vo.getDeptId());
                wrapper.eq(s, 0);
                wrapper.eq("status", "143");
                wrapper.eq("rule_type", "146");
                TAlarmRules rule = tAlarmRulesMapper.selectOne(wrapper);
                //按规则内的阈值进行判断
                if (rule != null) {
                    if (tags.getTemperature() <= rule.getThresholdMin()
                            || tags.getTemperature() >= rule.getThresholdMax()) {
                        TAlarmInfo info = new TAlarmInfo();
                        info.setCreateTime(LocalDateTime.now());
                        info.setAlarmTime(LocalDateTime.now());
                        info.setEquipmentType("78");
                        info.setEventStatus(88);
                        info.setAlarmCategory("15");
                        info.setAlarmContent(tags.getTemperature() + "℃");
                        info.setEquipmentNumber(tags.getDeviceCode());
                        info.setType(vo.getType());
                        info.setShedId(vo.getShedId());
                        info.setBedId(vo.getBedId());
                        info.setShedName(vo.getShedName());
                        info.setBedName(vo.getBedName());
                        info.setLivestockId(vo.getLivestockId());
                        if (vo.getDeptId() != null) {
                            info.setDeptId(vo.getDeptId());
                        }
                        QueryWrapper<TAlarmInfo> infoWrapper = new QueryWrapper<>();
                        infoWrapper.eq("equipment_number", vo.getDeviceCode());
                        infoWrapper.eq(s, 0);
                        infoWrapper.eq("event_status", 88);
                        vo.setStatus("151");
                        tLivestockMapper.updateById(vo);
                        if (tAlarmInfoMapper.selectCount(infoWrapper) <= 0) {
                            tAlarmInfoMapper.insert(info);
                        }
                        tHeartDeviceMapper.insert(tags);
                        return info.toString();
                    }
                }
            }
            tHeartDeviceMapper.insert(tags);
        }
        if(tags !=null){
            return tags.toString();
        }else{
            return "";
        }
    }

}
