package com.smart.device.install.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.TBaseBuilding;
import com.smart.device.common.install.entity.TManagerCameras;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.install.feign.DeviceAccessFeign;
import com.smart.device.install.mapper.TBaseBuildingMapper;
import com.smart.device.install.mapper.TManagerCamerasMapper;
import com.smart.device.install.service.ITManagerCamerasService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author f
 */
@Service
public class TManagerCamerasServiceImpl extends ServiceImpl<TManagerCamerasMapper, TManagerCameras> implements ITManagerCamerasService {

    @Resource
    private TManagerCamerasMapper tManagerCamerasMapper;
    @Resource
    private DeviceAccessFeign deviceAccessFeign;
    @Resource
    private TBaseBuildingMapper tBaseBuildingMapper;

    @Override
    public IPage<TManagerCameras> managerCamerasList(PageDomain page,TManagerCameras vo) {
        Page<TManagerCameras> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TManagerCameras> iPage = tManagerCamerasMapper.managerCamerasList(pg,vo);
        return iPage;
    }

    @Override
    public TManagerCameras managerCamerasAdd(TManagerCameras entity) {
        TManagerCameras exist  = tManagerCamerasMapper.getManagerCameras(entity);
        camerasSetArea(entity);
        if(exist != null){
            entity.setId(exist.getId());
            managerCamerasUpdate(entity);
        }else{
            entity = saveManagerCameras(entity);
        }
        return entity;
    }
    // 查询 建筑物的区域，
    private void camerasSetArea(TManagerCameras entity){
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
    public TManagerCameras saveManagerCameras(TManagerCameras entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        entity.setId(idWorker.nextId());
        entity.setCreateTime(LocalDateTime.now());
        DeviceBaseVo device = deviceAccessFeign.selectDeviceCameras(entity.getSn());
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
        tManagerCamerasMapper.insert(entity);
        return entity;
    }
    @Override
    public int managerCamerasDel(Long id) {
        return tManagerCamerasMapper.deleteById(id);
    }

    @Override
    public TManagerCameras managerCamerasUpdate(TManagerCameras entity) {
        camerasSetArea(entity);
        entity.setUpdateTime(LocalDateTime.now());
        tManagerCamerasMapper.updateById(entity);
        return entity;
    }

    @Override
    public TManagerCameras selectCamerasByID(Long id) {
        TManagerCameras cameras = tManagerCamerasMapper.selectById(id);
        return cameras;
    }


    @Override
    public List<TManagerCameras> camerasListNoPage(TManagerCameras vo){
        Page<TManagerCameras> pg = new Page<>();
        IPage<TManagerCameras> iPage = tManagerCamerasMapper.managerCamerasList(pg,vo);
        return iPage.getRecords();
    }

    @Override
    public TManagerCameras getManagerCameras(TManagerCameras entity) {
        TManagerCameras cameras = tManagerCamerasMapper.getManagerCameras(entity);
        return cameras;
    }


}
