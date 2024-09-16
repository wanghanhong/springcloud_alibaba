package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TPoolPackage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITPoolPackageService;
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
@RequestMapping("/api/card/v1/tpoolpackage")
public class TPoolPackageController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITPoolPackageService tPoolPackageService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tPoolPackageList")
    public Result tPoolPackageList(HttpServletRequest request,PageDomain page,TPoolPackage entity) {
        try {
            IPage iPage = tPoolPackageService.tPoolPackageList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tPoolPackageAdd")
    public Result tPoolPackageAdd(@RequestBody TPoolPackage entity) {
        try {
            tPoolPackageService.tPoolPackageAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tPoolPackageDel")
    public Result tPoolPackageDel(Long id) {
        try {
            tPoolPackageService.tPoolPackageDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tPoolPackageUpdate")
    public Result tPoolPackageUpdate(@RequestBody TPoolPackage entity) {
        try {
            tPoolPackageService.tPoolPackageUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tPoolPackageDetail")
    public Result tPoolPackageDetail(HttpServletRequest request,TPoolPackage entity) {
        try {
            TPoolPackage vo = tPoolPackageService.tPoolPackageDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
