package com.space.wechat.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class CompareParamJsonUtil {
	private static final String[] BASIC_TYPE = { "java.lang.Integer",
			"java.lang.Long", "java.lang.Float", "java.lang.Double",
			"java.lang.Boolean", "java.lang.String", "java.math.BigDecimal" };
	private static final String TYPE = "TYPE";
	private static final String KEY = "KEY";

	private List<String> path = new ArrayList<String>();
	private String type;
	private String expectedValue;
	private String actualValue;
	private int arrayIndex = 0;

	public static void main(String[] args) {
		String templateJson = "{	\"params\":{		\"a\":\"java.lang.Integer|false\",		\"b\":{			\"a\":\"java.math.BigDecimal|true\",			\"b\":\"java.lang.Boolean|false\"		},		\"c\":[			{				\"a\":\"java.lang.Integer|false\",				\"b\":\"java.lang.String|true\",				\"d\":\"java.lang.String|false\"			}		]	}}";
		String actualJson = "{	\"params\":{		\"a\":2,		\"b\":{			\"a\":2.3,			\"b\":true		},		\"c\":[			{				\"a\":111,				\"b\":\"ccc\"			},			{				\"a\":222,				\"b\":\"ddd\"			},			{				\"a\":22,				\"b\":\"dd\",				\"c\":33			},			{				\"a\":22			}		]	}}";
		JSONObject t = JSON.parseObject(templateJson);
		JSONObject a = JSON.parseObject(actualJson);
		String r = new CompareParamJsonUtil().compare(a, t);

		System.out.println(r);
	}

	/**
	 * @param expectedJson
	 * @param actualJson
	 * @return null:正确
	 */
	public String compare(JSONObject actualJson, JSONObject templateJson) {
		Boolean result = compareJSONObject(actualJson, templateJson);
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

		errStr += "错误路径：" + p + "，错误类型：" + type + "，错误原因：";
		if (type.equals(KEY)) {
			errStr += "键名不存在，模板key:" + expectedValue + "，实际key：" + actualValue;
		} else if (type.equals(TYPE)) {
			errStr += "类型错误：模板类型:" + expectedValue + "，实际类型：" + actualValue;
		}

		return errStr;
	}

	/*
	 * 校验json是否符合规范，不进行值的比对，只比对键名类型 还有根据预期json中的是否必填，来比较
	 * 
	 * 如果是对象默认 必须填
	 */
	public boolean compareJSONObject(JSONObject actualJson,
			JSONObject templateJson) {
		Map<String, Object> actualMap = (Map<String, Object>) actualJson;
		Map<String, Object> templateMap = (Map<String, Object>) templateJson;

		// 1.比较 key名 。。。 两头比较。 右边包含左边， 而且 右边的必填项左边必须存在
		Set<String> keySetActual = actualMap.keySet();
		// for (String keyActual : keySetActual) {
		// if (templateMap.get(keyActual) == null) {
		// type = KEY;
		// path.add(keyActual);
		// actualValue = keyActual;
		// expectedValue = "null";
		// return false;
		// }
		// }
		Set<String> keySetTemplate = templateMap.keySet();
		for (String keyTemplate : keySetTemplate) {
			if (templateMap.get(keyTemplate) instanceof JSONObject
					|| templateMap.get(keyTemplate) instanceof JSONArray
					|| ((String) templateMap.get(keyTemplate)).split("\\|")[1]
							.equals("true")) {
				if (actualMap.get(keyTemplate) == null) {
					type = KEY;
					path.add(keyTemplate);
					actualValue = "null";
					expectedValue = keyTemplate;
					return false;
				}
			}
		}
		// 2.比较数据类型
		for (String keyTemplate : keySetTemplate) {
			Object value1 = actualMap.get(keyTemplate);
			Object value2 = templateMap.get(keyTemplate);
			if (value1 == null) {
				continue;
			}
			boolean flag = true;
			String type1 = getClassType(value1.getClass().getName());
			System.out.println("keyActual=" + keyTemplate
					+ " templateMap value2=" + value2);
			String type2 = parseTypeStr(value2);
			if (value1 instanceof JSONObject || value1 instanceof JSONArray) {
				if (!type1.equals(type2)) {
					flag = false;
				}
			} else if (!(value2 instanceof String)) {

				flag = false;
			} else {
				// type2 = ((String) value2).split("\\|")[0];// 类型
				if (!type1.equals(type2)) {// ！1 此处类型比较时，可能存在问题， --------需调试 统一
					if (!(type1.equals("string") && type2.equals("number"))
							&& !(type1.equals("number") && type2
									.equals("string"))) {
						flag = false;
					}
				}
			}
			if (!flag) {
				type = TYPE;
				path.add(keyTemplate);
				actualValue = type1;
				expectedValue = type2;
				return false;
			}
		}
		// 3.比较object内部的object或array<object>

		for (String keyActual : keySetActual) {
			Object value1 = actualMap.get(keyActual);
			Object value2 = templateMap.get(keyActual);
			if (value1 instanceof JSONObject) {
				if (!compareJSONObject((JSONObject) value1, (JSONObject) value2)) {
					path.add(keyActual);
					return false;
				}
			} else if (value1 instanceof JSONArray) {
				if (!compareJSONArray((JSONArray) value1, (JSONArray) value2)) {
					path.add(keyActual + "-" + arrayIndex);
					arrayIndex = 0;
					return false;
				}
			}
		}

		return true;
	}

	private String parseTypeStr(Object value2) {
		String className = value2.getClass().getName();
		if (className.contains("JSON")) {
			return className;
		}
		String[] types = value2.toString().split("[|]");
		return getClassType(types[0]);
	}

	public String getClassType(String className) {
		if (className.indexOf("String") > -1) {
			return "string";
		} else if (className.indexOf("Integer") > -1
				|| className.indexOf("Double") > -1
				|| className.indexOf("Float") > -1) {
			return "number";
		} else if (className.indexOf("Boolean") > -1) {
			return "boolean";
		} else {
			return className;
		}
	}

	private boolean compareJSONArray(JSONArray jsonArray, JSONArray jsonArray2) {
		if (jsonArray.size() > 0) {
			Object value = jsonArray2.get(0);
			int length = jsonArray.size();
			// 类型比较
			for (int i = 0; i < length; i++) {
				if (jsonArray.get(i).getClass() != value.getClass()) {
					type = TYPE;
					arrayIndex = i;
					actualValue = getClassType(jsonArray.get(i).getClass()
							.getName());
					expectedValue = value.getClass().getName();
					return false;
				}
			}
			for (int i = 0; i < length; i++) {
				if (!compareJSONObject((JSONObject) jsonArray.get(i),
						(JSONObject) value)) {
					arrayIndex = i;
					return false;
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
}
