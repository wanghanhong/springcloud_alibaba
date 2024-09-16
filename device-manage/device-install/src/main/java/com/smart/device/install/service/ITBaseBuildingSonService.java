package com.smart.device.install.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.TBaseBuilding;
import com.smart.device.common.install.entity.TBaseBuildingSon;
import java.util.List;


/**
 * @author f
 */
public interface ITBaseBuildingSonService extends IService<TBaseBuildingSon> {

    
    /**
     * 建筑物设备添加
     *
     * @param entity
     * @return
     */
    TBaseBuildingSon baseBuildingAdd(TBaseBuildingSon entity);

    int baseBuildingAdd(List<TBaseBuildingSon> list,TBaseBuilding building);

    int baseBuildingAdd(TBaseBuilding baseBuilding);

    /**
     * 建筑物设备删除
     *
     * @param
     * @return
     */
    int baseBuildingDel(Long id);
    int baseBuildingDelBybuildingId(Long buildingId);
    /**
     * 建筑物设备修改
     *
     * @param entity
     * @return
     */
    TBaseBuildingSon baseBuildingUpdate(TBaseBuildingSon entity);
    int baseBuildingUpdate(List<TBaseBuildingSon> list);

    /**
     *  根据buildingId 查询列表
     *
     * @param
     * @return
     */
    List<TBaseBuildingSon> selecByBuildingId(Long buildingId);

    // 根据 建筑物和楼层 查询某一层的设备。
    TBaseBuildingSon selectBaseBuildingSon(Long buildingId,Integer floorNum);


    void calAndSetFloorDeviceNum(Long buildingId,Integer buildingFloor);

}
