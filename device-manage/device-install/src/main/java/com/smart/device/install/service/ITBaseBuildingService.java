package com.smart.device.install.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TBaseBuilding;

import java.util.List;

/**
 * @author f
 */
public interface ITBaseBuildingService extends IService<TBaseBuilding> {

    List<Integer> selectFloors(String buildId);

    List<TBaseBuilding> selectBuildings(TBaseBuilding vo);

    /**
     * 建筑物列表
     *
     * @param entity
     * @return
     */
    IPage<TBaseBuilding> baseBuildingList(PageDomain page, TBaseBuilding entity);

    /**
     * 建筑物添加
     *
     * @param entity
     * @return
     */
    TBaseBuilding baseBuildingAdd(TBaseBuilding entity);

    /**
     * 建筑物删除
     *
     * @param
     * @return
     */
    int baseBuildingDel(Long id);

    /**
     * 建筑物修改
     *
     * @param entity
     * @return
     */
    TBaseBuilding baseBuildingUpdate(TBaseBuilding entity);
    /**
     *  根据ID查询详情
     *
     * @param
     * @return
     */
    TBaseBuilding selectBaseBuildingByID(Long id);

    /**
     * 根据单位查询建筑物列表，然后在查询设备详情
     *
     * @param
     * @return
     */
    List<TBaseBuilding> baseBuildingAndSonList(Long companyId,Long buildId);

}
