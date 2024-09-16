package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TEdition;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITEditionService;
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
@Api(tags = "版本信息")
@RequestMapping("/api/v1/brd/tedition")
public class TEditionController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITEditionService tEditionService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tEditionList")
    @RequiresPermissions("version:view")
    public Result tEditionList(HttpServletRequest request,PageDomain page,TEdition entity) {
        try {
            IPage<TEdition> iPage = tEditionService.tEditionList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tEditionAdd")
    public Result tEditionAdd(@RequestBody TEdition entity) {
        try {
            tEditionService.tEditionAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tEditionDel")
    public Result tEditionDel(Long id) {
        try {
            tEditionService.tEditionDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tEditionUpdate")
    public Result tEditionUpdate(@RequestBody TEdition entity) {
        try {
            tEditionService.tEditionUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tEditionDetail")
    public Result tEditionDetail(HttpServletRequest request,TEdition entity) {
        try {
            TEdition vo = tEditionService.tEditionDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
