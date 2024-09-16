package com.smart.brd.manage.base.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TDeviceCameras;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITDeviceCamerasService;
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
@RequestMapping("/api/v1/brd/tdevicecameras")
public class TDeviceCamerasController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITDeviceCamerasService tDeviceCamerasService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tDeviceCamerasList")
    public Result tDeviceCamerasList(HttpServletRequest request,PageDomain page,TDeviceCameras entity) {
        try {
            IPage<TDeviceCameras> iPage = tDeviceCamerasService.tDeviceCamerasList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tDeviceCamerasAdd")
    public Result tDeviceCamerasAdd(@RequestBody TDeviceCameras entity) {
        try {
            tDeviceCamerasService.tDeviceCamerasAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tDeviceCamerasDel")
    public Result tDeviceCamerasDel(Long id) {
        try {
            tDeviceCamerasService.tDeviceCamerasDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tDeviceCamerasUpdate")
    public Result tDeviceCamerasUpdate(@RequestBody TDeviceCameras entity) {
        try {
            tDeviceCamerasService.tDeviceCamerasUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tDeviceCamerasDetail")
    public Result tDeviceCamerasDetail(HttpServletRequest request,TDeviceCameras entity) {
        try {
            TDeviceCameras vo = tDeviceCamerasService.tDeviceCamerasDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
