package com.smart.brd.manage.base.screen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.brd.manage.base.entity.vo.AnalysisVo;
import com.smart.brd.manage.base.entity.vo.ShedOutAnalysisVo;
import com.smart.brd.manage.base.entity.vo.ShedOutAnalysisYearVo;
import java.util.List;


public interface TLivestockAnalysisMapper extends BaseMapper<TLivestockAnalysis> {
    List<AnalysisVo> deadNumBySuitable(TLivestockAnalysis tla);

    List<AnalysisVo> deadNumByDeadInfo(TLivestockAnalysis tla);

    public List<AnalysisVo> getOutTotal(TLivestockAnalysis tla);

    public List<ShedOutAnalysisVo> getOutNumAnalysis(TLivestockAnalysis tla);

    public List<ShedOutAnalysisYearVo> getOutNumAnalysisByYear(TLivestockAnalysis tla);

    List<AnalysisVo> deadNumByYear(TLivestockAnalysis tla);

    List<AnalysisVo> deadNumByMonth(TLivestockAnalysis tla);

    public List<AnalysisVo> inNumByStatus(TLivestockAnalysis tla);

    public List<AnalysisVo> inNumByYear(TLivestockAnalysis tla);

    List<AnalysisVo> inNumBySuitable(TLivestockAnalysis tla);
    // 家畜存栏按月统计
    List<AnalysisVo> inNumByMonth(TLivestockAnalysis tla);

    Integer entryNumTotal(TLivestockAnalysis tla);

    List<AnalysisVo> eliminateBySuitable(TLivestockAnalysis tla);
}
