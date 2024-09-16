package com.smart.brd.manage.base.screen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.brd.manage.base.entity.vo.AnalysisVo;
import com.smart.brd.manage.base.entity.vo.ShedOutAnalysisVo;
import com.smart.brd.manage.base.entity.vo.ShedOutAnalysisYearVo;
import com.smart.brd.manage.base.screen.entity.ScreenLine;
import com.smart.brd.manage.base.screen.entity.ScreenLineVo;
import com.smart.brd.manage.base.screen.mapper.TLivestockAnalysisMapper;
import com.smart.brd.manage.base.screen.service.ITLivestockAnalysisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TLivestockAnalysisServiceImpl extends ServiceImpl<TLivestockAnalysisMapper, TLivestockAnalysis> implements ITLivestockAnalysisService {
    @Resource
    private TLivestockAnalysisMapper tLivestockAnalysisMapper;

    @Override
    public List<AnalysisVo> deadNumByMonth(TLivestockAnalysis tla) {
        return tLivestockAnalysisMapper.deadNumByMonth(tla);
    }

    @Override
    public List<AnalysisVo> deadNumByYear(TLivestockAnalysis tla) {
        return tLivestockAnalysisMapper.deadNumByYear(tla);
    }

    @Override
    public List<AnalysisVo> deadNumBySuitable(TLivestockAnalysis tla) {
        return tLivestockAnalysisMapper.deadNumBySuitable(tla);
    }

    @Override
    public List<AnalysisVo> deadNumByDeadInfo(TLivestockAnalysis tla) {
        return tLivestockAnalysisMapper.deadNumByDeadInfo(tla);
    }

    @Override
    public List<AnalysisVo> outNumBySuitable(TLivestockAnalysis tla) {
        return tLivestockAnalysisMapper.getOutTotal(tla);
    }

    @Override
    public List<ShedOutAnalysisVo> outNumAnalysis(TLivestockAnalysis tla) {


        return tLivestockAnalysisMapper.getOutNumAnalysis(tla);
    }

    @Override
    public List<ShedOutAnalysisYearVo> outNumAnalysisByYear(TLivestockAnalysis tla) {
        return tLivestockAnalysisMapper.getOutNumAnalysisByYear(tla);
    }

    @Override
    public List<AnalysisVo> inNumBySuitable(TLivestockAnalysis tla) {
        List<AnalysisVo> analysisVos = tLivestockAnalysisMapper.inNumBySuitable(tla);
        Integer aaa=0;
        for (AnalysisVo analysisVo : analysisVos) {
            aaa=aaa+analysisVo.getDictNum();
        }
        for (AnalysisVo analysisVo : analysisVos) {
            if (aaa==0||analysisVo.getDictNum()==0){
                analysisVo.setPercent("0%");
            }else {
                DecimalFormat df = new DecimalFormat("0.00");
                analysisVo.setPercent(df.format((float)analysisVo.getDictNum()/(float)aaa*100)+"%");
            }
        }
        return analysisVos;
    }

    @Override
    public List<AnalysisVo> inNumByStatus(TLivestockAnalysis tla) {
        List<AnalysisVo> analysisVos = tLivestockAnalysisMapper.inNumByStatus(tla);
        //避免初始状态下，返回为空，页面为空
        if (analysisVos.size()<1){
            AnalysisVo analysisVoZ = new AnalysisVo();
            AnalysisVo analysisVoY = new AnalysisVo();
            analysisVoZ.setDictName("异常");
            analysisVoZ.setDictNum(0);
            analysisVos.add(analysisVoZ);
            analysisVoY.setDictName("正常");
            analysisVoY.setDictNum(0);
            analysisVos.add(analysisVoY);
            return analysisVos;
        }
        return analysisVos;
    }
    // 家畜存栏按月统计
    @Override
    public List<AnalysisVo> inNumByMonth(TLivestockAnalysis tla){
        List<AnalysisVo> list = tLivestockAnalysisMapper.inNumByMonth(tla);
        //修改最新一个月的数据为实时统计
        AnalysisVo analysisVo = list.get(list.size()-1);
        Integer num = tLivestockAnalysisMapper.entryNumTotal(tla);
        analysisVo.setDictNum(num);
        return list;
    }

    @Override
    public List<AnalysisVo> inNumByYear(TLivestockAnalysis tla) {
        List<AnalysisVo> list=tLivestockAnalysisMapper.inNumByYear(tla);
        //修改最新一年的数据为实时统计
        AnalysisVo analysisVo = list.get(list.size()-1);
        Integer num = tLivestockAnalysisMapper.entryNumTotal(tla);
        analysisVo.setDictNum(num);
        return list;
    }

    @Override
    public List<AnalysisVo> eliminateBySuitable(TLivestockAnalysis tla) {
        List<AnalysisVo> analysisVos = tLivestockAnalysisMapper.inNumByStatus(tla);
        return analysisVos;
    }

    @Override
    public ScreenLineVo outNumByMouth(TLivestockAnalysis tla) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> list = new ArrayList<>();
        ScreenLine screenLine = new ScreenLine();
        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();
        List<ShedOutAnalysisVo> outNumAnalysis = tLivestockAnalysisMapper.getOutNumAnalysis(tla);
        outNumAnalysis.forEach(out ->{
            xAxisData.add(out.getOutDate());
            yAxisData.add(out.countPig.toString());
        });
        screenLine.setName("出栏");
        screenLine.setXAxisData(xAxisData);
        screenLine.setYAxisData(yAxisData);
        list.add(screenLine);
        vo.setList(list);
        return vo;
    }

    @Override
    public ScreenLineVo byMonthInName(TLivestockAnalysis tla) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> screenLines = new ArrayList<>();
        ScreenLine screenLine = new ScreenLine();
        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();
        List<AnalysisVo> list = tLivestockAnalysisMapper.inNumByMonth(tla);
        //修改最新一个月的数据为实时统计
        AnalysisVo analysisVo = list.get(list.size()-1);
        Integer num = tLivestockAnalysisMapper.entryNumTotal(tla);
        analysisVo.setDictNum(num);
        list.forEach(in ->{
            xAxisData.add(in.getDictName().toString());
            yAxisData.add(in.getDictNum().toString());
        });
        screenLine.setName("存栏");
        screenLine.setXAxisData(xAxisData);
        screenLine.setYAxisData(yAxisData);
        screenLines.add(screenLine);
        vo.setList(screenLines);
        return vo;
    }

    @Override
    public ScreenLineVo inNumByMonth2(TLivestockAnalysis tla) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> screenLines = new ArrayList<>();
        ScreenLine screenLine = new ScreenLine();
        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();
        List<AnalysisVo> list = tLivestockAnalysisMapper.inNumByMonth(tla);
        AnalysisVo analysisVo = list.get(list.size()-1);
        Integer num = tLivestockAnalysisMapper.entryNumTotal(tla);
        analysisVo.setDictNum(num);
        list.forEach(in ->{
            xAxisData.add(in.getDictName().toString());
            yAxisData.add(in.getDictNum().toString());
        });
        screenLine.setName("家畜存栏按月统计");
        screenLine.setXAxisData(xAxisData);
        screenLine.setYAxisData(yAxisData);
        screenLines.add(screenLine);
        vo.setList(screenLines);
        return vo;
    }

    @Override
    public ScreenLineVo inNumByYear2(TLivestockAnalysis tla) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> screenLines = new ArrayList<>();
        ScreenLine screenLine = new ScreenLine();
        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();
        List<AnalysisVo> list=tLivestockAnalysisMapper.inNumByYear(tla);
        AnalysisVo analysisVo = list.get(list.size()-1);
        Integer num = tLivestockAnalysisMapper.entryNumTotal(tla);
        analysisVo.setDictNum(num);
        list.forEach(in ->{
            xAxisData.add(in.getDictName().toString());
            yAxisData.add(in.getDictNum().toString());
        });
        screenLine.setName("家畜存栏按年统计");
        screenLine.setXAxisData(xAxisData);
        screenLine.setYAxisData(yAxisData);
        screenLines.add(screenLine);
        vo.setList(screenLines);
        return vo;
    }

    @Override
    public ScreenLineVo deadNumByMonth2(TLivestockAnalysis tla) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> screenLines = new ArrayList<>();
        ScreenLine screenLine = new ScreenLine();
        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();
        List<AnalysisVo> list = tLivestockAnalysisMapper.deadNumByMonth(tla);
        list.forEach(in ->{
            xAxisData.add(in.getDictName().toString());
            yAxisData.add(in.getDictNum().toString());
        });
        screenLine.setName("家畜死亡按月统计");
        screenLine.setXAxisData(xAxisData);
        screenLine.setYAxisData(yAxisData);
        screenLines.add(screenLine);
        vo.setList(screenLines);
        return vo;
    }

    @Override
    public ScreenLineVo deadNumByYear2(TLivestockAnalysis tla) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> screenLines = new ArrayList<>();
        ScreenLine screenLine = new ScreenLine();
        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();
        List<AnalysisVo> list = tLivestockAnalysisMapper.deadNumByYear(tla);
        list.forEach(in ->{
            xAxisData.add(in.getDictName().toString());
            yAxisData.add(in.getDictNum().toString());
        });
        screenLine.setName("家畜死亡按年统计");
        screenLine.setXAxisData(xAxisData);
        screenLine.setYAxisData(yAxisData);
        screenLines.add(screenLine);
        vo.setList(screenLines);
        return vo;
    }

    @Override
    public ScreenLineVo outNumAnalysis2(TLivestockAnalysis tla) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> screenLines = new ArrayList<>();
        ScreenLine screenLine = new ScreenLine();
        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();
        List<ShedOutAnalysisVo> list = tLivestockAnalysisMapper.getOutNumAnalysis(tla);
        list.forEach(in ->{
            xAxisData.add(in.getOutDate().toString());
            yAxisData.add(in.getCountPig().toString());
        });
        screenLine.setName("家畜出栏趋势图(按月查询)");
        screenLine.setXAxisData(xAxisData);
        screenLine.setYAxisData(yAxisData);
        screenLines.add(screenLine);
        vo.setList(screenLines);
        return vo;
    }

    @Override
    public ScreenLineVo outNumAnalysisByYear2(TLivestockAnalysis tla) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> screenLines = new ArrayList<>();
        ScreenLine screenLine = new ScreenLine();
        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();
        List<ShedOutAnalysisYearVo> list = tLivestockAnalysisMapper.getOutNumAnalysisByYear(tla);
        list.forEach(in ->{
            xAxisData.add(in.getOutDate().toString());
            yAxisData.add(in.getCountPig().toString());
        });
        screenLine.setName("家畜出栏趋势图(按年查询)");
        screenLine.setXAxisData(xAxisData);
        screenLine.setYAxisData(yAxisData);
        screenLines.add(screenLine);
        vo.setList(screenLines);
        return vo;
    }
}
