package com.smart.device.install.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TBasePic;

/**
 * @author f
 */
public interface ITBasePicService extends IService<TBasePic> {

    /**
     * 烟感列表
     *
     * @param entity
     * @return
     */
    IPage<TBasePic> basePicList(PageDomain page, TBasePic entity);

    /**
     * 烟感添加
     *
     * @param entity
     * @return
     */
    TBasePic basePicAdd(TBasePic entity);

    /**
     * 烟感删除
     *
     * @param
     * @return
     */
    int basePicDel(Long id);

    /**
     * 烟感修改
     *
     * @param entity
     * @return
     */
    TBasePic basePicUpdate(TBasePic entity);
    /**
     *  根据ID查询详情
     *
     * @param
     * @return
     */
    TBasePic selectBasePicByID(Long id);

}
