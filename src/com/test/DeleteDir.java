package com.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * 删除目录以及目录下所有文件
 * @author lishihuan
 *
 */
public class DeleteDir
{
  public static void main(String[] args)
  {
    List delDirList = new ArrayList();

    String voicePath = "E:/home/Recored";

    Long holdTime = Long.valueOf(1200000L);

    File file = new File(voicePath);
    String[] dirList = file.list();
    for (String dirName : dirList)
    {
      File busnissFile = new File(voicePath + "/" + dirName);
      Long busnissModifiedTime = Long.valueOf(busnissFile.lastModified());
      Long nowTime = Long.valueOf(System.currentTimeMillis());

      if (nowTime.longValue() - busnissModifiedTime.longValue() > holdTime.longValue()) {
        delDirList.add(new File(voicePath + "/" + dirName));
      }

    }

    if (delDirList.size() > 0)
      for (int i = 0; i < delDirList.size(); ++i) {
        boolean result = deletDir((File)delDirList.get(i));
        if (result)
          System.out.println("删除工单目录名称" + ((File)delDirList.get(i)).getName());
      }
  }

  public static boolean deletDir(File dir)
  {
    if (dir.isDirectory()) {
      String[] children = dir.list();

      for (int i = 0; i < children.length; ++i) {
        boolean success = deletDir(new File(dir, children[i]));
        if (!success) {
          return false;
        }
      }
    }

    return dir.delete();
  }
}