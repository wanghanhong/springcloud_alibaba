package com.smart.device.install;

import com.smart.device.common.install.entity.vo.ScreenVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {


    public static void main(String [] args){
       String month =  DateTimeFormatter.ofPattern("yyyy.MM").format(LocalDateTime.now());
        System.out.println("month:" + month);

        LocalDateTime firstday = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime lastDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX).with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("firstday:" + firstday);
        System.out.println("lastDay:" + lastDay);

//        List<ScreenVo> list = new ArrayList<ScreenVo>();
//        LocalDateTime date = LocalDateTime.now();
//        for(int i=11;i>=0;i--){
//            LocalDateTime dateTime =date.minusMonths(i);
//            LocalDateTime firstday = dateTime.with(TemporalAdjusters.firstDayOfMonth());
//            LocalDateTime lastDay = dateTime.with(TemporalAdjusters.lastDayOfMonth());
//            System.out.println("firstday:" + firstday);
//            System.out.println("lastDay:" + lastDay);
//            ScreenVo vo = new ScreenVo();
//            vo.setStartTime(firstday);
//            vo.setEndTime(lastDay);
//            list.add(vo);
//        }

        System.out.println(12);
    }


}
