package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardData;
import com.smart.card.manage.mapper.TCardDataMapper;
import com.smart.card.manage.service.ITCardDataService;
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
public class TCardDataServiceImpl extends ServiceImpl<TCardDataMapper, TCardData> implements ITCardDataService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardDataMapper tCardDataMapper;

    @Override
    public IPage<TCardData> tCardDataList(PageDomain page, TCardData vo) {
        Page<TCardData> pg = new Page<>(page.getPageNum(), page.getPageSize());
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
        IPage<TCardData> iPage = tCardDataMapper.tCardDataList(pg,vo);
        return iPage;
    }

    @Override
    public TCardData tCardDataAdd(TCardData entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardDataUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardDataDel(Long id) {
        int res = tCardDataMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardData tCardDataUpdate(TCardData entity) {
        tCardDataMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardData tCardDataDetail(TCardData entity) {
        TCardData detail = tCardDataMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
