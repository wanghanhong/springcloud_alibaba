package com.smart.video.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.video.manage.service.ITVideoService;
import com.smart.video.manage.common.utils.ExcelUtils;
import com.smart.video.manage.entity.TVideo;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 
 */
@RestController
@Api(tags = "设备管理")
@RequestMapping("/api/v1/brd/tdevice")
public class TVideoController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITVideoService tdeviceService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tdeviceList")
    public Result tdeviceList(HttpServletRequest request, PageDomain page, TVideo entity) {
        try {
            IPage iPage = tdeviceService.tDeviceList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "id", paramType = "body", required = false,dataType = "Long"),
            @ApiImplicitParam(name = "deviceType", value = "设备类型", paramType = "body", required = false,dataType = "String"),
            @ApiImplicitParam(name = "deviceCode", value = "设备编号", paramType = "body", required = false, dataType = "String"),
            @ApiImplicitParam(name = "deviceName", value = "设备名称", paramType = "body", required = false, dataType = "String")
    })
    @PostMapping("/tdeviceAdd")
    public Result tdeviceAdd(@RequestBody TVideo entity) {
        try {
            tdeviceService.tDeviceAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tdeviceDel")
    public Result tdeviceDel(Long deviceId) {
        try {
            tdeviceService.tDeviceDel(deviceId);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tdeviceUpdate")
    public Result tdeviceUpdate(@RequestBody TVideo entity) {
        try {
            tdeviceService.tDeviceUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tdeviceDetail")
    public Result tdeviceDetail(HttpServletRequest request, TVideo entity) {
        try {
            TVideo vo = tdeviceService.tDeviceDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "导入设备")
    @PostMapping("/import")
    public Result imports(@RequestParam MultipartFile path) {
        try {
            List<TVideo> tDevices = ExcelUtils.importExcel(path, 0, 1, TVideo.class);
            for (TVideo tDevice : tDevices) {
                tdeviceService.tDeviceAdd(tDevice);
            }
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "导出设备")
    @GetMapping("/export")
    public Result export(@RequestParam(required = false) Long[] ids, HttpServletResponse response) {
        List<TVideo> tDeviceList=tdeviceService.findList(ids);
        try {
            ExcelUtils.exportExcel(tDeviceList,null,"设备", TVideo.class,"设备.xls",response);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    
}
