package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardBill;
import com.smart.card.manage.mapper.TCardBillMapper;
import com.smart.card.manage.service.ITCardBillService;
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
public class TCardBillServiceImpl extends ServiceImpl<TCardBillMapper, TCardBill> implements ITCardBillService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardBillMapper tCardBillMapper;

    @Override
    public IPage<TCardBill> tCardBillList(PageDomain page, TCardBill vo) {
        Page<TCardBill> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TCardBill> iPage = tCardBillMapper.tCardBillList(pg,vo);
        return iPage;
    }

    @Override
    public TCardBill tCardBillAdd(TCardBill entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardBillUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardBillDel(Long id) {
        int res = tCardBillMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardBill tCardBillUpdate(TCardBill entity) {
        tCardBillMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardBill tCardBillDetail(TCardBill entity) {
        TCardBill detail = tCardBillMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
