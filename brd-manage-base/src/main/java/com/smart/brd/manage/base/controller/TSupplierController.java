package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TSupplier;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITSupplierService;
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
@Api(tags = "供应商")
@RequestMapping("/api/v1/brd/tsupplier")
public class TSupplierController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITSupplierService tSupplierService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tSupplierList")
    public Result tSupplierList(HttpServletRequest request,PageDomain page,TSupplier entity) {
        try {
            IPage<TSupplier> iPage = tSupplierService.tSupplierList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tSupplierAdd")
    public Result tSupplierAdd(@RequestBody TSupplier entity) {
        try {
            tSupplierService.tSupplierAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tSupplierDel")
    public Result tSupplierDel(Long supplierId) {
        try {
            tSupplierService.tSupplierDel(supplierId);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tSupplierUpdate")
    public Result tSupplierUpdate(@RequestBody TSupplier entity) {
        try {
            tSupplierService.tSupplierUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tSupplierDetail")
    public Result tSupplierDetail(HttpServletRequest request,TSupplier entity) {
        try {
            TSupplier vo = tSupplierService.tSupplierDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/



}
