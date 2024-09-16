package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TPoolPackage;
import com.smart.card.manage.mapper.TPoolPackageMapper;
import com.smart.card.manage.service.ITPoolPackageService;
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
public class TPoolPackageServiceImpl extends ServiceImpl<TPoolPackageMapper, TPoolPackage> implements ITPoolPackageService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TPoolPackageMapper tPoolPackageMapper;

    @Override
    public IPage<TPoolPackage> tPoolPackageList(PageDomain page, TPoolPackage vo) {
        Page<TPoolPackage> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TPoolPackage> iPage = tPoolPackageMapper.tPoolPackageList(pg,vo);
        return iPage;
    }

    @Override
    public TPoolPackage tPoolPackageAdd(TPoolPackage entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getPoolPackageId() == null ){
            entity.setPoolPackageId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tPoolPackageUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tPoolPackageDel(Long id) {
        int res = tPoolPackageMapper.deleteById(id);
        return res;
    }

    @Override
    public TPoolPackage tPoolPackageUpdate(TPoolPackage entity) {
        tPoolPackageMapper.updateById(entity);
        return entity;
    }

    @Override
    public TPoolPackage tPoolPackageDetail(TPoolPackage entity) {
        TPoolPackage detail = tPoolPackageMapper.selectById(entity.getPoolPackageId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
