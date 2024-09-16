package com.smart.brd.manage.base.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TDeviceExt;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITDeviceExtService;
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
@RequestMapping("/api/v1/brd/tdeviceext")
public class TDeviceExtController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITDeviceExtService tDeviceExtService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tDeviceExtList")
    public Result tDeviceExtList(HttpServletRequest request,PageDomain page,TDeviceExt entity) {
        try {
            IPage<TDeviceExt> iPage = tDeviceExtService.tDeviceExtList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tDeviceExtAdd")
    public Result tDeviceExtAdd(@RequestBody TDeviceExt entity) {
        try {
            tDeviceExtService.tDeviceExtAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tDeviceExtDel")
    public Result tDeviceExtDel(Long id) {
        try {
            tDeviceExtService.tDeviceExtDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tDeviceExtUpdate")
    public Result tDeviceExtUpdate(@RequestBody TDeviceExt entity) {
        try {
            tDeviceExtService.tDeviceExtUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tDeviceExtDetail")
    public Result tDeviceExtDetail(HttpServletRequest request,TDeviceExt entity) {
        try {
            TDeviceExt vo = tDeviceExtService.tDeviceExtDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
