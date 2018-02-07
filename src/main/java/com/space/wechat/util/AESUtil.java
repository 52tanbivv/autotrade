package com.space.wechat.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESUtil {

	public static final String AES_KEY = "J90a*&j_~!ks^T4j";
	private static final String AES = "AES";

	private static Logger logger = LoggerFactory.getLogger(AESUtil.class);

	public static String AES_KEY_DATA_SAFE = "J90a*&j_~!ks^T4j";

	/**
	 * 加密
	 * 
	 * @param encryptStr
	 * @return
	 */
	private static byte[] encrypt(byte[] src, String key) throws Exception {
		Cipher cipher = Cipher.getInstance(AES);
		SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);
		cipher.init(Cipher.ENCRYPT_MODE, securekey);// 设置密钥和加密形式
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * 
	 * @param decryptStr
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] src, String key) throws Exception {
		Cipher cipher = Cipher.getInstance(AES);
		SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);// 设置加密Key
		cipher.init(Cipher.DECRYPT_MODE, securekey);// 设置密钥和解密形式
		return cipher.doFinal(src);
	}

	/**
	 * 二行制转十六进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 系统重要业务数据解密.
	 * 
	 * @param data
	 * @return
	 */
	public final static String decryptForDataSafe(String data) {
		return decrypt(data, AES_KEY_DATA_SAFE);
	}

	public static void main(String[] args) {
		System.out.println(decrypt("06A9D4233F839878FEEAD2AD2C555575",
				"B12a*&j_~!as^T4j"));
	}

	/**
	 * 系统重要业务数据解密,有长度限制.
	 * 
	 * @param data
	 * @return
	 */
	public final static String decryptLimitForDataSafe(String data,
			int datalength) {
		return decryptLimit(data, AES_KEY_DATA_SAFE, datalength);
	}

	/**
	 * 系统重要业务数据加密.
	 * 
	 * @return
	 * @throws Exception
	 */
	public final static String encryptForDataSafe(String data) {
		return encrypt(data, AES_KEY_DATA_SAFE);
	}

	/**
	 * 系统重要业务数据加密,限制长度.
	 * 
	 * @return
	 * @throws Exception
	 */
	public final static String encryptLimitForDataSafe(String data,
			int datalength) {
		return encryptLimit(data, AES_KEY_DATA_SAFE, datalength);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data, String key) {
		if (StringUtil.isNullOrEmpty(data) || data.length() < 16) {
			return data;
		}
		try {
			return new String(decrypt(hex2byte(data.getBytes()), key));
		} catch (Exception e) {
			logger.error("数据解密失败:data=" + data + " key=" + key + " 错误原因:"
					+ e.getMessage());
			return data;
		}
	}

	public final static String decryptLimit(String data, String key,
			int datalength) {
		try {
			if (StringUtil.isNotEmpty(data) && data.length() > datalength) {
				return new String(decrypt(hex2byte(data.getBytes()), key));
			} else {
				return data;
			}
		} catch (Exception e) {
			logger.error("数据解密失败:data=" + data + " key=" + key + " datalength="
					+ datalength + " 错误原因:" + e.getMessage());
			return data;
		}
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String data, String key) {
		if (StringUtil.isNullOrEmpty(data)) {
			return "";
		}
		try {
			return byte2hex(encrypt(data.getBytes(), key));
		} catch (Exception e) {
			logger.error("数据加密失败:data=" + data + " key=" + key + " 错误原因:"
					+ e.getMessage());
			return data;
		}
	}

	public final static String encryptLimit(String data, String key,
			int datalength) {
		try {
			if (StringUtil.isNotEmpty(data) && data.length() < datalength) {
				return byte2hex(encrypt(data.getBytes(), key));
			} else {
				return data;
			}
		} catch (Exception e) {
			logger.error("数据加密失败:data=" + data + " key=" + key + " datalength="
					+ datalength + " 错误原因:" + e.getMessage());

			return data;
		}
	}

}
