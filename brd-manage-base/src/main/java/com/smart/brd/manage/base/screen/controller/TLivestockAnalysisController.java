package com.smart.brd.manage.base.screen.controller;

import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.brd.manage.base.entity.vo.AnalysisVo;
import com.smart.brd.manage.base.screen.entity.ScreenLineVo;
import com.smart.brd.manage.base.screen.service.ITLivestockAnalysisService;
import com.smart.common.core.domain.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "家畜统计分析")
@RequestMapping("/api/v1/brd/tlivestockAnalysis")
public class TLivestockAnalysisController {
    @Resource
    private ITLivestockAnalysisService tlivestockAnalysis;

    @ApiOperation(value = "家畜存栏按类别统计")
    @GetMapping("/inNumBySuitable")
    public Result inNumBySuitable(TLivestockAnalysis tla) {
        List<AnalysisVo> list = tlivestockAnalysis.inNumBySuitable(tla);
        return Result.SUCCESS(list);
    }
    @ApiOperation(value = "家畜存栏按状态统计")
    @GetMapping("/inNumByStatus")
    public Result inNumByStatus(TLivestockAnalysis tla) {
        List<AnalysisVo> list = tlivestockAnalysis.inNumByStatus(tla);
        return Result.SUCCESS(list);
    }
    @ApiOperation(value = "家畜存栏按月统计")
    @GetMapping("/inNumByMonth")
    public Result inNumByMonth(TLivestockAnalysis tla) {
        ScreenLineVo list= tlivestockAnalysis.inNumByMonth2(tla);
        return Result.SUCCESS(list);
    }
    @ApiOperation(value = "家畜存栏按年统计")
    @GetMapping("/inNumByYear")
    public Result inNumByYear(TLivestockAnalysis tla) {
        ScreenLineVo list = tlivestockAnalysis.inNumByYear2(tla);
        return Result.SUCCESS(list);
    }

    @ApiOperation(value = "家畜死亡按月统计")
    @GetMapping("/deadNumByMonth")
    public Result deadNumByMonth(TLivestockAnalysis tla) {
        ScreenLineVo list = tlivestockAnalysis.deadNumByMonth2(tla);
        return Result.SUCCESS(list);
    }

    @ApiOperation(value = "家畜死亡按年统计")
    @GetMapping("/deadNumByYear")
    public Result deadNumByYear(TLivestockAnalysis tla) {
        ScreenLineVo list = tlivestockAnalysis.deadNumByYear2(tla);
        return Result.SUCCESS(list);

    }
    @ApiOperation(value = "家畜死亡按类别统计")
    @GetMapping("/deadNumBySuitable")
    public Result deadNumBySuitable(TLivestockAnalysis tla) {
        List<AnalysisVo> list = tlivestockAnalysis.deadNumBySuitable(tla);
        return Result.SUCCESS(list);
    }
    @ApiOperation(value = "家畜死亡按死亡原因统计")
    @GetMapping("/deadNumByDeadInfo")
    public Result deadNumByDeadInfo(TLivestockAnalysis tla) {
        List<AnalysisVo> list = tlivestockAnalysis.deadNumByDeadInfo(tla);
        return Result.SUCCESS(list);
    }
    @ApiOperation(value = "当前月家畜出栏按类别统计")
    @GetMapping("/outNumBySuitable")
    public Result outNumBySuitable(TLivestockAnalysis tla) {
        List<AnalysisVo> list = tlivestockAnalysis.outNumBySuitable(tla);
        return Result.SUCCESS(list);
    }

    @ApiOperation(value = "家畜出栏趋势图(按月查询)")
    @GetMapping("/outNumAnalysisByMouth")
    public Result outNumAnalysisByMouth(TLivestockAnalysis tla) {
        ScreenLineVo list = tlivestockAnalysis.outNumAnalysis2(tla);
        return Result.SUCCESS(list);
    }

    @ApiOperation(value = "家畜出栏趋势图(按年查询)")
    @GetMapping("/outNumAnalysisByYear")
    public Result outNumAnalysisByYear(TLivestockAnalysis tla) {
        ScreenLineVo list = tlivestockAnalysis.outNumAnalysisByYear2(tla);
        return Result.SUCCESS(list);
    }

    @ApiOperation(value = "家畜淘汰按类型分类分析")
    @GetMapping("/eliminateBySuitable")
    public Result eliminateBySuitable(TLivestockAnalysis tla) {
        List<AnalysisVo> list = tlivestockAnalysis.eliminateBySuitable(tla);
        return Result.SUCCESS(list);
    }
    @ApiOperation(value = "大屏近期月份出栏数量图")
    @GetMapping("/outNumByMouth")
    public Result outNumByMouth(TLivestockAnalysis tla) {
        ScreenLineVo list = tlivestockAnalysis.outNumByMouth(tla);
        return Result.SUCCESS(list);
    }
    @ApiOperation(value = "大屏近期月份存栏数量图")
    @GetMapping("/ByMonthInName")
    public Result byMonthInName(TLivestockAnalysis tla) {
        ScreenLineVo list = tlivestockAnalysis.byMonthInName(tla);
        return Result.SUCCESS(list);
    }
}
