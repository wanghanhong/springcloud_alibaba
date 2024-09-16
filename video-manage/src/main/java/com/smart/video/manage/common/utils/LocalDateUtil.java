package com.smart.video.manage.common.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

public class LocalDateUtil {

    public static Map<String,Object> calEscapeAge(LocalDate startDate, LocalDate endDate){
        String term = "";
        Map<String,Object> map = new HashMap<>();
        try {
            Period period = startDate.until(endDate);
            int years = period.getYears();
            int months = period.getMonths();
            int days = period.getDays();
            map.put("years",years);
            map.put("months",months);
            map.put("days",days);
            String age = (years == 0?"":years+"年")+(months == 0?"":+ months+"个月")+(days == 0?"":days+"天");
            map.put("age",age);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(859L);
        Map<String,Object> map = LocalDateUtil.calEscapeAge(startDate,endDate);

        System.out.println(12);

    }


}
