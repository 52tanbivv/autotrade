package com.space.wechat.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Xss保护过滤器
 *
 * @author storezhang
 */
public class XssFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(XssFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("Xss filter inited!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		XssRequestWrapper xssRequest = new XssRequestWrapper(
				(HttpServletRequest) request);
		// logger.info("in xssfilter xssRequest=" + xssRequest);
		chain.doFilter(xssRequest, response);
	}

	@Override
	public void destroy() {
		logger.info("Xss filter destroyed!");
	}
}