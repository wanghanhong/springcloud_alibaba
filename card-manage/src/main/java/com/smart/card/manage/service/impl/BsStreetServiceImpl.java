package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.BsStreet;
import com.smart.card.manage.mapper.BsStreetMapper;
import com.smart.card.manage.service.IBsStreetService;
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
public class BsStreetServiceImpl extends ServiceImpl<BsStreetMapper, BsStreet> implements IBsStreetService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private BsStreetMapper bsStreetMapper;

    @Override
    public IPage<BsStreet> bsStreetList(PageDomain page, BsStreet vo) {
        Page<BsStreet> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<BsStreet> iPage = bsStreetMapper.bsStreetList(pg,vo);
        return iPage;
    }

    @Override
    public BsStreet bsStreetAdd(BsStreet entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            bsStreetUpdate(entity);
        }
        return entity;
    }

    @Override
    public int bsStreetDel(Long id) {
        int res = bsStreetMapper.deleteById(id);
        return res;
    }

    @Override
    public BsStreet bsStreetUpdate(BsStreet entity) {
        bsStreetMapper.updateById(entity);
        return entity;
    }

    @Override
    public BsStreet bsStreetDetail(BsStreet entity) {
        BsStreet detail = bsStreetMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
