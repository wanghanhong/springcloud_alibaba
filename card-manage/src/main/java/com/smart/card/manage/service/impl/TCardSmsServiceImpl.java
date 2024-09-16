package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardSms;
import com.smart.card.manage.mapper.TCardSmsMapper;
import com.smart.card.manage.service.ITCardSmsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author 
 */
@Service
public class TCardSmsServiceImpl extends ServiceImpl<TCardSmsMapper, TCardSms> implements ITCardSmsService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardSmsMapper tCardSmsMapper;

    @Override
    public IPage<TCardSms> tCardSmsList(PageDomain page, TCardSms vo) {
        Page<TCardSms> pg = new Page<>(page.getPageNum(), page.getPageSize());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(StringUtils.isNotBlank(vo.getStartTime())){
            String startStr = vo.getStartTime()+" 00:00:01";
            LocalDateTime startDateTime = LocalDateTime.parse(startStr, dtf);
            vo.setStartTime(startDateTime.toString());
        }
        if(StringUtils.isNotBlank(vo.getStartTime())){
            String endStr = vo.getEndTime()+" 23:59:59";
            LocalDateTime endDateTime = LocalDateTime.parse(endStr, dtf);
            vo.setEndTime(endDateTime.toString());
        }
        IPage<TCardSms> iPage = tCardSmsMapper.tCardSmsList(pg,vo);
        return iPage;
    }

    @Override
    public TCardSms tCardSmsAdd(TCardSms entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardSmsUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardSmsDel(Long id) {
        int res = tCardSmsMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardSms tCardSmsUpdate(TCardSms entity) {
        tCardSmsMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardSms tCardSmsDetail(TCardSms entity) {
        TCardSms detail = tCardSmsMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
