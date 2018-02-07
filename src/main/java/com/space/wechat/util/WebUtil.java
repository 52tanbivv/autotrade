package com.space.wechat.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebUtil {

	private static Logger logger = LoggerFactory.getLogger(WebUtil.class);

	public WebUtil() {
	}

	public static String transport(String url, String message) {
		StringBuffer sb = new StringBuffer();
		try {
			URL urls = new URL(url);
			HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
			uc.setRequestMethod("POST");
			uc.setRequestProperty("content-type",
					"application/x-www-form-urlencoded");
			// uc.setRequestProperty("charset", "utf-8");
			uc.setDoOutput(true);
			uc.setDoInput(true);
			uc.setReadTimeout(10000);
			uc.setConnectTimeout(10000);
			OutputStream os = uc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.write(message.getBytes("utf-8"));
			dos.flush();
			os.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			String readLine = "";
			while ((readLine = in.readLine()) != null) {
				sb.append(readLine);
			}
			in.close();
			// if (StringUtil.isNotEmpty(sb.toString())
			// && sb.toString().indexOf("Missing argument") > -1) {
			logger.error("门户网站同步异常:" + sb.toString() + " url=" + url
					+ "参数message=" + message);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("门户网站同步异常:" + e.getMessage() + "url=" + url
					+ " 参数message=" + message);
		}
		return sb.toString();
	}
}
