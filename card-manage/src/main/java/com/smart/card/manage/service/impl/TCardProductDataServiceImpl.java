package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardProductData;
import com.smart.card.manage.entity.vo.TCardProductDataDto;
import com.smart.card.manage.entity.vo.TCardVo;
import com.smart.card.manage.mapper.TCardProductDataMapper;
import com.smart.card.manage.service.ITCardProductDataService;
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
public class TCardProductDataServiceImpl extends ServiceImpl<TCardProductDataMapper, TCardProductData> implements ITCardProductDataService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardProductDataMapper tCardProductDataMapper;

    @Override
    public IPage<TCardProductDataDto> tCardProductDataList(PageDomain page, TCardVo vo) {
        Page<TCardProductDataDto> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TCardProductDataDto> iPage = tCardProductDataMapper.tCardProductDataList(pg,vo);
        return iPage;
    }

    @Override
    public TCardProductData tCardProductDataAdd(TCardProductData entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardProductDataUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardProductDataDel(Long id) {
        int res = tCardProductDataMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardProductData tCardProductDataUpdate(TCardProductData entity) {
        tCardProductDataMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardProductData tCardProductDataDetail(TCardProductData entity) {
        TCardProductData detail = tCardProductDataMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
