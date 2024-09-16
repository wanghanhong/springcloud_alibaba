package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardPackage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardPackageService;
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
@RequestMapping("/api/card/v1/tcardpackage")
public class TCardPackageController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardPackageService tCardPackageService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardPackageList")
    public Result tCardPackageList(HttpServletRequest request,PageDomain page,TCardPackage entity) {
        try {
            IPage iPage = tCardPackageService.tCardPackageList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardPackageAdd")
    public Result tCardPackageAdd(@RequestBody TCardPackage entity) {
        try {
            tCardPackageService.tCardPackageAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardPackageDel")
    public Result tCardPackageDel(Long id) {
        try {
            tCardPackageService.tCardPackageDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardPackageUpdate")
    public Result tCardPackageUpdate(@RequestBody TCardPackage entity) {
        try {
            tCardPackageService.tCardPackageUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardPackageDetail")
    public Result tCardPackageDetail(HttpServletRequest request,TCardPackage entity) {
        try {
            TCardPackage vo = tCardPackageService.tCardPackageDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
