package com.space.wechat.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class HMACUtil {


	private HMACUtil() {
	}

	/**
	 * 
	 * Description:生成HMAC摘要码
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @throws Exception
	 * @return String
	 */
	public static String encryptHMAC(String data, String key) {

		try {
			SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			byte[] returnByte = mac.doFinal(data.getBytes());
			return byte2Hex(returnByte);
		} catch (Exception e) {
			return null;
		}
	}

	private static String byte2Hex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10) {
				strbuf.append("0");
			}
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}
}
