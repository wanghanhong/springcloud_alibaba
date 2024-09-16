package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TBaseGroup;
import com.smart.card.manage.mapper.TBaseGroupMapper;
import com.smart.card.manage.service.ITBaseGroupService;
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
public class TBaseGroupServiceImpl extends ServiceImpl<TBaseGroupMapper, TBaseGroup> implements ITBaseGroupService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseGroupMapper tBaseGroupMapper;

    @Override
    public IPage<TBaseGroup> tBaseGroupList(PageDomain page, TBaseGroup vo) {
        Page<TBaseGroup> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBaseGroup> iPage = tBaseGroupMapper.tBaseGroupList(pg,vo);
        return iPage;
    }

    @Override
    public TBaseGroup tBaseGroupAdd(TBaseGroup entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getDeptId() == null ){
            entity.setDeptId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tBaseGroupUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tBaseGroupDel(Long id) {
        int res = tBaseGroupMapper.deleteById(id);
        return res;
    }

    @Override
    public TBaseGroup tBaseGroupUpdate(TBaseGroup entity) {
        tBaseGroupMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseGroup tBaseGroupDetail(TBaseGroup entity) {
        TBaseGroup detail = tBaseGroupMapper.selectById(entity.getDeptId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
