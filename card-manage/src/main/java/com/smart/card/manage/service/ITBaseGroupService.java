package com.smart.card.manage.service;

import com.smart.card.manage.entity.TBaseGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITBaseGroupService extends IService<TBaseGroup> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TBaseGroup> tBaseGroupList(PageDomain page,TBaseGroup entity);

    TBaseGroup tBaseGroupAdd(TBaseGroup entity);

    TBaseGroup tBaseGroupUpdate(TBaseGroup entity);

    int tBaseGroupDel(Long id);

    TBaseGroup tBaseGroupDetail(TBaseGroup entity);
    /**------基本方法结束-----------------------------------------*/

}
