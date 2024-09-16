package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.BsCounty;
import com.smart.card.manage.mapper.BsCountyMapper;
import com.smart.card.manage.service.IBsCountyService;
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
public class BsCountyServiceImpl extends ServiceImpl<BsCountyMapper, BsCounty> implements IBsCountyService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private BsCountyMapper bsCountyMapper;

    @Override
    public IPage<BsCounty> bsCountyList(PageDomain page, BsCounty vo) {
        Page<BsCounty> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<BsCounty> iPage = bsCountyMapper.bsCountyList(pg,vo);
        return iPage;
    }

    @Override
    public BsCounty bsCountyAdd(BsCounty entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            bsCountyUpdate(entity);
        }
        return entity;
    }

    @Override
    public int bsCountyDel(Long id) {
        int res = bsCountyMapper.deleteById(id);
        return res;
    }

    @Override
    public BsCounty bsCountyUpdate(BsCounty entity) {
        bsCountyMapper.updateById(entity);
        return entity;
    }

    @Override
    public BsCounty bsCountyDetail(BsCounty entity) {
        BsCounty detail = bsCountyMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
