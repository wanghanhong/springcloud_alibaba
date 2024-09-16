package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.vo.ShedTransVo;
import com.smart.brd.manage.base.entity.vo.StockBedVo;
import com.smart.brd.manage.base.mapper.*;
import com.smart.brd.manage.base.service.ITShedTransferService;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
@Service
public class TShedTransferServiceImpl extends ServiceImpl<TShedTransferMapper, TShedTransfer> implements ITShedTransferService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TShedTransferMapper tShedTransferMapper;
    @Resource
    private TLivestockMapper tLivestockMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;
    @Resource
    private TLivestockShedMapper tLivestockShedMapper;
    @Resource
    private TLivestockBedMapper tLivestockBedMapper;

    @Override
    public IPage<TShedTransfer> tShedTransferList(PageDomain page, TShedTransfer vo) {
        Page<TShedTransfer> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tShedTransferMapper.tShedTransferList(pg,vo);
    }

    @Override
    @Transactional
    public Boolean tShedTransferAdd(ShedTransVo entityVo) {
        List<Long> list = entityVo.getLivestockId();
        for (Long id:list) {
            TShedTransfer entity = new TShedTransfer();
            BeanUtils.copyBeanProp(entity, entityVo);
            TLivestock stock = tLivestockMapper.queryById(id);
            TLivestockShed shed = tLivestockShedMapper.selectById(entity.getShedId());
            TLivestockBed bed = tLivestockBedMapper.selectById(entity.getBedId());
            entity.setLivestockId(id);
            entity.setCreateTime(LocalDateTime.now());
            entity.setTransferTime(LocalDateTime.now());
            entity.setSourceId(stock.getBedId());
            if (tShedTransferMapper.insert(entity)>0) {
                stock.setBedId(entity.getBedId());
                stock.setBedName(bed.getBedName());
                stock.setShedId(entity.getShedId());
                stock.setShedName(shed.getShedName());
                if (tLivestockMapper.updateById(stock) <1) {
                    throw  new CustomException("失败");
                }
            }
        }
        return true;
    }

    @Override
    public int tShedTransferDel(Long id) {
        TShedTransfer trans = this.getById(id);
        trans.setDeleteFlag(1);
        return tShedTransferMapper.updateById(trans);
    }

    @Override
    public TShedTransfer tShedTransferUpdate(ShedTransVo entityVo) {
        TShedTransfer entity = new TShedTransfer();
        BeanUtils.copyBeanProp(entity, entityVo);
        tShedTransferMapper.updateById(entity);
        return entity;
    }

    @Override
    public TShedTransfer tShedTransferDetail(TShedTransfer entity) {
        return tShedTransferMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public List<TBaseDict> getTrans() {
        QueryWrapper<TBaseDict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type_id", "dict_type_10");
        return tBaseDictMapper.selectList(wrapper);
    }

    @Override
    public List<TLivestockShed> getSheds() {
        return tLivestockShedMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<StockBedVo> getBeds(Long shedId) {
        List<TLivestockBed> beds = tLivestockBedMapper.selectList(new QueryWrapper<>());
        List<StockBedVo> bedVo = new ArrayList<>();
        for (TLivestockBed bed:beds) {
            StockBedVo vo = new StockBedVo();
            if (shedId != null) {
                if (!bed.getShedId().equals(shedId)) {
                    continue;
                }
            }
            BeanUtils.copyBeanProp(vo, bed);
            bedVo.add(vo);
        }
        return bedVo;
    }
}
