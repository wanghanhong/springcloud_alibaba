package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TAlarmRules;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITAlarmRulesService extends IService<TAlarmRules> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TAlarmRules> tAlarmRulesList(PageDomain page,TAlarmRules entity);

    TAlarmRules tAlarmRulesAdd(TAlarmRules entity);

    TAlarmRules tAlarmRulesUpdate(TAlarmRules entity);

    int tAlarmRulesDel(Long id);

    TAlarmRules tAlarmRulesDetail(TAlarmRules entity);
    /**------基本方法结束-----------------------------------------*/

}
