package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardProductSub;
import com.smart.card.manage.mapper.TCardProductSubMapper;
import com.smart.card.manage.service.ITCardProductSubService;
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
public class TCardProductSubServiceImpl extends ServiceImpl<TCardProductSubMapper, TCardProductSub> implements ITCardProductSubService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardProductSubMapper tCardProductSubMapper;

    @Override
    public IPage<TCardProductSub> tCardProductSubList(PageDomain page, TCardProductSub vo) {
        Page<TCardProductSub> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TCardProductSub> iPage = tCardProductSubMapper.tCardProductSubList(pg,vo);
        return iPage;
    }

    @Override
    public TCardProductSub tCardProductSubAdd(TCardProductSub entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardProductSubUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardProductSubDel(Long id) {
        int res = tCardProductSubMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardProductSub tCardProductSubUpdate(TCardProductSub entity) {
        tCardProductSubMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardProductSub tCardProductSubDetail(TCardProductSub entity) {
        TCardProductSub detail = tCardProductSubMapper.selectById(entity.getCardProductSubId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
