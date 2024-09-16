package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TLivestockLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITLivestockLogService;
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
@Api(tags = "家畜日志")
@RequestMapping("/api/v1/brd/tlivestocklog")
public class TLivestockLogController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITLivestockLogService tLivestockLogService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tLivestockLogList")
    public Result tLivestockLogList(HttpServletRequest request,PageDomain page,TLivestockLog entity) {
        try {
            IPage<TLivestockLog> iPage = tLivestockLogService.tLivestockLogList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tLivestockLogAdd")
    public Result tLivestockLogAdd(@RequestBody TLivestockLog entity) {
        try {
            tLivestockLogService.tLivestockLogAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tLivestockLogDel")
    public Result tLivestockLogDel(Long id) {
        try {
            tLivestockLogService.tLivestockLogDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tLivestockLogUpdate")
    public Result tLivestockLogUpdate(@RequestBody TLivestockLog entity) {
        try {
            tLivestockLogService.tLivestockLogUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tLivestockLogDetail")
    public Result tLivestockLogDetail(HttpServletRequest request,TLivestockLog entity) {
        try {
            TLivestockLog vo = tLivestockLogService.tLivestockLogDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
