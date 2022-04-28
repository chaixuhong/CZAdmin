package com.cz.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author chaixuhong
 * @date 2019-08-12
 * 日期工具类
 */
public class DateUtil {

    public static final String YMD = "yyyyMMdd";
    public static final String YMD_YEAR = "yyyy";
    public static final String YMD_BREAK = "yyyy-MM-dd";
    public static final String YMDHMS = "yyyyMMddHHmmssS";
    public static final String YMDHMS_BREAK = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMS_BREAK_HALF = "yyyy-MM-dd HH:mm";


    public static String getCurrent() {
        return getCurrent("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentDate() {
        return getCurrent("yyyy-MM-dd");
    }

    public static String getLocalDate() {
        return getCurrent("yyyy年MM月dd日");
    }

    public static String getCurrent(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static Date parse(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getYesterday(String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }

    public static String getTomorrow(String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date time = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }

    public static String getDaysBefore(String format, int day) {
        if (day > 0) {
            day = -day;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        Date time = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }

    public static Date getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        return time;
    }

    public static Date dateAdd(Date dateStr) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateStr);
        cal.add(Calendar.DATE, 1);
        Date time = cal.getTime();
        return time;
    }

    public static Date dateSub(Date dateStr, int number) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateStr);
        cal.add(Calendar.DATE, number);
        Date time = cal.getTime();
        return time;
    }

    public static String format(long mills, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(mills));
    }

    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static long parse(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static Date parses(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getDateZeroMills(long mills) {

        return parse(format(mills, "yyyy-MM-dd"), "yyyy-MM-dd");
    }

    public static final int daysBetween(Date early, Date late) {

        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    public static final Long timeBetween(Date early, Date late) {
        Calendar calearly = Calendar.getInstance();
        Calendar callate = Calendar.getInstance();
        calearly.setTime(early);
        callate.setTime(late);
        Long times = callate.getTime().getTime() - calearly.getTime().getTime();
        return times;
    }

    public static final Long timeBetween(String early, String late) {
        Calendar calearly = Calendar.getInstance();
        Calendar callate = Calendar.getInstance();
        calearly.setTime(parses(early,"yyyy-MM-dd HH:mm:ss"));
        callate.setTime(parses(late,"yyyy-MM-dd HH:mm:ss"));
        Long times = callate.getTime().getTime() - calearly.getTime().getTime();
        return times;
    }

    public static String getStartTime(String dateStr) {
        return dateStr + " 00:00:00";
    }

    public static String getEndTime(String dateStr) {
        return dateStr + " 23:59:59";
    }

    public static int getMouth(Date dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String mouth = sdf.format(dateStr);
        return Integer.parseInt(mouth);
    }

    public static int getDay(Date dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String day = sdf.format(dateStr);
        return Integer.parseInt(day);
    }

    /**
     * 判断两个时间是否是同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }


}
