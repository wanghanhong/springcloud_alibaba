package com.smart.brd.manage.base.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TBrdBreeder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITBrdBreederService;
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
@RequestMapping("/api/v1/brd/tbrdbreeder")
public class TBrdBreederController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBrdBreederService tBrdBreederService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tBrdBreederList")
    @RequiresPermissions("tbrdbreeder:view")
    public Result tBrdBreederList(HttpServletRequest request,PageDomain page,TBrdBreeder entity) {
        try {
            IPage<TBrdBreeder> iPage = tBrdBreederService.tBrdBreederList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tBrdBreederAdd")
    public Result tBrdBreederAdd(@RequestBody TBrdBreeder entity) {
        try {
            tBrdBreederService.tBrdBreederAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tBrdBreederDel")
    public Result tBrdBreederDel(Long id) {
        try {
            tBrdBreederService.tBrdBreederDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tBrdBreederUpdate")
    public Result tBrdBreederUpdate(@RequestBody TBrdBreeder entity) {
        try {
            tBrdBreederService.tBrdBreederUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tBrdBreederDetail")
    public Result tBrdBreederDetail(HttpServletRequest request,TBrdBreeder entity) {
        try {
            TBrdBreeder vo = tBrdBreederService.tBrdBreederDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
