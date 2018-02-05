package com.space.wechat.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	public static UrlEncodedFormEntity getPostParam(Map<String, String> param) throws UnsupportedEncodingException {
		List<NameValuePair> params = getReqParamNVP(param);
		// 编码格式转换
		return new UrlEncodedFormEntity(params, "utf-8");
	}

	public static List<NameValuePair> getReqParamNVP(Map<String, String> param) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		java.util.Iterator it = param.entrySet().iterator();
		while (it.hasNext()) {
			java.util.Map.Entry<String, String> entry = (java.util.Map.Entry<String, String>) it.next();

			params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));// entry.getKey()
																					// 返回与此项对应的键,entry.getValue()，返回与此项对应的值，logger.info(entry.getValue());
		}
		return params;
	}

	public static void main2(String[] args) {

		String url = "http://localhost:8080/integtest/login";
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", "admin");
		params.put("password", "zaq1234");
		try {
			Map<String, String> sessoinMap = submitPostRequest(url, params, null);
			String session = sessoinMap.get("Cookie");
			if (session != null && !session.equals("")) {
				Map<String, String> cookieMap = new HashMap<String, String>();
				cookieMap.put("Cookie", session);
				HashMap<String, String> paramsw = new HashMap<String, String>();
				paramsw.put("params", "{}");
				Map<String, String> resultMap = submitPostRequest("http://localhost:8080/integtest/api/user_list",
						paramsw, cookieMap);
				System.out.println(resultMap.get("responseMessage"));
				// {"apiState":"ALL","apiStr":"","developer":"","pageInx":1,"pageSize":5,"pageTotal":0}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String execCmd(String cmd) {
		StringBuffer result = new StringBuffer();
		BufferedReader br = null;
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStream os = proc.getInputStream();
			br = new BufferedReader(new InputStreamReader(os));
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}

	public static void main4(String[] args) {

		System.out.println(execCmd("telnet localhost 8083"));
		;
		String url = "http://localhost:8080/integtest/login";
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", "admin");
		params.put("password", "zaq1234");
		try {
			Map<String, String> sessoinMap = submitPostRequest(url, params, null);
			String session = sessoinMap.get("Cookie");
			if (session != null && !session.equals("")) {
				Map<String, String> cookieMap = new HashMap<String, String>();
				cookieMap.put("Cookie", session);
				String gitparams = "{\"object_attributes\": {\"id\": 1516,\"target_branch\": \"master\",\"source_branch\": \"master_zh_0401_01\",\"source_project_id\": 49,\"author_id\": 7,\"assignee_id\": 12,\"title\": \"修改测试用例findbyid接口返回格式\",\"created_at\": \"2016-04-01 00:53:24 UTC\",\"updated_at\": \"2016-04-01 00:57:17 UTC\",\"state\": \"merged\",\"merge_status\": \"can_be_merged\",\"target_project_id\": 49,\"iid\": 31,\"description\": \"\",\"position\": 0,\"source\": {\"name\":\"integratetest\",\"ssh_url\": \"git@10.10.10.191:yjf/integratetest.git\",\"http_url\": \"http://10.10.10.191/yjf/integratetest.git\",\"namespace\": \"yjf\",\"visibility_level\": 20},\"target\": {\"name\": \"integratetest\",\"ssh_url\": \"git@10.10.10.191:yjf/integratetest.git\",\"http_url\": \"http://10.10.10.191/yjf/integratetest.git\",\"namespace\": \"yjf\",\"visibility_level\": 20},\"last_commit\": {\"id\": \"d0a9de52100105b741b024cc32a7459f01af081e\",\"message\": \"[deployapi：api_list]修改测试用例findbyid接口返回格式\n\",\"timestamp\": \"2016-04-01T10:09:50+08:00\",\"url\": \"http://10.10.10.191/yjf/integratetest/commit/d0a9de52100105b741b024cc32a7459f01af081e\",\"author\": {\"name\": \"zhanghao\",\"email\": \"zhanghao@spacetech.com.cn\"}},\"url\": \"http://10.10.10.191/yjf/integratetest/merge_requests/31\",\"action\": \"open\"},\"object_kind\": \"merge_request\",\"user\": {\"name\": \"刘晓滨\",\"username\": \"lxb\",\"avatar_url\": \"http://gravatar.duoshuo.com/avatar/32624fabb0025c62aa65c72deed10ed8?s=40&d=identicon\"}}";
				HashMap<String, String> paramsw = new HashMap<String, String>();
				paramsw.put("params", gitparams);
				Map<String, String> resultMap = submitPostRequest("http://localhost:8080/integtest/githook/mock",
						paramsw, cookieMap);
				System.out.println(resultMap.get("responseMessage"));
				// {"apiState":"ALL","apiStr":"","developer":"","pageInx":1,"pageSize":5,"pageTotal":0}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		System.out.println(execCmd("telnet localhost 8083"));
		;
		String url = "http://localhost:8080/integtest/login";

		JSONObject params = new JSONObject();
		params.put("projectid", "12345l");
		params.put("token", "846b1b10a3c749dcecca5b71712f721fe3bef7f1");

		Map<String, String> paramsMap = new HashMap<String, String>();

		paramsMap.put("params", params.toJSONString());
		try {
			Map<String, String> resultMap = submitPostRequest("http://localhost:8085/api/deploy_deployTaskAuto",
					paramsMap, null);
			System.out.println(resultMap.get("responseMessage"));
			// {"apiState":"ALL","apiStr":"","developer":"","pageInx":1,"pageSize":5,"pageTotal":0}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Map<String, String> submitPostRequest(String url, Map<String, String> postParamMap,
			Map<String, String> requestHeaderMap) throws UnsupportedEncodingException {
		return submitPostRequest(url, getPostParam(postParamMap), requestHeaderMap);
	}

	/**
	 * 提交 POST 请求到腾讯服务器－官方接口调用
	 * 
	 * @param url
	 *            携带access_token
	 * @param param
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static Map<String, String> authSubmitPostRequest(String url, String param, String hashkey)
			throws ClientProtocolException, IOException {
		long size = 0;
		size += url.length();
		long s0 = System.currentTimeMillis();
		long s2 = 0;
		InputStream is = null;
		CloseableHttpClient client = getHttpClient();
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse response = null;
		HashMap<String, String> resultMap = new HashMap<String, String>();
		try {
			if (param != null) {
				post.setEntity(new StringEntity(param, "utf-8"));
			}

			long s1 = System.currentTimeMillis();
			response = client.execute(post);

			HttpEntity entity = response.getEntity();
			s2 = System.currentTimeMillis();
			if (entity != null) {
				// start 读取整个页面内容
				is = entity.getContent();
				// end 读取整个页面内容
				resultMap.put("responseMessage", readResponseInStream(is));
			}

		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (response != null) {
					response.close();
				}
				post.releaseConnection();
				client.close();
			} catch (Exception e) {
				// //e.printStackTrace();
				logger.error("提交微信HTTP请求出错：" + e.getMessage());
			}
		}

		long s3 = System.currentTimeMillis();
		return resultMap;
	}

	private static CloseableHttpClient getHttpClient() {

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(200);// 连接池最大并发连接数
		cm.setDefaultMaxPerRoute(50);// 单路由最大并发数

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

		return httpClient;
	}

	/**
	 * 提交GET 请求到服务器
	 * 
	 * @param url
	 * @return
	 */
	public static Map<String, String> authSubmitGetRequest(String url) {
		long s0 = System.currentTimeMillis();
		long s2 = 0;
		InputStream is = null;
		CloseableHttpClient client = getHttpClient();
		HttpGet post = new HttpGet(url);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		CloseableHttpResponse response = null;
		try {
			long s1 = System.currentTimeMillis();
			response = client.execute(post);
			HttpEntity entity = response.getEntity();

			if (entity != null) {

				is = entity.getContent();// start 读取整个页面内容

				resultMap.put("responseMessage", readResponseInStream(is)); // end
																			// 读取整个页面内容
			}

			logger.info("end authSubmitGetRequest ,response=" + resultMap.get("responseMessage"));
			s2 = System.currentTimeMillis();
		}

		catch (java.net.ConnectException e) {
			logger.error("网络异常：url=" + url + " 错误消息:" + e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage() + " URL=" + url);
			// e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (response != null) {
					response.close();
				}
				post.releaseConnection();
				client.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		return resultMap;
	}

	private static String parseJesssionId(CloseableHttpResponse response) {
		String result = "";
		if (response.getHeaders("Set-Cookie") != null && response.getHeaders("Set-Cookie").length > 0) {
			String cookieSession = response.getHeaders("Set-Cookie")[0].toString();
			String[] cookies = cookieSession.split(";");
			for (String cookie : cookies) {
				int jsessionIndex = cookie.indexOf("JSESSIONID");
				if (jsessionIndex > 0) {
					return cookie.substring(jsessionIndex, cookie.length());
				}
			}
		}
		return result;
	}

	/**
	 * 提交 POST 请求到服务器
	 * 
	 * @param requestType
	 *            请求业务类型
	 * @param url
	 *            要POST的url，如果参数不多的话可以将参数直接设置到url中
	 * @param cookie
	 * @param referer
	 * @param param
	 *            要post到服务器的参数
	 * @return POST后的 response和cookie
	 */
	public static Map<String, String> submitPostRequest(String url, UrlEncodedFormEntity param,
			Map<String, String> requestHeaderMap) {

		// logger.info("begin submitPostRequest url=" + url);
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse response = null;
		InputStream is = null;
		CloseableHttpClient client = getHttpClient();
		HashMap<String, String> resultMap = new HashMap<String, String>();
		try {
			setRequestHeader(requestHeaderMap, post);
			if (param != null) {
				post.setEntity(param);

			}

			response = client.execute(post);
			String jsessoiId = parseJesssionId(response);
			resultMap.put("Cookie", jsessoiId);
			resultMap.put("status", response.getStatusLine().getStatusCode() + "");
			HttpEntity entity = response.getEntity();
			if (entity != null) {

				is = entity.getContent();// start 读取整个页面内容
				resultMap.put("responseMessage", readResponseInStream(is)); // end
																			// 读取整个页面内容
			}

		} catch (java.net.ConnectException e) {
			logger.error("网络异常：url=" + url + " 错误消息:" + e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage() + " URL=" + url);
			// e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (response != null) {
					response.close();
				}
				post.releaseConnection();
				client.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		return resultMap;
	}

	private static String readResponseInStream(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	private static void setRequestHeader(Map<String, String> requestHeaderMap, HttpPost post) {
		if (requestHeaderMap != null) {
			Iterator iter = requestHeaderMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
				if (entry.getValue() != null) {
					post.setHeader(entry.getKey(), entry.getValue());

				}
			}
		}
	}

	/**
	 * 模拟登录服务器，调用API，并返回结果
	 * 
	 * @param baseUrl
	 * @param api
	 * @param params
	 * @param loginName
	 * @param password
	 * @return
	 */
	public static String callApi(String baseUrl, String api, String params, String loginName, String password) {
		JSONObject result = new JSONObject();
		String loginUrl = baseUrl + "/login";
		Map<String, String> loginParams = new HashMap<String, String>();
		loginParams.put("username", "admin");
		loginParams.put("password", "zaq1234");
		try {
			Map<String, String> sessoinMap = submitPostRequest(loginUrl, loginParams, null);
			String session = sessoinMap.get("Cookie");
			if (session != null && !session.equals("")) {
				Map<String, String> cookieMap = new HashMap<String, String>();
				cookieMap.put("Cookie", session);
				HashMap<String, String> paramsw = new HashMap<String, String>();
				paramsw.put("params", params);
				Map<String, String> resultMap = submitPostRequest(baseUrl + "/api/" + api, paramsw, cookieMap);
				return resultMap.get("responseMessage");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 提交GET 请求到服务器
	 * 
	 * @param requestType
	 *            请求业务类别
	 * @param url
	 *            请求url
	 * @param cookie
	 * @param referer
	 * @return 请求的response和cookie
	 */
	public static Map<String, String> submitGetRequest(String url, Map<String, String> requestHeaderMap) {

		HttpGet post = new HttpGet(url);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		CloseableHttpResponse response = null;
		try {
			if (requestHeaderMap != null) {
				Iterator iter = requestHeaderMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
					if (entry.getValue() != null) {
						post.setHeader(entry.getKey(), entry.getValue());
					}
				}
			}

			CloseableHttpClient client = getHttpClient();
			response = client.execute(post);
			StringBuffer cookies = new StringBuffer();
			Header[] hs = response.getAllHeaders();
			for (Header h : hs) {

				if ("Set-Cookie".equals(h.getName())) {
					cookies.append(h.getValue()).append(";");
				}
			}
			resultMap.put("status", response.getStatusLine().getStatusCode() + "");
			resultMap.put("cookieVal", StringUtil.getNullStr(cookies).replace(" Path=/; Secure; HttpOnly;", ""));
			resultMap.put("responseMessage", EntityUtils.toString(response.getEntity()));
		}

		catch (java.net.ConnectException e) {
			logger.error("网络异常：" + e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
			// e.printStackTrace();
		} finally {
			try {
				post.releaseConnection();
				// response.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		return resultMap;
	}

	/**
	 * 获取文件类型 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	private static String getFileType(String fileName) {
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}

	private final static String CRLF = "\r\n";
	private final static String twoHyphens = "--";
	private final static String BOUNDARY = "----WebKitFormBoundary3mCxd7ijjdKKBtAZ";

	public static Map<String, String> postFileUploadRequest(String post_url, Map<String, String> requestHeaderMap,
			String filename, String filepath) {

		java.net.URLConnection connection = getPostConn(post_url, requestHeaderMap);

		StringBuffer responseMessage = new StringBuffer();
		InputStream in = null;
		BufferedReader br = null;
		OutputStream output = null;
		InputStream fis = null;
		HttpClient client = new DefaultHttpClient();
		logger.info("in postFileUploadRequest filepath=" + filepath);
		HttpGet httpGet = new HttpGet(filepath);
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer resSB = new StringBuffer();
		StringBuffer resSB2 = new StringBuffer();
		try {
			// 从七牛下载文件，并上传到微信

			HttpResponse response = client.execute(httpGet);
			fis = response.getEntity().getContent();

			/**
			 * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
			 * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
			 */

			BufferedInputStream bis = new BufferedInputStream(fis);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int ch;
			while ((ch = bis.read()) != -1)
				baos.write(ch);
			byte[] fileData = baos.toByteArray();
			String filetype = getFileType(filename);

			// logger.info("fileData1=" + fileData);
			// logger.info("fileData length=" + fileData.length);
			// 设置request payload参数

			resSB.append("--" + BOUNDARY);
			resSB.append(CRLF);
			resSB.append("Content-Disposition: form-data; name=\"");
			resSB.append("uploadfile");
			resSB.append("\"; filename=\"").append(filename).append("\"");
			resSB.append(CRLF);
			resSB.append("Content-Type: ").append("image/" + filetype);
			resSB.append(CRLF);
			resSB.append(CRLF);

			resSB2.append(CRLF);
			resSB2.append(twoHyphens + BOUNDARY);
			resSB2.append(CRLF);
			resSB2.append("Content-Disposition: form-data; name=\"");
			resSB2.append("formId").append("\"");
			resSB2.append(CRLF);
			resSB2.append(CRLF);
			resSB2.append(twoHyphens + BOUNDARY + twoHyphens);
			resSB2.append(CRLF);

			logger.info("resSB=\r\n" + resSB.toString());

			// 设置HTTP头:
			connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			connection.setRequestProperty("Content-Length", String.valueOf(
					resSB.toString().getBytes().length + fileData.length + resSB2.toString().getBytes().length));
			output = connection.getOutputStream();

			// 设置参数
			output.write(resSB.toString().getBytes());
			// 传输文件
			output.write(fileData);

			// 设置参数
			output.write(resSB2.toString().getBytes());

			output.flush();

			int charCount = -1;
			in = connection.getInputStream();

			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			while ((charCount = br.read()) != -1) {
				responseMessage.append((char) charCount);
			}

			map.put("responseMessage", responseMessage.toString());
		} catch (Exception ex) {
			logger.error("resSB=\r\n" + resSB.toString());
			logger.error("resSB=\r\n" + resSB2.toString());
			ex.printStackTrace();
		} finally {

			httpGet.releaseConnection();
			try {
				if (in != null) {
					in.close();
				}
				if (fis != null) {
					fis.close();
				}

				if (output != null) {
					output.close();
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

		}
		return map;
	}

	private static URLConnection getPostConn(String post_url, Map<String, String> requestHeaderMap) {
		java.net.URLConnection connection;
		try {
			connection = new java.net.URL(post_url).openConnection();
			connection.setDoOutput(true);
			/**
			 * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
			 * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
			 */
			// 发送cookie信息上去，以表明自己的身份，否则会被认为没有权限
			Iterator iter = requestHeaderMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
				if (entry.getValue() != null) {
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			return connection;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}

	}

}
