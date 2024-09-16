package com.smart.device.message.common.utils;

import com.smart.common.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 三多
 * @Time 2020/4/10
 */
public class DateUtils {
    private static final String DEVICE_TIME = "2018-09-10 00:00:00";
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 设备时间转换成系统时间
     * 设备时间单位：秒
     *
     * @param interval 时间间隔
     * @return 返回系统时间
     */
    public static String covertTimeDeviceToSystem(long interval) {
        String endTime = null;
        try {
            LocalDateTime dateTime = LocalDateTime.parse(DEVICE_TIME, DateTimeFormatter.ofPattern(TIME_PATTERN));
            long time = dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
            System.out.println(time);
            LocalDateTime systemTime = dateTime.plus(interval, ChronoUnit.SECONDS);
            endTime = systemTime.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(TIME_PATTERN));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return endTime;
    }

    public static Date covertTimeStringT(String time) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        try {
            date = df.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static LocalDateTime covertToLocalDateTime(String time) {
        LocalDateTime datetime = LocalDateTime.now();
        if(StringUtils.isNotBlank(time)){
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
                datetime = LocalDateTime.parse(time, fmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return datetime;
    }
    public static LocalDateTime covertToLocalDateTime(Date date) {
        LocalDateTime datetime = LocalDateTime.now();
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            datetime = instant.atZone(zoneId).toLocalDateTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return datetime;
    }
    public static Date covertGMT8TimeStringT(String time) {
        Date dt1 = new Date();
        try {
            if(StringUtils.isNotBlank(time)){
                Date date = covertTimeStringT(time);
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(date);
                rightNow.add(Calendar.HOUR_OF_DAY, 8);//时间加8小时
                dt1 = rightNow.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt1;
    }

    public static LocalDateTime covertTimeStringToLocalDateTime(String time) {
        LocalDateTime datetime = LocalDateTime.now();
        if(StringUtils.isNotBlank(time)){
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern(TIME_PATTERN);
                datetime = LocalDateTime.parse(time, fmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return datetime;
    }

    public static Date covertTimeStringToDate(String time) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(TIME_PATTERN);
        try {
            date = df.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date covertLongToDate(Long longtime) {
        Date date = new Date();
        try {
            date = new Date(longtime * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static LocalDateTime covertLongToLocalDateTime(Long longtime) {
        LocalDateTime longToLocalDateTime = LocalDateTime.now();
        try {
            longToLocalDateTime =
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(longtime * 1000), ZoneId.systemDefault());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return longToLocalDateTime;
    }
    public static LocalDateTime covertToLocalDateTime(Long longtime) {
        LocalDateTime longToLocalDateTime = LocalDateTime.now();
        try {
            longToLocalDateTime =
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(longtime), ZoneId.systemDefault());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return longToLocalDateTime;
    }

    public static void main(String[] args) {
        Long longtime = 1589870117L;

        String str = "20200627T194552Z";
        LocalDateTime da = DateUtils.covertToLocalDateTime(str);
        System.out.println(da);

        System.out.println(111);
    }


}
