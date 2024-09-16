package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TProductSub;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITProductSubService;
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
@RequestMapping("/api/card/v1/tproductsub")
public class TProductSubController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITProductSubService tProductSubService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tProductSubList")
    public Result tProductSubList(HttpServletRequest request,PageDomain page,TProductSub entity) {
        try {
            IPage iPage = tProductSubService.tProductSubList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tProductSubAdd")
    public Result tProductSubAdd(@RequestBody TProductSub entity) {
        try {
            tProductSubService.tProductSubAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tProductSubDel")
    public Result tProductSubDel(Long id) {
        try {
            tProductSubService.tProductSubDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tProductSubUpdate")
    public Result tProductSubUpdate(@RequestBody TProductSub entity) {
        try {
            tProductSubService.tProductSubUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tProductSubDetail")
    public Result tProductSubDetail(HttpServletRequest request,TProductSub entity) {
        try {
            TProductSub vo = tProductSubService.tProductSubDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
