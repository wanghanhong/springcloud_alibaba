package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TPool;
import com.smart.card.manage.entity.TPoolHistory;
import com.smart.card.manage.entity.TPoolPackageUse;
import com.smart.card.manage.mapper.TPoolHistoryMapper;
import com.smart.card.manage.mapper.TPoolMapper;
import com.smart.card.manage.mapper.TPoolPackageUseMapper;
import com.smart.card.manage.service.ITPoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author 
 */
@Service
public class TPoolServiceImpl extends ServiceImpl<TPoolMapper, TPool> implements ITPoolService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TPoolMapper tPoolMapper;
    @Resource
    private TPoolPackageUseMapper tPoolPackageUseMapper;
    @Resource
    private TPoolHistoryMapper tPoolHistoryMapper;

    @Override
    public IPage<TPool> tPoolList(PageDomain page, TPool vo) {
        Page<TPool> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TPool> iPage = tPoolMapper.tPoolList(pg,vo);
        List<TPool> list = iPage.getRecords();
        TPoolHistory his = new TPoolHistory();
        BeanUtils.copyBeanProp(his, vo);
        if (list.isEmpty()) {
            IPage<TPoolHistory> history = tPoolHisList(page, his);
            TPool pool = new TPool();
            for (TPoolHistory hi:history.getRecords()) {
                BeanUtils.copyBeanProp(pool, hi);
                list.add(pool);
            }
            iPage.setRecords(list);
            return iPage;
        }
        for (TPool pool:list) {
            updateDate(pool);
            TPoolPackageUse use = tPoolPackageUseMapper.getUseByDate(pool.getId(), vo.getDate());
            if (Objects.isNull(use)) {
                continue;
            }
            pool.setUsedNum(use.getBalanceUsed());
            pool.setHasNum(use.getBalanceAmount());
            pool.setAvalNum(use.getBalanceAvailable());
            pool.setDate(use.getDate());
        }
        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public IPage<TPoolHistory> tPoolHisList(PageDomain page, TPoolHistory vo) {
        Page<TPoolHistory> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TPoolHistory> iPage = tPoolHistoryMapper.tPoolHistoryList(pg,vo);

        return iPage;
    }

    public void updateDate(TPool pool) {
        LocalDateTime time =  LocalDateTime.now();
        int year = time.getYear();
        int month = time.getMonthValue();
        int monthB = 10;
        String dateNow = year + "-";
        if (month < monthB) {
            dateNow += "0";
        }
        dateNow += month;
        String billDate = pool.getDate();
        TPoolHistory history = new TPoolHistory();
        if (!dateNow.equals(billDate)) {
            BeanUtils.copyBeanProp(history, pool);
            if (tPoolHistoryMapper.insert(history) > 0) {
                pool.setDate(dateNow);
                updateDate(pool);
            }
        }
    }

    @Override
    public TPool tPoolAdd(TPool entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tPoolUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tPoolDel(Long id) {
        int res = tPoolMapper.deleteById(id);
        return res;
    }

    @Override
    public TPool tPoolUpdate(TPool entity) {
        tPoolMapper.updateById(entity);
        return entity;
    }

    @Override
    public TPool tPoolDetail(TPool entity) {
        TPool detail = tPoolMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
