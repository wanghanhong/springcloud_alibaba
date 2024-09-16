package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TFeed;
import com.smart.brd.manage.base.mapper.TFeedMapper;
import com.smart.brd.manage.base.service.ITFeedService;
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
public class TFeedServiceImpl extends ServiceImpl<TFeedMapper, TFeed> implements ITFeedService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TFeedMapper tFeedMapper;

    @Override
    public IPage<TFeed> tFeedList(PageDomain page, TFeed vo) {
        Page<TFeed> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tFeedMapper.tFeedList(pg,vo);
    }

    @Override
    public TFeed tFeedAdd(TFeed entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tFeedUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tFeedDel(Long id) {
        return tFeedMapper.deleteById(id);
    }

    @Override
    public TFeed tFeedUpdate(TFeed entity) {
        tFeedMapper.updateById(entity);
        return entity;
    }

    @Override
    public TFeed tFeedDetail(TFeed entity) {
        return tFeedMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
