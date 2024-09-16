package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardIdentity;
import com.smart.card.manage.mapper.TCardIdentityMapper;
import com.smart.card.manage.service.ITCardIdentityService;
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
public class TCardIdentityServiceImpl extends ServiceImpl<TCardIdentityMapper, TCardIdentity> implements ITCardIdentityService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardIdentityMapper tCardIdentityMapper;

    @Override
    public IPage<TCardIdentity> tCardIdentityList(PageDomain page, TCardIdentity vo) {
        Page<TCardIdentity> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TCardIdentity> iPage = tCardIdentityMapper.tCardIdentityList(pg,vo);
        return iPage;
    }

    @Override
    public TCardIdentity tCardIdentityAdd(TCardIdentity entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getIccid() == null ){
            entity.setIccid(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardIdentityUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardIdentityDel(Long id) {
        int res = tCardIdentityMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardIdentity tCardIdentityUpdate(TCardIdentity entity) {
        tCardIdentityMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardIdentity tCardIdentityDetail(TCardIdentity entity) {
        TCardIdentity detail = tCardIdentityMapper.selectById(entity.getIccid());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
