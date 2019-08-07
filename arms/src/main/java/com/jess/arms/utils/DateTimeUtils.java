package com.jess.arms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by ybc on 2017/7/6.
 */

public class DateTimeUtils {
    public static String getNow() {
        return getNow("yyyy-MM-dd HH:mm:ss");
    }

    public static String getNow(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
        return formatter.format(new Date());
    }

    /**
     * 过去一月
     *
     * @param format
     * @return
     */
    public static String preMonth(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = formatter.format(m);
        return mon;
    }

    //时间计数器，最多只能到99小时，如需要更大小时数需要改改方法
    public static String showTimeCount(long time) {
        if (time >= 360000) {
            return "00:00:00";
        }
        String timeCount = "";
        long hourc = time / 3600;
        String hour = "0" + hourc;
        hour = hour.substring(hour.length() - 2, hour.length());

        long minuec = (time - hourc * 3600) / (60);
        String minue = "0" + minuec;
        minue = minue.substring(minue.length() - 2, minue.length());

        long secc = time - hourc * 3600 - minuec * 60;
        String sec = "0" + secc;
        sec = sec.substring(sec.length() - 2, sec.length());

        if (hourc == 0) {
            timeCount = minue + ":" + sec;
        } else {
            timeCount = hour + ":" + minue + ":" + sec;
        }
        return timeCount;
    }

    public static String formatData(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
        return formatter.format(time);
    }

    /**
     * 日期从一种格式转化成另一种
     *
     * @param time
     * @param startPattern
     * @param endPattern
     * @return
     * @throws ParseException
     */
    public static String formatData(String time, String startPattern, String endPattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(startPattern);
        SimpleDateFormat endFormatter = new SimpleDateFormat(endPattern);
        try {
            return endFormatter.format(formatter.parse(time));
        } catch (ParseException e) {
            return time;
        }
    }

    /**
     * 当前年份
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.YEAR);
    }

    /**
     * 当前月份
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.MONTH) + 1;
    }

    /**
     * 当前日期
     *
     * @return
     */
    public static int getCurrentDay() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取当前日期是星期几<br>
     * 0-6 表示周日-周六
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return w;
    }

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(String day, String dateFormat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.CHINA);
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = simpleDateFormat.parse(day);
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    // 获得本周一与当前日期相差的天数
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    // 获得当前周- 周一的日期
    public static String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = formatter.format(monday);
        return preMonday;
    }


    // 获得当前周- 周日  的日期
    public static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = formatter.format(monday);
        return preMonday;
    }

    /**
     * 判断当前日期是星期几<br>
     *
     * @return dayForWeek 判断结果<br>
     */
    public static String dayForWeek() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(date);
        return currSun;
    }
}
