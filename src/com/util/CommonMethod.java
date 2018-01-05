package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class CommonMethod {

	/**
	 * 将文件转成base64 字符串
	 * 
	 * @param path文件路径
	 * @return String
	 * @throws Exception
	 */
	public static String encodeBase64File(String path) throws Exception {
		File file = new File(path);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return new BASE64Encoder().encode(buffer);

	}

	/**
	 * 将base64字符解码保存文件
	 * 
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */
	public static void decoderBase64File(String base64Code, String targetPath)
			throws Exception {
		byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();

	}

	/**
	 * 将base64字符保存文本文件
	 * 
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */
	public static void toFile(String base64Code, String targetPath)
			throws Exception {

		byte[] buffer = base64Code.getBytes();
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}

	
	/**
	 * @Title: doPostQuery
	 * @Description: 发送post请求
	 * @param url
	 * @param query
	 * @param @throws HttpException
	 * @return String
	 * @throws
	 */
	public static String doPostQuery(String url, String query)
			throws HttpException {
		String result = null;
		result = doPostQuery(url, query, 10000,false);
		return result;
	}
	
	public static String doPostQuery(String url, String query, int time)
			throws HttpException {
		String result = null;
		result = doPostQuery(url, query, time,false);
		return result;
	}

	public static String doPostQuery(String url, String query, int time,Boolean isReturnJson)
			throws HttpException {
		StringBuffer stringBuffer = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Connection", "close");
		method.setRequestHeader("Content-type",
				"application/json;charset=UTF-8");
		if(isReturnJson){
			method.setRequestHeader("Accept",
					"application/json;charset=UTF-8");
		}
		client.getHttpConnectionManager().getParams()
				.setConnectionTimeout(time);
		method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, time);
		try {
			RequestEntity requestEntity = new ByteArrayRequestEntity(
					query.getBytes("UTF-8"), "UTF-8");
			method.setRequestEntity(requestEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 发出请求
		int stateCode = 0;
		StopWatch stopWatch = new StopWatch();
		try {
			stopWatch.start();
			stateCode = client.executeMethod(method);
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stopWatch.stop();
			if (stateCode == HttpStatus.SC_OK) {
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(
									method.getResponseBodyAsStream()));
					String str = "";
					while ((str = reader.readLine()) != null) {
						stringBuffer.append(str);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			method.abort();
			method.releaseConnection();
			/*
			try {
				((SimpleHttpConnectionManager) client
						.getHttpConnectionManager()).shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
		}
		return stringBuffer.toString();
	}
	
	public static String doPostXml(String url, String query, int time)
            throws HttpException {
        StringBuffer stringBuffer = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.setRequestHeader("Connection", "close");
        method.setRequestHeader("Content-type",
                "text/xml;charset=UTF-8");
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(time);
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, time);
        try {
            RequestEntity requestEntity = new ByteArrayRequestEntity(
                    query.getBytes("UTF-8"), "UTF-8");
            method.setRequestEntity(requestEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
     // 发出请求
        int stateCode = 0;
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            stateCode = client.executeMethod(method);
        
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stopWatch.stop();
            if (stateCode == HttpStatus.SC_OK) {
                try {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    method.getResponseBodyAsStream()));
                    String str = "";
                    while ((str = reader.readLine()) != null) {
                        stringBuffer.append(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            method.abort();
            try {
                ((SimpleHttpConnectionManager) client
                        .getHttpConnectionManager()).shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String doGetQuery(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * @Title: hit 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param a(被匹配的长字符串)
	 * @param @param b(匹配的短字符串)
	 * @return int
	 * @throws
	 */
	public static int getCountStr(String a, String b) {
		if (a.length() < b.length()) {
			return 0;
		}
		char[] a_t = a.toCharArray();
		char[] b_t = b.toCharArray();
		int count = 0, temp = 0, j = 0;

		for (int i = 0; i < a_t.length; i++) {
			// 保证一个连续的字符串 b 跟 a中某段相匹配
			if (a_t[i] == b_t[j] && j < b_t.length) {
				temp++;
				j++;
				// 此时连续的字符串 b 跟 已跟 a 中某段相匹配
				if (temp == b_t.length) {
					count++;
					temp = 0;
					j = 0;
				}
			}
			// 只要有一个字符不匹配，temp计数从来
			else {
				temp = 0;
				j = 0;
			}
		}

		return count;
	}
	
	
	
	/**
	 * @param list 
	 * @Title: readTxt 
	 * @Description: java读取txt文件 
	 * @param fileName
	 * @param @return
	 * @return List<String>
	 * @throws
	 */
	public static List<String> readTxt(String fileName, List<String> list){  
		try {
	        File file=new File(fileName);
	        InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"utf-8");
	        BufferedReader br=new BufferedReader(isr);
	        String line = "";
	        while ((line=br.readLine())!=null) {
	        	list.add(line.trim());
	        }
	        br.close();
	        isr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> readContent(String fileName, List<String> list){  
        try {
            File file=new File(fileName);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"utf-8");
            BufferedReader br=new BufferedReader(isr);
            String line = "";
            FileWriter fw = new FileWriter("d:\\125.txt");   
            while ((line=br.readLine())!=null) {
                if(line.trim().length()==0){
                    continue;
                }
                try {
                  JSONArray array = JSONArray.parseArray(line);
                  JSONArray contentArray = array.getJSONArray(0);
                  JSONObject jsonObject = contentArray.getJSONObject(0);
                  String content = jsonObject.getString("content");
                  String[] split = content.split("\\(");
                  contentArray.clear();
                  array.clear();
                  long time = 45628700l;
                  for(int i=split.length-1;i>=0;i--){
                      String word = split[i];
                      if(word!=null&&word.length()>0&&word.indexOf(")")>0){
                          JSONObject wordObject = new JSONObject();
                          wordObject.put("timestamp", 1451970830400L);
                          String[] wordSplit = word.split("\\)");
                          String words = wordSplit[1];
                          if(words.indexOf("time=")<0){
                              words+=";time="+(time-10)+" "+time;
                              time = time - 20;
                          }
                          wordObject.put("content", words.replace(",", ""));
                          if(word.startsWith("custom")){
                              wordObject.put("talkertype", "2");
                              wordObject.put("id", "3350");
                          }else{
                              wordObject.put("talkertype", "1");
                              wordObject.put("id", "3351");
                          }
                          wordObject.put("time", "13:13:50");
                          wordObject.put("userphone", "12312312");
                          wordObject.put("cmd", "");
                          wordObject.put("answer", "");
                          wordObject.put("answerType", "3");
                          wordObject.put("channelId", "0f02bb6ed2bf11e5");
                          contentArray.add(wordObject);
                      }
                  }
                  array.add(contentArray);
                  list.add(array.toJSONString());
                  fw.append(array.toJSONString()+"\n"); 
                  fw.flush();
                 
                } catch (Exception e) {
                   e.printStackTrace();
                   System.out.println(line);
                }
            }
            fw.close();
            br.close();
            isr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	/*public static void writeTxt(String fileName, String content){  
        try {
            File file=new File(fileName);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"utf-8");
            BufferedReader br=new BufferedReader(isr);
            String line = "";
            while ((line=br.readLine())!=null) {
                list.add(line);
            }
            br.close();
            isr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }*/
		
	/**
	 * put
	 * @param url
	 * @param query
	 * @param time
	 * @return
	 */
	public static String doPutQuery(String url, String query, int time){
		StringBuffer stringBuffer = new StringBuffer();
		HttpClient client = new HttpClient();
		PutMethod method = new PutMethod(url);
		method.setRequestHeader("Connection", "close");
		method.setRequestHeader("Content-type",
				"application/json;charset=UTF-8");
		client.getHttpConnectionManager().getParams()
				.setConnectionTimeout(time);
		method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, time);
		try {
			RequestEntity requestEntity = new ByteArrayRequestEntity(
					query.getBytes("UTF-8"), "UTF-8");
			method.setRequestEntity(requestEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 发出请求
		int stateCode = 0;
		StopWatch stopWatch = new StopWatch();
		try {
			stopWatch.start();
			stateCode = client.executeMethod(method);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stopWatch.stop();
			if (stateCode == HttpStatus.SC_OK) {
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(
									method.getResponseBodyAsStream(), "utf-8"));
					String str = "";
					while ((str = reader.readLine()) != null) {
						stringBuffer.append(str);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		method.abort();
		try {
			((SimpleHttpConnectionManager) client
					.getHttpConnectionManager()).shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	public static String sendPost(String url,Map<String, String> params){
		URL u = null;  
        HttpURLConnection con = null;  
        StringBuffer sb = new StringBuffer();  
        if (params != null) {  
            for (Entry<String, String> e : params.entrySet()) {  
                sb.append(e.getKey());  
                sb.append("=");  
                sb.append(e.getValue());  
                sb.append("&");  
            }  
            sb.substring(0, sb.length() - 1);  
        }  
        System.out.println("send_url:" + url);  
        System.out.println("send_data:" + sb.toString());  
        try {  
            u = new URL(url);  
            con = (HttpURLConnection) u.openConnection();  
            con.setRequestMethod("POST");  
            con.setDoOutput(true);  
            con.setDoInput(true);  
            con.setUseCaches(false);  
            con.setConnectTimeout(10000);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");  
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");  
            osw.write(sb.toString());  
            osw.flush();  
            osw.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (con != null) {  
                con.disconnect();  
            }  
        }  
        StringBuffer buffer = new StringBuffer();  
        try {  
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                buffer.append(temp);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return buffer.toString();  
	}
	
	public static String secToTime(int time) {  
        String timeStr = null;  
        int hour = 0;  
        int minute = 0;  
        int second = 0;  
        if (time <= 0)  
            return "00:00";  
        else {  
            minute = time / 60;  
            if (minute < 60) {  
                second = time % 60;  
                timeStr = unitFormat(minute) + ":" + unitFormat(second);  
            } else {  
                hour = minute / 60;  
                if (hour > 99)  
                    return "99:59:59";  
                minute = minute % 60;  
                second = time - hour * 3600 - minute * 60;  
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
            }  
        }  
        return timeStr;  
    }  
  
    public static String unitFormat(int i) {  
        String retStr = null;  
        if (i >= 0 && i < 10)  
            retStr = "0" + Integer.toString(i);  
        else  
            retStr = "" + i;  
        return retStr;  
    }  
	
    public static String getFileHeader(String filePath) {  
        FileInputStream is = null;  
        String value = null;  
        try {  
            is = new FileInputStream(filePath);  
            byte[] b = new byte[5];  
            /* 
             * int read() 从此输入流中读取一个数据字节。 int read(byte[] b) 从此输入流中将最多 b.length 
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len) 
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。 
             */  
            is.read(b, 0, b.length);  
            value = bytesToHexString(b);
        	} catch (Exception e) {  
        } finally {  
            if (null != is) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                }  
            }  
        }  
        return value;  
    }  
	
	private static String bytesToHexString(byte[] src) {  
        StringBuilder builder = new StringBuilder();  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        String hv;  
        for (int i = 0; i < src.length; i++) {  
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写  
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();  
            if (hv.length() < 2) {  
                builder.append(0);  
            }  
            builder.append(hv);  
        }  
        System.out.println(builder.toString());  
        return builder.toString();  
    }  
}
