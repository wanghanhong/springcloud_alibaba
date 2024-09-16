package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.vo.ShedOutAllVo;
import com.smart.brd.manage.base.entity.vo.ShedOutVo;
import com.smart.brd.manage.base.mapper.*;
import com.smart.brd.manage.base.service.ITLivestockShedoutService;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author junglelocal
 */
@Service
public class TLivestockShedoutServiceImpl extends ServiceImpl<TLivestockShedoutMapper, TLivestockShedout> implements ITLivestockShedoutService {

    @Resource
    private TLivestockShedoutMapper tLivestockShedoutMapper;
    @Resource
    private TLivestockOutMapper tLivestockOutMapper;
    @Resource
    private TLivestockMapper tLivestockMapper;
    @Resource
    private TLivestockLogMapper tLivestockLogMapper;
    @Resource
    private TDiseaseTreatMapper tDiseaseTreatMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;

    @Override
    public IPage<TLivestockOut> tLivestockOutList(PageDomain page, ShedOutAllVo entity) {
        Page<TLivestockOut> pg = new Page<>(page.getPageNum(), page.getPageSize());
        if (entity.getEndTime() != null) {
            entity.setEndTime(entity.getEndTime());
        }
        return  tLivestockOutMapper.tLivestockOutList(pg, entity);
    }

    @Override
    public IPage<ShedOutVo> tLivestockShedoutList(PageDomain page, ShedOutVo entity) {
        Page<ShedOutVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        if (entity.getEndTime() != null) {
            entity.setEndTime(entity.getEndTime());
        }
        IPage<ShedOutVo> iPage = tLivestockShedoutMapper.tLivestockShedoutList(pg, entity);
        List<ShedOutVo> list = iPage.getRecords();
        for (ShedOutVo out : list) {
            TLivestock stock = tLivestockMapper.selectById(getById(out.getId()).getLivestockId());
            if (stock != null) {
                BeanUtils.copyBeanProp(out, stock);
            }
        }
        iPage.setRecords(list);
        return iPage;
    }

    public void outSituation() {
        List<TLivestockShedout> stocks = tLivestockShedoutMapper.selectList(new QueryWrapper<>());
        for (TLivestockShedout out : stocks) {
            if (out.getOutDate().isBefore(LocalDate.now())) {
                TLivestock s = tLivestockMapper.selectById(out.getLivestockId());
                s.setDeleteFlag(1);
                tLivestockMapper.updateById(s);
            }
        }
    }

    @Override
    @Transactional
    public TLivestockShedout tLivestockOutAdd(ShedOutAllVo entity) {
        TLivestockShedout out = new TLivestockShedout();
        BeanUtils.copyBeanProp(out, entity);
        List<Long> ids = entity.getLivestockIds();
        String msg="";
        for (Long id : ids) {
            out.setLivestockId(id);
            out.setCreateTime(LocalDateTime.now());
            out.setOutDate(entity.getOutTime());
            out.setOutSingle(entity.getOutSingle());
            TLivestock stock = tLivestockMapper.selectById(id);
            TDiseaseTreat tDiseaseTreat = tDiseaseTreatMapper.selectOne(new LambdaQueryWrapper<TDiseaseTreat>().eq(TDiseaseTreat::getDeleteFlag, 0).eq(TDiseaseTreat::getLivestockId, id).eq(TDiseaseTreat::getTreatmentState, 127));
            if (tDiseaseTreat!=null){
                String dictName = tBaseDictMapper.selectOne(new LambdaQueryWrapper<TBaseDict>().eq(TBaseDict::getDictTypeId, "dict_type_117").eq(TBaseDict::getDictId, stock.getVarieties())).getDictName();
                msg+="【品种为："+dictName+"，编号为："+stock.getDeviceCode()+"正在治疗，不能出栏"+"】";
            }
            TLivestockLog log = new TLivestockLog();
            BeanUtils.copyBeanProp(log, stock);
            log.setType(stock.getType());
            log.setCreateTime(LocalDateTime.now());
            log.setPreListingDate(LocalDate.from(entity.getOutTime()));
            //家畜出栏时将家畜主表逻辑删除字段改为1
            stock.setDeleteFlag(1);
                if (tLivestockMapper.updateById(stock) > 0) {
                    tLivestockShedoutMapper.insert(out);
                }
            }
        if (!"".equals(msg)){
            throw new CustomException(msg);
        }
        TLivestockOut outAll = new TLivestockOut();
        BeanUtils.copyBeanProp(outAll, entity);
        outAll.setCreateTime(LocalDateTime.now());
        outAll.setOutDate(entity.getOutTime());
        outAll.setOutNumber((long) ids.size());
        if (tLivestockOutMapper.insert(outAll) != 0) {
            Long outId = outAll.getId();
            for (Long id : ids) {
                UpdateWrapper<TLivestockShedout> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("livestock_id", id);
                updateWrapper.set("out_id", outId);
                this.update(updateWrapper);
            }
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int tLivestockOutDel(Long id) {
        TLivestockShedout shedout = tLivestockShedoutMapper.selectById(id);
        shedout.setDeleteFlag(1);
        TLivestock log = tLivestockMapper.selectById(shedout.getLivestockId());
        TLivestock stock = new TLivestock();
        BeanUtils.copyBeanProp(stock, log);
        stock.setDeleteFlag(0);
        //将牲畜主表被逻辑删除的状态改为0
        if (tLivestockMapper.updateById(stock) > 0) {
            //删除这条log
                //更新出栏表状态为已删除
                int i = tLivestockShedoutMapper.updateById(shedout);
                //每删除一条去查询是否还有
                Long outId = shedout.getOutId();
                int num = tLivestockShedoutMapper.selectByOutId(outId);
                //如果查询到的结果是0 就逻辑删除这条数据
                if (num == 0) {
                    TLivestockOut tLivestockOut = new TLivestockOut();
                    tLivestockOut.setId(outId);
                    tLivestockOut.setDeleteFlag(1);
                    tLivestockOutMapper.updateById(tLivestockOut);
                }
                return i;
        }
        return 0;
    }

    @Override
    public ShedOutVo tLivestockOutDetail(Long shedoutId) {
        ShedOutVo vo = new ShedOutVo();
        TLivestock stock = tLivestockMapper.selectById(getById(shedoutId).getLivestockId());
        ShedOutVo shedout = tLivestockShedoutMapper.selectDetailById(shedoutId);
        Double outSingle = shedout.getOutSingle();
        if (stock != null) {
            BeanUtils.copyBeanProp(vo, stock);
            vo.setOutSingle(outSingle);
            vo.setEntryTime(shedout.getEntryTime());
            vo.setOutDate(shedout.getOutDate());
            vo.setVarietie(shedout.getVarietie());
        }
        return vo;
    }
}
