package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TDeviceExt;
import com.smart.brd.manage.base.mapper.TDeviceExtMapper;
import com.smart.brd.manage.base.service.ITDeviceExtService;
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
public class TDeviceExtServiceImpl extends ServiceImpl<TDeviceExtMapper, TDeviceExt> implements ITDeviceExtService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TDeviceExtMapper tDeviceExtMapper;

    @Override
    public IPage<TDeviceExt> tDeviceExtList(PageDomain page, TDeviceExt vo) {
        Page<TDeviceExt> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tDeviceExtMapper.tDeviceExtList(pg,vo);
    }

    @Override
    public TDeviceExt tDeviceExtAdd(TDeviceExt entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tDeviceExtUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tDeviceExtDel(Long id) {
        TDeviceExt tDeviceExt = tDeviceExtMapper.selectById(id);
        tDeviceExt.setDeleteFlag(1);
        return tDeviceExtMapper.updateById(tDeviceExt);
    }

    @Override
    public TDeviceExt tDeviceExtUpdate(TDeviceExt entity) {
        tDeviceExtMapper.updateById(entity);
        return entity;
    }

    @Override
    public TDeviceExt tDeviceExtDetail(TDeviceExt entity) {
        return tDeviceExtMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
