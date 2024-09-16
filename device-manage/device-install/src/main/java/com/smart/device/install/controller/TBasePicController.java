package com.smart.device.install.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.install.entity.TBasePic;
import com.smart.device.install.service.ITBasePicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/install")
public class TBasePicController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBasePicService iTBasePicService;

    @ApiOperation(value = "平面图查询接口")
    @ApiImplicitParam
    @GetMapping("/base/pic/basePicList")
    public Result basePicList(PageDomain page, TBasePic entity) {
        try {
            IPage iPage = iTBasePicService.basePicList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PIC);
        }
    }

    @ApiOperation(value = "平面图添加接口")
    @ApiImplicitParam
    @PostMapping("/base/pic/basePicAdd")
    public Result basePicAdd(@RequestBody TBasePic entity) {
        try {
            iTBasePicService.basePicAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PIC);
        }
    }

    @ApiOperation(value = "平面图删除接口")
    @ApiImplicitParam
    @GetMapping("/base/pic/basePicDel")
    public Result basePicDel(Long id) {
        try {
            iTBasePicService.basePicDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PIC);
        }
    }

    @ApiOperation(value = "修改平面图接口")
    @ApiImplicitParam
    @PostMapping("/base/pic/basePicUpdate")
    public Result basePicUpdate(@RequestBody TBasePic entity) {
        try {
            iTBasePicService.basePicUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PIC);
        }
    }

    @ApiOperation(value = "根据ID查询平面图详情")
    @ApiImplicitParam
    @GetMapping("/selectbasePicDetail")
    public Result selectbasePicByID(Long id) {
        try {
            TBasePic vo = iTBasePicService.selectBasePicByID(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PIC);
        }
    }
    /**------基本方法结束-----------------------------------------*/



}
