package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.brd.manage.base.entity.TAlarmInfo;
import com.smart.brd.manage.base.entity.TLivestock;
import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.brd.manage.base.mapper.TAlarmInfoMapper;
import com.smart.brd.manage.base.mapper.TLivestockMapper;
import com.smart.brd.manage.base.service.ITAlarmInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 
 */
@Service
public class TAlarmInfoServiceImpl extends ServiceImpl<TAlarmInfoMapper, TAlarmInfo> implements ITAlarmInfoService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TAlarmInfoMapper tAlarmInfoMapper;
    @Resource
    private TLivestockMapper tLivestockMapper;

    @Override
    public IPage<TAlarmInfo> tAlarmInfoList(PageDomain page, TAlarmInfo vo) {
        Page<TAlarmInfo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tAlarmInfoMapper.tAlarmInfoList(pg,vo);
    }

    @Override
    public TAlarmInfo tAlarmInfoAdd(TAlarmInfo entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tAlarmInfoUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tAlarmInfoDel(Long id) {
        TAlarmInfo tAlarmInfo = tAlarmInfoMapper.selectById(id);
        tAlarmInfo.setDeleteFlag(1);
        return tAlarmInfoMapper.updateById(tAlarmInfo);
    }

    @Override
    public TAlarmInfo tAlarmInfoUpdate(TAlarmInfo entity) {
        entity.setProcessingTime(LocalDateTime.now());
        tAlarmInfoMapper.updateById(entity);
        if (String.valueOf(BrdConstant.dict_28).equals(entity.getTreatmentResults())){
            TAlarmInfo tAlarmInfo = tAlarmInfoMapper.selectById(entity.getId());
            TLivestock tLivestock = new TLivestock();
            tLivestock.setLivestockId(tAlarmInfo.getLivestockId());
            tLivestock.setDeleteFlag(1);
            tLivestockMapper.updateById(tLivestock);
        }
        return entity;
    }

    @Override
    public TAlarmInfo tAlarmInfoDetail(TAlarmInfo entity) {
        return tAlarmInfoMapper.selectById(entity.getId());
    }


    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public int getUnprocessedNum(TLivestockAnalysis tLivestockAnalysis) {
        QueryWrapper<TAlarmInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("delete_flag",0);
        wrapper.eq("event_status",88);
        if(!StringUtils.isEmpty(tLivestockAnalysis.getDeptIds())){
            String[] split = tLivestockAnalysis.getDeptIds().split(",");
            wrapper.in("dept_id", split);
        }
        List<TAlarmInfo> tAlarmInfos = tAlarmInfoMapper.selectList(wrapper);
        return tAlarmInfos.size();
    }

}
