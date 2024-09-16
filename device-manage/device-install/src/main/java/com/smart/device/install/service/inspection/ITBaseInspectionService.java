package com.smart.device.install.service.inspection;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TBaseInspection;
import com.smart.device.common.install.entity.THisInspection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface ITBaseInspectionService extends IService<TBaseInspection> {

    /**
     * 烟感列表
     *
     * @param entity
     * @return
     */
    IPage<TBaseInspection> baseInspectionList(PageDomain page, TBaseInspection entity);

    /**
     * 烟感添加
     *
     * @param entity
     * @return
     */
    TBaseInspection baseInspectionAdd(TBaseInspection entity);

    /**
     * 烟感删除
     *
     * @param
     * @return
     */
    int baseInspectionDel(Long id);

    /**
     * 烟感修改
     *
     * @param entity
     * @return
     */
    TBaseInspection baseInspectionUpdateAndFirefighter(TBaseInspection entity);

    TBaseInspection baseInspectionUpdat(TBaseInspection entity);
    /**
     *  根据ID查询详情
     *
     * @param
     * @return
     */
    TBaseInspection selectBaseInspectionByID(Long id);

    List<TBaseInspection> insAndInsBuildsList(@Param("vo") TBaseInspection vo);





}
