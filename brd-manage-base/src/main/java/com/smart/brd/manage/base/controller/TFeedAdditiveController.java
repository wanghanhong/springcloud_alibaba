package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TFeedAdditive;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITFeedAdditiveService;
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
@Api(tags = "饲料添加剂")
@RequestMapping("/api/v1/brd/tfeedadditive")
public class TFeedAdditiveController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITFeedAdditiveService tFeedAdditiveService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tFeedAdditiveList")
    public Result tFeedAdditiveList(HttpServletRequest request,PageDomain page,TFeedAdditive entity) {
        try {
            IPage<TFeedAdditive> iPage = tFeedAdditiveService.tFeedAdditiveList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tFeedAdditiveAdd")
    public Result tFeedAdditiveAdd(@RequestBody TFeedAdditive entity) {
        try {
            tFeedAdditiveService.tFeedAdditiveAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tFeedAdditiveDel")
    public Result tFeedAdditiveDel(Long id) {
        try {
            tFeedAdditiveService.tFeedAdditiveDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tFeedAdditiveUpdate")
    public Result tFeedAdditiveUpdate(@RequestBody TFeedAdditive entity) {
        try {
            tFeedAdditiveService.tFeedAdditiveUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tFeedAdditiveDetail")
    public Result tFeedAdditiveDetail(HttpServletRequest request,TFeedAdditive entity) {
        try {
            TFeedAdditive vo = tFeedAdditiveService.tFeedAdditiveDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
