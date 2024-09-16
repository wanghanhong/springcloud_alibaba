package com.smart.brd.manage.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.brd.manage.base.entity.TDeviceInstall;
import com.smart.brd.manage.base.service.ITDeviceInstallService;
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
@RequestMapping("/api/v1/brd/camerinstall")
public class TDeviceCamerasInstallController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITDeviceInstallService tDeviceInstallService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/camerasInstallList")
    public Result tDeviceInstallList(HttpServletRequest request, PageDomain page, TDeviceInstall entity) {
        try {
            entity.setDeviceType(BrdConstant.DEVICE_TYPE_CAMERAS);
            IPage<TDeviceInstall> iPage = tDeviceInstallService.tDeviceInstallList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/camerasInstallAdd")
    public Result camerasInstallAdd(@RequestBody TDeviceInstall entity) {
        try {
            entity.setDeviceType(BrdConstant.DEVICE_TYPE_CAMERAS);
            tDeviceInstallService.tDeviceInstallAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
