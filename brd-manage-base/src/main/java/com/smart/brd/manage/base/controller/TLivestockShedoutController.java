package com.smart.brd.manage.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.TLivestockOut;
import com.smart.brd.manage.base.entity.vo.ShedOutAllVo;
import com.smart.brd.manage.base.entity.vo.ShedOutVo;
import com.smart.brd.manage.base.service.ITLivestockShedoutService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author junglelocal
 */
@RestController
@Api(tags = "家畜出栏")
@RequestMapping("/api/v1/brd/tlivestockout")
public class TLivestockShedoutController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITLivestockShedoutService itLivestockShedoutService;

    @ApiOperation(value = "详细查询接口")
    @GetMapping("/tLivestockShedoutList")
    public Result tLivestockShedoutList(HttpServletRequest request, PageDomain page, ShedOutVo entity) {
        try {
            IPage<ShedOutVo> iPage = itLivestockShedoutService.tLivestockShedoutList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "总体查询接口")
    @GetMapping("/tLivestockOutList")
    @RequiresPermissions("livestockOut:view")
    public Result tLivestockOutList(HttpServletRequest request, PageDomain page, ShedOutAllVo entity) {
        try {
            IPage<TLivestockOut> iPage = itLivestockShedoutService.tLivestockOutList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tLivestockShedoutAdd")
    public Result tLivestockShedoutAdd(@RequestBody ShedOutAllVo entity) {
        try {
            itLivestockShedoutService.tLivestockOutAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tLivestockShedoutDel")
    public Result tLivestockShedoutDel(Long bedId) {
        try {
            int i = itLivestockShedoutService.tLivestockOutDel(bedId);
            if (i > 0) {
                return Result.SUCCESS();
            }else {
                return Result.ERROR();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tLivestockShedoutDetail")
    public Result tLivestockShedoutDetail(HttpServletRequest request,@RequestParam Long shedoutId) {
        try {
            ShedOutVo barnsVo = itLivestockShedoutService.tLivestockOutDetail(shedoutId);
            return Result.SUCCESS(barnsVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/
}
