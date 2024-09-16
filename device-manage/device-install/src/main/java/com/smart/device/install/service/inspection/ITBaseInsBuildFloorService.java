package com.smart.device.install.service.inspection;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.TBaseInsBuildFloor;
import com.smart.device.install.entity.vo.InsBuildFloorVo;
import com.smart.device.install.entity.vo.InsBuildingVo;

import java.util.List;

/**
 * @author f
 */
public interface ITBaseInsBuildFloorService extends IService<TBaseInsBuildFloor> {

    /**
     * 巡检添加
     *
     * @param entity
     * @return
     */
    TBaseInsBuildFloor baseInsBuildFloorAdd(TBaseInsBuildFloor entity);

    int baseInsBuildFloorListAdd(InsBuildingVo entity);

    TBaseInsBuildFloor selectById(Long id);
    /**
     * 巡检删除
     *
     * @param
     * @return
     */
    int baseInsBuildFloorDel(Long id);
    int delByInspectionId(Long InspectionId);

    /**
     *  根据InspectionId 查询列表
     *
     * @param
     * @return
     */
    List<TBaseInsBuildFloor> selecByInspectionId(Long InspectionId);

    /**
     *  根据 巡检ID、建筑物ID 和楼层 查询 当前楼层所有的绑定的设备
     *     *
     * @param
     * @return
     */

    List<InsBuildFloorVo> selecByInsAndBuildAndFloor(InsBuildFloorVo vo);


    boolean checkFloorCountGroupByStatus(Long insBuildId);

}
