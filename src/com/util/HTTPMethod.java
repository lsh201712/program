package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

public class HTTPMethod
{
  private static Logger log = Logger.getLogger(HTTPMethod.class);

  public static String doPutQuery(String url, String query, int time)
  {
    StringBuffer stringBuffer = new StringBuffer();
    HttpClient client = new HttpClient();
    PutMethod method = new PutMethod(url);
    method.setRequestHeader("Connection", "close");
    method.setRequestHeader("Content-type", 
      "application/json;charset=UTF-8");
    client.getHttpConnectionManager().getParams()
      .setConnectionTimeout(time);
    method.getParams().setParameter("http.socket.timeout", Integer.valueOf(time));
    try {
      RequestEntity requestEntity = new ByteArrayRequestEntity(
        query.getBytes("UTF-8"), "UTF-8");
      method.setRequestEntity(requestEntity);
    } catch (Exception e) {
      e.printStackTrace();
    }

    int stateCode = 0;
    StopWatch stopWatch = new StopWatch();
    try {
      stopWatch.start();
      stateCode = client.executeMethod(method);
      log.info("==============stateCode" + stateCode + "=======");
    } catch (HttpException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      stopWatch.stop();
      if (stateCode == 200) {
        try {
          BufferedReader reader = new BufferedReader(
            new InputStreamReader(
            method.getResponseBodyAsStream(), "utf-8"));
          String str = "";
          while ((str = reader.readLine()) != null)
            stringBuffer.append(str);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    method.abort();
    try {
      ((SimpleHttpConnectionManager)client
        .getHttpConnectionManager()).shutdown();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stringBuffer.toString();
  }

  public static String doPostQuery(String url, String query, int time)
  {
    HttpClient client = new HttpClient();
    PostMethod method = new PostMethod(url);
    StringBuffer stringBuffer = new StringBuffer();
    try {
      method.setRequestHeader("Connection", "close");
      method.setRequestHeader("Content-type", 
        "application/json;charset=UTF-8");
      client.getHttpConnectionManager().getParams()
        .setConnectionTimeout(time);
      method.getParams().setParameter("http.socket.timeout", Integer.valueOf(time));

      RequestEntity requestEntity = new ByteArrayRequestEntity(
        query.getBytes("UTF-8"), "UTF-8");
      method.setRequestEntity(requestEntity);
    } catch (Exception e) {
      e.printStackTrace();
    }

    int stateCode = 0;
    StopWatch stopWatch = new StopWatch();
    try {
      stopWatch.start();
      stateCode = client.executeMethod(method);
      log.info("==============stateCode" + stateCode + "=======");
    } catch (HttpException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      stopWatch.stop();
      if (stateCode == 200) {
        try {
          BufferedReader reader = new BufferedReader(
            new InputStreamReader(
            method.getResponseBodyAsStream(), "utf-8"));
          String str = "";
          while ((str = reader.readLine()) != null)
            stringBuffer.append(str);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      method.abort();
      try {
        ((SimpleHttpConnectionManager)client
          .getHttpConnectionManager()).shutdown();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return stringBuffer.toString();
  }

  public static String doGetQuery(String url, int time)
  {
    StringBuffer stringBuffer = new StringBuffer();
    HttpClient client = new HttpClient();
    GetMethod method = new GetMethod(url);
    method.setRequestHeader("Connection", "keep-alive");
    method.setRequestHeader(" Accept-Language", "zh-CN,zh;q=0.8");
    method.setRequestHeader(" Accept-Encoding", "gzip, deflate, sdch");
    method.setRequestHeader("Content-type", 
      "application/json;charset=UTF-8");
    client.getHttpConnectionManager().getParams()
      .setConnectionTimeout(time);
    method.getParams().setParameter("http.socket.timeout", Integer.valueOf(time));

    int stateCode = 0;
    StopWatch stopWatch = new StopWatch();
    try {
      stopWatch.start();
      stateCode = client.executeMethod(method);
      log.info("==============stateCode" + stateCode + "=======");
    } catch (HttpException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      stopWatch.stop();
      if (stateCode == 200) {
        try {
          BufferedReader reader = new BufferedReader(
            new InputStreamReader(
            method.getResponseBodyAsStream(), "utf-8"));
          String str = "";
          while ((str = reader.readLine()) != null)
            stringBuffer.append(str);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      method.abort();
      try {
        ((SimpleHttpConnectionManager)client
          .getHttpConnectionManager()).shutdown();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return stringBuffer.toString();
  }

  public static String doDeleteQuery(String url, int time)
    throws HttpException
  {
    StringBuffer stringBuffer = new StringBuffer();
    HttpClient client = new HttpClient();
    DeleteMethod method = new DeleteMethod(url);
    method.setRequestHeader("Connection", "close");
    method.setRequestHeader("Content-type", 
      "application/json;charset=UTF-8");
    client.getHttpConnectionManager().getParams()
      .setConnectionTimeout(time);
    method.getParams().setParameter("http.socket.timeout", Integer.valueOf(time));

    int stateCode = 0;
    StopWatch stopWatch = new StopWatch();
    try {
      stopWatch.start();
      stateCode = client.executeMethod(method);
      log.info("==============stateCode" + stateCode + "=======");
    } catch (HttpException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      stopWatch.stop();
      if (stateCode == 200) {
        try {
          BufferedReader reader = new BufferedReader(
            new InputStreamReader(
            method.getResponseBodyAsStream(), "utf-8"));
          String str = "";
          while ((str = reader.readLine()) != null)
            stringBuffer.append(str);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      method.abort();
      try {
        ((SimpleHttpConnectionManager)client
          .getHttpConnectionManager()).shutdown();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return stringBuffer.toString();
  }

  public static String doPostXml(String url, String query, int time)
    throws HttpException
  {
    StringBuffer stringBuffer = new StringBuffer();
    HttpClient client = new HttpClient();
    PostMethod method = new PostMethod(url);
    method.setRequestHeader("Connection", "close");
    method.setRequestHeader("Content-type", 
      "text/xml;charset=UTF-8");
    client.getHttpConnectionManager().getParams()
      .setConnectionTimeout(time);
    method.getParams().setParameter("http.socket.timeout", Integer.valueOf(time));
    try { RequestEntity requestEntity = new ByteArrayRequestEntity(
        query.getBytes("UTF-8"), "UTF-8");
      method.setRequestEntity(requestEntity);
    } catch (Exception e) {
      e.printStackTrace();
    }

    int stateCode = 0;
    StopWatch stopWatch = new StopWatch();
    try {
      stopWatch.start();
      stateCode = client.executeMethod(method);
      log.info("==============stateCode" + stateCode + "=======");
    } catch (HttpException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace(); } finally {
      stopWatch.stop();
      if (stateCode == 200) {
        try {
          BufferedReader reader = new BufferedReader(
            new InputStreamReader(
            method.getResponseBodyAsStream()));
          String str = "";
          while ((str = reader.readLine()) != null)
            stringBuffer.append(str);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      method.abort();
      try { ((SimpleHttpConnectionManager)client
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
}