package com.yulin.WheelViewDemo.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/**
 * Created by YuLin on 2017/1/18 0018.
 */
public class DateTimeUtils {
    @SuppressLint({"UseSparseArrays"})
    private static final Map<Integer, String> WEEK_CHINESE_NAME_MAP = new HashMap(7);
    private static final long MILLIS_IN_SEC = 1000L;
    private static final long MILLIS_IN_MIN = 60000L;
    private static final long MILLIS_IN_HOUR = 3600000L;
    private static final long MILLIS_IN_DAY = 86400000L;
    private static String INVALID_DATE_ERR = "日期为空，或者不是有效的日期";
    private static String INVALID_CAL_ERR = "日历为空";

    public DateTimeUtils() {
    }

    public static DateFormat getDateFormat(int dateStyle, Locale locale) {
        return DateFormat.getDateInstance(dateStyle, locale);
    }

    public static DateFormat getDateFormat(int dateStyle) {
        return DateFormat.getDateInstance(dateStyle, Locale.getDefault());
    }

    public static DateFormat getDateFormat(Locale locale) {
        return DateFormat.getDateInstance(2, locale);
    }

    public static DateFormat getDateFormat() {
        return DateFormat.getDateInstance(2, Locale.getDefault());
    }

    public static DateFormat getDateFormatByDefult() {
        return getDateTimeFormatByCustom("yyyy-MM-dd");
    }

    public static DateFormat getTimeFormat(int timeStyle, Locale locale) {
        return DateFormat.getTimeInstance(timeStyle, locale);
    }

    public static DateFormat getTimeFormat(int timeStyle) {
        return DateFormat.getTimeInstance(timeStyle, Locale.getDefault());
    }

    public static DateFormat getTimeFormat(Locale locale) {
        return DateFormat.getTimeInstance(2, locale);
    }

    public static DateFormat getTimeFormat() {
        return DateFormat.getTimeInstance(2, Locale.getDefault());
    }

    public static DateFormat getTimeFormatByDefult() {
        return getDateTimeFormatByCustom("HH:mm:ss");
    }

    public static DateFormat getTimeFormatByDefult24Hour() {
        return getDateTimeFormatByCustom("HH:mm:ss");
    }

    public static DateFormat getDateTimeFormat(int dateStyle, int timeStyle, Locale locale) {
        return DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
    }

    public static DateFormat getDateTimeFormat(int dateStyle, int timeStyle) {
        return DateFormat.getDateTimeInstance(dateStyle, timeStyle, Locale.getDefault());
    }

    public static DateFormat getDateTimeFormat(Locale locale) {
        return DateFormat.getDateTimeInstance(2, 2, locale);
    }

    public static DateFormat getDateTimeFormat() {
        return DateFormat.getDateTimeInstance(2, 2, Locale.getDefault());
    }

    public static DateFormat getDateTimeFormatByCustom(String customFormat) {
        return new SimpleDateFormat(customFormat, Locale.getDefault());
    }

    public static DateFormat getDateTimeFormatByDefult() {
        return getDateTimeFormatByCustom("yyyy-MM-dd HH:mm:ss");
    }

    public static DateFormat getDateTimeFormatByDefult24Hour() {
        return getDateTimeFormatByCustom("yyyy-MM-dd HH:mm:ss");
    }

    public static DateFormat getYearFormat() {
        return getDateTimeFormatByCustom("yyyy");
    }

    public static DateFormat getMonthFormat() {
        return getDateTimeFormatByCustom("MM");
    }

    public static DateFormat getDayFormat() {
        return getDateTimeFormatByCustom("dd");
    }

    public static DateFormat getWeekFormat() {
        return getDateTimeFormatByCustom("E");
    }

    public static DateFormat getHourFormat() {
        return getDateTimeFormatByCustom("hh");
    }

    public static DateFormat getHourFormatBy24() {
        return getDateTimeFormatByCustom("HH");
    }

    public static DateFormat getMinuteFormat() {
        return getDateTimeFormatByCustom("mm");
    }

    public static DateFormat getSecondFormat() {
        return getDateTimeFormatByCustom("ss");
    }

    public static String getCurrentDateTimeByFormat(DateFormat fromat) {
        return fromat.format(new Date());
    }

    public static String getCurrentDateTimeByFormat(String customFormat) {
        return getCurrentDateTimeByFormat(getDateTimeFormatByCustom(customFormat));
    }

    public static String getCurrentDateTimeByDefultFormat() {
        return getCurrentDateTimeByFormat(getDateTimeFormatByDefult());
    }

    public static String getCurrentDateTimeByDefult24HourFormat() {
        return getCurrentDateTimeByFormat(getDateTimeFormatByDefult24Hour());
    }

    public static String getCurrentDateTime(int dateStyle, int timeStyle, Locale locale) {
        return getCurrentDateTimeByFormat(getDateTimeFormat(dateStyle, timeStyle, locale));
    }

    public static String getCurrentDateTime(int dateStyle, int timeStyle) {
        return getCurrentDateTimeByFormat(getDateTimeFormat(dateStyle, timeStyle));
    }

    public static String getCurrentDateTime(Locale locale) {
        return getCurrentDateTimeByFormat(getDateTimeFormat(locale));
    }

    public static String getCurrentDateTime() {
        return getCurrentDateTimeByFormat(getDateTimeFormat());
    }

    public static String getCurrentDate(int dateStyle, Locale locale) {
        return getCurrentDateTimeByFormat(getDateFormat(dateStyle, locale));
    }

    public static String getCurrentDate(int dateStyle) {
        return getCurrentDateTimeByFormat(getDateFormat(dateStyle));
    }

    public static String getCurrentDate(Locale locale) {
        return getCurrentDateTimeByFormat(getDateFormat(locale));
    }

    public static String getCurrentDate() {
        return getCurrentDateTimeByFormat(getDateFormat());
    }

    public static String getCurrentDateByDefultFormat() {
        return getCurrentDateTimeByFormat(getDateFormatByDefult());
    }

    public static String getCurrentTime(int timeStyle, Locale locale) {
        return getCurrentDateTimeByFormat(getTimeFormat(timeStyle, locale));
    }

    public static String getCurrentTime(int timeStyle) {
        return getCurrentDateTimeByFormat(getTimeFormat(timeStyle));
    }

    public static String getCurrentTime(Locale locale) {
        return getCurrentDateTimeByFormat(getTimeFormat(locale));
    }

    public static String getCurrentTime() {
        return getCurrentDateTimeByFormat(getTimeFormat());
    }

    public static String getCurrentTimeByDefultFormat() {
        return getCurrentDateTimeByFormat(getTimeFormatByDefult());
    }

    public static String getCurrentTimeByDefult24HourFormat() {
        return getCurrentDateTimeByFormat(getTimeFormatByDefult24Hour());
    }

    public static int getYear(Date date) {
        return Integer.valueOf(getYearFormat().format(date)).intValue();
    }

    public static int getYear(long timeMillis) {
        return getYear(new Date(timeMillis));
    }

    public static int getCurrentYear() {
        return getYear(System.currentTimeMillis());
    }

    public static int getMonth(Date date) {
        return Integer.valueOf(getMonthFormat().format(date)).intValue();
    }

    public static int getMonth(long timeMillis) {
        return getMonth(new Date(timeMillis));
    }

    public static int getCurrentMonth() {
        return getMonth(System.currentTimeMillis());
    }

    public static int getDay(Date date) {
        return Integer.valueOf(getDayFormat().format(date)).intValue();
    }

    public static int getDay(long timeMillis) {
        return getDay(new Date(timeMillis));
    }

    public static int getCurrentDay() {
        return getDay(System.currentTimeMillis());
    }

    public static int getHour(Date date) {
        return Integer.valueOf(getHourFormat().format(date)).intValue();
    }

    public static int getHour(long timeMIllis) {
        return getHour(new Date(timeMIllis));
    }

    public static int getCurrentHour() {
        return getHour(System.currentTimeMillis());
    }

    public static int getHourBy24(Date date) {
        return Integer.valueOf(getHourFormatBy24().format(date)).intValue();
    }

    public static int getHourBy24(long timeMillis) {
        return getHourBy24(new Date(timeMillis));
    }

    public static int getCurrentHourBy24() {
        return getHourBy24(System.currentTimeMillis());
    }

    public static int getMinute(Date date) {
        return Integer.valueOf(getMinuteFormat().format(date)).intValue();
    }

    public static int getMinute(long timeMillis) {
        return getMinute(new Date(timeMillis));
    }

    public static int getCurrentMinute() {
        return getMinute(System.currentTimeMillis());
    }

    public static int getSecond(Date date) {
        return Integer.valueOf(getSecondFormat().format(date)).intValue();
    }

    public static int getSecond(long timeMillis) {
        return getSecond(new Date(timeMillis));
    }

    public static int getCurrentSecond() {
        return getSecond(System.currentTimeMillis());
    }

    public static String converDate(String strDate) {
        StringBuffer sb = new StringBuffer();
        char[] chars = strDate.toCharArray();

        for(int w = 0; w < strDate.length(); ++w) {
            sb.append(chars[w]);
            if(w + 1 == 4) {
                sb.append('年');
                ++w;
            } else if(w + 1 == 7) {
                sb.append('月');
                ++w;
            } else if(w + 1 == 10) {
                sb.append('日');
                ++w;
            }
        }

        return sb.toString();
    }

    public static String converTime(String time) {
        StringBuffer sb = new StringBuffer();
        char[] chars = time.toCharArray();

        for(int w = 0; w < time.length(); ++w) {
            sb.append(chars[w]);
            if(w + 1 == 2) {
                sb.append('时');
                ++w;
            } else if(w + 1 == 5) {
                sb.append('分');
                ++w;
            } else if(w + 1 == 8) {
                sb.append('秒');
                ++w;
            }
        }

        return sb.toString();
    }

    public static String[] getBygoneYear(int number) {
        String[] years = new String[number];
        int currentYear = Integer.valueOf(getCurrentYear()).intValue();

        for(int w = 0; w < number; ++w) {
            years[w] = String.valueOf(currentYear - w);
        }

        return years;
    }



    public static int hourAddBy24Hour(int hour, int addNumber) {
        return hour + addNumber % 24;
    }

    public static int hourAddBy12Hour(int hour, int addNumber) {
        return hour + addNumber % 12;
    }

    public static int[] getCurrentTimes() {
        Date date = new Date(System.currentTimeMillis());
        return new int[]{Integer.valueOf(getYearFormat().format(date)).intValue(), Integer.valueOf(getMonthFormat().format(date)).intValue(), Integer.valueOf(getDayFormat().format(date)).intValue(), Integer.valueOf(getHourFormat().format(date)).intValue(), Integer.valueOf(getMinuteFormat().format(date)).intValue()};
    }

    public static int[] getCurrentTimesBy24Hour() {
        Date date = new Date(System.currentTimeMillis());
        return new int[]{Integer.valueOf(getYearFormat().format(date)).intValue(), Integer.valueOf(getMonthFormat().format(date)).intValue(), Integer.valueOf(getDayFormat().format(date)).intValue(), Integer.valueOf(getHourFormatBy24().format(date)).intValue(), Integer.valueOf(getMinuteFormat().format(date)).intValue()};
    }

    public static String getWeekChineseName(int dayOfWeek) {
        if(WEEK_CHINESE_NAME_MAP.size() != 7) {
            WEEK_CHINESE_NAME_MAP.put(Integer.valueOf(1), "日");
            WEEK_CHINESE_NAME_MAP.put(Integer.valueOf(2), "一");
            WEEK_CHINESE_NAME_MAP.put(Integer.valueOf(3), "二");
            WEEK_CHINESE_NAME_MAP.put(Integer.valueOf(4), "三");
            WEEK_CHINESE_NAME_MAP.put(Integer.valueOf(5), "四");
            WEEK_CHINESE_NAME_MAP.put(Integer.valueOf(6), "五");
            WEEK_CHINESE_NAME_MAP.put(Integer.valueOf(7), "六");
        }

        return (String)WEEK_CHINESE_NAME_MAP.get(Integer.valueOf(dayOfWeek % 8));
    }



    public static String formatDate(Date date, String format) {
        String dateStr = "";
        if(date == null) {
            return "";
        } else {
            try {
                dateStr = getDateTimeFormatByCustom(format).format(date);
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            return dateStr;
        }
    }

    public static Date formatDate(String date, String format) {
        if(date != null && !date.equals("")) {
            if(format != null && !format.equals("")) {
                try {
                    return getDateTimeFormatByCustom(format).parse(date);
                } catch (Exception var3) {
                    var3.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Date getDate(int y, int m, int d, int hour, int min, int second) {
        return getCalendar(y, m, d, hour, min, second).getTime();
    }

    public static Calendar getCalendar(int y, int m, int d, int hour, int min, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(y, m - 1, d, hour, min, second);
        return cal;
    }

    public static String getSimpleTimeDesc(Date date) {
        if(date == null) {
            return null;
        } else {
            long second = (System.currentTimeMillis() - date.getTime()) / 1000L;
            return second < 60L?"几秒之前":(second < 3600L?"" + second / 60L + "分钟之前":(second < 86400L?"" + second / 60L / 60L + "小时之前":(second < 2592000L?"" + second / 60L / 60L / 24L + "天之前":(second < 31536000L?"" + second / 60L / 60L / 24L / 30L + "个月之前":"" + second / 60L / 60L / 24L / 365L + "年之前"))));
        }
    }

    public static Date getDateTime(String dateString) {
        return getDateTime(dateString, "yyyy-MM-dd HH:mm");
    }

    public static Date getDateTime(String dateString, String format) {
        Date date = null;
        if(dateString != null && !"".equals(dateString)) {
            try {
                date = (new SimpleDateFormat(format)).parse(dateString);
            } catch (Exception var6) {
                try {
                    date = (new SimpleDateFormat("yyyy-MM-dd")).parse(dateString);
                } catch (Exception var5) {
                    var6.printStackTrace();
                }
            }

            return date;
        } else {
            return date;
        }
    }

    public static String getGreetings() {
        int hour = getCurrentHourBy24();
        return hour < 7?"凌晨好":(hour < 12?"上午好":(hour < 14?"中午好":(hour < 17?"下午好":(hour < 19?"傍晚好":(hour < 22?"晚上好":"夜深了")))));
    }

    public static Calendar getFormatCalendar(Calendar cal, int tempMin) {
        int minute = getMinute(cal.getTime());
        int addMinute = (int)Math.ceil((double)((float)minute / ((float)tempMin * 1.0F))) * tempMin;
        cal.set(12, addMinute);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal;
    }

    public static Calendar getFormatCalendar(int tempMin) {
        Calendar cal = Calendar.getInstance();
        return getFormatCalendar(cal, tempMin);
    }

    public static enum TIME_UNIT {
        MILLIS,
        SECS,
        MINS,
        HOURS,
        DAYS;

        private TIME_UNIT() {
        }
    }
}
