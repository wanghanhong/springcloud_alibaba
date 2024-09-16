package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TLivestockLog;
import com.smart.brd.manage.base.mapper.TLivestockLogMapper;
import com.smart.brd.manage.base.service.ITLivestockLogService;
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
public class TLivestockLogServiceImpl extends ServiceImpl<TLivestockLogMapper, TLivestockLog> implements ITLivestockLogService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TLivestockLogMapper tLivestockLogMapper;

    @Override
    public IPage<TLivestockLog> tLivestockLogList(PageDomain page, TLivestockLog vo) {
        Page<TLivestockLog> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tLivestockLogMapper.tLivestockLogList(pg,vo);
    }

    @Override
    public TLivestockLog tLivestockLogAdd(TLivestockLog entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getLivestockId() == null ){
            entity.setLivestockId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tLivestockLogUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tLivestockLogDel(Long id) {
        return tLivestockLogMapper.deleteById(id);
    }

    @Override
    public TLivestockLog tLivestockLogUpdate(TLivestockLog entity) {
        tLivestockLogMapper.updateById(entity);
        return entity;
    }

    @Override
    public TLivestockLog tLivestockLogDetail(TLivestockLog entity) {
        return tLivestockLogMapper.selectById(entity.getLivestockId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
