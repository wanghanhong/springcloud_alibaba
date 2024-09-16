package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TLivestockPurchase;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITLivestockPurchaseService;
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
@Api(tags = "家畜采购商")
@RequestMapping("/api/v1/brd/tlivestockpurchase")
public class TLivestockPurchaseController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITLivestockPurchaseService tLivestockPurchaseService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tLivestockPurchaseList")
    public Result tLivestockPurchaseList(HttpServletRequest request,PageDomain page,TLivestockPurchase entity) {
        try {
            IPage<TLivestockPurchase> iPage = tLivestockPurchaseService.tLivestockPurchaseList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tLivestockPurchaseAdd")
    public Result tLivestockPurchaseAdd(@RequestBody TLivestockPurchase entity) {
        try {
            tLivestockPurchaseService.tLivestockPurchaseAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tLivestockPurchaseDel")
    public Result tLivestockPurchaseDel(Long id) {
        try {
            tLivestockPurchaseService.tLivestockPurchaseDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tLivestockPurchaseUpdate")
    public Result tLivestockPurchaseUpdate(@RequestBody TLivestockPurchase entity) {
        try {
            tLivestockPurchaseService.tLivestockPurchaseUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tLivestockPurchaseDetail")
    public Result tLivestockPurchaseDetail(HttpServletRequest request,TLivestockPurchase entity) {
        try {
            TLivestockPurchase vo = tLivestockPurchaseService.tLivestockPurchaseDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
