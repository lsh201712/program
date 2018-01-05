package com.sha1;

import java.io.UnsupportedEncodingException;

import com.sha1.util.HexUtil;
import com.sha1.util.SignUtil;



/**
 * umap-lite系统安全认证
 * 
 * signature加密流程：
 * 1. 将timestamp、nonce、appKey 三个参数进行字典排序
 * 2. 将三个参数字符串拼接成一个字符串进行sha1加密 生成signature
 * 
 * 备注： 
 * 1. timestamp有效时长为1小时
 * 2. nonce为随机6位长度字符，每个nonce串只使用一次
 * 3. appKey为系统密钥，环境不同密钥不同
 * 
 * @author ZHANGYANYAN910
 * @date 2017-5-18
 */
public class AuthMain {
	
	public static void main(String[] args) {
		String appId = "BC_HYC"; //业务系统ID
		String um = "TEST"; //当前坐席UM
		String appKey = "paic1234"; // 环境不同对应的appKey不同
		
		String nonce = SignUtil.genNonce();
		String timestamp = Long.toString(System.currentTimeMillis());
		String signature = SignUtil.getSignature(timestamp, nonce, appKey);
		System.out.println("签名串=" + signature);
		
		System.out.println("前端umaplite.min.js开发环境路径 : ");
		String jsUrl = "http://iqsh-d9435:7001/umap-lite/rel/umaplite.min.js";
		System.out.println(jsUrl);
		
		System.out.println("需要拼接的认证参数 : ");
		String reqParam = "?appId="+appId+"&um="+um+"&signature="+signature+"&timestamp="+timestamp+"&nonce="+nonce;
		System.out.println(reqParam);
		
		System.out.println("业务系统访问的完整路径 : ");
		System.out.println(jsUrl+reqParam);
		
	/*	byte[] hex2byte = HexUtil.hex2byte(signature);
		StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < hex2byte.length; i++) {  
            sb.append(byteToHexString(hex2byte[i]));  
        }  
        System.out.println(sb.toString());*/
		//295AF6D0828436CD83B6D5F06EB531A5F6B5D48A
		String te = SignUtil.getSignature("1513073220172", "081797", "Wbtg7OuXWyecgmi8");
		System.out.println(te);
		
		
	}
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",  
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };  
	
	 private static String byteToHexString(byte b) {  
	        int ret = b;  
	        //System.out.println("ret = " + ret);  
	        if (ret < 0) {  
	            ret += 256;  
	        }  
	        int m = ret / 16;  
	        int n = ret % 16;  
	        return hexDigits[m] + hexDigits[n];  
	    }  
}
