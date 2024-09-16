package com.smart.device.access.service.impl;

import cn.hutool.http.HttpStatus;
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
import com.smart.device.access.mapper.TDeviceDictMapper;
import com.smart.device.access.mapper.TDeviceWaterpressMapper;
import com.smart.device.access.service.BaseDeviceFactory;
import com.smart.device.access.service.ITDeviceWaterpressService;
import com.smart.device.common.access.entity.TDeviceWaterpress;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.constant.DeviceConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 水压设备实现类
* @author
*/
@Slf4j
@Service("tDeviceWaterpressServiceImpl")
public class TDeviceWaterpressServiceImpl extends ServiceImpl<TDeviceWaterpressMapper, TDeviceWaterpress> implements ITDeviceWaterpressService,BaseDeviceFactory {

    @Resource
    private TDeviceWaterpressMapper tDeviceWaterpressMapper;
    @Autowired
    private DeviceCommonUtils deviceCommonUtils;
    @Resource
    private TDeviceDictMapper tDeviceDictMapper;

    @Override
    public Result saveDeviceInfo(List<ImportDeviceVO> successList) {
        List<Long> lstDeviceImei = tDeviceWaterpressMapper.findPressDevice();
        List<TDeviceWaterpress> pressDeviceList = new ArrayList<>();
        if (successList.size() > 0) {
            for (ImportDeviceVO sdDevice : successList) {
                //校验imei的合法性,唯一性,只导入新增的设备数据
                if (lstDeviceImei != null && lstDeviceImei.contains(sdDevice.getImei())) {
                    continue;
                }
                TDeviceWaterpress tDeviceWaterpress = new TDeviceWaterpress();
                Long id = 0L;
                if (StringUtils.isNotEmpty(sdDevice.getProtocol()) && StringUtils.isNotEmpty(sdDevice.getProductName()) && null != sdDevice.getImei()) {
                    id = deviceCommonUtils.getDeviceId(sdDevice.getDeviceType(), sdDevice.getProtocol(), sdDevice.getProductName(), sdDevice.getImei(),sdDevice.getDeviceModel());
                    List<Long> lstDeviceIds = tDeviceWaterpressMapper.findWaterpressIds();
                    if (lstDeviceIds != null && lstDeviceIds.contains(id)) {
                        continue;
                    }
                } else {
                    return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "保存数据失败，请输入必填字段");
                }
                setDeviceAttr(sdDevice, tDeviceWaterpress);
                tDeviceWaterpress.setId(id);
                tDeviceWaterpress.setDeviceCode(id);
                pressDeviceList.add(tDeviceWaterpress);
            }
            //插入设备表
            if (pressDeviceList.size() > 0) {
                log.info("导入数据到压力设备表");
                this.saveBatch(pressDeviceList);
            }
        }
        return new Result(HttpStatus.HTTP_OK, "导入成功", true);
    }

    private void setDeviceAttr(ImportDeviceVO vo, TDeviceWaterpress entity) {
        BeanUtils.copyBeanProp(vo,entity);
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
        IPage<TDeviceWaterpress> page = new Page<>(deviceVO.getPageNum(),deviceVO.getPageSize());
        QueryWrapper<TDeviceWaterpress> wrapper = new QueryWrapper<>();
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
        IPage<TDeviceWaterpress> pressIPage = baseMapper.selectPage(page, wrapper);
        return new Result(pressIPage);
    }

    @Override
    public Result createCtwingDevice(DeviceAccessVO deviceVO) {
        return null;
    }

    @Override
    public List<TDeviceWaterpress> deviceLiquidlevelAll(TDeviceWaterpress vo) {
        vo.setDeviceType(DeviceConstant.device_type_liquidlevel);
        List<TDeviceWaterpress> list =  tDeviceWaterpressMapper.deviceWaterpresssAll(vo);
        return list;
    }

    @Override
    public TDeviceWaterpress deviceWaterpressUpdate(TDeviceWaterpress entity) {
        tDeviceWaterpressMapper.updateById(entity);
        return entity;
    }

    @Override
    public List<TDeviceWaterpress> deviceWaterpresssAll(TDeviceWaterpress vo) {
        vo.setDeviceType(DeviceConstant.device_type_waterpress);
        List<TDeviceWaterpress> list =  tDeviceWaterpressMapper.deviceWaterpresssAll(vo);
        return list;
    }

    @Override
    public DeviceBaseVo selectDeviceWaterpress(Long id,Long imei) {
        DeviceBaseVo vo = new DeviceBaseVo();
        vo.setId(id);
        vo.setImei(imei);
        DeviceBaseVo deviceBaseVo = tDeviceWaterpressMapper.selectDeviceWaterpress(vo);
        return deviceBaseVo;
    }

    @Override
    public List<DeviceBaseVo> selectWaterpressAll(Integer type) {
        List<DeviceBaseVo>  list = new ArrayList<>();
        if(type == 2){
            list = tDeviceWaterpressMapper.selectWaterpressAllLow();
        }else{
            list = tDeviceWaterpressMapper.selectWaterpressAllHigh();
        }
        return list;
    }

    @Override
    public void devicesDelete(Long id) {
        TDeviceWaterpress entity = new TDeviceWaterpress();
        entity.setId(id);
        entity.setDeleteFlag(1);
        tDeviceWaterpressMapper.updateById(entity);
    }

}

