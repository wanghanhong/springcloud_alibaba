package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TPackageSub;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITPackageSubService;
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
@RequestMapping("/api/card/v1/tpackagesub")
public class TPackageSubController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITPackageSubService tPackageSubService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tPackageSubList")
    public Result tPackageSubList(HttpServletRequest request,PageDomain page,TPackageSub entity) {
        try {
            IPage iPage = tPackageSubService.tPackageSubList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tPackageSubAdd")
    public Result tPackageSubAdd(@RequestBody TPackageSub entity) {
        try {
            tPackageSubService.tPackageSubAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tPackageSubDel")
    public Result tPackageSubDel(Long id) {
        try {
            tPackageSubService.tPackageSubDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tPackageSubUpdate")
    public Result tPackageSubUpdate(@RequestBody TPackageSub entity) {
        try {
            tPackageSubService.tPackageSubUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tPackageSubDetail")
    public Result tPackageSubDetail(HttpServletRequest request,TPackageSub entity) {
        try {
            TPackageSub vo = tPackageSubService.tPackageSubDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
