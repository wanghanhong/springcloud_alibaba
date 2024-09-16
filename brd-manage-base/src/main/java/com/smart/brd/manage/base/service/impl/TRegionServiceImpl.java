package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TRegion;
import com.smart.brd.manage.base.mapper.TRegionMapper;
import com.smart.brd.manage.base.service.ITRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;


/**
 * @author
 */
@Service
public class TRegionServiceImpl extends ServiceImpl<TRegionMapper, TRegion> implements ITRegionService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TRegionMapper tRegionMapper;

    @Override
    public IPage<TRegion> tRegionList(PageDomain page, TRegion vo) {
        Page<TRegion> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tRegionMapper.tRegionList(pg,vo);
    }

    @Override
    public TRegion tRegionAdd(TRegion entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getRegionCode() == null ){
            this.save(entity);
        }else{
            tRegionUpdate(entity);
        }
        if(entity.getId() == null ){
            entity.setId(id);
            this.save(entity);
        }else{
            tRegionUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tRegionDel(Long id) {
        return tRegionMapper.deleteById(id);
    }

    @Override
    public TRegion tRegionUpdate(TRegion entity) {
        tRegionMapper.updateById(entity);
        return entity;
    }

    @Override
    public TRegion tRegionDetail(TRegion entity) {
        return tRegionMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
