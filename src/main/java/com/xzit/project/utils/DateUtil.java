package com.xzit.project.utils;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * desc 时间处理工具类
 */
public class DateUtil {
    /**
     * 根据年 月 获取对应的月份 天数
     * */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 时间字符串转Date
     */
    public static Date dateStringChangeToDate(String date, String format){

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * 获得格式化时间
	 */
	public static Timestamp getFormatTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(new Date()));
        return time;
    }

    /**
     * 格式化时间
     * @return
     */
    public static String getFormatTimeCheck(){
        String time = getFormatTime().toString();
        return time.substring(0,10);
    }
    /**
     * 获得两个时间区间内的所有时间
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public static List<String> findDates(String start, String end){
        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin = null;
        try {
            dBegin = sdf.parse(start);
            Date dEnd = sdf.parse(end);
            List<Date> lDate = new ArrayList();
            lDate.add(dBegin);
            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(dBegin);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(dEnd);
            // 测试此日期是否在指定日期之后
            while (dEnd.after(calBegin.getTime()))
            {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                lDate.add(calBegin.getTime());
            }
            for (Date date:lDate) {
                result.add(sdf.format(date).toString().substring(0,10));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 时间转换
     * @param time
     * @param format
     * @return
     */
    public static String zonedDateTimeFormat(ZonedDateTime time, String format) {
        DateTimeFormatter nTZ = DateTimeFormatter.ofPattern(format);
        return time.format(nTZ);
    }

    /**
     * 从两个字符串时间内计算天数
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static int getDays(String startTime,String endTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Long beginTime = dateFormat.parse(startTime).getTime();
        Long lastTime = dateFormat.parse(endTime).getTime();
        return Math.toIntExact((lastTime - beginTime) / 86400000) + 1;
    }

	/**
	 * 获得当前时间戳
	 * 类型为：long
	 */
	public static long getTimeTest(){
		return System.currentTimeMillis();
	}

	public static boolean compareTime(ZonedDateTime date,Integer day){
	    boolean flag = false;
	    long nowTime = new Date().getTime();
	    long dateTime = Date.from(Instant.from(date)).getTime();
	    if (nowTime - dateTime <= day * 86400000){
	        flag = true;
        }
        return flag;
    }
    public static ZonedDateTime getZoneDateTime(){
	    return ZonedDateTime.now();
    }
    public static ZonedDateTime changeShanghaiToUTC(String beijingDateTimeStr){
        DateTimeFormatter beijingFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Shanghai"));
        if(StringUtils.isEmpty(beijingDateTimeStr)){
            return null;
        }
        ZonedDateTime beijingDateTime = ZonedDateTime.parse(beijingDateTimeStr, beijingFormatter);
        return beijingDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    }

    public static String zoneDateTimeFormate(ZonedDateTime time){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String date = time.format(formatter);
	    return date;
    }
    /**
     * 获取当前日期时间
     * @param format 	指定转换日期格式
     * @return
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(new Date());
    }

    /**
     * 接口测试运行时间专用
     * @return
     */
    public static String testTime(){
        String str = "yyyy-MM-dd HH:mm:ss:SSS";
        return getCurrentDate(str).toString();

    }
    /**
     *
     * 根据指定的格式对日期字符串进行转化
     *
     * @param strDate 	待转换的字符串日期
     * @param format 	指定转换日期格式
     * @return Date 	返回java.util.Date日期对象
     * @throws
     */
    public static Date getDate(String strDate, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }



    /**
     *
     * 根据指定的格式对日期进行转化
     *
     * @param date 		 待转换的日期时间
     * @param outFormat 指定的输出格式
     * @return res		outformat格式字符串日期
     */
    public static String format(Date date, String outFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(outFormat);
        String res = sf.format(date);
        return res;
    }

    /**
     *
     * 根据指定的格式的日期字符串执行指定日期的转化
     *
     * @param dateStr 	字符串时间
     * @param inForamt 	输入格式
     * @param outformat 输出格式
     * @return			outformat格式字符串日期
     */
    public static String format(String dateStr, String inForamt, String outformat) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(inForamt);
            Date date = sf.parse(dateStr);
            return new SimpleDateFormat(outformat).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将毫秒数转换为日期时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis	毫秒日期时间
     * @return 		天,时:分:秒.毫秒格式字符串日期
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     *
     * 获取两日期之间的所有日期，包含当前两个日期
     *
     * @param date1			开始时间
     * @param date2			结束时间
     * @param formatStr		日期格式
     * @return 两日期之间的所有日期（包含当前两个日期）
     */
    public static List<String> getAmongDate(String date1, String date2, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        List<String> dateList = new ArrayList<String>();
        dateList.add(date1);
        if (date1.equals(date2)) {
            return dateList;
        }

        String tmp;
        if (date1.compareTo(date2) > 0) { // 确保 date1的日期不晚于date2
            tmp = date1;
            date1 = date2;
            date2 = tmp;
        }

        tmp = format.format(str2Date(date1, format).getTime() + 3600 * 24 * 1000);
        while (tmp.compareTo(date2) < 0) {
            dateList.add(tmp);
            tmp = format.format(str2Date(tmp, format).getTime() + 3600 * 24 * 1000);
        }

        dateList.add(date2);
        return dateList;
    }

    private static Date str2Date(String str, SimpleDateFormat format) {
        if (str == null)
            return null;

        try {
            return format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对日期某个属性只增加N值
     *
     * @param date		日期时间
     * @param type		增加的类型
     * @param num		增量
     * @return
     */
    public static Date addDate(Date date, int type, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type, num);
        return cal.getTime();
    }

    /**
     * 对日期某个属性只增加N值
     * 和 addDate方法相同，（废弃）
     * @param date		日期时间
     * @param type		增加的类型
     * @param num		增量
     * @return
     */
    public static Date setDate(Date date, int type, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type, num);
        return cal.getTime();
    }

    /**
     * 时间按小时加法
     * @param date		日期时间
     * @param num		增量
     * @return	日期时间增加num小时
     */
    public static Date addHour(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, num);
        return cal.getTime();
    }

    /**
     * 时间按天加法
     * @param date		日期时间
     * @param num		增量
     * @return	日期时间增加num天
     */
    public static Date addDate(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, num);
        return cal.getTime();
    }

    /**
     * 时间按月加法
     * @param date		日期时间
     * @param num		增量
     * @return	日期时间增加num月
     */
    public static Date addMonth(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, num);
        return cal.getTime();
    }

    /**
     * 两个时间的小时差数
     *
     * @param date1	开始日期时间
     * @param date2	结束日期时间
     * @return
     */
    public static int differenceHour(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60));
    }

    /**
     * 今年
     */
    public static String getCurrentYear() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.format(new Date());
    }

    /**
     * 去年
     * @return
     */
    public static String getPrevYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.format(calendar.getTime());
    }

    /**
     * 去年
     * @return
     */
    public static String getPrevYear(SimpleDateFormat sf) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        return sf.format(calendar.getTime());
    }

    /**
     * 当月
     * @return
     */
    public static String getCurrentMoth() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
        return sf.format(new Date());
    }

    /**
     * 上月
     */
    public static String getPrevMoth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
        return sf.format(calendar.getTime());
    }

    /**
     * 上月
     */
    public static String getPrevMoth(SimpleDateFormat sf) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return sf.format(calendar.getTime());
    }

    /**
     * 去年同月
     */
    public static String getPrevYearCurrentMonth(String outFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        calendar.add(Calendar.MONTH, 0);
        SimpleDateFormat sf = new SimpleDateFormat(outFormat);
        return sf.format(calendar.getTime());
    }

    /**
     * 当天
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(new Date());
    }

    /**
     * 当天
     * @return
     */
    public static String getCurrentDate(SimpleDateFormat sf) {
        return sf.format(new Date());
    }

    /**
     * 昨天
     * @return
     */
    public static String getPrevDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(calendar.getTime());
    }

    /**
     * 昨天
     * @return
     */
    public static String getPrevDate(SimpleDateFormat sf) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return sf.format(calendar.getTime());
    }

    /**
     * 今天
     * @return
     */
    public static String getNowDateByFormat(String format) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        return sf.format(calendar.getTime());
    }


    /**
     * 昨天
     * @return
     */
    public static String getPrevDateByFormat(String format) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return sf.format(calendar.getTime());
    }

    /**
     * 大前天
     * @return
     */
    public static String getThreePrevDateByFormat(String format) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        return sf.format(calendar.getTime());
    }

    /**
     * 前一天(废弃方法)
     * @param st	格式:yyyyMMdd
     */
    public static String getPrevDate(String st) {
        int date = Integer.parseInt(st);
        date = date - 1;
        return "" + date;
    }

    /**
     * 上周
     * @return
     */
    public static String getPrevWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(calendar.getTime());
    }

    /**
     * 上周
     * @return
     */
    public static String getPrevWeek(SimpleDateFormat sf) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        return sf.format(calendar.getTime());
    }

    /**
     * 获取当前时间所在年的周数
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取某年某周的第一天
     *
     * @param year 年
     * @param week 周数
     * @return
     */
    public static Date getStartDateByWeek(int year, int week) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        // cal.setFirstDayOfWeek(1);
        cal.set(Calendar.DAY_OF_WEEK, 1);
        cal.add(Calendar.DATE, 1);
        Date result = cal.getTime();
        if (result.getTime() < new GregorianCalendar(year, 0, 1).getTimeInMillis())
            return new GregorianCalendar(year, 0, 1).getTime();
        return result;
    }

    /**
     * 获取某年某周的最后一天
     *
     * @param year 	年
     * @param week 	周数
     * @return
     */
    public static Date getEndDateByWeek(int year, int week) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, 7);
        cal.add(Calendar.DATE, 1);
        Date result = cal.getTime();
        if (result.getTime() > new GregorianCalendar(year, 11, 31).getTimeInMillis())
            return new GregorianCalendar(year, 11, 31).getTime();
        return result;
    }

    /**
     * 获取月份
     *
     * @param number 		0为当月
     * @param outDateFormat 返回格式
     * @return
     */
    public static String getMonth(int number, String outDateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, number);
        SimpleDateFormat sf = new SimpleDateFormat(outDateFormat);
        return sf.format(calendar.getTime());
    }

    /**
     * 获取月份
     *
     * @param yearNumber 0为当月
     * @param monthNumber 0为当年
     * @param outDateFormat 返回格式
     * @return
     */
    public static String getYearMonth(int yearNumber, int monthNumber, String outDateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, yearNumber);
        calendar.add(Calendar.MONTH, monthNumber);
        SimpleDateFormat sf = new SimpleDateFormat(outDateFormat);
        return sf.format(calendar.getTime());
    }

    /**
     * 取得指定日期所在周的第一天
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 获取上周第一天
     */
    public static Date getPrevWeekOfFirstDay() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        int offset = 1 - (dayOfWeek == 0 ? 7 : dayOfWeek);
        calendar.add(Calendar.DATE, offset - 7);
        return calendar.getTime();
    }

    /**
     * 获取上周最后一天
     * @return
     */
    public static Date getPrevWeekOfEndDay() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 7 - (dayOfWeek == 0 ? 7 : dayOfWeek);
        calendar.add(Calendar.DATE, offset - 7);
        return calendar.getTime();
    }

    /**
     * 获取某一日期的前、后某天的日期
     * @param date 		某日期
     * @param nextDay 	前(-1,前一天)、后某天的天数
     * @return 日期
     */
    public static String getDate(String date, int nextDay) {
        String result = "";
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            Date ldate = df.parse(date);
            result = df.format(new Date(ldate.getTime() + nextDay * 24 * 60 * 60 * 1000)).toString();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取上月的最后一天
     * @return
     */
    public static String getLastMonthLastDay() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int year = 0;
        int month = cal.get(Calendar.MONTH); // 上个月月份
        // int day1 = cal.getActualMinimum(Calendar.DAY_OF_MONTH);//起始天数
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 结束天数
        if (month == 0) {
            year = cal.get(Calendar.YEAR) - 1;
            month = 12;
        } else {
            year = cal.get(Calendar.YEAR);
        }
        String endDay = year + "-" + month + "-" + day;
        return endDay;
    }

    /**
     * 获取上月的第一天
     * @return
     */
    public static String getLastMonthFirstDay() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int year = 0;
        int month = cal.get(Calendar.MONTH); // 上个月月份
        int day = cal.getActualMinimum(Calendar.DAY_OF_MONTH);// 起始天数
        // int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 结束天数
        if (month == 0) {
            year = cal.get(Calendar.YEAR) - 1;
            month = 12;
        } else {
            year = cal.get(Calendar.YEAR);
        }
        String endDay = year + "-" + month + "-" + day;
        return endDay;
    }

    /**
     * 获取下月第一天日期
     * @param dateFormat 输出格式
     * @return
     */
    public static String getNextMonthFirstDay(String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return df.format(calendar.getTime());
    }

    /**
     * 获取下月最后日期
     * @param dateFormat 输出格式
     * @return
     */
    public static String getNextMonthLastDay(String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DATE));
        return df.format(calendar.getTime());
    }

    /**
     * 获取最后日期
     *
     * @param dateFormat 输出格式
     * @return
     */
    public static String getMonthLastDay(String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DATE));
        return df.format(calendar.getTime());
    }

    /**
     * 获取当天月第一天
     * @return 返回格式 yyyyMMdd
     */
    public static String getMonthFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gc.getTime());
        return day_first;
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month,
                                           SimpleDateFormat sf) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return sf.format(cal.getTime());
    }

    /**
     * 获取某年某月最大天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMaxDay(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1);
        c.add(Calendar.DAY_OF_YEAR, -1);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回某年某月日期集合
     *
     * @param date 	 yyyyMM
     * @return ("20131001","1日")
     */
    public static Map<String, String> getMonthDays(String date) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);

        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDay; i++) {
            map.put(date + "" + (i < 10 ? ("0" + i) : i), (i < 10 ? ("0" + i) : i) + "日");
        }
        return map;
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 获取当前之前num天的日期集合,并按照格式输出
     *
     * @param format 	输出日期的格式
     * @param num		要获得的天数
     * @return 规定格式的日期集合
     */
    public static String[] getFrontDate(int num, String format) {
        String nowDate = format(new Date(), "yyyyMMdd");
        String[] dates = new String[num];
        dates[0] = TypeUtils.getString(format(nowDate, "yyyyMMdd", format));
        int nowDateInt = Integer.parseInt(nowDate);
        for (int i = 1; i <= num - 1; i++) {
            dates[i] = TypeUtils.getString(format(TypeUtils.getString(nowDateInt - i), "yyyyMMdd", format));
        }
        return dates;
    }

    /**
     * 得到两个时间的时间差 返回相差的分钟 时间格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param d1 第一个时间
     * @param d2 第二个时间
     * @param d1-d2
     * @return
     */
    public static long getAccesstime(Date d1, Date d2) {
        long minute = 0;
        long l = d1.getTime() - d2.getTime();
        minute = l / (1000 * 60);
        return minute;
    }

    /**
     * 得到两个时间的时间差 返回相差的分钟
     *
     * @param d1  现在的系统时间
     * @param d2 从数据库拿到的时间
     * @return
     */
    public static long getAccesstime(Date d1, String d2) {
        long minute = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d3 = sdf.parse(d2);
            long l = d1.getTime() - d3.getTime();
            minute = l / (1000 * 60);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return minute;
    }

    /**
     * 得到两个时间的时间差 返回相差的分钟
     *
     * @param d1   现在的系统时间
     * @param d2  从数据库拿到的时间
     * @return
     */
    public static long getAccesstime(String d1, String d2) {
        long minute = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            if (!TypeUtils.isEmpty(d1) && !TypeUtils.isEmpty(d2)) {
                Date d3 = sdf.parse(d2);
                Date d4 = sdf.parse(d1);
                long l = d4.getTime() - d3.getTime();
                minute = l / (1000 * 60);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return minute;
        }
        return minute;
    }

    /**
     * 获取星期类型 周一周五 0，周六、周日 1
     *
     * @param dt
     * @return
     */
    public static String getWeekOfType(Date dt) {
        String[] weekDays = { "1", "0", "0", "0", "0", "0", "1" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 把unix 转成时间格式 返回（yyyy-MM-dd HH:mm:ss）
     *
     * @param unix
     * @return
     */
    public static String toLocalTime(String unix) {
        Long timestamp = Long.parseLong(unix) * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        return date;
    }

    /**
     * 把时间转成 unix格式
     *
     * @param local  时间格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String toUnixTime(String local) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String unix = "";
        try {
            unix = df.parse(local).getTime() / 1000 + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return unix;
    }

    /**
     * 把时间转成 unix格式
     *
     * @param addDayNum		增加天数
     * @return
     */
    public static String toUnixtime(int addDayNum) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String unix = "";
        try {
            unix = df.parse(DateUtil.format(DateUtil.addDate(new Date(), addDayNum), "yyyy-MM-dd HH:mm:ss")).getTime() / 1000 + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return unix;
    }


	/*下面方法废弃*/
    /**
     * 废弃方法 (原因：命名不规范)
     */
    public static List<Map<String, String>> getLastDays(Date date, int num) {
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        String[] weekOfDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        for (int i = 0; i <= num; i++) {
            Map<String, String> maps = new HashMap<String, String>();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, i);
            String day = format(cal.getTime(), "yyyy-MM-dd");
            int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (week < 0) {
                week = 0;
            }
            StringBuffer values = new StringBuffer();
            values.append(day);
            values.append(" ");
            values.append(weekOfDays[week]);
            maps.put("key", day);
            maps.put("value", values.toString());

            lists.add(maps);
        }
        return lists;
    }

    /**
     *
     * 获得两个日期的 天数集合,格式"yyyy-MM-dd"
     * 废弃方法 (原因：命名不规范)
     * @param start 	开始时间
     * @param end 		结束时间
     * @return
     */
    public static List<String> getDates2(String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<String>();
        try {
            Date date_start = sdf.parse(start);
            Date date_end = sdf.parse(end);
            Date date = date_start;
            Calendar cd = Calendar.getInstance();

            while (date.getTime() <= date_end.getTime()) {
                list.add(sdf.format(date));
                cd.setTime(date);
                cd.add(Calendar.DATE, 1);// 增加一天
                date = cd.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 废弃方法 (原因：命名不规范)
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }
    /**
     * 获取两个时间区间的年月
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getDateSectionMonth(String startDate,String endDate){
        List<String> date = new ArrayList<String>();
        int syear = TypeUtils.getInt(startDate.substring(0,4));
        int eyear = TypeUtils.getInt(endDate.substring(0,4));
        int smon = TypeUtils.getInt(startDate.substring(5,7));
        int emon = TypeUtils.getInt(endDate.substring(5,7));
        if(syear==eyear){
            for(int i=smon; i<=emon; i++){
                date.add(syear+"-"+(i<10?"0"+i:i+""));
            }
        }else if(syear<eyear&&(eyear-syear)==1){
            for(int j=smon; j<=12; j++){
                date.add(syear+"-"+(j<10?"0"+j:j+""));
            }
            for(int j=1; j<=emon; j++){
                date.add(eyear+"-"+(j<10?"0"+j:j+""));
            }
        }else if(syear<eyear&&(eyear-syear)>1){
            for(int j=smon; j<=12; j++){
                date.add(syear+"-"+(j<10?"0"+j:j+""));
            }
            for(int i=(syear+1);i<eyear;i++){
                for(int j = 1;j<=12;j++){
                    date.add(i+"-"+(j<10?"0"+j:j+""));
                }
            }
            for(int j=1; j<=emon; j++){
                date.add(eyear+"-"+(j<10?"0"+j:j+""));
            }
        }
        return date;
    }
}
