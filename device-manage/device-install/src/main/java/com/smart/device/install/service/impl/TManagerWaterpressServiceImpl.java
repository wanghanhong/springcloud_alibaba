package com.smart.device.install.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.*;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.install.feign.DeviceAccessFeign;
import com.smart.device.install.mapper.TBaseBuildingMapper;
import com.smart.device.install.mapper.TManagerWaterpressMapper;
import com.smart.device.install.service.FdfsServiceImpl;
import com.smart.device.install.service.ITBaseBuildingSonService;
import com.smart.device.install.service.ITManagerWaterpressService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author f
 */
@Service
public class TManagerWaterpressServiceImpl extends ServiceImpl<TManagerWaterpressMapper, TManagerWaterpress> implements ITManagerWaterpressService {

    @Resource
    private TManagerWaterpressMapper tManagerWaterpressMapper;
    @Resource
    private ITBaseBuildingSonService itBaseBuildingSonService;
    @Resource
    private FdfsServiceImpl fdfsService;
    @Resource
    private DeviceAccessFeign deviceAccessFeign;
    @Resource
    private TBaseBuildingMapper tBaseBuildingMapper;

    @Override
    public IPage<TManagerWaterpress> managerWaterpressList(PageDomain page, TManagerWaterpress vo) {
        Page<TManagerWaterpress> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TManagerWaterpress> iPage = tManagerWaterpressMapper.managerWaterpressList(pg,vo);
        return iPage;
    }

    @Override
    public TManagerWaterpress managerWaterpressAdd(TManagerWaterpress entity) {
//        // 检测下是否已经绑定，已经绑定的话，走UPdate 方法
        TManagerWaterpress exist  = tManagerWaterpressMapper.getManagerWaterpress(entity);
        waterpressSetArea(entity);
        if(exist != null){
             entity.setId(exist.getId());
             managerWaterpressUpdate(entity);
         }else{
            entity = saveManagerWaterpress(entity);
         }
         itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getBuildingId(),entity.getBuildingFloor());
        return entity;
    }

    // 查询 建筑物的区域，
    private void waterpressSetArea(TManagerWaterpress entity){
        if(entity != null && entity.getBuildingId() != null){
            TBaseBuilding build = tBaseBuildingMapper.selectById(entity.getBuildingId());
            if(build != null){
                entity.setProvince(build.getProvince());
                entity.setCity(build.getCity());
                entity.setCounty(build.getCounty());
                entity.setTown(build.getTown());
            }
        }
    }

    private TManagerWaterpress saveManagerWaterpress(TManagerWaterpress entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id             entity.setId(idWorker.nextId());
        entity.setCreateTime(LocalDateTime.now());
        DeviceBaseVo device = deviceAccessFeign.selectDeviceWaterpress(entity.getImei());
        if(device != null ){
            entity.setDeviceId(device.getId());
            entity.setDeviceCode(device.getDeviceCode());
            entity.setDeviceType(device.getDeviceType());
            entity.setParentType(device.getParentType());
            entity.setDeviceTypeName(device.getDeviceTypeName());
            entity.setDeviceModel(device.getDeviceModel());
            entity.setDeviceState(device.getDeviceState());
            entity.setDeviceStateName(device.getDeviceStateName());
        }
        tManagerWaterpressMapper.insert(entity);
        return entity;
    }
    @Override
    public int managerWaterpressDel(Long id) {
        return tManagerWaterpressMapper.deleteById(id);
    }

    @Override
    public TManagerWaterpress managerWaterpressUpdate(TManagerWaterpress entity) {
        waterpressSetArea(entity);
        entity.setUpdateTime(LocalDateTime.now());
        tManagerWaterpressMapper.updateById(entity);
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getBuildingId(),entity.getBuildingFloor());
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getOldbuildingId(),entity.getOldbuildingFloor());
        return entity;
    }

    @Override
    public TManagerWaterpress selectWaterpressByID(Long id) {
        TManagerWaterpress entity = tManagerWaterpressMapper.selectById(id);
        if(StringUtils.isNotBlank(entity.getFileUrl()) ){
            String fileUrl = fdfsService.getResAccessUrl(entity.getFileUrl());
            entity.setFileUrlString(fileUrl);
        }else {
            entity.setFileUrlString("");
        }
        return entity;
    }
    @Override
    public TManagerWaterpress selectWaterpress(TManagerWaterpress vo){
        List<TManagerWaterpress> list = tManagerWaterpressMapper.selectWaterpress(vo);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public TManagerWaterpress getManagerWaterpress(TManagerWaterpress entity) {
        return tManagerWaterpressMapper.getManagerWaterpress(entity);
    }

    @Override
    public DeviceCompanyVo waterpressPerSonByDeviceId(Long deviceId) {
        DeviceCompanyVo vo = tManagerWaterpressMapper.waterpressUserByDeviceId(deviceId);
        if(vo == null || vo.getImei() == null){
            vo = new DeviceCompanyVo();
            vo.setIsXcx(0);
            vo = tManagerWaterpressMapper.waterpressPerSonByDeviceId(deviceId);
        }
        return vo;
    }

    @Override
    public void updateWaterpressStatus(TManagerWaterpress entity) {
        entity.setUpdateTime(LocalDateTime.now());
        tManagerWaterpressMapper.updateWaterpressStatus(entity);
    }

}
