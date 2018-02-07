package com.space.wechat.web.ueditor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.ActionEnter;
import com.space.wechat.util.StringUtil;

/**
 * 文件上传下载Controller.
 * 
 * @author hbz
 */
@Controller
@RequestMapping(value = "/ueditor")
public class UeditorHandleContrller {

	/**
	 * 初始化百度编辑器，可传入其他参数生成不同的编辑器
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/init")
	public void initUeditor(HttpServletResponse response, HttpServletRequest request) {
		response.setContentType("application/json");
		// 配置路径，首先获取webpp根目录绝对路径
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		// 将config.json放到与ueditor.config.js同一级的目录下。将ueditor所有文件放入到wapapp-static-ueditor下
		// 设置获取服务端配置文件地址修正路径，此路径同时作用于文件上传
		rootPath = rootPath + "static";
		PrintWriter writer = null;
		try {
			String exec = new ActionEnter(request, rootPath).exec();
			if ("uploadimage".equals(request.getParameter("action"))) {
				JSONObject json = JSON.parseObject(exec);
				json.put("url", StringUtil.getNullStr(json.get("url")));
				exec = json.toJSONString();
			}
			writer = response.getWriter();
			writer.write(exec);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
