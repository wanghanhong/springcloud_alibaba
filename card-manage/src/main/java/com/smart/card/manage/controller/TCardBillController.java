package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardBill;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardBillService;
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
@RequestMapping("/api/card/v1/tcardbill")
public class TCardBillController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardBillService tCardBillService;

    @ApiOperation(value = "查询欠费")
    @GetMapping("/tCardBillList")
    public Result tCardBillList(HttpServletRequest request,PageDomain page,TCardBill entity) {
        try {
            IPage iPage = tCardBillService.tCardBillList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardBillAdd")
    public Result tCardBillAdd(@RequestBody TCardBill entity) {
        try {
            tCardBillService.tCardBillAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardBillDel")
    public Result tCardBillDel(Long id) {
        try {
            tCardBillService.tCardBillDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardBillUpdate")
    public Result tCardBillUpdate(@RequestBody TCardBill entity) {
        try {
            tCardBillService.tCardBillUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardBillDetail")
    public Result tCardBillDetail(HttpServletRequest request,TCardBill entity) {
        try {
            TCardBill vo = tCardBillService.tCardBillDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
