package com.space.wechat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class KeyUtil {
	private static HashMap<String, String> keymap = new HashMap<String, String>() {
		{
			put("0", "7");
			put("1", "0");
			put("2", "3");
			put("3", "5");
			put("4", "1");
			put("5", "7");
			put("6", "3");
			put("7", "7");
			put("8", "8");
			put("9", "1");
			put("A", "4");
			put("B", "7");
			put("C", "0");
			put("D", "3");
			put("E", "5");
			put("F", "7");
			put("G", "0");
			put("H", "8");
			put("I", "6");
			put("J", "0");
			put("K", "8");
			put("L", "9");
			put("M", "0");
			put("N", "8");
			put("O", "0");
			put("P", "2");
			put("Q", "6");
			put("R", "9");
			put("S", "5");
			put("T", "0");
			put("U", "4");
			put("V", "8");
			put("W", "1");
			put("X", "9");
			put("Y", "2");
			put("Z", "5");
			put("a", "9");
			put("b", "2");
			put("c", "9");
			put("d", "4");
			put("e", "9");
			put("f", "6");
			put("g", "9");
			put("h", "4");
			put("i", "1");
			put("j", "3");
			put("k", "6");
			put("l", "2");
			put("m", "9");
			put("n", "5");
			put("o", "1");
			put("p", "9");
			put("q", "4");
			put("r", "2");
			put("s", "5");
			put("t", "3");
			put("u", "4");
			put("v", "1");
			put("w", "3");
			put("x", "6");
			put("y", "2");
			put("z", "6");
		}
	};

	public static void main(String[] args) {
		String xxtype = "01";
		String dlsid = "0123";
		String random = "866489";
		String key = xxtype + dlsid + random;
		String key1 = encryptKey(key);
		String key2 = decryptKey(key1);
		System.out.println("key1:" + key);
		System.out.println("key2:" + key1);
		System.out.println("key3:" + key2);
	}

	/**
	 * 根据参数组装key
	 * 
	 * @param xxtype
	 * @param dlskey
	 * @param randomStr
	 * @return
	 */
	public static String assemblyKey(Integer xxtype, Long dlsid,
			String randomStr) {
		String key = zeroFill(String.valueOf(xxtype), 2)
				+ zeroFill(String.valueOf(dlsid), 4) + randomStr;
		return key;
	}

	/**
	 * key加密
	 * 
	 * @param key
	 * @return
	 */
	public static String encryptKey(String key) {
		char[] keyArr = key.toCharArray();
		List<String> keylist = new ArrayList<String>();
		for (int i = 0; i < keyArr.length; i++) {
			List<String> all = new ArrayList<String>();
			Set set = keymap.entrySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				if (entry.getValue().equals(String.valueOf(keyArr[i]))) {
					all.add((String) entry.getKey());
				}
			}
			keylist.add(all.get((int) (Math.random() * all.size())));
		}
		return StringUtils.join(keylist, "");
	}

	/**
	 * key解密
	 * 
	 * @param key
	 * @return
	 */
	public static String decryptKey(String key) {
		char[] keyArr = key.toCharArray();
		List<String> keylist = new ArrayList<String>();
		for (int i = 0; i < keyArr.length; i++) {
			keylist.add(keymap.get(String.valueOf(keyArr[i])));
		}
		return StringUtils.join(keylist, "");
	}

	/**
	 * 自动将字符串补足到指定位数 ，如1111——》0000001111
	 * 
	 * @param kh
	 * @return
	 */
	public static String zeroFill(String key, Integer num) {
		if (StringUtil.isNotEmpty(key) && key.length() < num) {
			for (int i = num - key.length(); i > 0; i--) {
				key = "0" + key;
			}
		}
		return key;
	}

}
