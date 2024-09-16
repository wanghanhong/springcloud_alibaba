package com.smart.device.install.service.inspection.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.*;
import com.smart.device.install.common.constant.InstallConstant;
import com.smart.device.install.entity.vo.HisInspectionVo;
import com.smart.device.install.mapper.inspection.TBaseFirefighterMapper;
import com.smart.device.install.mapper.inspection.TBaseInspectionMapper;
import com.smart.device.install.mapper.inspection.THisInspectionDetailsMapper;
import com.smart.device.install.service.inspection.ITBaseInsBuildFloorService;
import com.smart.device.install.service.inspection.ITBaseInsBuildService;
import com.smart.device.install.service.inspection.ITBaseInspectionService;
import com.smart.device.install.service.inspection.ITHisInspectionDetailsService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class THisInspectionDetailsServiceImpl extends ServiceImpl<THisInspectionDetailsMapper, THisInspectionDetails> implements ITHisInspectionDetailsService {

    @Resource
    private TBaseInspectionMapper tBaseInspectionMapper;
    @Resource
    private THisInspectionDetailsMapper tHisInspectionDetailsMapper;
    @Resource
    private ITBaseInsBuildFloorService baseInsBuildFloorService;
    @Resource
    private ITBaseInsBuildService baseInsBuildService;
    @Resource
    private ITBaseInspectionService baseInspectionService;


    @Override
    public THisInspectionDetails hisInspectionDetailsAdd(HisInspectionVo entity) {
        // 保存
        List<THisInspectionDetails> details = entity.getDetails();
        TBaseInspection ins = baseInspectionService.selectBaseInspectionByID(entity.getInspectionId());
        TBaseInsBuildFloor floor = baseInsBuildFloorService.selectById(entity.getInsBuildFloorId());
        // 保存消防通道 0 正常 1 堵塞
        if(entity.getFireEngineAccess() != null && entity.getFireEngineAccess() == 1){
            THisInspectionDetails  fire_access = new THisInspectionDetails();
            fire_access.setFireEngineAccess(entity.getFireEngineAccess());

            fire_access.setCompanyId(ins.getCompanyId());
            fire_access.setCompanyName(ins.getCompanyName());
            fire_access.setBuildingId(floor.getBuildingId());
            fire_access.setBuildingName(floor.getBuildingName());
            fire_access.setFloorNum(floor.getFloorNum());
            fire_access.setDeviceQuestion(InstallConstant.HIS_INS_DEVICE_ACCESS_CONTENT);
            details.add(fire_access);
        }
        details.stream().forEach(e->{
            e.setFirefighterId(ins.getFirefighterId());
            e.setFirefighterName(ins.getFirefighterName());
            e.setFireFighterPhone(ins.getPhone());

            e.setInspectionId(entity.getInspectionId());
            e.setInsBuildId(entity.getInsBuildId());
            e.setInsBuildFloorId(entity.getInsBuildFloorId());
            InspectionAdd(e);
        });
        // 更改楼层
        checkInsBuildFloor(entity);
        // 检查建筑物是否完成
        checkInsBuild(entity);
        // 检查单位是否完成
        checkInspection(entity);
        return null;
    }

    public void checkInsBuildFloor(HisInspectionVo entity) {
        //  更改完成
        TBaseInsBuildFloor floor = baseInsBuildFloorService.selectById(entity.getInsBuildFloorId());
        floor.setStatus(2);
        baseInsBuildFloorService.updateById(floor);
    }
    public void checkInsBuild(HisInspectionVo entity) {
        // 检查建筑物是否完成
        TBaseInsBuild build = baseInsBuildService.selecById(entity.getInsBuildId());
        Boolean flag = baseInsBuildFloorService.checkFloorCountGroupByStatus(entity.getInsBuildId());
        if(flag == true){
            build.setStatus(2);
        }
        baseInsBuildService.updateById(build);
    }
    public void checkInspection(HisInspectionVo entity) {
        // 检查单位是否完成
        TBaseInspection ins = baseInspectionService.selectBaseInspectionByID(entity.getInspectionId());
        Boolean flag = baseInsBuildService.checkBuildCountGroupByStatus(entity.getInspectionId());
        if(flag == true){
            ins.setStatus(2);
        }
        try {
            ins.setPointRepair(getHisInsDetailsList(entity.getInspectionId(), InstallConstant.HIS_INS_DEVICE_STATUS_REPAIR).size());
            ins.setPointReplace(getHisInsDetailsList(entity.getInspectionId(), InstallConstant.HIS_INS_DEVICE_STATUS_REPLACE).size());
            ins.setPointLose(getHisInsDetailsList(entity.getInspectionId(), InstallConstant.HIS_INS_DEVICE_STATUS_LOSE).size());

            ins.setAccessNum(getHisInsDetailsAccessList(entity.getInspectionId(), InstallConstant.HIS_INS_DEVICE_ACCESS_STATUS).size());
        }catch (Exception e){
            e.printStackTrace();
        }
        baseInspectionService.updateById(ins);
    }

    @Override
    public THisInspectionDetails InspectionAdd(THisInspectionDetails entity){
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        entity.setId(idWorker.nextId());
        entity.setCreateTime(LocalDateTime.now());
        tHisInspectionDetailsMapper.insert(entity);
        return entity;
    }
    public List<THisInspectionDetails> getHisInsDetailsList(Long inspectionId,Integer deviceStatus) {
        THisInspectionDetails vo = new THisInspectionDetails();
        vo.setDeviceStatus(deviceStatus);
        vo.setInspectionId(inspectionId);
        List<THisInspectionDetails> tHisInspectionDeviceList=tHisInspectionDetailsMapper.getHisInsDetailsList(vo);
        return tHisInspectionDeviceList;
    }
    public List<THisInspectionDetails> getHisInsDetailsAccessList(Long inspectionId,Integer deviceStatus) {
        THisInspectionDetails vo = new THisInspectionDetails();
        vo.setInspectionId(inspectionId);
        vo.setFireEngineAccess(deviceStatus);
        List<THisInspectionDetails> tHisInspectionDeviceList=tHisInspectionDetailsMapper.getHisInsDetailsList(vo);
        return tHisInspectionDeviceList;
    }
    @Override
    public HisInspectionVo getInsDetailsGroup(Long inspectionId){
        HisInspectionVo vo = new HisInspectionVo();
        TBaseInspection ins = tBaseInspectionMapper.selectById(inspectionId);

        vo.setCompanyName(ins.getCompanyName());
        vo.setFirefighterName(ins.getFirefighterName());
        vo.setFirefighterphone(ins.getPhone());
        vo.setCreateTime(ins.getCreateTime());
        vo.setPointNum(ins.getPointNum());

        vo.setRepairList(getHisInsDetailsList(inspectionId, InstallConstant.HIS_INS_DEVICE_STATUS_REPAIR));
        vo.setReplaceList(getHisInsDetailsList(inspectionId, InstallConstant.HIS_INS_DEVICE_STATUS_REPLACE));
        vo.setLoseList(getHisInsDetailsList(inspectionId, InstallConstant.HIS_INS_DEVICE_STATUS_LOSE));

        vo.setAccessList(getHisInsDetailsAccessList(inspectionId, InstallConstant.HIS_INS_DEVICE_ACCESS_STATUS));
        return vo;
    };



    @Override
    public void UpdateById(THisInspectionDetails tHisInspectionDetails) {
        tHisInspectionDetailsMapper.updateById(tHisInspectionDetails);
    }

    @Override
    public THisInspectionDetails selectId(Long fireId) {
        return tHisInspectionDetailsMapper.selectId(fireId);
    }


}
