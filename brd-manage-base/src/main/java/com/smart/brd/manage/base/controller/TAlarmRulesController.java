package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TAlarmRules;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITAlarmRulesService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 
 */
@RestController
@Api(tags = "告警规则")
@RequestMapping("/api/v1/brd/talarmrules")
public class TAlarmRulesController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITAlarmRulesService tAlarmRulesService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tAlarmRulesList")
    public Result tAlarmRulesList(HttpServletRequest request,PageDomain page,TAlarmRules entity) {
        try {
            IPage<TAlarmRules> iPage = tAlarmRulesService.tAlarmRulesList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tAlarmRulesAdd")
    public Result tAlarmRulesAdd(@RequestBody TAlarmRules entity) {
        try {
            tAlarmRulesService.tAlarmRulesAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tAlarmRulesDel")
    public Result tAlarmRulesDel(Long id) {
        try {
            tAlarmRulesService.tAlarmRulesDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tAlarmRulesUpdate")
    public Result tAlarmRulesUpdate(@RequestBody TAlarmRules entity) {
        try {
            tAlarmRulesService.tAlarmRulesUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tAlarmRulesDetail")
    public Result tAlarmRulesDetail(HttpServletRequest request,TAlarmRules entity) {
        try {
            TAlarmRules vo = tAlarmRulesService.tAlarmRulesDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
