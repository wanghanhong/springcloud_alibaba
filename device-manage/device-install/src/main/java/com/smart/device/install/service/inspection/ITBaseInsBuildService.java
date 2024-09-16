package com.smart.device.install.service.inspection;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.TBaseInsBuild;
import com.smart.device.install.entity.vo.InspectionVo;

import java.util.List;
import java.util.Map;

/**
 * @author f
 */
public interface ITBaseInsBuildService extends IService<TBaseInsBuild> {

    /**
     * 巡检添加
     *
     * @param entity
     * @return
     */
    TBaseInsBuild baseInsBuildAdd(TBaseInsBuild entity);

    int baseInsBuildListAdd(InspectionVo entity);

    /**
     * 巡检删除
     *
     * @param
     * @return
     */
    int baseInsBuildDel(Long id);
    int delByInspectionId(Long inspectionId);

    TBaseInsBuild selecById(Long id);

    /**
     * 巡检修改
     *
     * @param entity
     * @return
     */
    TBaseInsBuild updateInsBuildById(TBaseInsBuild entity);
    int baseInsBuildUpdate(InspectionVo entity);

    /**
     *  根据InspectionId 查询列表
     *
     * @param
     * @return
     */
    List<TBaseInsBuild> selecByInspectionId(Long inspectionId);

    // 根据InspectionId 查询 二级和和三级列表
    List<TBaseInsBuild> insAndInsFloorByInsId(Long inspectionId);

    boolean checkBuildCountGroupByStatus(Long inspectionId);


}
