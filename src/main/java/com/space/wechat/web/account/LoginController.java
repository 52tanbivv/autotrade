package com.space.wechat.web.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.alibaba.fastjson.JSON;
import com.space.wechat.common.ConstantVar;
import com.space.wechat.entity.User;
import com.space.wechat.service.account.ShiroDbRealm.ShiroUser;
import com.space.wechat.service.account.loginService;
import com.space.wechat.util.StringUtil;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	private loginService loginService;

	/**
	 * 登录验证，
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "main.html";
		} else {
			return "login.html";
		}
	}

	/**
	 * 取出Shiro中的当前用户user
	 */
	public static User getCurrentUser() {

		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user != null) {
			return user.getUser();
		} else {
			return null;
		}

	}

	/**
	 * 取出Shiro中的当前用户name.
	 */
	public String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.getName();
	}

	private String printErrorMessage(String message) {
		Map result = new HashMap();
		result.put("message", message);
		result.put("code", "1");
		return JSON.toJSONString(result);
	}

	private String printSuccessMessage() {
		Map result = new HashMap();
		result.put("message", "success");
		result.put("code", "0");
		return JSON.toJSONString(result);
	}

	/**
	 * 和教育
	 * 
	 * @throws IOException
	 **/
	@RequestMapping(value = "/ssologin", produces = "text/plain; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String ssologin(Model model, ServletRequest request) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (StringUtil.isNullOrEmpty(username)) {
			return "fail";
		} else {

			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password, false);
			SecurityUtils.getSubject().login(token);
			if (SecurityUtils.getSubject().isAuthenticated()
					&& getCurrentUser() != null) {
				return "success";
			} else {
				return "fail";
			}
		}
	}

	/**
	 * 登录验证成功
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "success", method = RequestMethod.GET)
	public String success(Model model, HttpServletRequest request) {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		// 重置登录密码错误次数

		byte[] hashPassword = Digests.sha1(
				ConstantVar.DEFAULT_PASSWORD.getBytes(),
				Encodes.decodeHex(getCurrentUser().getSalt()),
				ConstantVar.HASH_INTERATIONS);
		String defaultpassword = Encodes.encodeHex(hashPassword);
		if (defaultpassword.equals(getCurrentUser().getPassword())) {
			model.addAttribute("ifmodpwd", "true");
		} else {
			model.addAttribute("ifmodpwd", "false");
		}
		model.addAttribute("userModel", getCurrentUser());
		return "main.html";

	}

	/**
	 * 登录验证失败处理
	 * 
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			Model model) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "main.html";
		} else {
			model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
					userName);
			model.addAttribute("error", "您输入的密码和账户名不匹配，请重新输入。");

			return "login.html";
		}

	}

}
