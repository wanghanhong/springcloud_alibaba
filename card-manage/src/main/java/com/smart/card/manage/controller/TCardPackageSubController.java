package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardPackageSub;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardPackageSubService;
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
@RequestMapping("/api/card/v1/tcardpackagesub")
public class TCardPackageSubController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardPackageSubService tCardPackageSubService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardPackageSubList")
    public Result tCardPackageSubList(HttpServletRequest request,PageDomain page,TCardPackageSub entity) {
        try {
            IPage iPage = tCardPackageSubService.tCardPackageSubList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardPackageSubAdd")
    public Result tCardPackageSubAdd(@RequestBody TCardPackageSub entity) {
        try {
            tCardPackageSubService.tCardPackageSubAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardPackageSubDel")
    public Result tCardPackageSubDel(Long id) {
        try {
            tCardPackageSubService.tCardPackageSubDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardPackageSubUpdate")
    public Result tCardPackageSubUpdate(@RequestBody TCardPackageSub entity) {
        try {
            tCardPackageSubService.tCardPackageSubUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardPackageSubDetail")
    public Result tCardPackageSubDetail(HttpServletRequest request,TCardPackageSub entity) {
        try {
            TCardPackageSub vo = tCardPackageSubService.tCardPackageSubDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
