package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.common.dict.DictCache;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.common.utils.LocalDateUtil;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.dto.EscapeDto;
import com.smart.brd.manage.base.entity.vo.EscapeVo;
import com.smart.brd.manage.base.mapper.*;
import com.smart.brd.manage.base.service.ITLivestockEscapeService;
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
import java.util.Map;

/**
 * @author
 */
@Service
public class TLivestockEscapeServiceImpl extends ServiceImpl<TLivestockEscapeMapper, TLivestockEscape> implements ITLivestockEscapeService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TLivestockEscapeMapper tLivestockEscapeMapper;
    @Resource
    private TLivestockMapper tLivestockMapper;
    @Resource
    private TLivestockLogMapper tLivestockLogMapper;
    @Resource
    private TDiseaseTreatMapper tDiseaseTreatMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;

    @Override
    public IPage<EscapeDto> tLivestockEscapeList(PageDomain page, EscapeVo vo) {
        Page<EscapeVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        if(StringUtils.isNotBlank(vo.getStartTime())){
            vo.setStartTime(vo.getStartTime()+ " 00:00:00");
        }
        if(StringUtils.isNotBlank(vo.getEndTime())){
            vo.setEndTime(vo.getEndTime()+ " 23:59:59");
        }
        IPage<EscapeDto> iPage = tLivestockEscapeMapper.tLivestockEscapeList(pg,vo);
        return iPage;
    }

    @Override
    @Transactional
    public TLivestockEscape tLivestockEscapeAdd(EscapeVo entity) {
        String info = isTread(entity.getLivestockId(), "添加淘汰");
        if(!"".equals(info)){
            throw new CustomException(info);
        }
        TLivestockEscape escape = new  TLivestockEscape();
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();


        if(entity.getId() == null){
            BeanUtils.copyBeanProp(escape, entity);
            escape.setId(id);
            escape.setDictName(DictCache.getDict(entity.getDictId()));
            escape.setCreateTime(LocalDateTime.now());
            escape.setEscapeDate(LocalDate.parse(entity.getEscapeDate()));
            this.save(escape);
            //淘汰后将存栏列表中的该条数据逻辑删除
            Long livestockId = entity.getLivestockId();
            QueryWrapper<TLivestock> tLivestockQueryWrapper = new QueryWrapper<>();
            tLivestockQueryWrapper.eq("livestock_id",livestockId);
            TLivestock tLivestock = tLivestockMapper.selectOne(tLivestockQueryWrapper);
            tLivestock.setDeleteFlag(1);
            tLivestockMapper.updateById(tLivestock);
//            //向log表中添加一条信息
//            TLivestockLog tLivestockLog = new TLivestockLog();
//            BeanUtils.copyBeanProp(tLivestockLog,tLivestock);
//            tLivestockLog.setCreateTime(LocalDateTime.now());
//            tLivestockLogMapper.insert(tLivestockLog);
        }else{
            tLivestockEscapeUpdate(entity);
        }
        return escape;
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
    public int tLivestockEscapeDel(Long id) {
        int res = tLivestockEscapeMapper.deleteById(id);
        return res;
    }

    @Override
    public TLivestockEscape tLivestockEscapeUpdate(EscapeVo entity) {
        TLivestockEscape escape = new  TLivestockEscape();
        BeanUtils.copyBeanProp(escape, entity);
        escape.setEscapeDate(LocalDate.parse(entity.getEscapeDate()));
        tLivestockEscapeMapper.updateById(escape);
        return escape;
    }

    @Override
    public EscapeDto tLivestockEscapeDetail(EscapeVo entity) {
        EscapeDto detail = tLivestockEscapeMapper.tLivestockEscapeDetail(entity);
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public EscapeVo queryEscapeAge(EscapeVo entity) {
        LocalDate from = entity.getBirthDate();
        LocalDate to = LocalDate.parse(entity.getEscapeDate());
        Map<String,Object> map = LocalDateUtil.calEscapeAge(from,to);
        entity.setEscapeAge(String.valueOf(map.get("age")));
        return entity;
    }

}
