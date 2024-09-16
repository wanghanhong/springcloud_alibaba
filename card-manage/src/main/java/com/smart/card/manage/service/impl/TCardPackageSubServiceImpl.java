package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardPackageSub;
import com.smart.card.manage.mapper.TCardPackageSubMapper;
import com.smart.card.manage.service.ITCardPackageSubService;
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
public class TCardPackageSubServiceImpl extends ServiceImpl<TCardPackageSubMapper, TCardPackageSub> implements ITCardPackageSubService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardPackageSubMapper tCardPackageSubMapper;

    @Override
    public IPage<TCardPackageSub> tCardPackageSubList(PageDomain page, TCardPackageSub vo) {
        Page<TCardPackageSub> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TCardPackageSub> iPage = tCardPackageSubMapper.tCardPackageSubList(pg,vo);
        return iPage;
    }

    @Override
    public TCardPackageSub tCardPackageSubAdd(TCardPackageSub entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardPackageSubUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardPackageSubDel(Long id) {
        int res = tCardPackageSubMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardPackageSub tCardPackageSubUpdate(TCardPackageSub entity) {
        tCardPackageSubMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardPackageSub tCardPackageSubDetail(TCardPackageSub entity) {
        TCardPackageSub detail = tCardPackageSubMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
