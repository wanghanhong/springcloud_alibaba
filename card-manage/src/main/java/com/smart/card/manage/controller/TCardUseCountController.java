package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardUseCount;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardUseCountService;
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
@RequestMapping("/api/card/v1/tcardusecount")
public class TCardUseCountController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardUseCountService tCardUseCountService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardUseCountList")
    public Result tCardUseCountList(HttpServletRequest request,PageDomain page,TCardUseCount entity) {
        try {
            IPage iPage = tCardUseCountService.tCardUseCountList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardUseCountAdd")
    public Result tCardUseCountAdd(@RequestBody TCardUseCount entity) {
        try {
            tCardUseCountService.tCardUseCountAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardUseCountDel")
    public Result tCardUseCountDel(Long id) {
        try {
            tCardUseCountService.tCardUseCountDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardUseCountUpdate")
    public Result tCardUseCountUpdate(@RequestBody TCardUseCount entity) {
        try {
            tCardUseCountService.tCardUseCountUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardUseCountDetail")
    public Result tCardUseCountDetail(HttpServletRequest request,TCardUseCount entity) {
        try {
            TCardUseCount vo = tCardUseCountService.tCardUseCountDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
