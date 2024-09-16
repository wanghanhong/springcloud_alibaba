package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.TAlarmRules;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.mapper.TAlarmRulesMapper;
import com.smart.brd.manage.base.mapper.TBaseDictMapper;
import com.smart.brd.manage.base.service.DictListService;
import com.smart.brd.manage.base.service.ITAlarmRulesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;

import javax.annotation.Resource;

import com.smart.common.utils.IdWorker;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author
 */
@Service
public class TAlarmRulesServiceImpl extends ServiceImpl<TAlarmRulesMapper, TAlarmRules> implements ITAlarmRulesService {

    /**
     * ------通用方法开始-----------------------------------------
     */
    @Resource
    private TAlarmRulesMapper tAlarmRulesMapper;
    @Resource
    private DictListService dictListService;
    @Resource
    private TBaseDictMapper tBaseDictMapper;

    private static final String TYPE = "dict_type_1";

    @Override
    public IPage<TAlarmRules> tAlarmRulesList(PageDomain page, TAlarmRules vo) {
        Page<TAlarmRules> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tAlarmRulesMapper.tAlarmRulesList(pg, vo);
    }

    @Override
    @Transactional
    public TAlarmRules tAlarmRulesAdd(TAlarmRules entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        entity.setRuleId(id);
        entity.setCreateTime(LocalDateTime.now());
        if (Objects.isNull(entity.getAlarmRules())) {
            //根据告警类型填写告警规则
            String alarmRules = dictListService.selectNameById(entity.getRuleType());
            entity.setAlarmRules(alarmRules + "大于" + entity.getThresholdMax() + "或小于" + entity.getThresholdMin() + "时告警");
        }
        String[] split = dictListService.typeListToType(entity.getSuitableList()).split(",");
        for (String s : split) {
            List<TAlarmRules> tAlarmRules = tAlarmRulesMapper.selectList(new LambdaQueryWrapper<TAlarmRules>().eq(TAlarmRules::getDeleteFlag, 0).like(TAlarmRules::getSuitable, s).in(TAlarmRules::getDeptId, entity.getDeptIds()).eq(TAlarmRules::getRuleType,entity.getRuleType()));
            if (tAlarmRules.size()>0){
                TBaseDict dict = tBaseDictMapper.selectOne(new LambdaQueryWrapper<TBaseDict>().eq(TBaseDict::getDictTypeId, TYPE).eq(TBaseDict::getDictId, s));
                throw new CustomException(dict.getDictName()+"规则已存在，请勿重复添加！");
            }
        }
        entity.setSuitable(dictListService.typeListToType(entity.getSuitableList()));
        entity.setSuitableName(dictListService.typeListToName(TYPE, entity.getSuitableList()));
        this.save(entity);
        return entity;
    }

    @Override
    public int tAlarmRulesDel(Long id) {
        TAlarmRules tAlarmRules = tAlarmRulesMapper.selectById(id);
        tAlarmRules.setDeleteFlag(1);
        return tAlarmRulesMapper.updateById(tAlarmRules);
    }

    @Override
    @Transactional
    public TAlarmRules tAlarmRulesUpdate(TAlarmRules entity) {
        entity.setSuitable(dictListService.typeListToType(entity.getSuitableList()));
        if (Objects.isNull(entity.getAlarmRules())) {
            String alarmRules = dictListService.selectNameById(entity.getRuleType());
            entity.setAlarmRules(alarmRules + "大于"  + entity.getThresholdMax() + "或小于" + entity.getThresholdMin() + "时告警");
        }
        String[] split = dictListService.typeListToType(entity.getSuitableList()).split(",");
        for (String s : split) {
            List<TAlarmRules> tAlarmRules = tAlarmRulesMapper.selectList(new LambdaQueryWrapper<TAlarmRules>().eq(TAlarmRules::getDeleteFlag, 0).like(TAlarmRules::getSuitable, s).eq(TAlarmRules::getDeptId, entity.getDeptIds()).eq(TAlarmRules::getRuleType,entity.getRuleType()));
            if (tAlarmRules != null && tAlarmRules.size()>0 && !tAlarmRules.get(0).getRuleId().equals(entity.getRuleId())){
                TBaseDict dict = tBaseDictMapper.selectOne(new LambdaQueryWrapper<TBaseDict>().eq(TBaseDict::getDictTypeId, TYPE).eq(TBaseDict::getDictId, s));
                throw new CustomException(dict.getDictName()+"规则已存在，请重新选择类型！");
            }
        }
        entity.setSuitableName(dictListService.typeListToName(TYPE, entity.getSuitableList()));
        tAlarmRulesMapper.updateById(entity);
        return entity;
    }

    @Override
    public TAlarmRules tAlarmRulesDetail(TAlarmRules entity) {
        TAlarmRules detail = tAlarmRulesMapper.selectById(entity.getRuleId());
        List<Integer> su = new ArrayList<>();
        String[] split = detail.getSuitable().split(",");
        for (String s : split) {
            su.add(Integer.parseInt(s));
        }
        detail.setSuitableList(su);
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
