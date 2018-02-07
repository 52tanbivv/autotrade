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
@RequestMapping(value = "/api")
public class ApiController {

	private static Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private ServiceFactory serviceFactory;

	/**
	 * 取出Shiro中的当前用户user
	 */
	public static User getCurrentUser() {

		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user != null) {
			User newuser = user.getUser();
			newuser.setId(user.getId());
			return newuser;
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
			long time0 = System.currentTimeMillis();
			// System.out.println("INS API STRR=" + api + " " + paramsStr);
			JSONObject params = JSON.parseObject(paramsStr);
			boolean readonly = params.getBooleanValue("readonly");
			JSONObject param = params.getJSONObject("params");

			if (param == null) {
				param = new JSONObject();
			}

			if (readonly) {
				String result = serviceFactory.query(api, param);
				long time1 = System.currentTimeMillis();

				if (!api.equals("api_bugTreeList") && !api.equals("api_taskTreeList")) {
					String outStr = result;
					if (outStr.length() > 1500) {
						outStr = outStr.substring(0, 1490);
					}
					logger.info("QUERY : api=" + api + " time=" + (time1 - time0) + " params=" + paramsStr + " result="
							+ outStr);
				}
				if (time1 - time0 > 5000) {
					logger.error("TIMEOUT!!! api=" + api + "  time=" + (time1 - time0));
					logger.error("----------------END---------------");
				}
				return result.replace("	", "  ");

			} else {
				String result = serviceFactory.update(api, param);
				long time1 = System.currentTimeMillis();
				if (!api.equals("api_bugTreeList") && !api.equals("api_taskTreeList")) {
					String outStr = result;
					if (outStr.length() > 1500) {
						outStr = outStr.substring(0, 1490);
					}
					logger.info("UPDATE : api=" + api + " time=" + (time1 - time0) + " params=" + paramsStr + " result="
							+ outStr);
				}
				if (time1 - time0 > 5000) {
					logger.error("TIMEOUT!!! api=" + api + "  time=" + (time1 - time0));
					logger.error("----------------END---------------");
				}

				return result.replace("	", "  ");
			}
		} catch (ApiException e) {

			String errorMsg = e.getErrorMsg();
			if (errorMsg.indexOf("发生异常：") > -1) {
				errorMsg = errorMsg.substring(errorMsg.indexOf("【发生异常：") + 6, errorMsg.indexOf("】"));
			}
			logger.error("发生了API Exception的错误，" + errorMsg);
			return ServiceFactory.formatJsonResult(api, "", "500", errorMsg, "");
		} catch (Exception e) {
			logger.error("发生了非API Exception的错误，", e);
			String resultCode = "500";
			String fullStackTrace = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
			String resultMsg = fullStackTrace;
			return ServiceFactory.formatJsonResult(api, "", resultCode, resultMsg, "");
		}
	}
}
