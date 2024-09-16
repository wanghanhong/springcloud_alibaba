package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardStatus;
import com.smart.card.manage.mapper.TCardStatusMapper;
import com.smart.card.manage.service.ITCardStatusService;
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
public class TCardStatusServiceImpl extends ServiceImpl<TCardStatusMapper, TCardStatus> implements ITCardStatusService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardStatusMapper tCardStatusMapper;

    @Override
    public IPage<TCardStatus> tCardStatusList(PageDomain page, TCardStatus vo) {
        Page<TCardStatus> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TCardStatus> iPage = tCardStatusMapper.tCardStatusList(pg,vo);
        return iPage;
    }

    @Override
    public TCardStatus tCardStatusAdd(TCardStatus entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardStatusUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardStatusDel(Long id) {
        int res = tCardStatusMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardStatus tCardStatusUpdate(TCardStatus entity) {
        tCardStatusMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardStatus tCardStatusDetail(TCardStatus entity) {
        TCardStatus detail = tCardStatusMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
