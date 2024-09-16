package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TEdition;
import com.smart.brd.manage.base.mapper.TEditionMapper;
import com.smart.brd.manage.base.service.ITEditionService;
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
public class TEditionServiceImpl extends ServiceImpl<TEditionMapper, TEdition> implements ITEditionService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TEditionMapper tEditionMapper;

    @Override
    public IPage<TEdition> tEditionList(PageDomain page, TEdition vo) {
        Page<TEdition> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tEditionMapper.tEditionList(pg,vo);
    }

    @Override
    public TEdition tEditionAdd(TEdition entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tEditionUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tEditionDel(Long id) {
        TEdition tEdition = tEditionMapper.selectById(id);
        tEdition.setDeleteFlag(1);
        return tEditionMapper.updateById(tEdition);
    }

    @Override
    public TEdition tEditionUpdate(TEdition entity) {
        tEditionMapper.updateById(entity);
        return entity;
    }

    @Override
    public TEdition tEditionDetail(TEdition entity) {
        return tEditionMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
