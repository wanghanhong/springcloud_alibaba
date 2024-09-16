package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.entity.vo.DeviceVo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TDCamerasConfig;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITDCamerasConfigService;
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
@Api(tags = "首页视频列表")
@RequestMapping("/api/v1/brd/tdcamerasconfig")
public class TDCamerasConfigController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITDCamerasConfigService tDCamerasConfigService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tDCamerasConfigList")
    @RequiresPermissions("videoManagement:view")
    public Result tDCamerasConfigList(HttpServletRequest request,PageDomain page,TDCamerasConfig entity) {
        try {
            IPage<DeviceVo> iPage = tDCamerasConfigService.tDCamerasConfigList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tDCamerasConfigAdd")
    public Result tDCamerasConfigAdd(@RequestBody TDCamerasConfig entity) {
        try {
            tDCamerasConfigService.tDCamerasConfigAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tDCamerasConfigDel")
    public Result tDCamerasConfigDel(Long id) {
        try {
            tDCamerasConfigService.tDCamerasConfigDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tDCamerasConfigUpdate")
    public Result tDCamerasConfigUpdate(@RequestBody TDCamerasConfig entity) {
        try {
            tDCamerasConfigService.tDCamerasConfigUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tDCamerasConfigDetail")
    public Result tDCamerasConfigDetail(HttpServletRequest request,TDCamerasConfig entity) {
        try {
            TDCamerasConfig vo = tDCamerasConfigService.tDCamerasConfigDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
