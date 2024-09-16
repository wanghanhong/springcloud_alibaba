package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.BsProvince;
import com.smart.card.manage.mapper.BsProvinceMapper;
import com.smart.card.manage.service.IBsProvinceService;
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
public class BsProvinceServiceImpl extends ServiceImpl<BsProvinceMapper, BsProvince> implements IBsProvinceService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private BsProvinceMapper bsProvinceMapper;

    @Override
    public IPage<BsProvince> bsProvinceList(PageDomain page, BsProvince vo) {
        Page<BsProvince> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<BsProvince> iPage = bsProvinceMapper.bsProvinceList(pg,vo);
        return iPage;
    }

    @Override
    public BsProvince bsProvinceAdd(BsProvince entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            bsProvinceUpdate(entity);
        }
        return entity;
    }

    @Override
    public int bsProvinceDel(Long id) {
        int res = bsProvinceMapper.deleteById(id);
        return res;
    }

    @Override
    public BsProvince bsProvinceUpdate(BsProvince entity) {
        bsProvinceMapper.updateById(entity);
        return entity;
    }

    @Override
    public BsProvince bsProvinceDetail(BsProvince entity) {
        BsProvince detail = bsProvinceMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
