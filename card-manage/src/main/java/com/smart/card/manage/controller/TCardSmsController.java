package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardSms;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardSmsService;
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
@RequestMapping("/api/card/v1/tcardsms")
public class TCardSmsController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardSmsService tCardSmsService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardSmsList")
    public Result tCardSmsList(HttpServletRequest request,PageDomain page,TCardSms entity) {
        try {
            IPage iPage = tCardSmsService.tCardSmsList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardSmsAdd")
    public Result tCardSmsAdd(@RequestBody TCardSms entity) {
        try {
            tCardSmsService.tCardSmsAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardSmsDel")
    public Result tCardSmsDel(Long id) {
        try {
            tCardSmsService.tCardSmsDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardSmsUpdate")
    public Result tCardSmsUpdate(@RequestBody TCardSms entity) {
        try {
            tCardSmsService.tCardSmsUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardSmsDetail")
    public Result tCardSmsDetail(HttpServletRequest request,TCardSms entity) {
        try {
            TCardSms vo = tCardSmsService.tCardSmsDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
