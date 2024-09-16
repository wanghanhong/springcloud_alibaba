package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.entity.vo.PlaybackVideoVo;
import com.smart.brd.manage.base.entity.vo.PlaybackVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TDeviceInstall;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITDeviceInstallService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 
 */
@RestController
@Api(tags = "设备安装信息")
@RequestMapping("/api/v1/brd/tdeviceinstall")
public class TDeviceInstallController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITDeviceInstallService tDeviceInstallService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tDeviceInstallList")
    public Result tDeviceInstallList(HttpServletRequest request,PageDomain page,TDeviceInstall entity) {
        try {
            IPage<TDeviceInstall> iPage = tDeviceInstallService.tDeviceInstallList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tDeviceInstallAdd")
    public Result tDeviceInstallAdd(@RequestBody TDeviceInstall entity) {
        try {
            tDeviceInstallService.tDeviceInstallAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tDeviceInstallDel")
    public Result tDeviceInstallDel(Long id) {
        try {
            tDeviceInstallService.tDeviceInstallDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tDeviceInstallUpdate")
    public Result tDeviceInstallUpdate(@RequestBody TDeviceInstall entity) {
        try {
            tDeviceInstallService.tDeviceInstallUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tDeviceInstallDetail")
    public Result tDeviceInstallDetail(HttpServletRequest request,TDeviceInstall entity) {
        try {
            TDeviceInstall vo = tDeviceInstallService.tDeviceInstallDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/
    @ApiOperation(value = "查询回放")
    @GetMapping("/playbackVideo")
    public Result playbackVideo(PlaybackVideoVo playbackVideoVo) {
        try {
            PlaybackVo playbackVo = tDeviceInstallService.playbackVideo(playbackVideoVo);
            return Result.SUCCESS(playbackVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }
    @ApiOperation(value = "查询接口-不分页")
    @GetMapping("/tDeviceInstall")
    public Result tDeviceInstall(HttpServletRequest request,TDeviceInstall entity) {
        try {
            List<TDeviceInstall> list=tDeviceInstallService.getList(entity);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }


}
