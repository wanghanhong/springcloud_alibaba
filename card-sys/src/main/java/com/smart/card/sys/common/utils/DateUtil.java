package com.smart.card.sys.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 时间工具类
 */
public class DateUtil {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    private static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(date);
    }

    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = sdf.parse(date);
        return DateUtil.getDateFormat(d, format);
    }

    public static LocalDateTime changeStringTimeTo(String time) {
        if (StringUtils.isBlank(time)) {
            return LocalDateTime.now();
        }
        try {
            if (time.contains("+")) {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(time);
                return zonedDateTime.toLocalDateTime();
            } else {
                return LocalDateTime.parse(time);
            }
        } catch (Exception e) {

            return LocalDateTime.now();
        }
    }

    private List<Map<String, Object>> initialDateList(int start) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        LocalDateTime day = LocalDateTime.now();
        Map<String, Object> map = new HashMap();
        for (int i = start; i < start + 24; i++) {
            day = LocalDateTime.of(LocalDate.now(),LocalTime.MIN).plusHours(i);
            String date = day.format(formatter);
            map = new HashMap();
            map.put("time", date);
            map.put("num", 0);
            list.add(map);
        }
        return list;
    }

    private List<Map<String, Object>>  initialDateList2(int start) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        LocalDateTime day = LocalDateTime.now();
        Map<String, Object> map = new HashMap();
        for (int i = start; i < start + 24; i++) {
            day = LocalDateTime.of(LocalDate.now(),LocalTime.MIN).plusHours(i);
            String date = day.format(formatter);
            map = new HashMap();
            map.put("time", date);
            map.put("num", 0);
            list.add(map);
        }
        return list;
    }

    public List<LocalDateTime> initialDateListNormol() {
        List<LocalDateTime> list = new ArrayList<LocalDateTime>();
        LocalDateTime day = LocalDateTime.now();
        for (int i = 7; i > 0; i--) {
            day = LocalDateTime.of(LocalDate.now().minusDays(i), LocalTime.MAX);
            list.add(day);
        }
        return list;
    }

    public List<Map<String, Object>> changeList(List<Map<String, Object>> list) {
        List<Map<String, Object>> res = initialDateList(0);
        res.forEach(e -> {
            String outDate = String.valueOf(e.get("time"));
            list.forEach(map -> {
                String inDate = String.valueOf(map.get("time"));
                if (outDate.equals(inDate)) {
                    e.put("num", map.get("num"));
                }
            });
        });
        return res;
    }


    public List<Map<String, Object>> changeList2(List<Map<String, Object>> list) {
        List<Map<String, Object>> res = initialDateList2(0);
        res.forEach(e -> {
            String outDate = String.valueOf(e.get("time"));
            list.forEach(map -> {
                String inDate = String.valueOf(map.get("time"));
                if (outDate.equals(inDate)) {
                    e.put("num", map.get("num"));
                }
            });
        });
        return res;
    }

    public List<Map<String, Object>> changeListNotNow(List<Map<String, Object>> list) {
        List<Map<String, Object>> res = initialDateList(1);
        res.forEach(e -> {
            String outDate = String.valueOf(e.get("time"));
            list.forEach(map -> {
                String inDate = String.valueOf(map.get("time"));
                if (outDate.equals(inDate)) {
                    e.put("num", map.get("num"));
                }
            });
        });
        return res;
    }

    public static void main(String[] args){
     /*   Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date time = calendar.getTime();
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(time);
        System.out.println(yesterday);*/
        DateUtil DateUtil = new DateUtil();
        DateUtil.initialDateList2(0);
    }
}
