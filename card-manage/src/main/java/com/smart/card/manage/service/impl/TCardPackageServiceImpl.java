package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardPackage;
import com.smart.card.manage.mapper.TCardPackageMapper;
import com.smart.card.manage.service.ITCardPackageService;
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
public class TCardPackageServiceImpl extends ServiceImpl<TCardPackageMapper, TCardPackage> implements ITCardPackageService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardPackageMapper tCardPackageMapper;

    @Override
    public IPage<TCardPackage> tCardPackageList(PageDomain page, TCardPackage vo) {
        Page<TCardPackage> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TCardPackage> iPage = tCardPackageMapper.tCardPackageList(pg,vo);
        return iPage;
    }

    @Override
    public TCardPackage tCardPackageAdd(TCardPackage entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardPackageUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardPackageDel(Long id) {
        int res = tCardPackageMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardPackage tCardPackageUpdate(TCardPackage entity) {
        tCardPackageMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardPackage tCardPackageDetail(TCardPackage entity) {
        TCardPackage detail = tCardPackageMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
