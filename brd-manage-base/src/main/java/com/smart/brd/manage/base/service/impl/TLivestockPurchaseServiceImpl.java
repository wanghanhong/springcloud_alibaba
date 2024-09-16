package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TLivestockPurchase;
import com.smart.brd.manage.base.mapper.TLivestockPurchaseMapper;
import com.smart.brd.manage.base.service.ITLivestockPurchaseService;
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
public class TLivestockPurchaseServiceImpl extends ServiceImpl<TLivestockPurchaseMapper, TLivestockPurchase> implements ITLivestockPurchaseService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TLivestockPurchaseMapper tLivestockPurchaseMapper;

    @Override
    public IPage<TLivestockPurchase> tLivestockPurchaseList(PageDomain page, TLivestockPurchase vo) {
        Page<TLivestockPurchase> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tLivestockPurchaseMapper.tLivestockPurchaseList(pg,vo);
    }

    @Override
    public TLivestockPurchase tLivestockPurchaseAdd(TLivestockPurchase entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tLivestockPurchaseUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tLivestockPurchaseDel(Long id) {
        return tLivestockPurchaseMapper.deleteById(id);
    }

    @Override
    public TLivestockPurchase tLivestockPurchaseUpdate(TLivestockPurchase entity) {
        tLivestockPurchaseMapper.updateById(entity);
        return entity;
    }

    @Override
    public TLivestockPurchase tLivestockPurchaseDetail(TLivestockPurchase entity) {
        return tLivestockPurchaseMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
