package com.smart.brd.manage.base.screen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.brd.manage.base.entity.vo.AnalysisVo;
import com.smart.brd.manage.base.entity.vo.ShedOutAnalysisVo;
import com.smart.brd.manage.base.entity.vo.ShedOutAnalysisYearVo;
import com.smart.brd.manage.base.screen.entity.ScreenLineVo;

import java.util.List;

public interface ITLivestockAnalysisService extends IService<TLivestockAnalysis> {
    //家畜死亡趋势图(按月查询)
    List<AnalysisVo> deadNumByMonth(TLivestockAnalysis tla);
    //家畜死亡趋势图(按年查询)
    List<AnalysisVo> deadNumByYear(TLivestockAnalysis tla);
    //当前月家畜死亡按类别统计
    List<AnalysisVo> deadNumBySuitable(TLivestockAnalysis tla);
    //当前月家畜死亡按死亡原因统计
    List<AnalysisVo> deadNumByDeadInfo(TLivestockAnalysis tla);

    //当前月家畜出栏按类别统计
    public List<AnalysisVo> outNumBySuitable(TLivestockAnalysis tla);

    //家畜出栏趋势图(按月查询)
    public List<ShedOutAnalysisVo> outNumAnalysis(TLivestockAnalysis tla);

    //家畜出栏趋势图(按年查询)
    public List<ShedOutAnalysisYearVo> outNumAnalysisByYear(TLivestockAnalysis tla);
    //当前月家畜存栏按类别统计
    List<AnalysisVo> inNumBySuitable(TLivestockAnalysis tla);
    //当前月家畜存栏按状态统计(正常,异常)
    List<AnalysisVo> inNumByStatus(TLivestockAnalysis tla);
    //家畜存栏趋势图(按月查询)
    List<AnalysisVo> inNumByMonth(TLivestockAnalysis tla);
    //家畜存栏趋势图(按年查询)
    List<AnalysisVo> inNumByYear(TLivestockAnalysis tla);
    //家畜淘汰按类型分类分析
    List<AnalysisVo> eliminateBySuitable(TLivestockAnalysis tla);
    //大屏近期月份出栏数量图
    ScreenLineVo outNumByMouth(TLivestockAnalysis tla);
    //大屏近期月份存栏数量图
    ScreenLineVo byMonthInName(TLivestockAnalysis tla);

    ScreenLineVo inNumByMonth2(TLivestockAnalysis tla);

    ScreenLineVo inNumByYear2(TLivestockAnalysis tla);

    ScreenLineVo deadNumByMonth2(TLivestockAnalysis tla);

    ScreenLineVo deadNumByYear2(TLivestockAnalysis tla);

    ScreenLineVo outNumAnalysis2(TLivestockAnalysis tla);

    ScreenLineVo outNumAnalysisByYear2(TLivestockAnalysis tla);
}
