package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardProduct;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardProductService;
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
@RequestMapping("/api/card/v1/tcardproduct")
public class TCardProductController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardProductService tCardProductService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardProductList")
    public Result tCardProductList(HttpServletRequest request,PageDomain page,TCardProduct entity) {
        try {
            IPage iPage = tCardProductService.tCardProductList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardProductAdd")
    public Result tCardProductAdd(@RequestBody TCardProduct entity) {
        try {
            tCardProductService.tCardProductAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardProductDel")
    public Result tCardProductDel(Long id) {
        try {
            tCardProductService.tCardProductDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardProductUpdate")
    public Result tCardProductUpdate(@RequestBody TCardProduct entity) {
        try {
            tCardProductService.tCardProductUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardProductDetail")
    public Result tCardProductDetail(HttpServletRequest request,TCardProduct entity) {
        try {
            TCardProduct vo = tCardProductService.tCardProductDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
