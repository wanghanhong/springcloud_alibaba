package com.smart.card.manage.feign;

import com.smart.card.CardManagerApplication;
import org.springframework.boot.SpringApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        String str = "2020-12"+"-01 00:00:01";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(str, dtf);
        LocalDateTime startDateTime = date.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDateTime = date.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);



        System.out.println("12");

        LocalDate localDate = LocalDate.now();

        //这个月的第一天
        Date monthStart = Date.from(localDate.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        //这个月的最后一天 下个月的第一天
        Date monthEnd = Date.from(localDate.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        //今年的第一天
        Date yearStart = Date.from(localDate.with(TemporalAdjusters.firstDayOfYear()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        //今年的最后一天 下一年的第一天
        Date yearEnd = Date.from(localDate.plusYears(1).with(TemporalAdjusters.firstDayOfYear()).atStartOfDay(ZoneId.systemDefault()).toInstant());


    }

}
