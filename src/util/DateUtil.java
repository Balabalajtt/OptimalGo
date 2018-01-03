package util;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by 江婷婷 on 2017/12/30.
 */
public class DateUtil {
    private static SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");

    /**
     * @param date 日期
     * @return 年月日时分秒
     */
    public static String transfer(Date date) {
        return format1.format(date);
    }

    /**
     * @param time 毫秒数
     * @return 小时分钟
     */
    public static String transferDay(long time) {
        int seconds = (int) (time / 1000);
        String timeStr = "";
        long min = 0;
        long hour = 0;
        long day = 0;
        if (seconds > 60) {
            min = seconds / 60;
            if (min > 60) {
                min = (seconds / 60) % 60;
                hour = (seconds / 60) / 60;
                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    day = (((seconds / 60) / 60) / 24);
                }
            }
        }
        if (min != 0) {
            timeStr = min + "分钟";
        }
        if (hour != 0) {
            timeStr = hour + "小时" + timeStr;
        }
        if (day != 0) {
            timeStr = day + "天" + timeStr;
        }
        return timeStr;
    }

    /**
     *
     * @param time 毫秒数
     * @return 天数小时分钟
     */
    public static String transferHour(long time) {
        int seconds = (int) (time / 1000);
        String timeStr = "";
        long min = 0;
        long hour = 0;
        if (seconds > 60) {
            min = seconds / 60;
            if (min > 60) {
                min = (seconds / 60) % 60;
                hour = (seconds / 60) / 60;
            }
        }
        if (min != 0) {
            timeStr = min + "分钟";
        }
        if (hour != 0) {
            timeStr = hour + "小时" + timeStr;
        }
        return timeStr;
    }

    public static boolean hasTimeConflict(long d1, long d2, long d3, long d4) {
        if (d2<d3 || d4<d1) {
            return false;
        } else {
            return true;
        }
    }

    public static Date getDate(int year, int month, int day, int hour, int minute) {
        Date date = new Date();
        date.setYear(year - 1900);
        date.setMonth(month - 1);
        date.setDate(day);
        date.setHours(hour);
        date.setMinutes(minute);
        return date;
    }

    public static long getTime(int hour, int minute) {
        long time;
        time = hour * 60 * 60 * 1000 + minute * 60 * 1000;
        return time;
    }

    public static long getTime(int day, int hour, int minute) {
        long time = getTime(hour, day);
        time += day * 24 * 60 * 60 * 1000;
        return time;
    }

}
