package com.space.wechat.web.api;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.wechat.entity.User;
import com.space.wechat.exception.ApiException;
import com.space.wechat.service.ServiceFactory;
import com.space.wechat.service.account.ShiroDbRealm.ShiroUser;

@Controller
@RequestMapping(value = "/securityapi")
public class SecurityApiController {

	private static Logger logger = LoggerFactory.getLogger(SecurityApiController.class);

	@Autowired
	private ServiceFactory serviceFactory;

	/**
	 * 取出Shiro中的当前用户user
	 */
	public static User getCurrentUser() {

		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user != null) {
			return user.getUser();
		} else {
			logger.warn("error user");
			return null;
		}
	}

	/**
	 * 通用的api调用方法
	 * 
	 * @param api
	 * @param paramsStr
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{api}", produces = "text/plain; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String callApi(@PathVariable(value = "api") String api,
			@RequestParam("apiparams") String paramsStr, Model model, ServletRequest request) {
		try {
			JSONObject params = JSON.parseObject(paramsStr);
			boolean readonly = params.getBooleanValue("readonly");
			JSONObject param = params.getJSONObject("params");
			long time0 = System.currentTimeMillis();
			if (param == null) {
				param = new JSONObject();
			}

			if (readonly) {
				String result = serviceFactory.query(api, param);
				long time1 = System.currentTimeMillis();
				logger.info("QUERY : api=" + api + " time=" + (time1 - time0) + " params=" + paramsStr + " result="
						+ result);

				return result.replace("	", "  ");

			} else {
				String result = serviceFactory.update(api, param);
				long time1 = System.currentTimeMillis();
				logger.info("UPDATE : api=" + api + " time=" + (time1 - time0) + " params=" + paramsStr + " result="
						+ result);
				return result.replace("	", "  ");
			}
		} catch (ApiException e) {
			String errorMsg = e.getErrorMsg();
			if (errorMsg.indexOf("发生异常：") > -1) {
				errorMsg = errorMsg.substring(errorMsg.indexOf("【发生异常：") + 6, errorMsg.indexOf("】"));
			}
			logger.error("msg=" + errorMsg);
			return ServiceFactory.formatJsonResult(api, "", "500", errorMsg, "");
		} catch (Exception e) {
			System.out.println("333333");
			e.printStackTrace();
			String resultCode = "500";
			String fullStackTrace = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
			System.out.println("-----");
			System.out.println(fullStackTrace);
			System.out.println("=====");
			System.out.println(e.getMessage());
			System.out.println("-----");

			String resultMsg = e.getMessage();
			return ServiceFactory.formatJsonResult(api, "", resultCode, resultMsg, "");
		}
	}
}
