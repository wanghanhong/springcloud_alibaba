package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TProductSub;
import com.smart.card.manage.mapper.TProductSubMapper;
import com.smart.card.manage.service.ITProductSubService;
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
public class TProductSubServiceImpl extends ServiceImpl<TProductSubMapper, TProductSub> implements ITProductSubService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TProductSubMapper tProductSubMapper;

    @Override
    public IPage<TProductSub> tProductSubList(PageDomain page, TProductSub vo) {
        Page<TProductSub> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TProductSub> iPage = tProductSubMapper.tProductSubList(pg,vo);
        return iPage;
    }

    @Override
    public TProductSub tProductSubAdd(TProductSub entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tProductSubUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tProductSubDel(Long id) {
        int res = tProductSubMapper.deleteById(id);
        return res;
    }

    @Override
    public TProductSub tProductSubUpdate(TProductSub entity) {
        tProductSubMapper.updateById(entity);
        return entity;
    }

    @Override
    public TProductSub tProductSubDetail(TProductSub entity) {
        TProductSub detail = tProductSubMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
