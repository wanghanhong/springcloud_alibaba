package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TBaseDictType;
import com.smart.card.manage.mapper.TBaseDictTypeMapper;
import com.smart.card.manage.service.ITBaseDictTypeService;
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
public class TBaseDictTypeServiceImpl extends ServiceImpl<TBaseDictTypeMapper, TBaseDictType> implements ITBaseDictTypeService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseDictTypeMapper tBaseDictTypeMapper;

    @Override
    public IPage<TBaseDictType> tBaseDictTypeList(PageDomain page, TBaseDictType vo) {
        Page<TBaseDictType> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBaseDictType> iPage = tBaseDictTypeMapper.tBaseDictTypeList(pg,vo);
        return iPage;
    }

    @Override
    public TBaseDictType tBaseDictTypeAdd(TBaseDictType entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tBaseDictTypeUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tBaseDictTypeDel(Long id) {
        int res = tBaseDictTypeMapper.deleteById(id);
        return res;
    }

    @Override
    public TBaseDictType tBaseDictTypeUpdate(TBaseDictType entity) {
        tBaseDictTypeMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseDictType tBaseDictTypeDetail(TBaseDictType entity) {
        TBaseDictType detail = tBaseDictTypeMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
