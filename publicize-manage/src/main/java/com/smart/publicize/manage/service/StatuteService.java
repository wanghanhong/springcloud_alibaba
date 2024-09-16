package com.smart.publicize.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.domain.Result;
import com.smart.publicize.manage.entity.StatuteEntity;
import com.smart.publicize.manage.entity.StatuteVo;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:54
 * Describe:
 */
public interface StatuteService extends IService<StatuteEntity> {
    /**
     * 法规添加
     *
     * @param statuteEntity
     * @return
     */
    Result add(StatuteEntity statuteEntity);

    /**
     * 删除
     *
     * @param statuteEntity
     * @return
     */
    Result  del(StatuteEntity statuteEntity);

    /**
     * 修改
     *
     * @param statuteEntity
     * @return
     */
    Result update(StatuteEntity statuteEntity);

    /**
     * 查询
     *
     * @param statuteEntity
     * @return
     */
    IPage<StatuteEntity> getInfo(Page page, StatuteEntity statuteEntity);

    /**
     * 审核
     *
     * @param statuteEntity
     * @return
     */
    Result examine(StatuteEntity statuteEntity);
    /**
     * id查询
     *
     * @param id
     * @return
     */
    StatuteEntity getByIds(long id);
}
