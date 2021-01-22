package com.example.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    //h大写是24小时制 , 小写是12小时制
    public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_YMD = "yyyy-MM-dd";

    public static final String FORMAT_HM = "HH:mm";

    public static final String FORMAT_MDHM = "MM-dd HH:mm";



    private DateUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 时间戳转时间字符串
     * @param mills
     * @param regex
     * @return
     */
    public static String format(long mills , String regex) {
        SimpleDateFormat format = new SimpleDateFormat(regex);
        return format.format(new Date(mills));
    }

    /**
     * 时间字符串转时间戳
     * @param date
     * @param regex
     * @return
     */
    public static Long parse(String date , String regex) {
        try {
            return new SimpleDateFormat(regex).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            return -1l;
        }
    }

    /**
     * 获取现在的时间字符串
     * @return
     */
    public static String getCurrentDateStr(String regex) {
        return new SimpleDateFormat(regex).format(new Date());
    }

    /**
     * 将一个时间戳转变成一个日历对象
     * @param mills
     * @return
     */
    public static Calendar getCalendar(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取一个时间戳的年属性
     * @param mills
     * @return
     */
    public static int getYear(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取一个时间戳的月属性
     * @param mills
     * @return
     */
    public static int getMonth(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取一个时间戳的日属性
     * @param mills
     * @return
     */
    public static int getDay(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取一个时间戳的时属性
     * @param mills
     * @return
     */
    public static int getHour(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取一个时间戳的分属性
     * @param mills
     * @return
     */
    public static int getMinute(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取一个时间戳的秒属性
     * @param mills
     * @return
     */
    public static int getSecond(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取一个时间戳的星期属性
     * @param mills
     * @return
     */
    public static int getWeek(long mills) {
        Date date = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    // 将时间间隔转换为可读信息
    public static final String getTime(long mills) {

        long diff = new Date().getTime() - mills;
        diff /= 1000;
        if (diff < 60) return "刚刚";
        if (diff < 3600) return diff / 60 + "分钟前";
        if (diff > 3600 && diff < 3600 * 24) return diff / 3600 + "小时前";
        if (diff < 3600 * 24 * 7 && diff > 3600 * 24) return diff / (3600 * 24) + "天前";
        return format(mills , FORMAT_YMDHMS);
    }

}
