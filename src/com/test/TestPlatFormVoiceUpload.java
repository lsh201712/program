package com.test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpException;

import com.util.HTTPMethod;
/**
 * 通知平台上传录音
 * @author lishihuan
 *
 */

public class TestPlatFormVoiceUpload
{
  public static void main(String[] args)
  {
    String callIds = args[0];

    String voiceLoadPath = args[1];

    String webserviceUrl = "http://19.20.10.2:8081/FPSWebservice/services/querySpeech";

    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String loadDir = format.format(date);

    String localPlatForm = "XQD-CCOD";

    String param = "";
    if ("XQD-ZX".equals(localPlatForm)) {
      param = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:vie=\"http://vie.services.com\"> <soapenv:Body><vie:QuerySpeech> <vie:paramsXml>&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?>&lt;request>&lt;queryType>batch&lt;/queryType>&lt;param>&lt;savePath>" + 
        voiceLoadPath + 
        loadDir + 
        File.separator + 
        "&lt;/savePath>&lt;idList>" + 
        callIds + 
        "&lt;/idList>&lt;/param>&lt;/request></vie:paramsXml> " + 
        "</vie:QuerySpeech> " + 
        "</soapenv:Body> " + 
        "</soapenv:Envelope>";
    }
    else {
      param = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:vie=\"http://vie.services.com\"> <soapenv:Body><vie:QuerySpeech> <vie:param>&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?>&lt;request>&lt;queryType>batch&lt;/queryType>&lt;param>&lt;savePath>" + 
        voiceLoadPath + 
        loadDir + 
        File.separator + 
        "&lt;/savePath>&lt;idList>" + 
        callIds + 
        "&lt;/idList>&lt;/param>&lt;/request></vie:param> " + 
        "</vie:QuerySpeech> " + 
        "</soapenv:Body> " + 
        "</soapenv:Envelope>";
    }
    try
    {
      String result = HTTPMethod.doPostXml(webserviceUrl, param, 20000);
      System.out.println("webservice return：" + result);
      if ((result != null) && (result.length() > 0)) {
        String code = result.substring(
          result.indexOf("functionResult") + 15, 
          result.indexOf("functionResult") + 16);
        if ("0".equals(code))
          System.out.println("webservice 返回：" + result);
      }
    }
    catch (HttpException e) {
      e.printStackTrace();
    }
  }
}