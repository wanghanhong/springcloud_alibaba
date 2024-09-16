package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TRegion;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITRegionService;
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
@Api(tags = "区域")
@RequestMapping("/api/v1/brd/tregion")
public class TRegionController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITRegionService tRegionService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tRegionList")
    public Result tRegionList(HttpServletRequest request,PageDomain page,TRegion entity) {
        try {
            IPage<TRegion> iPage = tRegionService.tRegionList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tRegionAdd")
    public Result tRegionAdd(@RequestBody TRegion entity) {
        try {
            tRegionService.tRegionAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tRegionDel")
    public Result tRegionDel(Long id) {
        try {
            tRegionService.tRegionDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tRegionUpdate")
    public Result tRegionUpdate(@RequestBody TRegion entity) {
        try {
            tRegionService.tRegionUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tRegionDetail")
    public Result tRegionDetail(HttpServletRequest request,TRegion entity) {
        try {
            TRegion vo = tRegionService.tRegionDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
