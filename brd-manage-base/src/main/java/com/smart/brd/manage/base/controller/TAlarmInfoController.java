package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TAlarmInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITAlarmInfoService;
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
@Api(tags = "告警信息")
@RequestMapping("/api/v1/brd/talarminfo")
public class TAlarmInfoController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITAlarmInfoService tAlarmInfoService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tAlarmInfoList")
    @RequiresPermissions("piggeryalarm:view")
    public Result tAlarmInfoList(HttpServletRequest request,PageDomain page,TAlarmInfo entity) {
        try {
            IPage<TAlarmInfo> iPage = tAlarmInfoService.tAlarmInfoList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tAlarmInfoAdd")
    public Result tAlarmInfoAdd(@RequestBody TAlarmInfo entity) {
        try {
            tAlarmInfoService.tAlarmInfoAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tAlarmInfoDel")
    public Result tAlarmInfoDel(Long id) {
        try {
            tAlarmInfoService.tAlarmInfoDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tAlarmInfoUpdate")
    public Result tAlarmInfoUpdate(@RequestBody TAlarmInfo entity) {
        try {
            tAlarmInfoService.tAlarmInfoUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tAlarmInfoDetail")
    public Result tAlarmInfoDetail(HttpServletRequest request,TAlarmInfo entity) {
        try {
            TAlarmInfo vo = tAlarmInfoService.tAlarmInfoDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

}
