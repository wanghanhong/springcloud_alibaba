package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.entity.THeartDevice;
import com.smart.brd.manage.base.entity.vo.AnalysisVo;
import com.smart.brd.manage.base.mapper.THeartDeviceMapper;
import com.smart.brd.manage.base.screen.entity.ScreenLine;
import com.smart.brd.manage.base.screen.entity.ScreenLineVo;
import com.smart.brd.manage.base.service.ITHeartDeviceService;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 */
@Service
public class THeartDeviceServiceImpl extends ServiceImpl<THeartDeviceMapper, THeartDevice> implements ITHeartDeviceService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private THeartDeviceMapper tHeartDeviceMapper;

    @Override
    public IPage<THeartDevice> tHeartDeviceList(PageDomain page, THeartDevice vo) {
        Page<THeartDevice> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<THeartDevice> iPage = tHeartDeviceMapper.tHeartDeviceList(pg,vo);
        return iPage;
    }

    @Override
    public THeartDevice tHeartDeviceAdd(THeartDevice entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tHeartDeviceUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tHeartDeviceDel(Long id) {
        int res = tHeartDeviceMapper.deleteById(id);
        return res;
    }

    @Override
    public THeartDevice tHeartDeviceUpdate(THeartDevice entity) {
        tHeartDeviceMapper.updateById(entity);
        return entity;
    }

    @Override
    public THeartDevice tHeartDeviceDetail(THeartDevice entity) {
        THeartDevice detail = tHeartDeviceMapper.selectById(entity.getId());
        return detail;
    }

    @Override
    public ScreenLineVo temperatureCurve(String deviceCode) {
        ScreenLineVo vo = new ScreenLineVo();
        List<ScreenLine> screenLines = new ArrayList<>();
        ScreenLine screenLine = new ScreenLine();
        List<String> xAxisData = new ArrayList<>();
        List<Object> yAxisData = new ArrayList<>();
        List<AnalysisVo> list = tHeartDeviceMapper.temperatureCurve(deviceCode);
        if (list .size()>0){
             //查看当前点和下一个点的时间差 如果大于两小时存到newList 如果小于两小时再找下一个点
             //这个是新的数据
             ArrayList<AnalysisVo> newList = new ArrayList<AnalysisVo>();
            AnalysisVo analysisVo1 = list.get(0);
            LocalDateTime time3 = analysisVo1.getTime();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String format = df.format(time3);
            analysisVo1.setDictName(format);
            newList.add(analysisVo1);
             for (int i = 0; i < list.size(); i++) {
                 for(int j = i + 1; j < list.size(); j++){
                     LocalDateTime time = list.get(i).getTime();
                     LocalDateTime time1 = list.get(j).getTime();

                     //比较两个时间 如果<2小时 找下一个(continue) 如果>两小时 存到newList i=j 并退出当前循环
                     Duration between = Duration.between(time, time1);
                     long sMin = between.toHours();
                     if(sMin < 2){
                         continue;
                     }else {
                         AnalysisVo analysisVo = list.get(j);
                         LocalDateTime time2 = analysisVo.getTime();
                         String format1 = df.format(time2);
                         analysisVo.setDictName(format1);
                         newList.add(analysisVo);
                         i = j - 1;
                         break;
                     }
                 }
             }
             newList.forEach(in ->{
                 xAxisData.add(in.getDictName().toString());

                 yAxisData.add(in.getPercent());
             });
        }
        screenLine.setName("耳标温度曲线统计");
        screenLine.setXAxisData(xAxisData);
        screenLine.setYAxisData(yAxisData);
        screenLines.add(screenLine);
        vo.setList(screenLines);
        return vo;
    }


    /**------通用方法开始结束-----------------------------------------*/

}
