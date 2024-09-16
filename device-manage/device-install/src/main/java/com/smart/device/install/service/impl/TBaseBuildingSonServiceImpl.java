package com.smart.device.install.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.TBaseBuilding;
import com.smart.device.common.install.entity.TBaseBuildingSon;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.install.mapper.*;
import com.smart.device.install.service.ITBaseBuildingSonService;
import com.smart.device.install.service.ITBaseRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author f
 */
@Slf4j
@Service
public class TBaseBuildingSonServiceImpl extends ServiceImpl<TBaseBuildingSonMapper, TBaseBuildingSon> implements ITBaseBuildingSonService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseBuildingSonMapper tBaseBuildingSonMapper;
    @Resource
    private TManagerSmokeMapper managerSmokeMapper;
    @Resource
    private TManagerWaterpressMapper managerWaterpressMapper;
    @Resource
    private TManagerElectricMapper managerElectricMapper;
    @Resource
    private TBaseFirehydrantMapper baseFirehydrantMapper;



    @Override
    public TBaseBuildingSon baseBuildingAdd(TBaseBuildingSon entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        entity.setId(idWorker.nextId());
        entity.setCreateTime(LocalDateTime.now());
        this.save(entity);
        log.info("ccv-entity"+JSONObject.toJSONString(entity));
        return entity;
    }
    @Override
    public int baseBuildingAdd(List<TBaseBuildingSon> list,TBaseBuilding building) {
        log.info("zzy-son列表"+JSONObject.toJSONString(list));
        log.info("zxx-build"+JSONObject.toJSONString(building));
        if(list != null){
            for (int i=0;i<list.size();i++){
                TBaseBuildingSon entity = list.get(i);

                entity.setBuildingId(building.getId());
                baseBuildingAdd(entity);
            }
        }
        return 0;
    }
    @Override
    public int baseBuildingAdd(TBaseBuilding tBaseBuilding) {
        List<TBaseBuildingSon> list = tBaseBuilding.getBuildingSons();
        if(list != null){
            for (int i=0;i<list.size();i++){
                TBaseBuildingSon entity = list.get(i);

                entity.setParentId(tBaseBuilding);
                baseBuildingAdd(entity);
            }
        }
        return 0;
    }
    @Override
    public int baseBuildingDel(Long id) {
        return tBaseBuildingSonMapper.deleteById(id);
    }

    @Override
    public int baseBuildingDelBybuildingId(Long buildingId) {
        List<TBaseBuildingSon> list = selecByBuildingId(buildingId);
        for (int i=0;i<list.size();i++){
            baseBuildingDel(list.get(i).getId());
        }
        return 0;
    }

    @Override
    public TBaseBuildingSon baseBuildingUpdate(TBaseBuildingSon entity) {
        entity.setUpdateTime(LocalDateTime.now());
        tBaseBuildingSonMapper.updateById(entity);
        return entity;
    }

    @Override
    public int baseBuildingUpdate(List<TBaseBuildingSon> list) {
        if(list != null){
            for (int i=0;i<list.size();i++){
                baseBuildingUpdate(list.get(i));
            }
        }
        return 0;
    }

    @Override
    public List<TBaseBuildingSon> selecByBuildingId(Long buildingId){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("building_id",buildingId);
        List<TBaseBuildingSon> list = tBaseBuildingSonMapper.selectByMap(columnMap);
        return list;
    }

    @Override
    public TBaseBuildingSon selectBaseBuildingSon(Long buildingId,Integer floorNum) {
        List<TBaseBuildingSon> list = tBaseBuildingSonMapper.selectBaseBuildingSon(buildingId,floorNum);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return new TBaseBuildingSon();
    }

    @Override
    public void calAndSetFloorDeviceNum(Long buildingId, Integer buildingFloor) {
        TBaseBuildingSon son = selectBaseBuildingSon(buildingId,buildingFloor);
        int smokeNum = managerSmokeMapper.getSmokeNumByFloor(buildingId,buildingFloor);
        int waterpressNum = managerWaterpressMapper.getWaterpressNumByFloor(buildingId,buildingFloor);
        int electricNum = managerElectricMapper.getElectricNumByFloor(buildingId,buildingFloor);
        int firehydrantNum = baseFirehydrantMapper.getFirehydrantNumByFloor(buildingId,buildingFloor);
        int allNum = smokeNum+waterpressNum+electricNum;
        try {
            son.setSmokeNum(smokeNum);
            son.setWaterpressNum(waterpressNum);
            son.setElectricNum(electricNum);
            son.setFirehydrantNum(firehydrantNum);

            son.setFireAllNum(allNum);
            baseBuildingUpdate(son);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
