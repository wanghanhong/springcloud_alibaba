package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TPoolProduct;
import com.smart.card.manage.mapper.TPoolProductMapper;
import com.smart.card.manage.service.ITPoolProductService;
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
public class TPoolProductServiceImpl extends ServiceImpl<TPoolProductMapper, TPoolProduct> implements ITPoolProductService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TPoolProductMapper tPoolProductMapper;

    @Override
    public IPage<TPoolProduct> tPoolProductList(PageDomain page, TPoolProduct vo) {
        Page<TPoolProduct> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TPoolProduct> iPage = tPoolProductMapper.tPoolProductList(pg,vo);
        return iPage;
    }

    @Override
    public TPoolProduct tPoolProductAdd(TPoolProduct entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getProdId() == null ){
            entity.setProdId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tPoolProductUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tPoolProductDel(Long id) {
        int res = tPoolProductMapper.deleteById(id);
        return res;
    }

    @Override
    public TPoolProduct tPoolProductUpdate(TPoolProduct entity) {
        tPoolProductMapper.updateById(entity);
        return entity;
    }

    @Override
    public TPoolProduct tPoolProductDetail(TPoolProduct entity) {
        TPoolProduct detail = tPoolProductMapper.selectById(entity.getProdId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
