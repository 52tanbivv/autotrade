package com.space.wechat.util;

import java.util.Map;

public class SqlUtil {

	static final String TYPE_STRING = "s";
	static final String TYPE_INTEGER = "i";
	static final String TYPE_DATE = "d";

	static final String METHOD_EQUAL = "EQ";
	static final String METHOD_LIKE = "LIKE";
	static final String METHOD_IN = "IN";

	/**
	 * 组装查询条件
	 * 
	 * @param params
	 *            ，格式："p.search_i_catagory","p.search_s_name"
	 * @return
	 */
	public static String makeQueryCondition(Map<String, Object> paramValueMap,
			String... params) {

		StringBuffer condition = new StringBuffer("");

		for (String param : params) {
			condition.append(processCondition(paramValueMap, param));
		}
		return condition.toString();
	}

	/**
	 * 对参数值进行了封装
	 * 
	 * @param paramType
	 * @param paramValue
	 * @param method
	 * @return
	 */
	private static String getWrappedValue(String paramType, Object paramValue,
			String method) {

		if (paramValue.toString().indexOf(';') > 0) {
			throw new RuntimeException("发现SQL非法注入字符" + paramValue);
		}

		if (METHOD_EQUAL.equals(method)) {
			if (TYPE_INTEGER.equals(paramType)) {
				return new Integer(StringUtil.getIntOfObj(paramValue))// 数字型的先将字符串过滤成数字，防止一些非数字的异常字符出现，导致SQL异常
						.toString();
			} else if (TYPE_STRING.equals(paramType)) {
				return "'" + paramValue + "'";
			} else {
				throw new RuntimeException(
						"StringUtil.getWrappedValue:不正确的参数类型：" + paramType);
			}
		} else if (METHOD_LIKE.equals(method)) {
			if (TYPE_STRING.equals(paramType)) {
				return "'%" + paramValue + "%'";
			} else {
				throw new RuntimeException(
						"StringUtil.getWrappedValue:like 方法不能使用非字符串参数");
			}
		} else if (METHOD_IN.equals(method)) {
			return paramValue.toString();
		} else {
			throw new RuntimeException("StringUtil.getWrappedValue:不正确的方法："
					+ method);
		}
	}

	/**
	 * 
	 * @param controller
	 * @param paramStr
	 *            ，格式：p.EQ_name.s,表名前缀.比较条件_字段名.字段类型
	 * @return
	 */
	private static String processCondition(Map<String, Object> paramValueMap,
			String paramStr) {
		String[] strs = paramStr.split("\\u002E");
		String prifx = strs[0]; // 表名前缀
		String pageParam = strs[1];// 字段名
		String paramType = strs[2];// 字段类型
		String[] params = pageParam.split("_");

		String method = params[0]; // 比较方法
		String paramName = params[1];// 真正的参数名

		Object paramValue = paramValueMap.get(pageParam);
		if (paramValue == null || paramValue.toString().equals("")) {
			return "";
		}

		String wrappedValue = getWrappedValue(paramType, paramValue, method);
		if (METHOD_EQUAL.equals(method)) {
			return " AND " + prifx + "." + paramName + " = " + wrappedValue;
		} else if (METHOD_LIKE.equals(method)) {
			return " AND " + prifx + "." + paramName + " LIKE " + wrappedValue;
		} else if (METHOD_IN.equals(method)) {
			return " AND " + prifx + "." + paramName + " IN (" + wrappedValue
					+ ")";
		} else {
			throw new RuntimeException("StringUtil.makeFieldConditoin:不正确的方法："
					+ method);
		}

	}
}
