package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TFeedAdditive;
import com.smart.brd.manage.base.mapper.TFeedAdditiveMapper;
import com.smart.brd.manage.base.service.ITFeedAdditiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;

/**
 * @author 
 */
@Service
public class TFeedAdditiveServiceImpl extends ServiceImpl<TFeedAdditiveMapper, TFeedAdditive> implements ITFeedAdditiveService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TFeedAdditiveMapper tFeedAdditiveMapper;

    @Override
    public IPage<TFeedAdditive> tFeedAdditiveList(PageDomain page, TFeedAdditive vo) {
        Page<TFeedAdditive> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tFeedAdditiveMapper.tFeedAdditiveList(pg,vo);
    }

    @Override
    public TFeedAdditive tFeedAdditiveAdd(TFeedAdditive entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tFeedAdditiveUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tFeedAdditiveDel(Long id) {
        return tFeedAdditiveMapper.deleteById(id);
    }

    @Override
    public TFeedAdditive tFeedAdditiveUpdate(TFeedAdditive entity) {
        tFeedAdditiveMapper.updateById(entity);
        return entity;
    }

    @Override
    public TFeedAdditive tFeedAdditiveDetail(TFeedAdditive entity) {
        return tFeedAdditiveMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
