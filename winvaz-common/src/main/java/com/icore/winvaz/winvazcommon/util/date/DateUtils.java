package com.icore.winvaz.winvazcommon.util.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

/**
 * 日期时间工具类
 * @Author wdq
 * @Create 2021/6/28 18:08
 * @Version 1.0.0
 */
public class DateUtils {

    /**
     * 获取当前日期
     * @author wdq
     * @create 2021/6/28 21:24
     * @param
     * @Return java.time.LocalDate
     * @exception
     */
    public static LocalDate currentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前时间
     * @author wdq
     * @create 2021/6/28 21:23
     * @param
     * @Return java.time.LocalDateTime
     * @exception
     */
    public static LocalDateTime currentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取当天开始时间
     * @author wdq
     * @create 2021/6/28 21:20
     * @param
     * @Return java.time.LocalDateTime
     * @exception
     */
    public static LocalDateTime startTimeOfToday() {
        return LocalDateTime.of(currentLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取当天结束时间
     * @author wdq
     * @create 2021/6/28 21:25
     * @param
     * @Return java.time.LocalDateTime
     * @exception
     */
    public static LocalDateTime endTimeOfToday() {
        return LocalDateTime.of(currentLocalDate(), LocalTime.MAX);
    }

    /**
     * 获取本月的第一天
     * @author wdq
     * @create 2021/6/29 09:55
     * @param
     * @Return java.time.LocalDateTime
     * @exception
     */
    public static LocalDateTime startDayOfMonth() {
        return currentLocalDateTime().with(TemporalAdjusters.firstDayOfMonth());
    }
}
