package com.test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式判断整数
 * @author lishihuan
 *
 */
public class TestPattrn
{
  public static final int MAXNUM = 2000;
  public static final int MAXTIME = 10;
  public static final int MAXSHORTTIME = 5;

public static void main(String[] args)
  {
    int[] numAndTimes = new int[3];
    int num = 0;
    int times = 0;
    int shortTimes = 0;

    List paramList = new ArrayList();
    Map paramMap1 = new HashMap();
    paramMap1.put("id", Integer.valueOf(3));
    paramMap1.put("prameter_name", "record_move_max_num");
    paramMap1.put("prameter_value", "8000");
    paramMap1.put("prameter_desc", "定时任务每次将通话记录从redis移动到oracle最大数量");
    paramList.add(paramMap1);

    Map paramMap2 = new HashMap();
    paramMap2.put("id", Integer.valueOf(4));
    paramMap2.put("prameter_name", "call_time_long");
    paramMap2.put("prameter_value", "5");
    paramMap2.put("prameter_desc", "多少分钟无通话识别结果认为通话结束(单位为分钟)");
    paramList.add(paramMap2);

    Map paramMap3 = new HashMap();
    paramMap3.put("id", Integer.valueOf(8));
    paramMap3.put("prameter_name", "call_short_long");
    paramMap3.put("prameter_value", "5");
    paramMap3.put("prameter_desc", "默认多上分钟之内为短录音进行骚扰判断");
    paramList.add(paramMap3);

    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

    boolean matches = pattern.matcher("8").matches();
    System.out.println(matches);

    boolean b1 = pattern.matcher("call_time_long").matches();
    System.out.println(b1);
  }
}