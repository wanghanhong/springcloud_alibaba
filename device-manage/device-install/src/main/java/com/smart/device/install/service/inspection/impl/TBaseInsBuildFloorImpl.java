package com.smart.device.install.service.inspection.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.TBaseFirehydrant;
import com.smart.device.common.install.entity.TBaseInsBuildFloor;
import com.smart.device.install.entity.vo.DeviceVo;
import com.smart.device.install.entity.vo.InsBuildFloorVo;
import com.smart.device.install.entity.vo.InsBuildingVo;
import com.smart.device.install.mapper.TBaseFirehydrantMapper;
import com.smart.device.install.mapper.TManagerElectricMapper;
import com.smart.device.install.mapper.TManagerSmokeMapper;
import com.smart.device.install.mapper.TManagerWaterpressMapper;
import com.smart.device.install.mapper.inspection.TBaseInsBuildFloorMapper;
import com.smart.device.install.service.inspection.ITBaseInsBuildFloorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author f
 */
@Slf4j
@Service
public class TBaseInsBuildFloorImpl extends ServiceImpl<TBaseInsBuildFloorMapper, TBaseInsBuildFloor> implements ITBaseInsBuildFloorService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseInsBuildFloorMapper tBaseInsBuildFloorMapper;
    @Resource
    private TManagerElectricMapper tManagerElectricMapper;
    @Resource
    private TManagerSmokeMapper tManagerSmokeMapper;
    @Resource
    private TManagerWaterpressMapper tManagerWaterpressMapper;
    @Resource
    private TBaseFirehydrantMapper tBaseFirehydrantMapper;

    @Override
    public TBaseInsBuildFloor baseInsBuildFloorAdd(TBaseInsBuildFloor entity) {
        IdWorker idWorker = new IdWorker(0, 0);

        TBaseInsBuildFloor floor = tBaseInsBuildFloorMapper.selectById(entity.getId()) ;
        if(floor == null || floor.getId() == null){
            entity.setId(idWorker.nextId());
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else {
            entity.setUpdateTime(LocalDateTime.now());
            this.updateById(entity);
        }
        return entity;
    }

    @Override
    public TBaseInsBuildFloor selectById(Long id) {
        return tBaseInsBuildFloorMapper.selectById(id);
    }

    @Override
    public int baseInsBuildFloorDel(Long id) {
        return tBaseInsBuildFloorMapper.deleteById(id);
    }

    @Override
    public int delByInspectionId(Long buildingId) {
        List<TBaseInsBuildFloor> list = selecByInspectionId(buildingId);
        for (int i=0;i<list.size();i++){
            baseInsBuildFloorDel(list.get(0).getId());
        }
        return 0;
    }

    @Override
    public int baseInsBuildFloorListAdd(InsBuildingVo entity) {
        List<InsBuildFloorVo> list  = entity.getInsBuildFloors();
        if(list != null){
            for (int i=0;i<list.size();i++){
                InsBuildFloorVo vo = list.get(i);
                TBaseInsBuildFloor bean =new TBaseInsBuildFloor();

                if(vo.getInsBuildFloorId() != null){
                    log.info("ins-update-"+ JSONObject.toJSONString(vo));
                    setInsBuildFloorAttrUpdate(vo, bean);
                }else {
                    log.info("ins-add-"+ JSONObject.toJSONString(vo));
                    setInsBuildFloorAttr(entity, vo, bean);
                }
                setBuildFloorNum(vo, bean);
                baseInsBuildFloorAdd(bean);
            }
        }
        return 0;
    }

    private void setBuildFloorNum(InsBuildFloorVo vo, TBaseInsBuildFloor bean) {
        int floorPointNum = 0;
        if( vo.getEscapeRouteIs() != null && vo.getEscapeRouteIs() == 1){
            floorPointNum +=(vo.getEscapeRouteNum());
        }
        if(vo.getFirehydrantIs() != null && vo.getFirehydrantIs() == 1){
            floorPointNum +=(vo.getFirehydrantNum());
        }
        if(vo.getFireAllIs() != null && vo.getFireAllIs() == 1){
            floorPointNum +=(vo.getFireAllNum());
        }
        bean.setFloorPointNum(floorPointNum);
    }

    @Override
    public List<TBaseInsBuildFloor> selecByInspectionId(Long parentId){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("inspection_id",parentId);
        List<TBaseInsBuildFloor> list = tBaseInsBuildFloorMapper.selectByMap(columnMap);
        return list;
    }

    //  根据 巡检ID、建筑物ID 和楼层 查询 当前楼层所有的绑定的设备
    @Override
    public List<InsBuildFloorVo> selecByInsAndBuildAndFloor(InsBuildFloorVo vo) {
        if(vo.getSortno() != null && "2".equals(vo.getSortno()) ){
            vo.setSortno("desc");
        }else{
            vo.setSortno("asc");
        }
        vo.setStatus(0);
        List<InsBuildFloorVo> floorVos = tBaseInsBuildFloorMapper.selecByInsAndBuildAndFloor(vo);
        if(floorVos != null && floorVos.size() == 1){
            // 查询详细的时候才返回详细信息，如果是查询列表，就没有详细信息
            InsBuildFloorVo floorVo = floorVos.get(0);
            List<DeviceVo> list = new ArrayList<>();

            DeviceVo device = new DeviceVo();
            device.setCompanyId(vo.getCompanyId());
            device.setBuildingId(vo.getBuildingId());
            device.setBuildingFloor(vo.getFloorNum());
            List<DeviceVo> electlist = tManagerElectricMapper.electricListNoPage(device);
            List<DeviceVo> smokelist = tManagerSmokeMapper.smokeListNoPage(device);
            List<DeviceVo> waterpresslist = tManagerWaterpressMapper.wterpressNoPage(device);
            list.addAll(electlist);list.addAll(smokelist);list.addAll(waterpresslist);

            floorVo.setDeviceVos(list);

            TBaseFirehydrant drant = new TBaseFirehydrant();
            drant.setCompanyId(vo.getCompanyId());
            drant.setBuildingId(vo.getBuildingId());
            drant.setBuildingFloor(vo.getFloorNum());
            List<TBaseFirehydrant> drants = tBaseFirehydrantMapper.baseFirehydrantNoPage(drant);
            floorVo.setFirehydrantVos(drants);
        }
        return floorVos;
    }



    private void setInsBuildFloorAttr(InsBuildingVo entity, InsBuildFloorVo vo, TBaseInsBuildFloor bean) {
        bean.setId(vo.getInsBuildFloorId());

        bean.setInspectionId(entity.getInspectionId());
        bean.setBuildingId(entity.getBuildingId());
        bean.setBuildingName(entity.getBuildingName());
        bean.setInsBuildId(entity.getInsBuildId());

        bean.setFloorNum(vo.getFloorNum());
        bean.setEscapeRouteNum(vo.getEscapeRouteNum());
        bean.setEscapeRouteIs(vo.getEscapeRouteIs());
        bean.setFirehydrantNum(vo.getFirehydrantNum());
        bean.setFirehydrantIs(vo.getFirehydrantIs());
        bean.setFireAllNum(vo.getFireAllNum());
        bean.setFireAllIs(vo.getFireAllIs());

    }

    private void setInsBuildFloorAttrUpdate(InsBuildFloorVo vo, TBaseInsBuildFloor bean) {
        bean.setId(vo.getInsBuildFloorId());
        bean.setEscapeRouteIs(vo.getEscapeRouteIs());
        bean.setFirehydrantIs(vo.getFirehydrantIs());
        bean.setFireAllIs(vo.getFireAllIs());
        this.updateById(bean);
    }


    @Override
    public boolean checkFloorCountGroupByStatus(Long insBuildId) {
        Map<Integer,Integer> map = tBaseInsBuildFloorMapper.getFloorCountGroupByStatus(insBuildId);
        if(!map.isEmpty()){
            if(String.valueOf(map.get("count0")).equals(String.valueOf(map.get("count2"))) ){
                return true;
            }
        }
        return false;
    }

}
