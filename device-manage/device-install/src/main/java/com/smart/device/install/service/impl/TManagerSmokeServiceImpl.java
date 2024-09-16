package com.smart.device.install.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.upload.FastDfsUtil;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.TBaseBuilding;
import com.smart.device.common.install.entity.TBaseBuildingSon;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.install.feign.DeviceAccessFeign;
import com.smart.device.install.mapper.TBaseBuildingMapper;
import com.smart.device.install.mapper.TManagerSmokeMapper;
import com.smart.device.install.service.FdfsServiceImpl;
import com.smart.device.install.service.ITBaseBuildingSonService;
import com.smart.device.install.service.ITManagerSmokeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author f
 */
@Service
public class TManagerSmokeServiceImpl extends ServiceImpl<TManagerSmokeMapper, TManagerSmoke> implements ITManagerSmokeService {

    @Resource
    private TManagerSmokeMapper tManagerSmokeMapper;
    @Resource
    private ITBaseBuildingSonService itBaseBuildingSonService;
    @Resource
    private FdfsServiceImpl fdfsService;
    @Resource
    private DeviceAccessFeign deviceAccessFeign;
    @Resource
    private TBaseBuildingMapper tBaseBuildingMapper;

    @Override
    public IPage<TManagerSmoke> managerSmokeList(PageDomain page,TManagerSmoke vo) {
        Page<TManagerSmoke> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TManagerSmoke> iPage = tManagerSmokeMapper.managerSmokeList(pg,vo);
        return iPage;
    }
    @Override
    public TManagerSmoke managerSmokeAdd(TManagerSmoke entity) {
        TManagerSmoke exist  = tManagerSmokeMapper.getManagerSmoke(entity);
        smokeSetArea(entity);
        if(exist != null){
            entity.setId(exist.getId());
            managerSmokeUpdate(entity);
        }else{
            entity = saveTManagerSmoke(entity);
        }
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getBuildingId(),entity.getBuildingFloor());
        return entity;
    }
    // 查询 建筑物的区域，
    private void smokeSetArea(TManagerSmoke entity){
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
    public String managerSmokeAddSP(TManagerSmoke entity) {
        String res = "";
        TManagerSmoke exist  = tManagerSmokeMapper.getManagerSmoke(entity);
        if(exist != null){
            entity.setId(exist.getId());
            entity.setUpdateTime(LocalDateTime.now());
            tManagerSmokeMapper.updateById(entity);
            res = "保存成功。";
        }else{
            entity = saveTManagerSmoke(entity);
            res = "设备已重新绑定成功！";
        }
        return res;
    }

    public TManagerSmoke saveTManagerSmoke(TManagerSmoke entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        entity.setId(idWorker.nextId());
        entity.setCreateTime(LocalDateTime.now());
        DeviceBaseVo device = deviceAccessFeign.selectDeviceSmoke(entity.getImei());
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
        tManagerSmokeMapper.insert(entity);
        return entity;
    }
    @Override
    public int managerSmokeDel(Long id) {
        return tManagerSmokeMapper.deleteById(id);
    }

    @Override
    public TManagerSmoke managerSmokeUpdate(TManagerSmoke entity) {
        smokeSetArea(entity);
        entity.setUpdateTime(LocalDateTime.now());
        tManagerSmokeMapper.updateById(entity);
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getBuildingId(),entity.getBuildingFloor());
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getOldbuildingId(),entity.getOldbuildingFloor());
        return entity;
    }

    @Override
    public TManagerSmoke selectSmokeByID(Long id) {
        TManagerSmoke entity = tManagerSmokeMapper.selectById(id);
        if(StringUtils.isNotBlank(entity.getFileUrl()) ){
            String fileUrl = fdfsService.getResAccessUrl(entity.getFileUrl());
            entity.setFileUrlString(fileUrl);
        }else {
            entity.setFileUrlString("");
        }
        return entity;
    }
    @Override
    public TManagerSmoke selectSmoke(TManagerSmoke vo){
        List<TManagerSmoke> list = tManagerSmokeMapper.selectSmoke(vo);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public TManagerSmoke getManagerSmoke(TManagerSmoke entity) {
        return tManagerSmokeMapper.getManagerSmoke(entity);
    }

    @Override
    public DeviceCompanyVo smokePerSonByDeviceId(Long deviceId) {
        DeviceCompanyVo vo = tManagerSmokeMapper.smokeUserByDeviceId(deviceId);
        if(vo == null || vo.getImei() == null){
            vo = new DeviceCompanyVo();
            vo.setIsXcx(0);
            vo = tManagerSmokeMapper.smokePerSonByDeviceId(deviceId);
        }
        return vo;
    }

    @Override
    public void updateSmokeStatus(TManagerSmoke entity) {
        entity.setUpdateTime(LocalDateTime.now());
        tManagerSmokeMapper.updateSmokeStatus(entity);
    }

}
