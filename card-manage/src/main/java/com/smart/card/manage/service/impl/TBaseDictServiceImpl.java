package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TBaseDict;
import com.smart.card.manage.mapper.TBaseDictMapper;
import com.smart.card.manage.service.ITBaseDictService;
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
public class TBaseDictServiceImpl extends ServiceImpl<TBaseDictMapper, TBaseDict> implements ITBaseDictService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseDictMapper tBaseDictMapper;

    @Override
    public IPage<TBaseDict> tBaseDictList(PageDomain page, TBaseDict vo) {
        Page<TBaseDict> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBaseDict> iPage = tBaseDictMapper.tBaseDictList(pg,vo);
        return iPage;
    }

    @Override
    public TBaseDict tBaseDictAdd(TBaseDict entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tBaseDictUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tBaseDictDel(Long id) {
        int res = tBaseDictMapper.deleteById(id);
        return res;
    }

    @Override
    public TBaseDict tBaseDictUpdate(TBaseDict entity) {
        tBaseDictMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseDict tBaseDictDetail(TBaseDict entity) {
        TBaseDict detail = tBaseDictMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
