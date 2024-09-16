package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardStatusService;
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
@RequestMapping("/api/card/v1/tcardstatus")
public class TCardStatusController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardStatusService tCardStatusService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardStatusList")
    public Result tCardStatusList(HttpServletRequest request,PageDomain page,TCardStatus entity) {
        try {
            IPage iPage = tCardStatusService.tCardStatusList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardStatusAdd")
    public Result tCardStatusAdd(@RequestBody TCardStatus entity) {
        try {
            tCardStatusService.tCardStatusAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardStatusDel")
    public Result tCardStatusDel(Long id) {
        try {
            tCardStatusService.tCardStatusDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardStatusUpdate")
    public Result tCardStatusUpdate(@RequestBody TCardStatus entity) {
        try {
            tCardStatusService.tCardStatusUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardStatusDetail")
    public Result tCardStatusDetail(HttpServletRequest request,TCardStatus entity) {
        try {
            TCardStatus vo = tCardStatusService.tCardStatusDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
