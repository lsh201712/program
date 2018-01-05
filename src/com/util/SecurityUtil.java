package com.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES	加密解密
 * @author lishihuan
 *
 */public class SecurityUtil {
	
    public static String encrypt(String s,String k) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		if (s == null || k == null) {
			throw new IllegalArgumentException();
		}
		SecretKeySpec key = new SecretKeySpec(k.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(s.getBytes());// 加密
		return HexUtil.byte2hex(result);
	}

    public static String decrypt(String s,String k) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		if (s == null || k == null) {		
		throw new IllegalArgumentException();
		}
		SecretKeySpec key = new SecretKeySpec(k.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(HexUtil.hex2byte(s)); // 加密
		return new String(result);
    }

}
