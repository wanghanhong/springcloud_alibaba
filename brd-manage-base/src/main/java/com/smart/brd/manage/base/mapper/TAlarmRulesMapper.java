package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TAlarmRules;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TAlarmRulesMapper extends BaseMapper<TAlarmRules> {

    IPage<TAlarmRules> tAlarmRulesList(Page<TAlarmRules> page, @Param("vo")TAlarmRules vo);


}
