package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestSortAnnoy {
	public static void main(String[] args) {
		try {
			// 读取
			String fileName = "d:/2.txt";
			File file = new File(fileName);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					file), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			List<JSONObject> array = new ArrayList<JSONObject>();
			while ((line = br.readLine()) != null) {
				if (line.trim().length() == 0) {
					continue;
				}
				array.add(JSONObject.parseObject(line));
			}
			// 对array按照每个对象字段content中的time 进行排序
	/*		for (JSONObject jsonObject : array) {
				String content = jsonObject.getString("content");
				System.out.println(content);
				// 获取time

				long Millisecond = Long.parseLong(content.substring(content.indexOf("=") + 1, content.lastIndexOf(" ")));
				System.out.println(Millisecond);

				 String startMill = Millisecond.split(" ")[0]; 
				 System.out.println(startMill); 

			}*/
		  sortTimeArray(array);
		/*  for (JSONObject jsonObject : array) {
			System.out.println(jsonObject.getString("content"));
		  }*/
		  System.out.println(array.toString());
		  System.out.println(JSONObject.toJSONString(array));
		  

			// 写入
			
			  String outFileName="d:/3.txt"; 
			  FileWriter fw = new FileWriter(outFileName); 
			 /* for (JSONObject jsonObject : array) {
				  fw.append(jsonObject.toString()+"\n");
				  fw.append(jsonObject.toString());
			
			}*/
			  fw.append(JSONObject.toJSONString(array));
			fw.flush(); 
			fw.close();
			br.close(); 
			isr.close();
			 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    
    private static void sortTimeArray(List<JSONObject> list){
    	JSONObject tempJsonObject = new JSONObject(); //记录中间值
    	for (int out =list.size(); out>0 ;out--) {
			for (int in = 0; in < list.size()-1; in++) {
				JSONObject theOneObject =list.get(in) ;
				JSONObject theTowObject = list.get(in +1);
				
				String oneContent = theOneObject.getString("content");
				String twoContent = theTowObject.getString("content");
				
				long oneMillSecond = Long.parseLong(oneContent.substring(oneContent.indexOf("=") + 1, oneContent.lastIndexOf(" ")));
				long twoMillSecond = Long.parseLong(twoContent.substring(twoContent.indexOf("=") + 1, twoContent.lastIndexOf(" ")));

				if (twoMillSecond<oneMillSecond) {
					tempJsonObject = theOneObject;
					list.set(in, theTowObject);
					list.set(in+1, tempJsonObject);
				}
							
			}
		}
    }
}
