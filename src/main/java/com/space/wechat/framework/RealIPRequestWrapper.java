package com.space.wechat.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealIPRequestWrapper extends HttpServletRequestWrapper {

	private static Logger logger = LoggerFactory
			.getLogger(RealIPRequestWrapper.class);

	public RealIPRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getRemoteAddr() {
		String realIP = super.getHeader("X-real-ip");
		String newIP = realIP != null ? realIP : super.getRemoteAddr();
		return newIP;
	}

	@Override
	public String getRemoteHost() {
		return getRemoteAddr();
	}
}