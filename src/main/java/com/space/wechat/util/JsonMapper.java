/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.space.wechat.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.space.wechat.common.ConstantVar;

/**
 * 简单封装Jackson，实现JSON String<->Java Object的Mapper.
 * 
 * 封装不同的输出风格, 使用不同的builder函数创建实例.
 * 
 * @author calvin
 */
public class JsonMapper {

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	private static ObjectMapper mapper;

	public JsonMapper() {
		this(null);
	}

	public JsonMapper(Include include) {
		mapper = new ObjectMapper();
		// 设置输出时包含属性的风格
		if (include != null) {
			mapper.setSerializationInclusion(include);
		}
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, false);

	}

	public static JsonMapper alwaysMapper() {
		return new JsonMapper(Include.NON_NULL);
	}

	/**
	 * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
	 */
	public static JsonMapper nonEmptyMapper() {
		return new JsonMapper(Include.NON_EMPTY);
	}

	/**
	 * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
	 */
	public static JsonMapper nonDefaultMapper() {
		return new JsonMapper(Include.NON_DEFAULT);
	}

	/**
	 * Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
	 */
	public String toJson(Object object) {

		try {
			// logger.warn("write to json string error:" + object);

			String result = mapper.writeValueAsString(object);
			// System.out.println(result);
			return result;
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * 反序列化POJO或简单Collection如List<String>.
	 * 
	 * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
	 * 
	 * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)
	 * 
	 * @see #fromJson(String, JavaType)
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调用本函数.
	 * 
	 * @see #createCollectionType(Class, Class...)
	 */
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 構造泛型的Collection Type如: ArrayList<MyBean>,
	 * 则调用constructCollectionType(ArrayList.class,MyBean.class) HashMap
	 * <String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
	 */
	public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * 當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
	 */
	public <T> T update(String jsonString, T object) {
		try {
			return (T) mapper.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
		return null;
	}

	/**
	 * 輸出JSONP格式數據.
	 */
	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * 設定是否使用Enum的toString函數來讀寫Enum, 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
	 * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
	 */
	public void enableEnumUseToString() {
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}

	/**
	 * 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。
	 * 默认会先查找jaxb的annotation，如果找不到再找jackson的。
	 */
	public void enableJaxbAnnotation() {
		JaxbAnnotationModule module = new JaxbAnnotationModule();
		mapper.registerModule(module);
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public static ObjectMapper getMapper() {
		return mapper;
	}

	/**
	 * 异步请求json组装返回页面
	 * 
	 * @param api
	 * @param obj
	 * @param returnMsg
	 * @param version
	 * @return
	 */
	public static Map<String, Object> formatJsonMap(String api, Object obj, String returnCode, String returnMsg,
			String version) {
		// String json = JSON.toJSONString(obj,
		// SerializerFeature.WriteMapNullValue);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("api", api);
		resultMap.put("data", obj);
		resultMap.put("v", version);
		resultMap.put("ret", formatReturnMsg(returnCode, returnMsg));
		return resultMap;
	}

	public static Map<String, Object> formatReturnMsg(String code, String msg) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		return returnMap;
	}

	public static Map<String, Object> successSingleReturnMsg(String api, String numTitle, Object numValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(numTitle, numValue);
		return JsonMapper.formatJsonMap(api, map, ConstantVar.AJAX_REQUEST_SIGN_SUCCESS, "", "");
	}

	public static Map<String, Object> failSingleReturnMsg(String api, String msg) {
		return JsonMapper.formatJsonMap(api, "", ConstantVar.AJAX_REQUEST_SIGN_FAIL, msg, "");
	}

	public static Map<String, Object> successSingleReturnMsgOfEmptyData(
			String api, String msg) {
		return JsonMapper.formatJsonMap(api, "",
				ConstantVar.AJAX_REQUEST_SIGN_SUCCESS, msg, "");
	}

	public static Map<String, Object> successSingleReturnMsg(String api,
			String names, Object... values) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] ns = names.split(",");
		for (int i = 0; i < ns.length; i++) {
			map.put(ns[i], values[i]);
		}
		return JsonMapper.formatJsonMap(api, map, ConstantVar.AJAX_REQUEST_SIGN_SUCCESS, "", "");
	}

	public static Map<String, Object> successSingleReturnMsg(String api,
			Object object) {
		return JsonMapper.formatJsonMap(api, object,
				ConstantVar.AJAX_REQUEST_SIGN_SUCCESS, "", "");
	}

	public static String formMapToJson(Map<String, Object> params) {
		return JSON.toJSONString(params, SerializerFeature.WriteMapNullValue);
	}
}
