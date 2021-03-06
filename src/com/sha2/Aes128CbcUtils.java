package com.sha2;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Aes128CbcUtils {
    // 算法名称
	final static String KEY_ALGORITHM = "AES";
	// 加解密算法/模式/填充方式
	final static String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	// 字符集
    final static String CHARSET_NAME = "UTF-8";
    // 填充矢量
    final static byte[] iv = {0x30, 0x31, 0x30, 0x32, 0x30, 0x33, 0x30, 0x34, 0x30, 0x35, 0x30, 0x36, 0x30, 0x37, 0x30, 0x38};
    //final static byte[] iv = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

	private static Key getKey(byte[] keyBytes) {
		// 如果密钥不足16位，那么就补足
		int base = 16;
		
		if ((keyBytes.length % base) != 0) {
			int groups = (keyBytes.length / base) + ((keyBytes.length % base) != 0 ? 1 : 0);
			byte[] temp = new byte[groups * base];
			Arrays.fill(temp, (byte) 0);
			System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
			keyBytes = temp;
		}
		
		// 转化成JAVA的密钥格式
		return new SecretKeySpec(keyBytes, KEY_ALGORITHM);
	}

	/**
	 * 加密方法
	 * 
	 * @param content 要加密的字符串
	 * @param key 加密密钥
	 * @return 
	 */
	public static String encrypt(String content, String key) {
		try {
			byte[] enc = encrypt(content.getBytes(CHARSET_NAME), key.getBytes(CHARSET_NAME));
			String base64Str = Base64.encodeBase64String(enc);
			return new String(base64Str.getBytes(), CHARSET_NAME);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 加密方法
	 * 
	 * @param content 要加密的字符串
	 * @param keyBytes 加密密钥
	 * @return
	 */
	public static byte[] encrypt(byte[] content, byte[] keyBytes) {
		byte[] encryptedText = null;
		
		try {
			Key key = getKey(keyBytes);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
			encryptedText = cipher.doFinal(content);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedText;
	}
	
	/**
	 * 解密方法
	 * 
	 * @param encryptedData 要解密的字符串
	 * @param keyBytes 解密密钥
	 * @return
	 */
	public static String decrypt(String encryptedData, String key) {
		try {
			byte[] enc = encryptedData.getBytes(CHARSET_NAME);
			enc = Base64.decodeBase64(enc);
			byte[] dec = decrypt(enc, key.getBytes(CHARSET_NAME));
			return new String(dec, CHARSET_NAME);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 解密方法
	 * 
	 * @param encryptedData 要解密的字符串
	 * @param keyBytes 解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes) {
		byte[] encryptedText = null;
		
		try {
			Key key = getKey(keyBytes);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			encryptedText = cipher.doFinal(encryptedData);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return encryptedText;
	}
	
	public static void main(String[] args) throws Exception {
		/*//加解密 密钥
  		byte[] keybytes = "paic1234".getBytes();
  		String content = "a=1&b=2&c=3";
  		// 加密字符串
  		System.out.println("加密前的：" + content);
  		System.out.println("加密密钥：" + new String(keybytes));
  		// 加密方法
  		byte[] enc = Aes128CbcUtils.encrypt(content.getBytes(), keybytes);
  		System.out.println("加密后的内容：" + new String(Hex.encode(enc)));
  		// 解密方法
  		byte[] dec = Aes128CbcUtils.decrypt(enc, keybytes);
  		System.out.println("解密后的内容：" + new String(dec));*/
  
		// 加解密 密钥
		String key = "83519aa6d30ecdc3";// "paic1234";
		long timestamp = System.currentTimeMillis();
		
		long nonce = SecureRandom.getInstance("SHA1PRNG").nextInt(100000);
		String toSign = timestamp+""+nonce;
		System.out.println("signature：" + toSign);
		String signature = HmacSHA1Utils.getSignature(toSign, key);
		String content = "timestamp="+timestamp+"&nonce="+nonce+"&umId=LIUKAIHUA001"+"&signature="+signature;
	    // 加密字符串
		System.out.println("加密前的：" + content);
		System.out.println("加密密钥：" + key);
		// 加密方法
		String enc = Aes128CbcUtils.encrypt(content, key);
		System.out.println("加密后的内容：" + enc);
		//URL encode
		enc = URLEncoder.encode(enc, CHARSET_NAME);
		System.out.println("URL encode：" + enc);
		//URL decode
		String dec = URLDecoder.decode("VKW2MKAOtjG%2B6uY5nF4M2fvDSWmOz4nOZ4LqTSJHs6mD5WrIPR1IpY9%2F59MobxuS12N6tE4094jd%0D%0AyuebKvYlQq9Q%2BXJ8ay5Sq0MWGRkz4fcF%2Fk0NdEPWLOMS8ufk1xfCCUIVt4aZy1%2B3dhOEoBcGNjUH%0D%0AnJEeDT0BiJHQ7A%2F2gL6emcojvm184eGYNX0PKGge%0D%0A", CHARSET_NAME);
		System.out.println("URL decode：" + dec);
		
		dec = "VKW2MKAOtjG+6uY5nF4M2QqJFCupGkUmHRQZUNUx1UOl86Jd9fCbNs0HtrSybGDRrTcTWjj16tuWhbuAXaowIptSeMXtBnV+J0PawtykyNpN3nvKBULHOW1nguHyUbXxRM9tibMIr2WC94X+Gzlw/HczSxEAFLGphD4uwYA1HLqgJc0nwWb0vjtI9rkGnDSY";
		System.out.println("decode too =" + URLDecoder.decode(dec, CHARSET_NAME));
		// 解密方法
		dec = Aes128CbcUtils.decrypt(dec, key);
		System.out.println("解密后的内容：" + dec);
	}
}
