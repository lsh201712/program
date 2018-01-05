package com.test;

import com.alibaba.fastjson.JSONObject;
import com.util.HTTPMethod;

public class TestHttpRequst {

	public static void main(String[] args) {
			try {
				String agentId ="100";
				String callId ="";
				String callLength ="0";
				String platForm ="XQD-SHW";
				
				
				String result = HTTPMethod.doGetQuery("http://localhost:8080/pingan_cti/sendMessage/getSeatMessage?agentId="+agentId+
						"&callId="+callId+"&callLength="+callLength+"&platForm="+platForm);
				
			 JSONObject.parse(result);
				System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}


	}

}
