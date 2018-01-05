package com.test;

import java.util.Calendar;

/**
 * 判断当前时间小时
 * @author lishihuan
 *
 */
public class DateJudge {
	
	public static void main(String[] args)
	  {
	    Calendar c = Calendar.getInstance();
	    System.out.println(c.get(11));
	    int nowHour = c.get(11);
	    if (nowHour < 15)
	      System.out.println("小于15");
	    else
	      System.out.println("大于15");
	  }

}
