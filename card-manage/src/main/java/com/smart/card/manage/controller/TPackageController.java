package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TPackage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITPackageService;
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
@RequestMapping("/api/card/v1/tpackage")
public class TPackageController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITPackageService tPackageService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tPackageList")
    public Result tPackageList(HttpServletRequest request,PageDomain page,TPackage entity) {
        try {
            IPage iPage = tPackageService.tPackageList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tPackageAdd")
    public Result tPackageAdd(@RequestBody TPackage entity) {
        try {
            tPackageService.tPackageAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tPackageDel")
    public Result tPackageDel(Long id) {
        try {
            tPackageService.tPackageDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tPackageUpdate")
    public Result tPackageUpdate(@RequestBody TPackage entity) {
        try {
            tPackageService.tPackageUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tPackageDetail")
    public Result tPackageDetail(HttpServletRequest request,TPackage entity) {
        try {
            TPackage vo = tPackageService.tPackageDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
