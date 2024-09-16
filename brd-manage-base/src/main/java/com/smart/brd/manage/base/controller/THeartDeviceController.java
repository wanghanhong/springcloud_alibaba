package com.smart.brd.manage.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.THeartDevice;
import com.smart.brd.manage.base.screen.entity.ScreenLineVo;
import com.smart.brd.manage.base.service.ITHeartDeviceService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 
 */
@RestController
@Api(tags = "耳标信息")
@RequestMapping("/api/card/v1/theartdevice")
public class THeartDeviceController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITHeartDeviceService tHeartDeviceService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tHeartDeviceList")
    public Result tHeartDeviceList(HttpServletRequest request,PageDomain page,THeartDevice entity) {
        try {
            IPage<THeartDevice> iPage = tHeartDeviceService.tHeartDeviceList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tHeartDeviceAdd")
    public Result tHeartDeviceAdd(@RequestBody THeartDevice entity) {
        try {
            tHeartDeviceService.tHeartDeviceAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tHeartDeviceDel")
    public Result tHeartDeviceDel(Long id) {
        try {
            tHeartDeviceService.tHeartDeviceDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tHeartDeviceUpdate")
    public Result tHeartDeviceUpdate(@RequestBody THeartDevice entity) {
        try {
            tHeartDeviceService.tHeartDeviceUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tHeartDeviceDetail")
    public Result tHeartDeviceDetail(HttpServletRequest request,THeartDevice entity) {
        try {
            THeartDevice vo = tHeartDeviceService.tHeartDeviceDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/
    @ApiOperation(value = "耳标温度曲线统计图")
    @GetMapping("/temperatureCurve")
    public Result temperatureCurve(String deviceCode) {
        ScreenLineVo list= tHeartDeviceService.temperatureCurve(deviceCode);
        return Result.SUCCESS(list);
    }

}
