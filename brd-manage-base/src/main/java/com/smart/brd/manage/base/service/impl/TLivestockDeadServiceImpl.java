package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smart.brd.manage.base.common.dict.DictCache;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.vo.DeadVo;
import com.smart.brd.manage.base.mapper.*;
import com.smart.brd.manage.base.service.ITLivestockDeadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author
 */
@Service
public class TLivestockDeadServiceImpl extends ServiceImpl<TLivestockDeadMapper, TLivestockDead> implements ITLivestockDeadService{

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TLivestockDeadMapper tLivestockDeadMapper;
    @Resource
    private TLivestockMapper tLivestockMapper;
    @Resource
    private TLivestockLogMapper tLivestockLogMapper;
    @Resource
    private TDiseaseTreatMapper tDiseaseTreatMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;

    @Override
    public IPage<DeadVo> tLivestockDeadList(PageDomain page, DeadVo vo) {
        Page<DeadVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        if(StringUtils.isNotBlank(vo.getStartTime())){
            vo.setStartTime(vo.getStartTime()+ " 00:00:00");
        }
        if(StringUtils.isNotBlank(vo.getEndTime())){
            vo.setEndTime(vo.getEndTime()+ " 23:59:59");
        }
//        queryWrapper.between(NoticeEntity::getReleaseTime, entity.getReleaseTime() + " 00:00:00", entity.getReleaseTime() + " 23:59:59");
        IPage<DeadVo> iPage = tLivestockDeadMapper.tLivestockDeadList(pg,vo);
        return iPage;
    }

    @Override
    @Transactional
    public TLivestockDead tLivestockDeadAdd(DeadVo entity) {
        String info = isTread(entity.getLivestockId(), "添加死亡");
        if(!"".equals(info)){
            throw new CustomException(info);
        }
        TLivestockDead dead = new  TLivestockDead();
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            BeanUtils.copyBeanProp(dead, entity);
            dead.setId(id);
            dead.setCreateTime(LocalDateTime.now());
            dead.setDictName(DictCache.getDict(entity.getDictId()));
            this.save(dead);
        }else{
            tLivestockDeadUpdate(entity);
        }
        TLivestock stock = tLivestockMapper.selectById(entity.getLivestockId());
        stock.setDeleteFlag(1);
        tLivestockMapper.updateById(stock);
        TLivestockLog log = new TLivestockLog();
        BeanUtils.copyBeanProp(log, stock);
        log.setCreateTime(LocalDateTime.now());
        TLivestockLog tLivestockLog = tLivestockLogMapper.selectById(entity.getLivestockId());
        if(tLivestockLog==null) {
            tLivestockLogMapper.insert(log);
        }
        return dead;
    }
    //判断这个牲畜是否在治疗
    public String isTread(Long id,String sta){
        TLivestock stock = tLivestockMapper.selectById(id);
        TDiseaseTreat tDiseaseTreat = tDiseaseTreatMapper.selectOne(new LambdaQueryWrapper<TDiseaseTreat>().eq(TDiseaseTreat::getDeleteFlag, 0).eq(TDiseaseTreat::getLivestockId, id).eq(TDiseaseTreat::getTreatmentState, 127));
        if (tDiseaseTreat!=null){
            String dictName = tBaseDictMapper.selectOne(new LambdaQueryWrapper<TBaseDict>().eq(TBaseDict::getDictTypeId, "dict_type_117").eq(TBaseDict::getDictId, stock.getVarieties())).getDictName();
            return "【品种为："+dictName+"，编号为："+stock.getDeviceCode()+"正在治疗，不能"+sta+"】";
        }
        return "";
    }

    @Override
    public int tLivestockDeadDel(Long id) {
        int res = tLivestockDeadMapper.deleteById(id);
        return res;
    }

    @Override
    public TLivestockDead tLivestockDeadUpdate(DeadVo entity) {
        TLivestockDead dead = new  TLivestockDead();
        BeanUtils.copyBeanProp(dead, entity);
        tLivestockDeadMapper.updateById(dead);
        return dead;
    }

    @Override
    public DeadVo tLivestockDeadDetail(DeadVo entity) {
        DeadVo detail = tLivestockDeadMapper.tLivestockDeadDetail(entity);
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
