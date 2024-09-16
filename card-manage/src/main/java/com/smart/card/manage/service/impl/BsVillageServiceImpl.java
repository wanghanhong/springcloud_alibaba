package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.BsVillage;
import com.smart.card.manage.mapper.BsVillageMapper;
import com.smart.card.manage.service.IBsVillageService;
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
public class BsVillageServiceImpl extends ServiceImpl<BsVillageMapper, BsVillage> implements IBsVillageService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private BsVillageMapper bsVillageMapper;

    @Override
    public IPage<BsVillage> bsVillageList(PageDomain page, BsVillage vo) {
        Page<BsVillage> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<BsVillage> iPage = bsVillageMapper.bsVillageList(pg,vo);
        return iPage;
    }

    @Override
    public BsVillage bsVillageAdd(BsVillage entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            bsVillageUpdate(entity);
        }
        return entity;
    }

    @Override
    public int bsVillageDel(Long id) {
        int res = bsVillageMapper.deleteById(id);
        return res;
    }

    @Override
    public BsVillage bsVillageUpdate(BsVillage entity) {
        bsVillageMapper.updateById(entity);
        return entity;
    }

    @Override
    public BsVillage bsVillageDetail(BsVillage entity) {
        BsVillage detail = bsVillageMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
