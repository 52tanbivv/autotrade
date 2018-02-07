package com.space.wechat.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * User: rizenguo Date: 2014/10/29 Time: 15:23
 */
public class SignatureUtil {

	private final static String charset = "UTF-8";

	private static Logger log = Logger.getLogger(SignatureUtil.class.getName());

	/**
	 * 签名算法 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。 sign
	 * 
	 * @param o
	 *            要参与签名的数据对象
	 * @return 签名
	 * @throws IllegalAccessException
	 */
	public static String getSign(Object o, String partnerkey)
			throws IllegalAccessException {
		ArrayList<String> list = new ArrayList<String>();
		Class cls = o.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.get(o) != null && f.get(o) != "") {
				list.add(f.getName() + "=" + f.get(o) + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + partnerkey;
		// log.info("Sign Before MD5:" + result);
		result = MD5Util.MD5Encode(result, charset).toUpperCase();
		// log.info("Sign Result:" + result);
		return result;
	}

	public static String getSign(Map<String, Object> map, String partnerkey) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != "") {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + partnerkey;
		// log.info("Sign Before MD5:" + result);
		result = MD5Util.MD5Encode(result, charset).toUpperCase();
		// log.info("Sign Result:" + result);
		return result;
	}

	/**
	 * 从API返回的XML数据里面重新计算一次签名
	 * 
	 * @param responseString
	 *            API返回的XML数据
	 * @return 新鲜出炉的签名
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static String getSignFromResponseString(String responseString,
			String partnerkey) throws IOException, SAXException,
			ParserConfigurationException {
		Map<String, Object> map = XMLParser.getMapFromXML(responseString);
		// 清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
		map.put("sign", "");
		// 将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
		return SignatureUtil.getSign(map, partnerkey);
	}

	/**
	 * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
	 * 
	 * @param responseString
	 *            API返回的XML数据字符串
	 * @return API签名是否合法
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static boolean checkIsSignValidFromResponseString(
			String responseString, String partnerkey)
			throws ParserConfigurationException, IOException, SAXException {

		Map<String, Object> map = XMLParser.getMapFromXML(responseString);
		log.info(map.toString());

		String signFromAPIResponse = map.get("sign").toString();
		if (signFromAPIResponse == "" || signFromAPIResponse == null) {
			log.info("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
			return false;
		}
		log.info("服务器回包里面的签名是:" + signFromAPIResponse);
		// 清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
		map.put("sign", "");
		// 将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
		String signForAPIResponse = SignatureUtil.getSign(map, partnerkey);

		if (!signForAPIResponse.equals(signFromAPIResponse)) {
			// 签名验不过，表示这个API返回的数据有可能已经被篡改了
			log.info("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
			return false;
		}
		log.info("恭喜，API返回的数据签名验证通过!!!");
		return true;
	}

}
