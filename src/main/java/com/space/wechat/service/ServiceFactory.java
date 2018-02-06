package com.space.wechat.service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.wechat.entity.User;
import com.space.wechat.exception.ApiException;
import com.space.wechat.service.kindergarten.xtgl.AccountService;
import com.space.wechat.service.kindergarten.xtgl.RoleService;
import com.space.wechat.service.menu.MenuService;
import com.space.wechat.service.project.ProjectService;
import com.space.wechat.util.HttpClientUtil;
import com.space.wechat.util.OSSClientUtil;
import com.space.wechat.web.api.ApiController;

@Component
@Transactional(readOnly = false)
public class ServiceFactory {

	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
	private static String MOCK_SERVER_URL = "http://localhost:8080/integtest/mockcall";

	@Autowired
	private AccountService accountService;
	@Autowired
	private MenuService menuService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private RoleService roleService;

	private String SUCCESS = "200";
	private String ERROR_INTERNAL_SERVER = "500";
	private String ERROR_CLASS_NOTEXISTS = "001_001";
	private String ERROR_METHOD_NOTEXISTS = "001_002";

	/**
	 * 需要走事务通道
	 * 
	 * @param apiMethod
	 * @param paramsStr
	 * @return
	 */
	@Transactional(readOnly = false)
	public String update(String apiMethod, JSONObject param) {
		return this.callApi(apiMethod, param);
	}

	/**
	 * 不用走事务通道
	 * 
	 * @param apiMethod
	 * @param paramsStr
	 * @return
	 */
	public String query(String apiMethod, JSONObject param) {
		return this.callApi(apiMethod, param);
	}

	/**
	 * 调用API的通用方法<BR>
	 * <BR>
	 * 通过反射找到对应的api以及api方法，并对调用结果进行封装
	 * 
	 * @param apiMethod
	 *            要调用的api方法，格式[API/METHOD]，ServiceFactory会根据API+
	 *            Service定位到具体的api类，并根据反射找到要执行的方法并进行调用
	 * @return
	 */
	public String callApi(String apiMethod, JSONObject params) {
		Object methodResult = null;
		String resultCode = SUCCESS;
		String resultMsg = "";
		User user = ApiController.getCurrentUser();

		// 如果是模拟输入
		if (isMockCall(params)) {

			HashMap<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("api", apiMethod);

			Map<String, String> resultMap;
			try {
				resultMap = HttpClientUtil.submitPostRequest(MOCK_SERVER_URL, paramsMap, null);
				String status = resultMap.get("status");
				if ("200".equals(status)) {
					return resultMap.get("responseMessage");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}

			// return apiMockService.callMockApi(apiMethod, params);
		} else {
			params.put("currentUser", user);
			String[] apiMethods = apiMethod.split("_");
			String apiName = apiMethods[0];
			String methodName = apiMethods[1];

			JSONObject result = new JSONObject();

			try {
				Class[] argsClass = getJsonArgClass();

				if ("menu".equals(apiName)) {
					Method method = menuService.getClass().getMethod(methodName, argsClass);
					methodResult = method.invoke(menuService, params);
				} else if ("user".equals(apiName)) {
					Method method = accountService.getClass().getMethod(methodName, argsClass);
					methodResult = method.invoke(accountService, params);
				} else if ("project".equals(apiName)) {
					Method method = projectService.getClass().getMethod(methodName, argsClass);
					methodResult = method.invoke(projectService, params);
				} else if ("role".equals(apiName)) {
					Method method = roleService.getClass().getMethod(methodName, argsClass);
					methodResult = method.invoke(roleService, params);
				} else if ("oss".equals(apiName)) {
					OSSClientUtil client = new OSSClientUtil();
					Method method = client.getClass().getMethod(methodName, argsClass);
					methodResult = method.invoke(client, params);
				} else {
					// 服务不存在
					resultCode = ERROR_CLASS_NOTEXISTS;
				}
			}

			catch (ApiException e) {
				logger.info("API ERROR HAPPENED!" + e.getErrorMsg());
				throw e;
			} catch (java.lang.reflect.InvocationTargetException e) {
				String errormsg = org.apache.commons.lang.exception.ExceptionUtils
						.getFullStackTrace(e.getTargetException());
				logger.info("Invocation ERROR HAPPENED!"
						+ org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e.getTargetException()));
				throw new ApiException(errormsg);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
		return formatJsonResult(apiMethod, methodResult, resultCode, resultMsg, "");
	}

	private boolean isMockCall(JSONObject params) {
		if (params.containsKey("mockcall")) {
			return params.getBoolean("mockcall");
		} else {
			return false;
		}
	}

	private Class[] getJsonArgClass() throws ClassNotFoundException {
		Class jsonClass = Class.forName("com.alibaba.fastjson.JSONObject");
		Class[] argsClass = new Class[1];
		argsClass[0] = jsonClass;
		return argsClass;
	}

	public static String formatJsonResult(String api, Object obj, String returnCode, String returnMsg, String version) {
		JSONObject result = new JSONObject();
		result.put("api", api);
		result.put("data", obj);
		result.put("v", version);
		result.put("ret", formatReturnMsg(returnCode, returnMsg));
		return JSON.toJSONString(result).replace("	", "    ");
	}

	public static JSONObject formatReturnMsg(String code, String msg) {
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
}
