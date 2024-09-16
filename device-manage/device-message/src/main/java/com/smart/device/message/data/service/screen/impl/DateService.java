package com.smart.device.message.data.service.screen.impl;

import com.smart.device.common.install.entity.vo.ScreenVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Slf4j
@Service
public class DateService{

    public ScreenVo setScreenVo(){
        // 查询最近一个月的数据
        ScreenVo vo = new ScreenVo();
        LocalDateTime firstday = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime lastDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX).with(TemporalAdjusters.lastDayOfMonth());
        vo.setStartTime(firstday);
        vo.setEndTime(lastDay);
        return vo;
    }

    // 查询最近12个月的每月开始于结束时间
    public List<ScreenVo> getMonth() {
        List<ScreenVo> list = new ArrayList<ScreenVo>();
        LocalDateTime date = LocalDateTime.now();
        for(int i=11;i>=0;i--){
            LocalDateTime dateTime =date.minusMonths(i);
            LocalDateTime firstday = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIN).with(TemporalAdjusters.firstDayOfMonth());
            LocalDateTime lastDay = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX).with(TemporalAdjusters.lastDayOfMonth());
            ScreenVo vo = new ScreenVo();
            vo.setStartTime(firstday);
            vo.setEndTime(lastDay);
            list.add(vo);
        }
        return list;
    }


}
