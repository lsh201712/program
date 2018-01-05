package com.test;

import java.util.HashSet;
import java.util.Set;

public class JudgeStringLocation
{
  public static void main(String[] args)
  {
    String[] srcs = { "你按时阿萨德我知道肯德基你爽了数据今年昨天", "昨天的时间", "我知道了" };
    String sub = "我知道你昨天爽了";

    getIndex(srcs, sub);
  }

  public static void getIndex(String[] srcs, String sub) {
    int end = 1;
    Set resultSet = new HashSet();
    for (int i = 0; i < srcs.length; ++i) {
      String src = srcs[i];
      String lsub = "";
      for (int j = 1; j <= sub.length(); ++j) {
        lsub = sub.substring(0, j);
        if (src.contains(lsub)) {
          if (lsub.length() == sub.length()) {
            end = 0;
          }
        }
        else
        {
          if (j == 1) {
            break;
          }
          resultSet.add(src);
          sub = sub.substring(j - 1);
          i = -1;
          break;
        }
      }

      if (end == 0) {
        resultSet.add(src);
        break;
      }
    }
    System.out.println(resultSet);
  }
}