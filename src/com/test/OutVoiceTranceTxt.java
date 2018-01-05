package com.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 读取转写完成文本输出话者分离格式文本
 * @author lishihuan
 *
 */

public class OutVoiceTranceTxt
{
  public static void main(String[] args)
  {
    String path = args[0];
    String outpath = args[1];

    int count = 0;
    BufferedWriter bfWrite = null;
    BufferedReader bfRead = null;

    File file = new File(path);
    File[] array = file.listFiles();
    for (int i = 0; i < array.length; ++i) {
      try {
        FileReader fRead = new FileReader(array[i]);
        bfRead = new BufferedReader(fRead);
        File outFile = new File(outpath + array[i].getName());
        if (!outFile.exists()) {
          outFile.createNewFile();
        }
        bfWrite = new BufferedWriter(new FileWriter(outFile));
        String tranceContent = null;
        if ((tranceContent = bfRead.readLine()) != null)
        {
          JSONArray contaArray = JSONObject.parseArray(tranceContent);
          for (int j = 0; j < contaArray.size(); ++j) {
            JSONObject jsonObject = contaArray.getJSONObject(j);

            String result = jsonObject.getString("content");
            if ((result != null) && (!"".equals(result))) {
              String speakContent = result.substring(0, result.indexOf(";"));
              if ((!"".equals(speakContent)) && (speakContent != null)) {
                bfWrite.write(speakContent);
                bfWrite.newLine();
              }
            }
          }
        }

        bfWrite.flush();
      }
      catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          if (bfRead != null) {
            bfRead.close();
          }
          if (bfWrite != null)
            bfWrite.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }

      ++count;
    }

    System.out.println("输出完成！");
    System.out.println("完成数量" + count);
  }
}