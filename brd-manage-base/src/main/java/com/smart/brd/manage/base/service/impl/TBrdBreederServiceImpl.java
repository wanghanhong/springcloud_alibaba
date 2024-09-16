package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TBrdBreeder;
import com.smart.brd.manage.base.mapper.TBrdBreederMapper;
import com.smart.brd.manage.base.service.ITBrdBreederService;
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
public class TBrdBreederServiceImpl extends ServiceImpl<TBrdBreederMapper, TBrdBreeder> implements ITBrdBreederService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBrdBreederMapper tBrdBreederMapper;

    @Override
    public IPage<TBrdBreeder> tBrdBreederList(PageDomain page, TBrdBreeder vo) {
        Page<TBrdBreeder> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tBrdBreederMapper.tBrdBreederList(pg,vo);
    }

    @Override
    public TBrdBreeder tBrdBreederAdd(TBrdBreeder entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tBrdBreederUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tBrdBreederDel(Long id) {
        return tBrdBreederMapper.deleteById(id);
    }

    @Override
    public TBrdBreeder tBrdBreederUpdate(TBrdBreeder entity) {
        tBrdBreederMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBrdBreeder tBrdBreederDetail(TBrdBreeder entity) {
        return tBrdBreederMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
