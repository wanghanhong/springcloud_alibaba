package com.smart.device.access.service.impl;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.domain.Result;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.bean.BeanUtils;
import com.smart.device.access.common.utils.DeviceCommonUtils;
import com.smart.device.common.access.entity.vo.DeviceAccessVO;
import com.smart.device.access.entity.vo.ImportDeviceVO;
import com.smart.device.access.entity.vo.SdDeviceCtWingVO;
import com.smart.device.access.mapper.TDeviceSmokeMapper;
import com.smart.device.access.service.BaseDeviceFactory;
import com.smart.device.access.service.CtWingAPIService;
import com.smart.device.access.service.ITDeviceSmokeService;
import com.smart.device.common.access.entity.TDeviceSmoke;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.constant.DeviceConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 烟感设备实现类
* @author 
*/
@Service("tDeviceSmokeServiceImpl")
@Slf4j
public class TDeviceSmokeServiceImpl extends ServiceImpl<TDeviceSmokeMapper, TDeviceSmoke> implements ITDeviceSmokeService,BaseDeviceFactory {

    @Resource
    private TDeviceSmokeMapper tDeviceSmokeMapper;
    @Autowired
    private DeviceCommonUtils deviceCommonUtils;
    @Resource
    private CtWingAPIService ctWingAPIService;


    @Override
    public Result saveDeviceInfo(List<ImportDeviceVO> successList) {
        List<Long> lstDeviceImei = tDeviceSmokeMapper.findSmokeDevice();
        List<TDeviceSmoke> smokeDeviceList = new ArrayList<>();
        if (successList.size() > 0) {
            for (ImportDeviceVO sdDevice : successList) {
                //校验imei的合法性,唯一性,只导入新增的设备数据
                if (lstDeviceImei != null && lstDeviceImei.contains(sdDevice.getImei())) {
                    continue;
                }
                TDeviceSmoke tDeviceSmoke = new TDeviceSmoke();
                Long id = 0L;
                if (null != sdDevice.getImei()) {
                    id = deviceCommonUtils.getDeviceId(sdDevice.getDeviceType(), sdDevice.getProtocol(), sdDevice.getProductName(), sdDevice.getImei(),sdDevice.getDeviceModel());
                    List<Long> lstDeviceIds = tDeviceSmokeMapper.findSmokeIds();
                    if (lstDeviceIds != null && lstDeviceIds.contains(id)) {
                        continue;
                    }
                } else {
                    return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "保存数据失败，请输入必填字段");
                }
                setDeviceAttr(sdDevice, tDeviceSmoke);

                tDeviceSmoke.setId(id);
                tDeviceSmoke.setDeviceCode(id);
                smokeDeviceList.add(tDeviceSmoke);
            }
            //插入设备表
            if (smokeDeviceList.size() > 0) {
                log.info("导入数据到烟感设备表");
                this.saveBatch(smokeDeviceList);
            }
        }
        return new Result(HttpStatus.HTTP_OK, "导入成功", true);
    }

    private void setDeviceAttr(ImportDeviceVO vo,TDeviceSmoke entity) {
        BeanUtils.copyBeanProp(entity,vo);
        if(vo.getOutFactoryTime() != null){
            entity.setOutFactoryTime(new Date(Long.parseLong(vo.getOutFactoryTime())));
        }
        if(vo.getScrapTime() != null){
            entity.setScrapTime(new Date(Long.parseLong(vo.getScrapTime())));
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setDeleteFlag(0);
        entity.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
        entity.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
    }


    @Override
    public Result selectDeviceInfo(DeviceAccessVO deviceVO) {
        IPage<TDeviceSmoke> page = new Page<>(deviceVO.getPageNum(),deviceVO.getPageSize());
        QueryWrapper<TDeviceSmoke> wrapper = new QueryWrapper<>();
        if(null != deviceVO.getDeviceType()){
            wrapper.eq("parent_type",deviceVO.getDeviceType());
        }
        if(null != deviceVO.getDeviceCode()){
            wrapper.eq("device_code",deviceVO.getDeviceCode());
        }
        if(StringUtils.isNotEmpty(deviceVO.getDeviceName())){
            wrapper.like("device_name",deviceVO.getDeviceName());
        }
        if(null != deviceVO.getOpUserId()){
            wrapper.eq("op_user_id",deviceVO.getOpUserId());
        }
        if(null != deviceVO.getOpCompanyId()){
            wrapper.eq("op_company_id",deviceVO.getOpCompanyId());
        }
        if(null != deviceVO.getDeleteFlag()){
            wrapper.eq("delete_flag",deviceVO.getDeleteFlag());
        }
        wrapper.orderByDesc("create_time");
        IPage<TDeviceSmoke> smokeIPage = baseMapper.selectPage(page, wrapper);
        return new Result(smokeIPage);
    }

    @Override
    public Result createCtwingDevice(DeviceAccessVO deviceVO) {
        //批量导入ctwing平台
        if(deviceVO.getIdlist() != null && deviceVO.getIdlist().length > 1){
            List<Long> oblist = Arrays.asList(deviceVO.getIdlist());
            List<TDeviceSmoke> tDeviceSmokeList = baseMapper.selectBatchIds(oblist);
            for(int i=0;i<tDeviceSmokeList.size();i++){
                //烟感设备表里thirdDeviceId字段没有值，说明没有同步到ctwing平台
                if(StringUtils.isEmpty(tDeviceSmokeList.get(i).getThirdDeviceId())){
                    SdDeviceCtWingVO  sdDeviceCtWing = new SdDeviceCtWingVO();
                    sdDeviceCtWing.setDeviceType(deviceVO.getDeviceType());
                    if (StringUtils.isNotEmpty(tDeviceSmokeList.get(i).getDeviceName())) {
                        sdDeviceCtWing.setDeviceName(tDeviceSmokeList.get(i).getDeviceName());
                    }
                    if(null != tDeviceSmokeList.get(i).getImei()){
                        sdDeviceCtWing.setImei(tDeviceSmokeList.get(i).getImei().toString());
                    }
                    sdDeviceCtWing.setAutoObserver(0);
                    try {
                        JSONObject obj = ctWingAPIService.createDevice(sdDeviceCtWing);
                        if ("0".equals(String.valueOf(obj.get("code")))) {
                            if (obj.get("result") != null) {
                                tDeviceSmokeList.get(i).setThirdDeviceId(String.valueOf(obj.getJSONObject("result").get("deviceId")));
                                baseMapper.updateById(tDeviceSmokeList.get(i));
                            }
                        }else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
//                        return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "同步成功");
                    }
                }
            }
        }else{
            //单个导入ctwing平台
            Long oblist = deviceVO.getIdlist()[0];
            TDeviceSmoke tDeviceSmoke = baseMapper.selectById(oblist);
            if(StringUtils.isEmpty(tDeviceSmoke.getThirdDeviceId())){
                SdDeviceCtWingVO  sdDeviceCtWing = new SdDeviceCtWingVO();
                sdDeviceCtWing.setDeviceType(deviceVO.getDeviceType());
                if (StringUtils.isNotEmpty(tDeviceSmoke.getDeviceName())) {
                    sdDeviceCtWing.setDeviceName(tDeviceSmoke.getDeviceName());
                }
                if(null != tDeviceSmoke.getImei()){
                    sdDeviceCtWing.setImei(tDeviceSmoke.getImei().toString());
                }
                sdDeviceCtWing.setAutoObserver(0);
                try {
                    JSONObject obj = ctWingAPIService.createDevice(sdDeviceCtWing);
                    if ("0".equals(String.valueOf(obj.get("code")))) {
                        if (obj.get("result") != null) {
                            tDeviceSmoke.setThirdDeviceId(String.valueOf(obj.getJSONObject("result").get("deviceId")));
                            baseMapper.updateById(tDeviceSmoke);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
//                    return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "同步成功");
                }
            }
        }

        return new Result(HttpStatus.HTTP_OK, "同步成功", true);
    }

    @Override
    public TDeviceSmoke deviceSmokeUpdate(TDeviceSmoke entity) {
        tDeviceSmokeMapper.updateById(entity);
        return entity;
    }

    @Override
    public DeviceBaseVo selectDeviceSmoke(Long id,Long imei){
        DeviceBaseVo vo = new DeviceBaseVo();
        vo.setId(id);
        vo.setImei(imei);
        DeviceBaseVo deviceBaseVo = tDeviceSmokeMapper.selectDeviceSmoke(vo);
        return deviceBaseVo;
    }

    @Override
    public List<DeviceBaseVo> selectSmokeAll(Integer type) {
        List<DeviceBaseVo>  list = new ArrayList<>();
        if(type == 2){
            list = tDeviceSmokeMapper.selectSmokeAllLow();
        }else{
            list = tDeviceSmokeMapper.selectSmokeAllHigh();
        }
        return list;
    }

    @Override
    public void devicesDelete(Long id) {
        TDeviceSmoke entity = new TDeviceSmoke();
        entity.setId(id);
        entity.setDeleteFlag(1);
        tDeviceSmokeMapper.updateById(entity);
    }

}


