package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TProduct;
import com.smart.card.manage.mapper.TProductMapper;
import com.smart.card.manage.service.ITProductService;
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
public class TProductServiceImpl extends ServiceImpl<TProductMapper, TProduct> implements ITProductService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TProductMapper tProductMapper;

    @Override
    public IPage<TProduct> tProductList(PageDomain page, TProduct vo) {
        Page<TProduct> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TProduct> iPage = tProductMapper.tProductList(pg,vo);
        return iPage;
    }

    @Override
    public TProduct tProductAdd(TProduct entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tProductUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tProductDel(Long id) {
        int res = tProductMapper.deleteById(id);
        return res;
    }

    @Override
    public TProduct tProductUpdate(TProduct entity) {
        tProductMapper.updateById(entity);
        return entity;
    }

    @Override
    public TProduct tProductDetail(TProduct entity) {
        TProduct detail = tProductMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
