package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardNbiot;
import com.smart.card.manage.mapper.TCardNbiotMapper;
import com.smart.card.manage.service.ITCardNbiotService;
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
public class TCardNbiotServiceImpl extends ServiceImpl<TCardNbiotMapper, TCardNbiot> implements ITCardNbiotService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardNbiotMapper tCardNbiotMapper;

    @Override
    public IPage<TCardNbiot> tCardNbiotList(PageDomain page, TCardNbiot vo) {
        Page<TCardNbiot> pg = new Page<>(page.getPageNum(), page.getPageSize());
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
        IPage<TCardNbiot> iPage = tCardNbiotMapper.tCardNbiotList(pg,vo);
        return iPage;
    }

    @Override
    public TCardNbiot tCardNbiotAdd(TCardNbiot entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardNbiotUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardNbiotDel(Long id) {
        int res = tCardNbiotMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardNbiot tCardNbiotUpdate(TCardNbiot entity) {
        tCardNbiotMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardNbiot tCardNbiotDetail(TCardNbiot entity) {
        TCardNbiot detail = tCardNbiotMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
