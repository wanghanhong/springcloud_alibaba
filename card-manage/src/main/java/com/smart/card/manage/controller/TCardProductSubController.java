package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardProductSub;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardProductSubService;
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
@RequestMapping("/api/card/v1/tcardproductsub")
public class TCardProductSubController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardProductSubService tCardProductSubService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardProductSubList")
    public Result tCardProductSubList(HttpServletRequest request,PageDomain page,TCardProductSub entity) {
        try {
            IPage iPage = tCardProductSubService.tCardProductSubList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardProductSubAdd")
    public Result tCardProductSubAdd(@RequestBody TCardProductSub entity) {
        try {
            tCardProductSubService.tCardProductSubAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardProductSubDel")
    public Result tCardProductSubDel(Long id) {
        try {
            tCardProductSubService.tCardProductSubDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardProductSubUpdate")
    public Result tCardProductSubUpdate(@RequestBody TCardProductSub entity) {
        try {
            tCardProductSubService.tCardProductSubUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardProductSubDetail")
    public Result tCardProductSubDetail(HttpServletRequest request,TCardProductSub entity) {
        try {
            TCardProductSub vo = tCardProductSubService.tCardProductSubDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
