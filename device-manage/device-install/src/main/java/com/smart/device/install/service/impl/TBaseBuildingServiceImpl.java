package com.smart.device.install.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.TBaseBuilding;
import com.smart.device.common.install.entity.TBaseBuildingSon;
import com.smart.device.install.mapper.TBaseBuildingMapper;
import com.smart.device.install.service.ITBaseBuildingService;
import com.smart.device.install.service.ITBaseBuildingSonService;
import com.smart.device.install.service.ITBaseRegionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author f
 */
@Slf4j
@Service
public class TBaseBuildingServiceImpl extends ServiceImpl<TBaseBuildingMapper, TBaseBuilding> implements ITBaseBuildingService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseBuildingMapper tBaseBuildingMapper;
    @Resource
    private ITBaseRegionService tBaseRegionService;
    @Resource
    private ITBaseBuildingSonService itBaseBuildingSonService;

    @Override
    public IPage<TBaseBuilding> baseBuildingList(PageDomain page, TBaseBuilding vo) {
        Page<TBaseBuilding> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBaseBuilding> iPage = tBaseBuildingMapper.baseBuildingList(pg,vo);

        List<TBaseBuilding> list = iPage.getRecords();
        String str = tBaseRegionService.geTBaseRegionsBySdBaseBuilding(list);
        Map<String,String> map = tBaseRegionService.mapRegions(str);

        for (int i = 0;i<list.size();i++){
            TBaseBuilding entity = list.get(i);
            list.get(i).setAreaName(getStringAreaName(entity, map));
        }
        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public TBaseBuilding baseBuildingAdd(TBaseBuilding entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long buildId = idWorker.nextId();
        if(entity.getId() == null ){

            entity.setId(buildId);
            entity.setCreateTime(LocalDateTime.now());
            entity.setBuildingCode(idWorker.nextId()+"");

            calFloorMax(entity);

            this.save(entity);

            itBaseBuildingSonService.baseBuildingAdd(entity.getBuildingSons(),entity);
        }
        return entity;
    }
    // 计算 最高层和最低层
    public TBaseBuilding calFloorMax(TBaseBuilding entity) {
         List<TBaseBuildingSon> list = entity.getBuildingSons();
         int floormax = 1;
         int floormin = 1;
         if(list != null){
             for ( int i = 0;i<list.size();i++) {
                 int floornum = list.get(i).getFloorNum();
                 if(floornum > floormax){
                     floormax = floornum;
                 }
                 if(floornum < floormin){
                     floormin = floornum;
                 }
             }
         }
        int floorCount = 0;
        if(floormin > 0){
            floorCount = floormax - floormin +1;
         }else{
            floorCount = floormax - floormin;
        }
        entity.setFloorMax(floormax);
        entity.setFloorMin(floormin);
        entity.setFloorCount(floorCount);
        return entity;
    }

    @Override
    public int baseBuildingDel(Long id) {
        // 检测如果建筑物绑定了设备，则不允许删除
        int countx = tBaseBuildingMapper.queryBuildCanDel(id);
        int res = 0;
        if(countx > 0){
        }else{
            itBaseBuildingSonService.baseBuildingDelBybuildingId(id);
            tBaseBuildingMapper.deleteById(id);
            res = 1;
        }
        return res;
    }

    @Override
    public TBaseBuilding baseBuildingUpdate(TBaseBuilding entity) {
//        itBaseBuildingSonService.baseBuildingUpdate(entity.getBuildingSons());
//        entity.setUpdateTime(LocalDateTime.now());
        itBaseBuildingSonService.baseBuildingDelBybuildingId(entity.getId());
        if(entity.getBuildingSons() != null){
            itBaseBuildingSonService.baseBuildingAdd(entity.getBuildingSons(),entity);
        }
        calFloorMax(entity);
        tBaseBuildingMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseBuilding selectBaseBuildingByID(Long id) {
        TBaseBuilding entity = tBaseBuildingMapper.selectById(id);
        if(entity != null){
            List<TBaseBuildingSon> list = itBaseBuildingSonService.selecByBuildingId(entity.getId());
            entity.setBuildingSons(list);
        }
        String codes =  selectCodes(entity);
        Map<String,String> map = tBaseRegionService.mapRegions(codes);
        entity.setAreaName(getStringAreaName(entity, map));
        return entity;
    }

    private String getStringAreaName(TBaseBuilding entity, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if(map.get(entity.getProvince()) != null){
            sb.append(map.get(entity.getProvince()));
        }
        if(map.get(entity.getCity()) != null){
            sb.append("/"+map.get(entity.getCity()));
        }
        if(map.get(entity.getCounty()) != null){
            sb.append("/"+map.get(entity.getCounty()));
        }
        if(map.get(entity.getTown()) != null){
            sb.append("/"+map.get(entity.getTown()));
        }
        return sb.toString();
    }

    /**------通用方法开始结束-----------------------------------------*/
    public String selectCodes(TBaseBuilding ebean) {
        String str = "";
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(ebean.getProvince())){
            sb.append(ebean.getProvince());
        }
        if(StringUtils.isNotBlank(ebean.getCity()) ){
            sb.append(","+ebean.getCity());
        }
        if(StringUtils.isNotBlank(ebean.getCounty()) ){
            sb.append(","+ebean.getCounty());
        }
        if(StringUtils.isNotBlank(ebean.getTown()) ){
            sb.append(","+ebean.getTown());
        }
        return sb.toString();
    }

    @Override
    public List<TBaseBuilding> selectBuildings(TBaseBuilding vo) {
        List<TBaseBuilding> list = tBaseBuildingMapper.selectBuildings(vo);
        return list;
    }

    @Override
    public List<Integer> selectFloors(String buildId) {
        List<Integer> list = new ArrayList<>();
        try {
            TBaseBuilding building = tBaseBuildingMapper.selectById(Long.valueOf(buildId));
            if(building != null){
                Integer floormax = building.getFloorMax();
                Integer floormin = building.getFloorMin();
                if(floormax != null && floormin != null){
                    for (int i = floormin; i <= floormax; i++){
                        if(i != 0){
                            list.add(i);
                        }
                    }
                }else{
                    list.add(1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            list.add(1);
        }
        return list;
    }

    @Override
    public List<TBaseBuilding> baseBuildingAndSonList(Long companyId,Long buildingId){
        List<TBaseBuilding> list = tBaseBuildingMapper.baseBuildingAndSonList(companyId,buildingId);
        return list;
    }



}
