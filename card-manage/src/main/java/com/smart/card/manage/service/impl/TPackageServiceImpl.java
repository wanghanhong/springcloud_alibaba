package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TPackage;
import com.smart.card.manage.mapper.TPackageMapper;
import com.smart.card.manage.service.ITPackageService;
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
public class TPackageServiceImpl extends ServiceImpl<TPackageMapper, TPackage> implements ITPackageService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TPackageMapper tPackageMapper;

    @Override
    public IPage<TPackage> tPackageList(PageDomain page, TPackage vo) {
        Page<TPackage> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TPackage> iPage = tPackageMapper.tPackageList(pg,vo);
        return iPage;
    }

    @Override
    public TPackage tPackageAdd(TPackage entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getPackageId() == null ){
            entity.setPackageId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tPackageUpdate(entity);
        }
        if(entity.getPackageId() == null ){
            entity.setPackageId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tPackageUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tPackageDel(Long id) {
        int res = tPackageMapper.deleteById(id);
        return res;
    }

    @Override
    public TPackage tPackageUpdate(TPackage entity) {
        tPackageMapper.updateById(entity);
        return entity;
    }

    @Override
    public TPackage tPackageDetail(TPackage entity) {
        TPackage detail = tPackageMapper.selectById(entity.getPackageId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
