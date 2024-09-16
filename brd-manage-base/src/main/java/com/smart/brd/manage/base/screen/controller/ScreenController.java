package com.smart.brd.manage.base.screen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.brd.manage.base.screen.entity.ScreenEntity;
import com.smart.brd.manage.base.screen.entity.ScreenLineVo;
import com.smart.brd.manage.base.screen.entity.ScreenVaccine;
import com.smart.brd.manage.base.screen.entity.ScreenVo;
import com.smart.brd.manage.base.screen.service.ScreenService;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author
 */
@RestController
@Api(tags = "全省大屏")
@RequestMapping("/api/v1/brd/analysis")
public class ScreenController {

    @Resource
    private ScreenService screenService;

    @ApiOperation(value = "1各市养殖场数量")
    @GetMapping("/brdFieldGroupByCity")
    public Result brdFieldGroupByCity(HttpServletRequest request, ScreenEntity entity) {
        try {
            ScreenVo vo = screenService.brdFieldGroupByCity(entity);
            return Result.SUCCESS(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "2各市存栏数量")
    @GetMapping("/livestockGroupByCity")
    public Result livestockGroupByCity(HttpServletRequest request, ScreenEntity entity) {
        try {
            ScreenVo vo= screenService.livestockGroupByCity(entity);
            return Result.SUCCESS(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "3全省存栏类别统计")
    @GetMapping("/livestockGroupBySuitable")
    public Result livestockGroupBySuitable(HttpServletRequest request, ScreenEntity entity) {
        try {
            ScreenVo vo= screenService.livestockGroupBySuitable(entity);
            return Result.SUCCESS(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "4全省最近12个月，每月存栏、出栏数量")
    @GetMapping("/livestockGroupByMonth")
    public Result livestockGroupByMonth(HttpServletRequest request, ScreenEntity entity) {
        try {
            ScreenLineVo vo = screenService.livestockGroupByMonth(entity);
            return Result.SUCCESS(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "5本省最近12个月价格图")
    @GetMapping("/priceMonth")
    public Result priceMonth(HttpServletRequest request, ScreenEntity entity) {
        try {
            ScreenLineVo vo= screenService.priceMonth(entity);
            return Result.SUCCESS(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "6疫情-列表")
    @GetMapping("/screenVaccineList")
    public Result screenVaccineList(HttpServletRequest request, PageDomain page,ScreenEntity entity) {
        try {
            IPage<ScreenVaccine> iPage = screenService.screenVaccineList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "7全省数据")
    @GetMapping("/provinceData")
    public Result provinceData(HttpServletRequest request,ScreenEntity entity) {
        try {
            ScreenVo vo= screenService.provinceData(entity);
            return Result.SUCCESS(vo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }


}
