package com.smart.publicize.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.domain.Result;
import com.smart.publicize.manage.entity.NoticeEntity;
import com.smart.publicize.manage.entity.NoticeVo;

import java.util.List;


/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:51
 * Describe:
 */
public interface NoticeService extends IService<NoticeEntity> {
    /**
     * 根据发布日期查询公告信息
     *
     * @param entity
     * @return
     */
    IPage<NoticeVo> noticeList(Page page, NoticeEntity entity);

    /**
     * 公告添加
     *
     * @param entity
     * @return
     */
    Result add(NoticeEntity entity);

    /**
     * 公告删除
     *
     * @param entity
     * @return
     */
    Result del(NoticeEntity entity);

    /**
     * 公告修改
     *
     * @param entity
     * @return
     */
    Result update(NoticeEntity entity);

    /**
     * id 查询
     *
     * @param id
     * @return
     */
    Result getByIds(long id);
    /**
     * 审核
     *
     * @param entity
     * @return
     */

    Result examine(NoticeEntity entity);

    List<NoticeEntity> queryNoticeListLimit();

}
