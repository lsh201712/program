package com.es;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.HTTPMethod;

public class TestEsMethod {
	public static void main(String[] args) throws HttpException {
	
	//count_single_recording("2017-09-23 18:00:00",1,"/home");
		/*createIndexSetting();*/
		
		//count_single_recording(args[0],Integer.parseInt(args[1]),args[2],args[3]);
		//updatefield("2017-10", "2017");
		//updatefield("2017-11","2017-11_new");
		updatefield(args[0],args[1]);
		
	}
	
	//插入索引数据
	public static void addData(){
		
		String url = "http://10.0.1.227:8084/";
		int count = 10000000;
		while (true) {
			count++;

			String query = "{ \"index\": {\"_id\":\"" + count + "\"}}\n";
			query = query + "{\"title\":\"喂你好哎李先生是吧我平安公司的小张。喂哎你好哎恋人您好钱兰起来了吗先生。ring。music。用完两分钟打打电话我问我问一下吧我也不知道。嗯行行行好嘞好嘞您先打电话问一下好嘞好嘞再见。\"}\n";
			String doPutQuery = HTTPMethod.doPutQuery(url + "pa_annoy/annoy/" + "_bulk", query, 30000);
			System.out.println(doPutQuery);
		}
	}
	
	//建索引
	public static void createIndex(){
		
		String url = "http://192.169.51.4:9200/";
	    System.out.println("开始创建pa_upload 索引映射结构");
	    String query = "{\"mappings\": {\"content\": {\"_all\": {\"analyzer\": \"ik\",\"search_analyzer\": \"ik\",\"term_vector\": \"no\"},\"properties\": {\"VOICE_ID\": {\"type\": \"string\",\"index\": \"not_analyzed\"},\"VOICE_PATH\": {\"type\": \"string\",\"index\": \"not_analyzed\"},\"PLAT_FORM\": {\"type\": \"string\",\"index\": \"not_analyzed\"},\"TRANS_STATE\": {\"type\": \"string\",\"index\": \"not_analyzed\"},\"TRANS_CONTENT\": {\"type\": \"string\",\"index\": \"not_analyzed\",\"term_vector\": \"with_positions_offsets\",\"ignore_above\":10000},\"CALL_START_TIME\": {\"type\": \"date\",\"format\": \"yyyy-MM-dd HH:mm:ss\"},\"UPLOAD_TIME\":{\"type\": \"date\",\"format\": \"yyyy-MM-dd HH:mm:ss\"}}}},\"settings\":{\"index\" : { \"max_result_window\" : 100000000}}}";

	    String doPutQuery = HTTPMethod.doPutQuery(url + "pa_upload/", query, 30000);
	    System.out.println(doPutQuery);
	    System.out.println("创建pa_upload索引完毕！");
	    String doGetQuery2 = HTTPMethod.doGetQuery(url + "pa_upload*/_mapping", 30000);
	    System.out.println(doGetQuery2);
	}
	
public static void createIndexSetting(){
		
		String url = "http://10.0.1.227:9200/";
	    System.out.println("开始创建pa_upload 索引映射结构");
	    String query = "{\"mappings\": {\"content\": {\"_all\": {\"analyzer\": \"ik\",\"search_analyzer\": \"ik\",\"term_vector\": \"no\"},\"properties\": {\"VOICE_ID\": {\"type\": \"string\",\"index\": \"not_analyzed\"},\"VOICE_PATH\": {\"type\": \"string\",\"index\": \"not_analyzed\"},\"PLAT_FORM\": {\"type\": \"string\",\"index\": \"not_analyzed\"},\"TRANS_STATE\": {\"type\": \"string\",\"index\": \"not_analyzed\"},\"TRANS_CONTENT\": {\"type\": \"string\",\"index\": \"not_analyzed\",\"term_vector\": \"with_positions_offsets\",\"ignore_above\":10000},\"CALL_START_TIME\": {\"type\": \"date\",\"format\": \"yyyy-MM-dd HH:mm:ss\"},\"UPLOAD_TIME\":{\"type\": \"date\",\"format\": \"yyyy-MM-dd HH:mm:ss\"}}}},\"settings\":{ \"index\" : { \"max_result_window\" : 100000000},\"number_of_shards\" : 9,\"number_of_replicas\" : 1}}";
	
	    String doPutQuery = HTTPMethod.doPutQuery(url + "pa_upload201711/", query, 30000);
	    System.out.println(doPutQuery);
	    System.out.println("创建pa_upload索引完毕！");
	    String doGetQuery2 = HTTPMethod.doGetQuery(url + "pa_upload*/_mapping", 30000);
	    System.out.println(doGetQuery2);
	}
	
	
	
	
	//查询数据
	//统计单边录音数量，参数开始时间,需要几个小时的数据，间隔1小时
	public static void count_single_recording(String startTime,int hour,String path,String platForm){
		String url227 = "http://10.0.1.227:9200/pa_annoy"+startTime.substring(0,7)+"/annoy/_search";
		String url = "http://192.169.51.4:9200/pa_annoy"+startTime.substring(0,7)+"/annoy/_search";
		String getTime  =  startTime;
		
		BufferedWriter bfWrite =null;
		try {
			
		
			File outFile = null;
			
			//是否将单边流水号写入txt
			boolean ifWtite =true;
			if (path==null||"".equals(path)) {
				ifWtite =false;
			}else {
				outFile = new File(path +File.separator+System.currentTimeMillis()+"result.txt" );
				if (!outFile.exists()) {
					outFile.createNewFile();
				}
				bfWrite = new BufferedWriter(new FileWriter(outFile));
			}
			
			for (int i = 0; i < hour; i++) {
				int getTimeHour  = Integer.parseInt(startTime.substring(11,13))+1*i;
				int lteHour = Integer.parseInt(startTime.substring(11,13))+1*i+1;
				String getTimeString = "";
				String  lteTimeString ="";
				if (getTimeHour<10) {
					getTimeString = "0"+getTimeHour;
				}else {
					getTimeString = getTimeHour+"";
				}
				if (lteHour<10) {
					lteTimeString ="0"+lteHour;
				}else {
					lteTimeString = lteHour+"";
				}
				
				getTime =getTime.substring(0,11)+getTimeString+getTime.substring(13,getTime.length());
				String lteTime  =getTime.substring(0,11)+lteTimeString+getTime.substring(13,getTime.length());
				/*System.out.println(getTime+"___"+lteTime);*/
				String query = "{\"query\":{\"filtered\":{\"filter\":[{\"bool\":{\"must\":[{\"range\":{\"CREATE_TIME\":{\"gte\":\""+ getTime  +"\",\"lte\":\""+ lteTime +"\"}}},{\"term\":{\"PLAT_FORM\":\"XQD-"+platForm+"\"}}]}}]}},\"sort\":{\"CREATE_TIME\":{\"order\":\"desc\",\"ignore_unmapped\":true}},\"from\":0,\"size\":10000}";
				System.out.println(url);
				System.out.println(query);
				String result = HTTPMethod.doPostQuery(url, query, 30000);
				
				JSONObject resultObject = JSON.parseObject(result);
				JSONObject hitsObject = resultObject.getJSONObject("hits");
				JSONArray hitsArray = hitsObject.getJSONArray("hits");
				int total = (Integer) hitsObject.get("total");
				System.out.println(getTime+"___"+lteTime+"时间段数据总量:"+total);
				int count  =0; //单边总数
				
				int seatCount = 0; //只有坐席端
				int cusCount = 0;  //只有客户端

				if (hitsArray.size()>0) {
						for (Object v : hitsArray) {	
							JSONObject jsonObject = ((JSONObject)v).getJSONObject("_source");
							String transContent = jsonObject.getString("TRANS_CONTENT");
							String voiceId = jsonObject.getString("VOICE_ID");
							String talkertype1 ="\"talkertype\":\"1\"";
							String talkertype2 ="\"talkertype\":\"2\"";
						
							if (transContent.contains(talkertype1)&&transContent.contains(talkertype2)) {
								
							}else {
								String talkTypeMiss ="";
								//统计客户端和坐席端数量1、坐席 2、客户
								if (transContent.contains(talkertype1)) {
									cusCount++; //包含坐席，客户端+1
									talkTypeMiss = "客户端丢失";
								}else {
									seatCount++; 
									talkTypeMiss ="坐席端丢失";
								}
								
								
								//将单边的流水号写入指定位置
								if (ifWtite) {
									bfWrite.write(voiceId+"           " + talkTypeMiss);
									bfWrite.newLine();
								}
								count++;
							}
						}
					}
					 bfWrite.flush();
					
					System.out.println(getTime+"___"+lteTime+"时间段单边数目:"+count);
					System.out.println(getTime+"___"+lteTime+"坐席端单边数目:"+seatCount);
					System.out.println(getTime+"___"+lteTime+"客户端单边数目:"+cusCount);
			}
			if (outFile!=null) {
				System.out.println("单边录音流水号位置:"+outFile.getAbsolutePath());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {	
				if (bfWrite != null){
		            bfWrite.close();
		        }
			} catch (Exception e2) {
				 e2.printStackTrace();
			}
		}
	}
	
	
	//更新绿瘦字段
	/**
	 * 
	 * @Title: 
	 * @Description:以月为结束单位，以每小时数据为单位更新
	 * @param startTime void
	 */
	
	public static void updatefield(String sourceIndex,String desIndex){
		
		String sourceIP = "172.16.92.75";
		String desIP = "172.16.92.57";
	
		
		String sourceUrl = "http://"+sourceIP+":9200/lvshou_sap"+sourceIndex+"/trans/_search";
		
		
		int[] intArray = {31,30,31,30,31,30,31,31,30,31,30,31}; 
		//每一天
		int month = Integer.parseInt(sourceIndex.substring(sourceIndex.indexOf("-")+1,sourceIndex.indexOf("-")+3));
		//System.out.println(month);
		int dayNum = intArray[month-1];
		
		//循环天数
		for (int i = 0; i < dayNum; i++) {
			String getDay="";
			int conunt= 0;
			//拼出来天数
			if ((i+1)<10) {
				 getDay = sourceIndex + "-0"+(i+1);
			}else {
				 getDay = sourceIndex +"-"+(i+1);
			}
	
			for (int j = 0; j < 24; j++) {
				String getTimeHour  ="";
				String lteTimeHour  ="";
				//拼出来时分秒
				if (j==0) {
					getTimeHour = "00:00:00";
					lteTimeHour = "01:00:00";
				}else if(j==23) {
					getTimeHour = "23:00:01";
					lteTimeHour = "23:59:59";
				}else {
					if (j<10) {
						getTimeHour = "0"+j+":00:01";
					}else {
						getTimeHour = j+":00:01";
					}
					if ((j+1)<10) {
						lteTimeHour = "0"+(j+1)+":00:00";
					}else {
						lteTimeHour = (j+1)+":00:00";
					}
				}
				getTimeHour=getDay+" "+getTimeHour;
				lteTimeHour=getDay+" "+lteTimeHour;
				
				
				//System.out.println(getTimeHour+ "至" + lteTimeHour);
				//correctInfoData
				String query = "{\"_source\":[\"UUID\",\"correctInfoData\"],\"query\":{\"filtered\":{\"filter\":[{\"bool\":{\"must\":[{\"range\":{\"relateData.callTime\":{\"gte\":\""+getTimeHour+"\",\"lte\":\""+lteTimeHour+"\"}}},{\"exists\":{\"field\":\"correctInfoData\"}}]}}]}},\"from\":0,\"size\":50000}";
				//System.out.println("sourceUrl"+sourceUrl);
				//System.out.println(query);
				String result = HTTPMethod.doPostQuery(sourceUrl, query, 30000);
				System.out.println(sourceUrl);
				System.out.println(query);
				
				JSONObject resultObject = JSON.parseObject(result);
				JSONObject hitsObject = resultObject.getJSONObject("hits");
				JSONArray hitsArray = hitsObject.getJSONArray("hits");
				int total = (Integer) hitsObject.get("total");
				//System.out.println(getTimeHour+"___"+lteTimeHour+"时间段数据总量:"+total);
				if (total==0) {
					continue;
				}
				System.out.println("total"+total);
				if (hitsArray.size()>0) {
					for (Object v : hitsArray) {	
						JSONObject jsonObject = ((JSONObject)v).getJSONObject("_source");
						String UUID = jsonObject.getString("UUID");
						JSONObject correctInfoData =  jsonObject.getJSONObject("correctInfoData");
						
						//更新新索引
						if (correctInfoData!=null) {
							String desUrl = "http://"+desIP+":9200/lvshou_sap"+desIndex+"/trans/";;
							desUrl=desUrl+UUID+"/_update";
							//System.out.println("更新地址索引"+desUrl);
							
							
							String updateResult =updateIndexDoc(correctInfoData, desUrl);
							if (updateResult!=null&&!"".equals(updateResult)) {
								System.out.println("更新索引id:"+UUID);
								conunt++;
							}
						}
					}
				}                                                                       	
			}	
			
			System.out.println(getDay+"更新总量"+conunt);
		}	
	}

		
	
	public static  String updateIndexDoc(JSONObject correctInfoData,String desUrl){
		
		 //修改的字段 
		   StringBuffer sb = new StringBuffer();
		   sb.append("{");
		   sb.append("\"doc\":");
		   
		   JSONObject  docObject= new JSONObject();
		   
		   docObject.put("correctInfoData", correctInfoData); 
		   
		   sb.append(docObject.toString());
		   sb.append("}");
	
	
		   String result = HTTPMethod.doPostQuery(desUrl, sb.toString(), 30000);
		  // System.out.println(result);
		
		   return result;
	}
		

	}
	