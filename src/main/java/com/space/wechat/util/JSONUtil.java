package com.space.wechat.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONUtil {
	private static final String[] BASIC_TYPE = { "java.lang.Integer",
			"java.lang.Long", "java.lang.Float", "java.lang.Double",
			"java.lang.Boolean", "java.lang.String", "java.math.BigDecimal" };
	private static final String VALUE = "VALUE";
	private static final String TYPE = "TYPE";
	private static final String KEY = "KEY";
	private static final String COUNT_PROPERTIES = "COUNT_PROPERTIES";
	private static final String COUNT_ARRAY = "COUNT_ARRAY";

	private List<String> path = new ArrayList<String>();
	private String type;
	private String expectedValue;
	private String actualValue;
	private int arrayIndex = 0;

	public static void main(String[] args) {
		String a = "{    \"list\": [        {            \"address\": \"食堂\",            \"bjid\": 2,            \"bjname\": \"托二班\",            \"campusid\": \"196\",            \"campusname\": \"光海微幼通展示平台\",            \"date\": \"2015-03-14 12:23:17\",            \"id\": \"2\",            \"money\": 100,            \"stuid\": 2,            \"stuname\": \"John\",            \"teacherid\": \"\",            \"teachername\": \"\",            \"type\": 1,            \"isbind\":false,            \"isservice\":true,            \"ispush\":false        },        {            \"address\": \"食堂\",            \"bjid\": 1,            \"bjname\": \"托一班\",            \"campusid\": \"196\",            \"campusname\": \"光海微幼通展示平台\",            \"date\": \"2015-03-13 12:23:17\",            \"id\": \"3\",            \"money\": 90,            \"stuid\": 3,            \"stuname\": \"Tom\",            \"teacherid\": \"\",            \"teachername\": \"\",            \"type\": 1,            \"isbind\":false,            \"isservice\":true,            \"ispush\":false        },        {            \"address\": \"食堂\",            \"bjid\": 2,            \"bjname\": \"托二班\",            \"campusid\": \"196\",            \"campusname\": \"光海微幼通展示平台\",            \"date\": \"2015-03-12 12:23:17\",            \"id\": \"4\",            \"money\": 500,            \"stuid\": 2,            \"stuname\": \"John\",            \"teacherid\": \"\",            \"teachername\": \"\",            \"type\": 2,            \"isbind\":false,            \"isservice\":true,            \"ispush\":false        },        {            \"address\": \"小卖部\",            \"bjid\": 2,            \"bjname\": \"托二班\",            \"campusid\": \"196\",            \"campusname\": \"光海微幼通展示平台\",            \"date\": \"2015-03-11 12:23:17\",            \"id\": \"5\",            \"money\": 30,            \"stuid\": 2,            \"stuname\": \"John\",            \"teacherid\": \"\",            \"teachername\": \"\",            \"type\": 1,            \"isbind\":false,            \"isservice\":true,            \"ispush\":false        }    ],    \"total\": 4}";
		String b = "{    \"list\": [        {            \"address\": \"食堂\",            \"bjid\": 2,            \"bjname\": \"托二班\",            \"campusid\": \"196\",            \"campusname\": \"光海微幼通展示平台\",            \"date\": \"2015-03-14 12:23:17\",            \"id\": \"2\",            \"isbind\": true,            \"ispush\": false,            \"isservice\": true,            \"money\": 100,            \"stuid\": 2,            \"stuname\": \"John\",            \"teacherid\": 0,            \"teachername\": \"\",            \"type\": 1        },        {            \"address\": \"食堂\",            \"bjid\": 1,            \"bjname\": \"托一班\",            \"campusid\": \"196\",            \"campusname\": \"光海微幼通展示平台\",            \"date\": \"2015-03-13 12:23:17\",            \"id\": \"3\",            \"isbind\": false,            \"ispush\": false,            \"isservice\": true,            \"money\": 90,            \"stuid\": 3,            \"stuname\": \"Tom\",            \"teacherid\": 0,            \"teachername\": \"\",            \"type\": 1        },        {            \"address\": \"食堂\",            \"bjid\": 2,            \"bjname\": \"托二班\",            \"campusid\": \"196\",            \"campusname\": \"光海微幼通展示平台\",            \"date\": \"2015-03-12 12:23:17\",            \"id\": \"4\",            \"isbind\": true,            \"ispush\": false,            \"isservice\": true,            \"money\": 500,            \"stuid\": 2,            \"stuname\": \"John\",            \"teacherid\": 0,            \"teachername\": \"\",            \"type\": 2        },        {            \"address\": \"小卖部\",            \"bjid\": 2,            \"bjname\": \"托二班\",            \"campusid\": \"196\",            \"campusname\": \"光海微幼通展示平台\",            \"date\": \"2015-03-11 12:23:17\",            \"id\": \"5\",            \"isbind\": true,            \"ispush\": false,            \"isservice\": true,            \"money\": 30,            \"stuid\": 2,            \"stuname\": \"John\",            \"teacherid\": 0,            \"teachername\": \"\",            \"type\": 1        }    ],    \"total\": 4}";
		String c = "a";
		String d = "['a','b','c']";
		String e = null;
		System.out.println(new JSONUtil().compare(a, b));
		String x = "[1,2,3]";
		String y = "[1,2,3.0]";
		String z = "f,,,f,,";
		// System.out.println(new JSONUtil().compare(x, y));
		System.out.println(new JSONUtil().compare(d, c));
		// String r2 = new JSONUtil().compare(c, d);
		// String r3 = new JSONUtil().compare(c, e);
		String g = "1";
		String type1 = "java.lang.String";
		if (g.getClass().getName().equals(type1)) {
			System.out.println(g.getClass().getName());
		}

	}

	/**
	 * 如果是jsonObject或jsonArray 则使用json做比较； 如果不是则使用 字符串equals比较
	 * 
	 * @param expectedJson
	 * @param actualJson
	 * @return null:相同 其他：错误信息
	 */
	public String compare(String expectedJson, String actualJson) {
		if (expectedJson == null) {
			return "error:预期值是null";
		}
		Boolean result = null;
		try {
			expectedJson = expectedJson.replaceAll("\t|	|	", "");
			actualJson = actualJson.replaceAll("\t|	|	", "");
			Object expected = JSON.parse(expectedJson);
			Object actual = JSON.parse(actualJson);
			if (expected instanceof JSONObject) {
				result = compareJSONObject((JSONObject) expected,
						(JSONObject) actual);
			} else if (expected instanceof JSONArray) {
				result = compareJSONArray((JSONArray) expected,
						(JSONArray) actual);
			} else {
				throw new Exception("不是json");
			}
		} catch (Exception e) {
			String fullStackTrace = org.apache.commons.lang.exception.ExceptionUtils
					.getFullStackTrace(e);
			result = expectedJson.equals(actualJson);
			if (result) {
				return null;// 字符串比较成功
			}
			return "字符串比较不相等：预期值：" + expectedJson + ",实际值：" + actualJson
					+ "\njson比较异常，异常信息：" + fullStackTrace + ";字符串比较也不相等";
		}
		if (!result) {
			return makeJSONCompareErrorMsg();
		}
		return null;
	}

	private String makeJSONCompareErrorMsg() {
		String errStr = "";
		// 组装 路径
		path.add("root");
		String p = "";
		for (int i = path.size() - 1; i >= 0; i--) {
			p += path.get(i) + "/";
		}
		p = p.substring(0, p.length() - 1);
		if (arrayIndex != 0) {
			p += "-" + arrayIndex;
		}

		errStr += "错误路径：" + p + "，错误类型：" + type + "---";
		if (type.equals(VALUE)) {
			errStr += "值错误：预期值：" + expectedValue + "，实际值：" + actualValue;
		} else if (type.equals(KEY)) {
			errStr += "key:" + expectedValue + "不存在";
		} else if (type.equals(TYPE)) {
			errStr += "类型错误：预期类型" + expectedValue + "，实际类型：" + actualValue;
		} else if (type.equals(COUNT_ARRAY)) {
			errStr += "数组数量不同：预期数量" + expectedValue + "，实际数量：" + actualValue;
		} else if (type.equals(COUNT_PROPERTIES)) {
			errStr += "属性数量不同：预期数量" + expectedValue + "，实际数量：" + actualValue;
		}

		return errStr;
	}

	/*
	 * 1.将2个json转成对象 1.比较对象个数 2.比较key名 3.比较类型 4.比较值 如果是对象
	 */
	public boolean compareJSONObject(JSONObject source, JSONObject target) {
		// 1.比较对象个数
		if (!comparePropertiesCount(source, target)) {
			return false;
		}
		// 2.比较 key名
		if (!compareKeyName(source, target)) {
			return false;
		}
		// 3.比较数据类型
		if (!compareValueType(source, target)) {
			return false;
		}
		// 4.比较值
		if (!comparePropertiesValue(source, target)) {
			return false;
		}
		return true;
	}

	/*
	 * 基本数据类型 4个 1.string 2.integer 3.double 4.boolean
	 */
	private boolean comparePropertiesValue(JSONObject source, JSONObject target) {
		Set<String> set = source.keySet();
		for (String key : set) {
			Object value = source.get(key);
			if (checkIsBasicType(value)) {
				if (!compareBasicValue(source.get(key), target.get(key))) {
					path.add(key);
					return false;
				}
			} else if (source.get(key) instanceof JSONObject) {
				if (compareJSONObject((JSONObject) source.get(key),
						(JSONObject) target.get(key))) {
					path.add(key);
					return false;
				}
			} else if (source.get(key) instanceof JSONArray) {
				if (!compareJSONArray((JSONArray) source.get(key),
						(JSONArray) target.get(key))) {
					path.add(key + "-" + arrayIndex);
					arrayIndex = 0;
					return false;
				}
			}
		}
		return true;
	}

	private boolean compareJSONArray(JSONArray jsonArray, JSONArray jsonArray2) {
		if (!compareArrayCount(jsonArray, jsonArray2)) {
			return false;
		}
		if (!compareArrayValueType(jsonArray, jsonArray2)) {
			return false;
		}
		// 这里可以进行改进，适应 同一个json数组中的每个元素具有不同的数据类型的场景。
		// 以下算法只适应数组中只有一种数据类型。
		if (jsonArray.size() > 0) {
			Object value = jsonArray.get(0);
			if (checkIsBasicType(value)) {
				for (int i = 0; i < jsonArray.size(); i++) {
					if (!compareBasicValue(jsonArray.get(i), jsonArray2.get(i))) {
						arrayIndex = i;
						return false;
					}
				}
			} else if (value instanceof JSONObject) {
				int length = jsonArray.size();
				for (int i = 0; i < length; i++) {
					if (!compareJSONObject((JSONObject) jsonArray.get(i),
							(JSONObject) jsonArray2.get(i))) {
						arrayIndex = i;
						return false;
					}
				}
			} else if (value instanceof JSONArray) {
				int length = jsonArray.size();
				for (int i = 0; i < length; i++) {
					if (!compareJSONArray((JSONArray) jsonArray.get(i),
							(JSONArray) jsonArray2.get(i))) {
						arrayIndex = i;
						return false;
					}
				}
			}
		}
		return true;
	}

	private Boolean checkIsBasicType(Object value) {
		for (int i = 0; i < BASIC_TYPE.length; i++) {
			if (BASIC_TYPE[i].equals(value.getClass().getName())) {
				return true;
			}
		}
		return false;
	}

	private Boolean compareBasicValue(Object obj1, Object obj2) {
		if (!obj1.equals(obj2)) {
			type = VALUE;
			expectedValue = "" + obj1;
			actualValue = "" + obj2;
			return false;
		}
		return true;
	}

	private boolean compareValueType(JSONObject source, JSONObject target) {
		Set<String> keySet1 = source.keySet();
		for (String str : keySet1) {
			Object value1 = source.get(str);
			Object value2 = target.get(str);
			if (value1.getClass() != value2.getClass()) {
				type = TYPE;
				path.add(str);
				expectedValue = value1.getClass().getName();
				actualValue = value2.getClass().getName();
				return false;
			}
		}
		return true;
	}

	private boolean compareArrayValueType(JSONArray jsonArray,
			JSONArray jsonArray2) {
		for (int i = 0; i < jsonArray.size(); i++) {
			if (jsonArray.get(i).getClass() != jsonArray2.get(i).getClass()) {
				type = TYPE;
				arrayIndex = i;
				expectedValue = jsonArray.get(i).getClass().getName();
				actualValue = jsonArray2.get(i).getClass().getName();
				return false;
			}
		}
		return true;
	}

	private boolean compareKeyName(JSONObject source, JSONObject target) {
		Set<String> keySet1 = source.keySet();
		for (String str : keySet1) {
			if (target.get(str) == null) {
				type = KEY;
				path.add(str);
				expectedValue = str;
				actualValue = "";
				return false;
			}
		}
		return true;
	}

	private boolean comparePropertiesCount(JSONObject source, JSONObject target) {
		int count1 = source.size();
		int count2 = target.size();
		if (count1 != count2) {
			type = COUNT_PROPERTIES;
			expectedValue = "" + count1;
			actualValue = "" + count2;
			return false;
		} else {
			return true;
		}
	}

	private boolean compareArrayCount(JSONArray jsonArray, JSONArray jsonArray2) {
		int count1 = jsonArray.size();
		int count2 = jsonArray2.size();
		if (count1 != count2) {
			type = COUNT_ARRAY;
			expectedValue = "" + count1;
			actualValue = "" + count2;
			return false;
		} else {
			return true;
		}
	}
}
