package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardProduct;
import com.smart.card.manage.mapper.TCardProductMapper;
import com.smart.card.manage.service.ITCardProductService;
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
public class TCardProductServiceImpl extends ServiceImpl<TCardProductMapper, TCardProduct> implements ITCardProductService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardProductMapper tCardProductMapper;

    @Override
    public IPage<TCardProduct> tCardProductList(PageDomain page, TCardProduct vo) {
        Page<TCardProduct> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TCardProduct> iPage = tCardProductMapper.tCardProductList(pg,vo);
        return iPage;
    }

    @Override
    public TCardProduct tCardProductAdd(TCardProduct entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardProductUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardProductDel(Long id) {
        int res = tCardProductMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardProduct tCardProductUpdate(TCardProduct entity) {
        tCardProductMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardProduct tCardProductDetail(TCardProduct entity) {
        TCardProduct detail = tCardProductMapper.selectById(entity.getCardProductId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
