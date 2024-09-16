package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.entity.vo.LivestockInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TDiseaseTreat;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITDiseaseTreatService;
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
@Api(tags = "畜病治疗")
@RequestMapping("/api/v1/brd/tdiseasetreat")
public class TDiseaseTreatController {

    /**
     * ------基本方法开始-----------------------------------------
     */
    @Resource
    private ITDiseaseTreatService tDiseaseTreatService;

    @ApiOperation(value = "查询治疗列表")
    @GetMapping("/tDiseaseTreatList")
    @RequiresPermissions("diseaseTreatPig:view")
    public Result tDiseaseTreatList(HttpServletRequest request, PageDomain page, TDiseaseTreat entity) {
        try {
            IPage<TDiseaseTreat> iPage = tDiseaseTreatService.tDiseaseTreatList(page, entity);
            return Result.SUCCESS(new PageResult(iPage));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "新增治疗信息")
    @PostMapping("/tDiseaseTreatAdd")
    public Result tDiseaseTreatAdd(@RequestBody TDiseaseTreat entity) {
        try {
            tDiseaseTreatService.tDiseaseTreatAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "添加治疗记录")
    @PostMapping("/addTreatRecord")
    public Result addTreatRecord(@RequestBody TDiseaseTreat entity) {
        try {
            tDiseaseTreatService.addTreatRecord(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tDiseaseTreatDel")
    public Result tDiseaseTreatDel(Long id) {
        try {
            tDiseaseTreatService.tDiseaseTreatDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "已康复")
    @PostMapping("/tDiseaseTreatUpdate")
    public Result tDiseaseTreatUpdate(@RequestBody TDiseaseTreat entity) {
        try {
            tDiseaseTreatService.tDiseaseTreatUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "死亡")
    @PostMapping("/death")
    public Result updateDeath(@RequestBody TDiseaseTreat entity) {
        try {
            tDiseaseTreatService.updateDeath(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

//    @ApiOperation(value = "查询详情")
//    @GetMapping("/tDiseaseTreatDetail")
//    public Result tDiseaseTreatDetail(HttpServletRequest request, TDiseaseTreat entity) {
//        try {
//            TDiseaseTreat vo = tDiseaseTreatService.tDiseaseTreatDetail(entity);
//            return Result.SUCCESS(vo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.FAIL);
//        }
//    }
    @ApiOperation(value = "治疗详情")
    @GetMapping("/diseaseTreatInfo")
    public Result diseaseTreatInfo(Long id) {
        try {
            TDiseaseTreat vo = tDiseaseTreatService.diseaseTreatInfo(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "根据耳标编号查询家畜信息")
    @GetMapping("/liveStockInfo")
    public Result liveStockInfo(String deviceCode) {
        try {
            LivestockInfo livestockInfo = tDiseaseTreatService.liveStockInfo(deviceCode);
            return Result.SUCCESS(livestockInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
