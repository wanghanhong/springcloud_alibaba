package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TCardVoice;
import com.smart.card.manage.mapper.TCardVoiceMapper;
import com.smart.card.manage.service.ITCardVoiceService;
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
public class TCardVoiceServiceImpl extends ServiceImpl<TCardVoiceMapper, TCardVoice> implements ITCardVoiceService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardVoiceMapper tCardVoiceMapper;

    @Override
    public IPage<TCardVoice> tCardVoiceList(PageDomain page, TCardVoice vo) {
        Page<TCardVoice> pg = new Page<>(page.getPageNum(), page.getPageSize());
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
        IPage<TCardVoice> iPage = tCardVoiceMapper.tCardVoiceList(pg,vo);
        return iPage;
    }

    @Override
    public TCardVoice tCardVoiceAdd(TCardVoice entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardVoiceUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardVoiceDel(Long id) {
        int res = tCardVoiceMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardVoice tCardVoiceUpdate(TCardVoice entity) {
        tCardVoiceMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardVoice tCardVoiceDetail(TCardVoice entity) {
        TCardVoice detail = tCardVoiceMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
