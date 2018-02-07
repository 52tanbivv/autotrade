package com.space.wechat.framework.filter;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XSS保护
 *
 * @author storezhang
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

	private static Logger logger = LoggerFactory
			.getLogger(XssRequestWrapper.class);
	private static Policy policy = null;

	static {
		// String path =
		// URLUtility.getClassPath(XssRequestWrapper.class)+File.separator+"antisamy-anythinggoes-1.4.4.xml";
		String path = XssRequestWrapper.class.getClassLoader()
				.getResource("antisamy-anythinggoes.xml").getFile();
		System.out.println("policy_filepath:" + path);
		if (path.startsWith("file")) {
			path = path.substring(6);
		}
		try {
			policy = Policy.getInstance(path);
		} catch (PolicyException e) {
			e.printStackTrace();
		}
	}

	public XssRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@SuppressWarnings("rawtypes")
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> request_map = super.getParameterMap();
		Iterator iterator = request_map.entrySet().iterator();
		System.out.println("request_map" + request_map.size());
		while (iterator.hasNext()) {
			Map.Entry me = (Map.Entry) iterator.next();
			// System.out.println(me.getKey()+":");
			String[] values = (String[]) me.getValue();
			for (int i = 0; i < values.length; i++) {
				String xssResult = xssClean(values[i]);
				if (xssResult != null && !xssResult.equals(values[i])) {
					logger.info("in XSS Filter getParameterMap:source="
							+ values[i] + " target=" + xssResult);
				}
				values[i] = xssResult;
			}
		}
		return request_map;
	}

	public String[] getParameterValues(String paramString) {
		String[] arrayOfString1 = super.getParameterValues(paramString);
		if (arrayOfString1 == null)
			return null;
		int i = arrayOfString1.length;
		String[] arrayOfString2 = new String[i];
		for (int j = 0; j < i; j++) {
			arrayOfString2[j] = xssClean(arrayOfString1[j]);

			if (arrayOfString2[j] != null
					&& !arrayOfString2[j].equals(arrayOfString1[j])) {
				logger.info("in XSS Filter :getParameterValues="
						+ arrayOfString1[j] + " target="
						+ xssClean(arrayOfString2[j]));
			}
		}
		return arrayOfString2;
	}

	public String getParameter(String paramString) {
		String str = super.getParameter(paramString);
		if (str == null)
			return null;
		String result = xssClean(str);
		if (str != null && !str.equals(result)) {
			logger.info("in XSS Filter getParameter:source=" + str + " target="
					+ result);
		}
		return result;
	}

	public String getHeader(String paramString) {
		String str = super.getHeader(paramString);
		if (str == null)
			return null;
		String result = xssClean(str);
		// logger.info("in XSS Filter getHeader:source=" + str + " target="
		// + result);
		return result;
	}

	private String xssClean(String value) {
		AntiSamy antiSamy = new AntiSamy();
		try {
			// CleanResults cr = antiSamy.scan(dirtyInput, policyFilePath);

			final CleanResults cr = antiSamy.scan(value, policy);
			String str = StringEscapeUtils.unescapeHtml(cr.getCleanHTML());
			str = str.replaceAll(
					(antiSamy.scan("&nbsp;", policy)).getCleanHTML(), "&nbsp;");
			// 安全的HTML输出
			return str;
		} catch (ScanException e) {
			e.printStackTrace();
		} catch (PolicyException e) {
			e.printStackTrace();
		}
		return value;
	}
}