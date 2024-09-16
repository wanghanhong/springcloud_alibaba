package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TPool;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITPoolService;
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
@RequestMapping("/api/card/v1/tpool")
public class TPoolController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITPoolService tPoolService;

    @ApiOperation(value = "前向流量-查询接口")
    @GetMapping("/tPoolListForward")
    public Result tPoolListForward(HttpServletRequest request,PageDomain page,TPool entity) {
        try {
            entity.setType(0);
            IPage iPage = tPoolService.tPoolList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "后向流量查询接口")
    @GetMapping("/tPoolListBackward")
    public Result tPoolListBackward(HttpServletRequest request,PageDomain page,TPool entity) {
        try {
            entity.setType(1);
            IPage iPage = tPoolService.tPoolList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tPoolAdd")
    public Result tPoolAdd(@RequestBody TPool entity) {
        try {
            tPoolService.tPoolAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tPoolDel")
    public Result tPoolDel(Long id) {
        try {
            tPoolService.tPoolDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tPoolUpdate")
    public Result tPoolUpdate(@RequestBody TPool entity) {
        try {
            tPoolService.tPoolUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tPoolDetail")
    public Result tPoolDetail(HttpServletRequest request,TPool entity) {
        try {
            TPool vo = tPoolService.tPoolDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
