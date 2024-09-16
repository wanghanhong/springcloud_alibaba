package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TPackageSub;
import com.smart.card.manage.mapper.TPackageSubMapper;
import com.smart.card.manage.service.ITPackageSubService;
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
public class TPackageSubServiceImpl extends ServiceImpl<TPackageSubMapper, TPackageSub> implements ITPackageSubService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TPackageSubMapper tPackageSubMapper;

    @Override
    public IPage<TPackageSub> tPackageSubList(PageDomain page, TPackageSub vo) {
        Page<TPackageSub> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TPackageSub> iPage = tPackageSubMapper.tPackageSubList(pg,vo);
        return iPage;
    }

    @Override
    public TPackageSub tPackageSubAdd(TPackageSub entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getPackageSubId() == null ){
            entity.setPackageSubId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tPackageSubUpdate(entity);
        }
        if(entity.getPackageSubId() == null ){
            entity.setPackageSubId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tPackageSubUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tPackageSubDel(Long id) {
        int res = tPackageSubMapper.deleteById(id);
        return res;
    }

    @Override
    public TPackageSub tPackageSubUpdate(TPackageSub entity) {
        tPackageSubMapper.updateById(entity);
        return entity;
    }

    @Override
    public TPackageSub tPackageSubDetail(TPackageSub entity) {
        TPackageSub detail = tPackageSubMapper.selectById(entity.getPackageSubId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
