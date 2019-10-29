package com.part.utils;

import com.google.common.collect.Maps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/*
 * @author jiangSD
 * @since 2019-10-23
 */
public class DateTimeUtils {

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getCurrYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getCurrYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }
    /**
     * 获得该月第一天
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        //格式化日期
        Date time = cal.getTime();
        return time;
    }

    /**
     * 获得该月最后一天
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        //格式化日期
        Date time = cal.getTime();
        return time;
    }

    /**
     * 某年某月有多少天
     * @param year
     * @param month
     * @return
     */
    public static Integer getYearMoneyDay(int year,int month){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 0); //输入类型为int类型
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth;
    }
    /**
     * 根据传入时间获取年、月、日
     * @return
     */
    public static Map<String,Object> getYearMoneyDay(Date time){
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2= new SimpleDateFormat("dd");
        Map<String,Object> map = Maps.newHashMap();
        map.put("year",sdf0.format(time));
        map.put("mouth",sdf1.format(time));
        map.put("day",sdf2.format(time));
        return map;
    }

    /**
     * 获取某一天起时
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date getDayStartTime(int year,int month,int day){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        //格式化日期
        Date time = cal.getTime();
        return time;
    }

    /**
     * 获取某一天终时
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date getDayEndTime(int year,int month,int day){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        //格式化日期
        Date time = cal.getTime();
        return time;
    }
    /**
     * 获取某一天
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date getDayTime(int year,int month,int day){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, day);
        //格式化日期
        Date time = cal.getTime();
        return time;
    }
    /**
     * 获取某一月
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthTime(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        Date time = cal.getTime();
        return time;
    }

    /**
     * 时间比较
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compareDate(Date DATE1, Date DATE2) {
        try {
            if (DATE1.getTime() > DATE2.getTime()) {
                return 1;
            } else if (DATE1.getTime() < DATE2.getTime()) {
                return 2;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    /**
     * 获取当天起止时间
     * @return
     */
    public static Map<String,Object> getDayStartAndEnd() {
        SimpleDateFormat formatStart = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Date notTime = new Date();
        String startTime = formatStart.format(notTime);
        String endTime = formatEnd.format(notTime);
        Map<String,Object> map = Maps.newHashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return map;
    }
    /**
     * 获取昨天起止时间
     * @return
     */
    public static Map<String,Object> getLastDayStartAndEnd() {
        Date dNow = new Date(); //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
        dBefore = calendar.getTime(); //得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore); //格式化前一天
        defaultStartDate = defaultStartDate+" 00:00:00";
        String defaultEndDate = defaultStartDate.substring(0,10)+" 23:59:59";
        Map<String,Object> map = Maps.newHashMap();
        map.put("startTime",defaultStartDate);
        map.put("endTime",defaultEndDate);
        return map;
    }
    /**
     * 获取七天起止时间
     *
     * @return
     */
    public static Map<String,Object> getSevenStartAndEnd() {
        SimpleDateFormat formatStart = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar lastDate = Calendar.getInstance();
        lastDate.roll(Calendar.DATE, -7);//日期回滚7天
        String startTime = formatStart.format(lastDate.getTime());
        String endTime = formatEnd.format(new Date());
        Map<String,Object> map = Maps.newHashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return map;
    }
    /**
     * 获取3天起止时间
     *
     * @return
     */
    public static Map<String,Object> getThreeStartAndEnd() {
        SimpleDateFormat formatStart = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar lastDate = Calendar.getInstance();
        lastDate.roll(Calendar.DATE, -3);//日期回滚7天
        String startTime = formatStart.format(lastDate.getTime());
        String endTime = formatEnd.format(new Date());
        Map<String,Object> map = Maps.newHashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return map;
    }
    /**
     * 获取本月起止时间
     *
     * @return
     */
    public static Map<String,Object> getMounthStartAndEnd() {
        SimpleDateFormat formatStart = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar calendar=Calendar.getInstance();
        Date theDate=calendar.getTime();
        GregorianCalendar gcLast=(GregorianCalendar)Calendar.getInstance();
        gcLast.setTime(theDate);
        //设置为第一天
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String startTime=formatStart.format(gcLast.getTime());
        Calendar calendarEnd =Calendar.getInstance();
        //设置日期为本月最大日期
        calendar.set(calendarEnd.DATE, calendarEnd.getActualMaximum(calendarEnd.DATE));
        String endTime=formatEnd.format(calendar.getTime());
        Map<String,Object> map = Maps.newHashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return map;
    }
    /**
     * 获取今年X年的最后一天
     *
     * @return
     */
    public static Date getXYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 时间相减
     */
    public static Map<String,Object> timeReduce(Date dateStart,Date dateStop){
        Map<String,Object> map = Maps.newHashMap();
        try {
            //毫秒ms
            long diff = dateStart.getTime() - dateStop.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            System.out.print("两个时间相差：");
            System.out.print(diffDays + " 天, ");
            System.out.print(diffHours + " 小时, ");
            System.out.print(diffMinutes + " 分钟, ");
            System.out.print(diffSeconds + " 秒.");
            map.put("day",diffDays);
            map.put("hour",diffHours);
            map.put("minute",diffMinutes);
            map.put("seconds",diffSeconds);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) throws ParseException {
//        String dateStart = "2018-09-06 11:30:00";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date xYear = getXYear(2022);
//        Date d1  = format.parse(dateStart);
//
//        Map<String, Object> stringObjectMap = timeReduce(d1, new Date());
        System.out.println(format.format(xYear));
    }
}
