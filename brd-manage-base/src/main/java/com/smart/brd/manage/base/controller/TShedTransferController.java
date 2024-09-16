package com.smart.brd.manage.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.TLivestockShed;
import com.smart.brd.manage.base.entity.TShedTransfer;
import com.smart.brd.manage.base.entity.vo.ShedTransVo;
import com.smart.brd.manage.base.entity.vo.StockBedVo;
import com.smart.brd.manage.base.service.ITShedTransferService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 */
@RestController
@Api(tags = "转栏记录")
@RequestMapping("/api/v1/brd/tshedtransfer")
public class TShedTransferController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITShedTransferService tShedTransferService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tShedTransferList")
    public Result tShedTransferList(HttpServletRequest request,PageDomain page,TShedTransfer entity) {
        try {
            IPage<TShedTransfer> iPage = tShedTransferService.tShedTransferList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tShedTransferAdd")
    public Result tShedTransferAdd(@RequestBody ShedTransVo entity) {
        try {
            tShedTransferService.tShedTransferAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tShedTransferDel")
    public Result tShedTransferDel(Long id) {
        try {
            tShedTransferService.tShedTransferDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tShedTransferUpdate")
    public Result tShedTransferUpdate(@RequestBody ShedTransVo entity) {
        try {
            tShedTransferService.tShedTransferUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tShedTransferDetail")
    public Result tShedTransferDetail(HttpServletRequest request,TShedTransfer entity) {
        try {
            TShedTransfer vo = tShedTransferService.tShedTransferDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "出栏理由列表")
    @GetMapping("/tTransDict")
    public Result tTransDict(HttpServletRequest request) {
        try {
            List<TBaseDict> vo = tShedTransferService.getTrans();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "栏列表")
    @GetMapping("/tBedList")
    public Result tBedList(HttpServletRequest request,Long shedId) {
        try {
            List<StockBedVo> vo = tShedTransferService.getBeds(shedId);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "舍列表")
    @GetMapping("/tShedList")
    public Result tShedList(HttpServletRequest request) {
        try {
            List<TLivestockShed> vo = tShedTransferService.getSheds();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
}
