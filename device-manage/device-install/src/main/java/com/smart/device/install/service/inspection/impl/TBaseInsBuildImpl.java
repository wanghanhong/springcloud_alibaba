package com.smart.device.install.service.inspection.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.*;
import com.smart.device.install.entity.vo.InsBuildFloorVo;
import com.smart.device.install.entity.vo.InsBuildingVo;
import com.smart.device.install.entity.vo.InspectionVo;
import com.smart.device.install.mapper.inspection.TBaseInsBuildFloorMapper;
import com.smart.device.install.mapper.inspection.TBaseInsBuildMapper;
import com.smart.device.install.service.ITBaseBuildingService;
import com.smart.device.install.service.inspection.ITBaseInsBuildFloorService;
import com.smart.device.install.service.inspection.ITBaseInsBuildService;
import com.smart.device.install.service.inspection.ITBaseInspectionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author f
 */
@Service
public class TBaseInsBuildImpl extends ServiceImpl<TBaseInsBuildMapper, TBaseInsBuild> implements ITBaseInsBuildService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private ITBaseInspectionService baseInspectionService;
    @Resource
    private TBaseInsBuildMapper tBaseInsBuildMapper;
    @Resource
    private ITBaseInsBuildFloorService itBaseInsBuildFloorService;
    @Resource
    private ITBaseBuildingService iTBaseBuildingService;

    @Override
    public TBaseInsBuild baseInsBuildAdd(TBaseInsBuild entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        if(entity.getId() == null ){
            entity.setId(idWorker.nextId());
            entity.setCreateTime(LocalDateTime.now());
            entity.setStatus(0);
            this.save(entity);
        }else {
            entity.setUpdateTime(LocalDateTime.now());
            this.updateById(entity);
        }
        return entity;
    }

    // 巡检计划添加接口-2
    @Override
    public int baseInsBuildListAdd(InspectionVo entity) {
        baseInsBuildUpdateAndFloor(entity);
        return 0;
    }
    // 巡检计划修改接口-2
    @Override
    public int baseInsBuildUpdate(InspectionVo entity) {
        baseInsBuildUpdateAndFloor(entity);
        return 0;
    }

    public void baseInsBuildUpdateAndFloor(InspectionVo entity) {
        List<InsBuildingVo> list  = entity.getInsBuilds();
        Integer pointNum = 0;
        if(list != null){
            for (int i=0;i<list.size();i++){
                InsBuildingVo vo = list.get(i);
                TBaseInsBuild bean = new TBaseInsBuild();

                /***************************/
                getAndSetBuildAttr(vo, bean);
                //  更改巡检计划总数量
                int buildNum = calbuildNum(vo);
                bean.setBuildPointNum(buildNum);
                //  可能没有保存
                bean = baseInsBuildAdd(bean);
                /*****************************/

                vo.setInsBuildId(bean.getId());
                itBaseInsBuildFloorService.baseInsBuildFloorListAdd(vo);

                pointNum +=  buildNum;
            }
        }
        TBaseInspection inspection = new TBaseInspection();
        inspection.setId(entity.getInspectionId());
        inspection.setPointNum(pointNum);
        baseInspectionService.baseInspectionUpdat(inspection);
    }

    private void getAndSetBuildAttr(InsBuildingVo vo, TBaseInsBuild bean){
        bean.setId(vo.getInsBuildId());

        bean.setInspectionId(vo.getInspectionId());
        bean.setInsBuildId(vo.getInsBuildId());
        bean.setBuildingId(vo.getBuildingId());
        bean.setBuildingName(vo.getBuildingName());

        TBaseBuilding building = new TBaseBuilding();
        building.setId(vo.getBuildingId());
        try{
            List<TBaseBuilding> builds = iTBaseBuildingService.selectBuildings(building);
            if(builds != null && builds.size() > 0){
                bean.setFloorMax(builds.get(0).getFloorMax());
                bean.setFloorMin(builds.get(0).getFloorMin());
                bean.setFloorCount(builds.get(0).getFloorCount());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int calbuildNum(InsBuildingVo buildingVo) {
        List<InsBuildFloorVo> list  = buildingVo.getInsBuildFloors();
        int buildNum = 0;
        if(list != null){
            for (int i=0;i<list.size();i++){
                InsBuildFloorVo vo = list.get(i);
                if(vo.getEscapeRouteIs() != null && vo.getEscapeRouteIs() == 1){
                    buildNum +=(vo.getEscapeRouteNum());
                }
                if(vo.getFirehydrantIs() != null && vo.getFirehydrantIs() == 1){
                    buildNum +=(vo.getFirehydrantNum());
                }
                if(vo.getFireAllIs() != null && vo.getFireAllIs() == 1){
                    buildNum +=(vo.getFireAllNum());
                }
            }
        }
        return buildNum;
    }

    @Override
    public int baseInsBuildDel(Long id) {
        return tBaseInsBuildMapper.deleteById(id);
    }

    @Override
    public int delByInspectionId(Long InspectionId) {
        List<TBaseInsBuild> list = selecByInspectionId(InspectionId);
        for (int i=0;i<list.size();i++){
            baseInsBuildDel(list.get(0).getId());
        }
        return 0;
    }

    @Override
    public TBaseInsBuild selecById(Long id) {
        return tBaseInsBuildMapper.selectById(id);
    }

    @Override
    public TBaseInsBuild updateInsBuildById(TBaseInsBuild entity) {
        entity.setUpdateTime(LocalDateTime.now());
        tBaseInsBuildMapper.updateById(entity);
        return entity;
    }

    @Override
    public List<TBaseInsBuild> selecByInspectionId(Long parentId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("inspection_id",parentId);
        List<TBaseInsBuild> list = tBaseInsBuildMapper.selectByMap(columnMap);
        return list;
    }

    @Override
    public List<TBaseInsBuild> insAndInsFloorByInsId(Long inspectionId) {

        List<TBaseInsBuild> list = tBaseInsBuildMapper.insAndInsFloorByInsId(inspectionId);
        TBaseInspection ins = baseInspectionService.getById(inspectionId);

        if(list == null || list.size() == 0){
            // 该 单位下所有的 楼及楼层
            list = changeIns(ins);
        }
        return list;
    }

    public List<TBaseInsBuild> changeIns(TBaseInspection ins){
        List<TBaseInsBuild> list = new ArrayList<>();
        // 该 单位下所有的 楼及楼层
        List<TBaseBuilding> baseBuildings = iTBaseBuildingService.baseBuildingAndSonList(ins.getCompanyId(),null);
        for(int i=0;i<baseBuildings.size();i++){
            TBaseBuilding build = baseBuildings.get(i);

            TBaseInsBuild insbuild = new TBaseInsBuild();

            insbuild.setInspectionId(ins.getId());
            insbuild.setBuildingId(build.getId());
            insbuild.setBuildingName(build.getBuildingName());
            insbuild.setCompanyId(build.getCompanyId());

            List<TBaseBuildingSon> buildingSons = build.getBuildingSons();
            List<TBaseInsBuildFloor> insBuildFloors = new ArrayList<>();

            for(int j=0;j<buildingSons.size();j++){
                TBaseBuildingSon floor = buildingSons.get(j);
                TBaseInsBuildFloor insfloor = new TBaseInsBuildFloor();

                insfloor.setInspectionId(ins.getId());
                insfloor.setFloorNum(floor.getFloorNum());
                insfloor.setEscapeRouteNum(floor.getEscapeRouteNum());
                insfloor.setFirehydrantNum(floor.getFirehydrantNum());
                insfloor.setFireAllNum(floor.getFireAllNum());

                insBuildFloors.add(insfloor);
            }
            insbuild.setInsBuildFloors(insBuildFloors);
            list.add(insbuild);
        }
        return list;
    }

    @Override
    public boolean checkBuildCountGroupByStatus(Long inspectionId) {
        Map<Integer,Integer> map = tBaseInsBuildMapper.getBuildCountGroupByStatus(inspectionId);
        if(!map.isEmpty()){
            if(String.valueOf(map.get("count0")).equals(String.valueOf(map.get("count2"))) ){
                return true;
            }
        }
        return false;
    }

}
