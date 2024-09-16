package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.BsCity;
import com.smart.card.manage.mapper.BsCityMapper;
import com.smart.card.manage.service.IBsCityService;
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
public class BsCityServiceImpl extends ServiceImpl<BsCityMapper, BsCity> implements IBsCityService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private BsCityMapper bsCityMapper;

    @Override
    public IPage<BsCity> bsCityList(PageDomain page, BsCity vo) {
        Page<BsCity> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<BsCity> iPage = bsCityMapper.bsCityList(pg,vo);
        return iPage;
    }

    @Override
    public BsCity bsCityAdd(BsCity entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            bsCityUpdate(entity);
        }
        return entity;
    }

    @Override
    public int bsCityDel(Long id) {
        int res = bsCityMapper.deleteById(id);
        return res;
    }

    @Override
    public BsCity bsCityUpdate(BsCity entity) {
        bsCityMapper.updateById(entity);
        return entity;
    }

    @Override
    public BsCity bsCityDetail(BsCity entity) {
        BsCity detail = bsCityMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
