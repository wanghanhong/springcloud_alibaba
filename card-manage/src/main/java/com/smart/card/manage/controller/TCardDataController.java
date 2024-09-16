package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardDataService;
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
@RequestMapping("/api/card/v1/tcarddata")
public class TCardDataController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardDataService tCardDataService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardDataList")
    public Result tCardDataList(HttpServletRequest request,PageDomain page,TCardData entity) {
        try {
            IPage iPage = tCardDataService.tCardDataList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardDataAdd")
    public Result tCardDataAdd(@RequestBody TCardData entity) {
        try {
            tCardDataService.tCardDataAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardDataDel")
    public Result tCardDataDel(Long id) {
        try {
            tCardDataService.tCardDataDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardDataUpdate")
    public Result tCardDataUpdate(@RequestBody TCardData entity) {
        try {
            tCardDataService.tCardDataUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardDataDetail")
    public Result tCardDataDetail(HttpServletRequest request,TCardData entity) {
        try {
            TCardData vo = tCardDataService.tCardDataDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
