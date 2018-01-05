package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	/**
	 * DATETIME_FORMAT_SPEC="dd-MMM-yy hh.mm.ss.000000 a";
	 */
	public static final String DATETIME_FORMAT_SPEC="dd-MMM-yy hh.mm.ss.000000 a";
	public static final String DATE_FORMAT_SPEC="dd-MMM-yy";
	
	public static final String DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss";
	
	/**
	 * DATE_PATH_FORMAT="yyyyMMdd"
	 */
	public static final String DATE_PATH_FORMAT="yyyyMMdd";

	public static final String DATE_PATH_FORMAT_HI="yyyy-MM-dd";
	
	public static final String DATE_MMDDHH_CHINESE = "MM月dd日 HH时";
	
	public static final String DATE_MMDD_CHINESE = "MM月dd日";
	
	public static final String DATE_HH_CHINESE = "HH时";
	/**
	 * 格式化Date类型到 字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String DateToStr(Date date, String format){
		SimpleDateFormat sdf= new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 格式化获取前一天的当前时间字符串
	 * @param format
	 * @return
	 */
	public static String getBeforeDayToStr(String format) {
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, -1);    //得到前一天
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}
	
	public static String DateToStr(Date date, String format,int offsetDay){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, 0-offsetDay*24);
		SimpleDateFormat sdf= new SimpleDateFormat(format);
		return sdf.format(c.getTime());
	}
	
	public static Date addSecond(Date date, int second){
		Calendar c= Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}
	public static Date longToDate(long time){
		Calendar c= Calendar.getInstance();
		c.setTimeInMillis(time);
		return c.getTime();
	}
	
	public static Date zoneToDatetime(long time){
		Date date= new Date(time*1000);
		
		Calendar c= Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, 8);
		return date;
	}
	
	public static Date SpecToDateTime(String s) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat(DATETIME_FORMAT_SPEC,Locale.US);
		return sdf.parse(s);
	}
//	public static Date SpecToDate(String s) throws ParseException{
//		SimpleDateFormat sdf= new SimpleDateFormat(DATE_FORMAT_SPEC,Locale.US);
//		return sdf.parse(s);
//	}
	
	public static Date StringToDate(String s) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.parse(s);
	}

	public static Date StringToDate(String s, String partern) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat(partern);
		return sdf.parse(s);
	}
	
	public static String getDayOfWeek_chinese(Date input){
		if(null == input)
			return "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(input);
		
		switch(calendar.get(Calendar.DAY_OF_WEEK)){
			case Calendar.SUNDAY:
				return "星期日";
			case Calendar.MONDAY:
				return "星期一";
			case Calendar.TUESDAY:
				return "星期二";
			case Calendar.WEDNESDAY:
				return "星期三";
			case Calendar.THURSDAY:
				return "星期四";
			case Calendar.FRIDAY:
				return "星期五";
			case Calendar.SATURDAY:
				return "星期六";
		}
		return "";
	}
	
	/**
	 * 获取当前时间本周开始日期
	 * @return
	 */
	public static String getWeekStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int d = 0;
		if(cal.get(Calendar.DAY_OF_WEEK)==1){
			d = -6;
		}else{
			d = 2-cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		//所在周开始日期
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + " 00:00:00";
	}
	
	/**
	 * 获取当前时间本周结束日期
	 * @return
	 */
	public static String getWeekEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int d = 0;
		if(cal.get(Calendar.DAY_OF_WEEK)==1){
			d = -6;
		}else{
			d = 2-cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		//所在周结束日期
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + " 23:59:59";
	}
	
	/**
	 * 本月开始时间
	 * @return
	 */
	public static String getNowMonthStart() {
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		
		cale.add(Calendar.MONTH, 0);
	    cale.set(Calendar.DAY_OF_MONTH, 1);
	    return new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime()) + " 00:00:00";
	}
	
	/**
	 * 上月开始时间
	 * @return
	 */
	public static String getMonthStart() {
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		
		cale.add(Calendar.MONTH, -1);
	    cale.set(Calendar.DAY_OF_MONTH, 1);
	    return new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime()) + " 00:00:00";
	}
	
	/**
	 * 本月结束时间
	 * @return
	 */
	public static String getNowMonthEnd() {
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		
		cale.add(Calendar.MONTH, 1);
	    cale.set(Calendar.DAY_OF_MONTH, 0);
	    return new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime()) + " 23:59:59";
	}
	
	/**
	 * 上月结束时间
	 * @return
	 */
	public static String getMonthEnd() {
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		
		cale.add(Calendar.MONTH, 0);
	    cale.set(Calendar.DAY_OF_MONTH, 0);
	    return new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime()) + " 23:59:59";
	}
	
	public static void main(String[] args) {
		System.out.println(getMonthEnd());
	}
	
}
