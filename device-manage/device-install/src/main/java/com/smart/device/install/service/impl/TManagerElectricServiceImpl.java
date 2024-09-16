package com.smart.device.install.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.upload.FastDfsUtil;
import com.smart.device.common.access.entity.TDeviceElectric;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.TBaseBuilding;
import com.smart.device.common.install.entity.TBaseBuildingSon;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.install.feign.DeviceAccessFeign;
import com.smart.device.install.mapper.TBaseBuildingMapper;
import com.smart.device.install.mapper.TManagerElectricMapper;
import com.smart.device.install.service.FdfsServiceImpl;
import com.smart.device.install.service.ITBaseBuildingSonService;
import com.smart.device.install.service.ITManagerElectricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 
 */
@Service
public class TManagerElectricServiceImpl extends ServiceImpl<TManagerElectricMapper, TManagerElectric> implements ITManagerElectricService {

    @Resource
    private TManagerElectricMapper tManagerElectricMapper;
    @Resource
    private ITBaseBuildingSonService itBaseBuildingSonService;
    @Resource
    private DeviceAccessFeign deviceAccessFeign;
    @Resource
    private FdfsServiceImpl fdfsService;
    @Resource
    private TBaseBuildingMapper tBaseBuildingMapper;

    @Override
    public IPage<TManagerElectric> managerElectricList(PageDomain page,TManagerElectric vo) {
        Page<TManagerElectric> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TManagerElectric> iPage = tManagerElectricMapper.managerElectricList(pg,vo);
        return iPage;
    }

    @Override
    public TManagerElectric managerElectricAdd(TManagerElectric entity) {
        TManagerElectric exist  = tManagerElectricMapper.getManagerElectric(entity);
        electricSetArea(entity);
        if(exist != null){
            entity.setId(exist.getId());
            managerElectricUpdate(entity);
        }else{
            entity = saveTManagerElectric(entity);
        }
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getBuildingId(),entity.getBuildingFloor());
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getOldbuildingId(),entity.getOldbuildingFloor());
        return entity;
    }
    private TManagerElectric saveTManagerElectric(TManagerElectric entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        entity.setId(idWorker.nextId());
        entity.setCreateTime(LocalDateTime.now());
        DeviceBaseVo device = deviceAccessFeign.selectDeviceElectric(entity.getImei());
        if(device != null){
            entity.setDeviceId(device.getId());
            entity.setDeviceCode(device.getDeviceCode());
            entity.setDeviceType(device.getDeviceType());
            entity.setParentType(device.getParentType());
            entity.setDeviceTypeName(device.getDeviceTypeName());
            entity.setDeviceModel(device.getDeviceModel());
            entity.setDeviceState(device.getDeviceState());
            entity.setDeviceStateName(device.getDeviceStateName());
        }
        tManagerElectricMapper.insert(entity);
        return entity;
    }
    // 查询 建筑物的区域，
    private void electricSetArea(TManagerElectric entity){
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
    @Override
    public int managerElectricDel(Long id) {
        return tManagerElectricMapper.deleteById(id);
    }

    @Override
    public TManagerElectric managerElectricUpdate(TManagerElectric entity) {
        electricSetArea(entity);
        entity.setUpdateTime(LocalDateTime.now());
        tManagerElectricMapper.updateById(entity);
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getBuildingId(),entity.getBuildingFloor());
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getOldbuildingId(),entity.getOldbuildingFloor());
        return entity;
    }

    @Override
    public TManagerElectric selectElectricByID(Long id) {
        TManagerElectric entity = tManagerElectricMapper.selectById(id);
        if(StringUtils.isNotBlank(entity.getFileUrl()) ){
            String fileUrl = fdfsService.getResAccessUrl(entity.getFileUrl());
            entity.setFileUrlString(fileUrl);
        }else {
            entity.setFileUrlString("");
        }
        return entity;
    }
    @Override
    public TManagerElectric selectElectric(TManagerElectric vo){
        List<TManagerElectric> list = tManagerElectricMapper.selectElectric(vo);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public TManagerElectric getManagerElectric(TManagerElectric entity) {
        return tManagerElectricMapper.getManagerElectric(entity);
    }

    @Override
    public DeviceCompanyVo electricPerSonByDeviceId(Long deviceId) {
        DeviceCompanyVo vo = tManagerElectricMapper.electricUserByDeviceId(deviceId);
        if(vo == null || vo.getImei() == null){
            vo = new DeviceCompanyVo();
            vo.setIsXcx(0);
            vo = tManagerElectricMapper.electricPerSonByDeviceId(deviceId);
        }
        return vo;
    }

    @Override
    public void updateElectricStatus(TManagerElectric entity) {
        entity.setUpdateTime(LocalDateTime.now());
        tManagerElectricMapper.updateElectricStatus(entity);
    }

}
