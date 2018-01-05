package com.test;

import com.alibaba.fastjson.JSONObject;
import com.util.HTTPMethod;

public class GetToken {
	
	public static void main(String[] args) {
		
		String url =  args[0];
		String client_id = args[1];
		String client_secret = args[2];
		
		String tokenUrl = "http://"+url+"/oauth/oauth2/access_token?client_id="+client_id+"&grant_type=client_credentials&client_secret="+client_secret;
		
		System.out.println(tokenUrl);
		String result =HTTPMethod.doGetQuery(tokenUrl);
		
		if (result == null || result.length() == 0) {
			System.out.println("获取token失败");
		}
		JSONObject jsonObject = JSONObject.parseObject(result);
		String ret = jsonObject.getString("ret");
		if (!"0".equals(ret)) {
			System.out.println(("获取token失败：错误码" + ret));
		}
		JSONObject dataObject = jsonObject.getJSONObject("data");
		String accessToken = dataObject.getString("access_token");
		System.out.println(accessToken);
		
		
	}
}
