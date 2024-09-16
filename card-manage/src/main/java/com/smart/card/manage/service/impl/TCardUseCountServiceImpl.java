package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardUseCount;
import com.smart.card.manage.mapper.TCardUseCountMapper;
import com.smart.card.manage.service.ITCardUseCountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author 
 */
@Service
public class TCardUseCountServiceImpl extends ServiceImpl<TCardUseCountMapper, TCardUseCount> implements ITCardUseCountService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardUseCountMapper tCardUseCountMapper;

    @Override
    public IPage<TCardUseCount> tCardUseCountList(PageDomain page, TCardUseCount vo) {
        Page<TCardUseCount> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TCardUseCount> iPage = tCardUseCountMapper.tCardUseCountList(pg,vo);
        return iPage;
    }

    @Override
    public TCardUseCount tCardUseCountAdd(TCardUseCount entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardUseCountUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardUseCountDel(Long id) {
        int res = tCardUseCountMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardUseCount tCardUseCountUpdate(TCardUseCount entity) {
        tCardUseCountMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardUseCount tCardUseCountDetail(TCardUseCount entity) {
        TCardUseCount detail = tCardUseCountMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
