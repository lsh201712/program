package com.sha2;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class HmacSHA1Utils {
	final static String HMAC_SHA1 = "HmacSHA1";
	// 字符集
    final static String CHARSET_NAME = "UTF-8";
	/**
	 * 生成签名数据
	 * 
	 * @param data 待加密的数据
	 * @param key 加密使用的key
	 * 
	 * @return 生成Base64编码的字符串
	 * 
	 */
	public static String getSignature(String toSign, String key) {
		try {
			return getSignature(toSign.getBytes(CHARSET_NAME), key.getBytes(CHARSET_NAME));
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 生成签名数据
	 * 
	 * @param data 待加密的数据
	 * @param key 加密使用的key
	 * 
	 * @return 生成Base64编码的字符串
	 * 
	 */
	public static String getSignature(byte[] toSign, byte[] key) {
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
			Mac mac = Mac.getInstance(HMAC_SHA1);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(toSign);
			rawHmac = Base64.encodeBase64(rawHmac);
			return new String(rawHmac, CHARSET_NAME);
		}
		catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch (IllegalStateException e) {
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		String nonce = "463328";
		String timestamp = "1513137737250";
		String key = "Wbtg7OuXWyecgmi8";
		
		String toSign = timestamp + nonce;
		System.out.println("toSign: " + toSign);
		
		String signature = getSignature(toSign, key);
		System.out.println("signature: " + signature);
	}
}
