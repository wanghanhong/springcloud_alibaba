package com.smart.device.install.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.TBaseProduct;
import com.smart.device.install.mapper.TBaseProductMapper;
import com.smart.device.install.service.ITBaseProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author f
 */
@Service
public class TBaseProductServiceImpl extends ServiceImpl<TBaseProductMapper, TBaseProduct> implements ITBaseProductService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseProductMapper TBaseProductMapper;

    @Override
    public IPage<TBaseProduct> baseProductList(PageDomain page, TBaseProduct vo) {
        Page<TBaseProduct> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBaseProduct> iPage = TBaseProductMapper.baseProductList(pg,vo);
        return iPage;
    }

    @Override
    public TBaseProduct baseProductAdd(TBaseProduct entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        entity.setId(idWorker.nextId());
        entity.setCreateTime(LocalDateTime.now());
        TBaseProductMapper.insert(entity);
        return entity;
    }

    @Override
    public int baseProductDel(Long id) {
        return TBaseProductMapper.deleteById(id);
    }

    @Override
    public TBaseProduct baseProductUpdate(TBaseProduct entity) {
        entity.setUpdateTime(LocalDateTime.now());
        TBaseProductMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseProduct selectBaseProductByID(Long id) {
        TBaseProduct entity = TBaseProductMapper.selectById(id);
        return entity;
    }
    /**------通用方法开始结束-----------------------------------------*/

}
