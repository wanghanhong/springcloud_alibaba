package com.smart.publicize.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.domain.Result;
import com.smart.publicize.manage.entity.NousEntity;
import com.smart.publicize.manage.entity.NousEntityVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:53
 * Describe:
 */
public interface NousService extends IService<NousEntity> {
    /**
     * 消防常识添加
     *
     * @param nousEntity
     * @return
     */
    Result add(NousEntity nousEntity) throws IOException;

    /**
     * 删除
     *
     * @param nousEntity
     * @return
     */
    Result  del(NousEntity nousEntity);

    /**
     * 修改
     *
     * @param nousEntity
     * @return
     */
    Result updateNous(NousEntity nousEntity,MultipartFile[] files);

    /**
     * 分页查询
     *
     * @param nousEntity
     * @return
     */
    IPage<NousEntity> getNousInfo(Page page, NousEntity nousEntity);
    /**
     * ById查询
     *
     * @param id
     * @return
     */
    NousEntity getByIds(long id);
    /**
     * 审核
     *
     * @param nousEntity
     * @return
     */
    Result examine(NousEntity nousEntity);
}
