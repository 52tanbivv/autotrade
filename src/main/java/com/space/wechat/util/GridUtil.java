package com.space.wechat.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 表格通用类
 * 
 * @author hbz
 * 
 */
public class GridUtil {

	public static String getGridFyJson(Object obj, int sEcho,
			int iTotalRecords, int iTotalDisplayRecords) {

		String json = JSON.toJSONString(obj,
				SerializerFeature.WriteMapNullValue);
		String result = "{\"sEcho\":" + sEcho + ",\"iTotalRecords\":"
				+ iTotalRecords + ",\"iTotalDisplayRecords\":"
				+ iTotalDisplayRecords + ",\"aaData\":" + json + "}";
		// System.out.println("result:" + result);
		result = result.replaceAll("\t", "");
		return result;
	}

	public static Map getGridFyJsonToMap(Object obj, int sEcho,
			int iTotalRecords, int iTotalDisplayRecords) {
		Map<String, Object> resultDataBean = new HashMap<String, Object>();
		resultDataBean.put("sEcho", sEcho);
		resultDataBean.put("iTotalRecords", iTotalRecords);
		resultDataBean.put("iTotalDisplayRecords", iTotalRecords);
		resultDataBean.put("aaData", obj);
		return resultDataBean;
	}

	public static String getGridJson(Object obj) {
		String result = "{\"aaData\":"
				+ JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue)
				+ "}";
		// System.out.println("result:" + result);
		result = result.replaceAll("\t", "");
		return result;
	}

	public static String getGridJsonNode(Object obj) {

		String result = JSON.toJSONString(obj,
				SerializerFeature.WriteMapNullValue);
		result = result.replaceAll("\t", "");
		// System.out.println("result:" + result);
		return result;
	}

	public static String getJsonUserSingleQuotes(Object obj) {
		String result = JSON.toJSONString(obj,
				SerializerFeature.UseSingleQuotes);
		// System.out.println("result:" + result);
		return result;
	}

	/**
	 * 
	 * @param obj
	 *            业务数据内容
	 * @param pageNum
	 *            页码，默认从零开始
	 * @param totalRecords
	 *            总数量
	 * @param pageLength
	 *            每页显示数量
	 * @param currentPageLength
	 *            当前页显示数量
	 * @return
	 */
	public static String getGridFyJson(Object obj, int pageNum,
			int totalRecords, int pageLength, int currentPageLength) {

		String json = JSON.toJSONString(obj,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.UseSingleQuotes);
		String result = "{\"pageNum\":" + pageNum + ",\"totalRecords\":"
				+ totalRecords + ",\"pageLength\":" + pageLength
				+ ",\"currentPageLength\":" + currentPageLength
				+ "+,\"aaData\":" + json + "}";
		// System.out.println("result:" + result);
		result = result.replaceAll("\t", "");
		return result;
	}

}
