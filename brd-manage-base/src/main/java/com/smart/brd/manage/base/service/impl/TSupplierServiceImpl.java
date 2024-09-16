package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TSupplier;
import com.smart.brd.manage.base.mapper.TSupplierMapper;
import com.smart.brd.manage.base.service.ITSupplierService;
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
public class TSupplierServiceImpl extends ServiceImpl<TSupplierMapper, TSupplier> implements ITSupplierService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TSupplierMapper tSupplierMapper;

    @Override
    public IPage<TSupplier> tSupplierList(PageDomain page, TSupplier vo) {
        Page<TSupplier> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tSupplierMapper.tSupplierList(pg,vo);
    }

    @Override
    public TSupplier tSupplierAdd(TSupplier entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getSupplierId() == null ){
            entity.setSupplierId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tSupplierUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tSupplierDel(Long id) {
        TSupplier supplier = getById(id);
        supplier.setDeleteFlag(1);
        return tSupplierMapper.updateById(supplier);
    }

    @Override
    public TSupplier tSupplierUpdate(TSupplier entity) {
        tSupplierMapper.updateById(entity);
        return entity;
    }

    @Override
    public TSupplier tSupplierDetail(TSupplier entity) {
        return  tSupplierMapper.selectById(entity.getSupplierId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
