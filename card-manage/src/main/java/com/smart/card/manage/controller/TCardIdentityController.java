package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardIdentity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardIdentityService;
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
@RequestMapping("/api/card/v1/tcardidentity")
public class TCardIdentityController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardIdentityService tCardIdentityService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardIdentityList")
    public Result tCardIdentityList(HttpServletRequest request,PageDomain page,TCardIdentity entity) {
        try {
            IPage iPage = tCardIdentityService.tCardIdentityList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardIdentityAdd")
    public Result tCardIdentityAdd(@RequestBody TCardIdentity entity) {
        try {
            tCardIdentityService.tCardIdentityAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardIdentityDel")
    public Result tCardIdentityDel(Long id) {
        try {
            tCardIdentityService.tCardIdentityDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardIdentityUpdate")
    public Result tCardIdentityUpdate(@RequestBody TCardIdentity entity) {
        try {
            tCardIdentityService.tCardIdentityUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardIdentityDetail")
    public Result tCardIdentityDetail(HttpServletRequest request,TCardIdentity entity) {
        try {
            TCardIdentity vo = tCardIdentityService.tCardIdentityDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
