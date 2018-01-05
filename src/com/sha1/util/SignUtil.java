package com.sha1.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

/**
 * 加密流程如下：
 * 
 * 1. 将timestamp、nonce、appKey 三个参数进行字典排序
 * 2. 将三个参数字符串拼接成一个字符串进行sha1加密 生成signature
 *  
 * @author ZHANGYANYAN910
 * @date 2017-5-4
 */
public class SignUtil {

	/**
	 * 生成签名
	 * 
	 * @param s
	 * @return
	 */
	public static String makeSign(String s) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(s.getBytes());
			byte[] messageDigest = digest.digest();
			return HexUtil.byte2hex(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			return StringUtils.EMPTY;
		}
	}

	/**
	 * 获取签名
	 * 
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @param appKey
	 *            app密钥
	 * @return
	 */
	public static String getSignature(String timestamp, String nonce,
			String appKey) {
		if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce)
				|| StringUtils.isBlank(appKey)) {
			return StringUtils.EMPTY;
		}
		String[] paramArr = { timestamp, nonce, appKey };
		Arrays.sort(paramArr);
		return makeSign(StringUtils.join(paramArr));
	}

	/**
	 * 生成nonce字符串 - 6位长度
	 * 
	 * @return
	 */
	public static String genNonce() {
		SecureRandom secureRandom = null;
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			secureRandom = new SecureRandom();
		}
		Integer a = secureRandom.nextInt(999999);
		if (a < 100000) {
			a += 100000;
		}
		return String.valueOf(a);
	}


}
