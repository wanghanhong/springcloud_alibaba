package com.smart.device.install.service.inspection;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TBaseFirefighter;

/**
 * @author f
 */
public interface ITBaseFirefighterService extends IService<TBaseFirefighter> {

    /**
     * 消防责任人列表
     *
     * @param entity
     * @return
     */
    IPage<TBaseFirefighter> baseFirefighterList(PageDomain page, TBaseFirefighter entity);

    /**
     * 消防责任人添加
     *
     * @param entity
     * @return
     */
    TBaseFirefighter baseFirefighterAdd(TBaseFirefighter entity);

    /**
     * 消防责任人删除
     *
     * @param
     * @return
     */
    int baseFirefighterDel(Long id);

    /**
     * 消防责任人修改
     *
     * @param entity
     * @return
     */
    TBaseFirefighter baseFirefighterUpdate(TBaseFirefighter entity);
    /**
     *  根据ID查询详情
     *
     * @param
     * @return
     */
    TBaseFirefighter selectBaseFirefighterByID(Long id);

    TBaseFirefighter selectBaseFirefighter(TBaseFirefighter vo);

    void checkUserAndSave(String phone,Long deptId);
}
