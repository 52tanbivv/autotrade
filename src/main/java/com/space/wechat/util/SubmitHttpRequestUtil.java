package com.space.wechat.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.wechat.common.ConstantVar;
import com.space.wechat.exception.ApiException;

/**
 * 模拟发送HTTP 请求的工具类
 * 
 * @author yejianfei
 * 
 */
public class SubmitHttpRequestUtil {

	private static Logger logger = LoggerFactory.getLogger(SubmitHttpRequestUtil.class);

	private final static String CRLF = "\r\n";
	private final static String twoHyphens = "--";
	private final static String BOUNDARY = "----WebKitFormBoundary3mCxd7ijjdKKBtAZ";

	/**
	 * 提交GET 请求到服务器
	 *
	 * @param url
	 * @return
	 */
	public static JSONObject submitGetRequest(String webPage, String name, String password) {

		try {
			String authString = name + ":" + password;
			System.out.println("auth string: " + authString);
			byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			System.out.println("Base64 encoded auth string: " + authStringEnc);
			URL url = new URL(webPage);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			is.close();
			String result = sb.toString();
			return JSON.parseObject(StringUtil.unicodeToString(result));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new ApiException("MalformedURLException:" + e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApiException("Exception:" + e.toString());
		}
	}

	/**
	 * 提交POST 请求到服务器
	 *
	 * @param url
	 * @return
	 */
	public static JSONObject submitPostRequest(String webPage, String name, String password, String param) {
		return new JSONObject();
		// try {
		// byte[] data = param.getBytes("UTF-8");
		// String authString = name + ":" + password;
		// System.out.println("auth string: " + authString);
		// byte[] authEncBytes =
		// Base64.getEncoder().encode(authString.getBytes());
		// String authStringEnc = new String(authEncBytes);
		// System.out.println("Base64 encoded auth string: " + authStringEnc);
		// URL url = new URL(webPage);
		// HttpURLConnection urlConnection = (HttpURLConnection)
		// url.openConnection();
		// urlConnection.setRequestMethod("POST");
		// urlConnection.setRequestProperty("Authorization", "Basic " +
		// authStringEnc);
		// urlConnection.setDoOutput(true);
		// OutputStream outStream = urlConnection.getOutputStream();
		// outStream.write(data);
		// outStream.flush();
		// outStream.close();
		// System.out.println(urlConnection.getResponseCode());
		// if (urlConnection.getResponseCode() != 200) {
		// throw new ApiException("post请求失败");
		// }
		// InputStream is = urlConnection.getInputStream();
		// InputStreamReader isr = new InputStreamReader(is);
		// int numCharsRead;
		// char[] charArray = new char[1024];
		// StringBuffer sb = new StringBuffer();
		// while ((numCharsRead = isr.read(charArray)) > 0) {
		// sb.append(charArray, 0, numCharsRead);
		// }
		// is.close();
		// String result = sb.toString();
		// return JSON.parseObject(StringUtil.unicodeToString(result));
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// throw new ApiException("MalformedURLException:" + e.toString());
		// } catch (IOException e) {
		// e.printStackTrace();
		// throw new ApiException("Exception:" + e.toString());
		// }
	}

	/**
	 * POST 请求的方法
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
	public static Map<String, String> postRequest(String url, UrlEncodedFormEntity param,
			Map<String, String> requestHeaderMap) {

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		Map<String, String> resultMap = new HashMap<String, String>();
		try {

			Iterator<Entry<String, String>> iter = requestHeaderMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
				if (entry.getValue() != null) {
					post.setHeader(entry.getKey(), entry.getValue());

				}
			}
			if (param != null) {
				post.setEntity(param);
			}

			HttpResponse response = client.execute(post);
			StringBuffer cookies = new StringBuffer();
			Header[] hs = response.getAllHeaders();
			for (Header h : hs) {

				if ("Set-Cookie".equals(h.getName())) {
					cookies.append(h.getValue()).append(";");
				}
			}

			resultMap.put("cookieVal", StringUtil.getNullStr(cookies).replace(" Path=/; Secure; HttpOnly;", ""));
			resultMap.put("responseMessage", EntityUtils.toString(response.getEntity()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				post.releaseConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return resultMap;
	}

	/**
	 * 从resonse中解析出Errcode
	 * 
	 * @param msg
	 * @return
	 */
	public static Integer getErrcode(String responseMessage) {

		JSONObject jo = JSON.parseObject(responseMessage);

		try {
			return jo.getInteger(ConstantVar.KEY_ERROR_CODE);
		} catch (Exception e) {
			logger.error("getErrcode 请求失败 getMessage =" + e.getMessage());
			return -1;
		}
	}

	/**
	 * 从resonse中解析出Errcode
	 * 
	 * @param msg
	 * @return
	 */
	public static String getErrmsg(String responseMessage) {

		JSONObject jo = JSON.parseObject(responseMessage);

		try {

			return jo.getString(ConstantVar.KEY_ERROR_MSG);

		} catch (Exception e) {
			logger.error("getErrmsg 请求失败");
			return null;
		}
	}

	public static UrlEncodedFormEntity getPostParam(Map<String, String> param) {
		List<NameValuePair> params = getReqParamNVP(param);
		// 编码格式转换
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("getPostParam error", e);
		}
		return entity;
	}

	public static List<NameValuePair> getReqParamNVP(Map<String, String> param) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		java.util.Iterator<Entry<String, String>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			java.util.Map.Entry<String, String> entry = (java.util.Map.Entry<String, String>) it.next();
			// entry.getKey() 返回与此项对应的键
			// entry.getValue() 返回与此项对应的值
			// logger.info(entry.getValue());
			params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return params;
	}

	/**
	 * 非法关键词查询 action=checkkeyword&userid=12&account=账号&password=密码&content=内容
	 * 
	 * @param content
	 * @return
	 */
	public static Map<String, String> checkSmsReply(String content) {
		Map<String, String> map1 = new HashMap<String, String>();
		try {

			Map<String, String> data = new HashMap<String, String>();
			data.put("action", "checkkeyword");
			data.put("userid", userid);
			data.put("account", account);
			data.put("password", password);
			data.put("content", content);

			Map<String, String> map = SubmitHttpRequestUtil.postRequest(sendUrlUTF8,
					SubmitHttpRequestUtil.getPostParam(data), new HashMap<String, String>());
			map1 = readOneLayerXML(map.get("responseMessage"));
			logger.warn("sms非法字符校验:" + map1.get("message"));
			if (map1.get("message") == null || map1.get("message").indexOf("没有包含屏蔽词") >= 0
					|| checkSMSAllowStr(map1.get("message"))) {
				map1.put("status", "true");
				return map1;
			} else {
				map1.put("status", "false");
				return map1;
			}

		} catch (Exception e) {
			logger.error("checkSmsReply ERROR, content = " + content, e);
			map1.put("status", "true");
			return map1;
		}

	}

	public static Boolean checkSMSAllowStr(String message) {

		return false;
	}

	public static String sendUrlGBK = "http://sh.ipyy.com:8888/smsGBK.aspx";
	private static String sendUrlUTF8 = "http://sh.ipyy.com:8888/sms.aspx";
	public static String receiveUrl = "http://sh.ipyy.com:8888/callApi.aspx";// 取回复短信的地址

	public static String userid = "1591";
	public static String account = "jkcs48";
	public static String password = "jkcs48999";

	/**
	 * 查看发送后的结果，由于数据是单级封闭为XML，所以用map来组装 也可用于查看检查是否合法内容的方法
	 * 
	 * @param xml
	 * @return
	 */
	public static HashMap<String, String> readOneLayerXML(String xml) {
		HashMap<String, String> map = new HashMap<String, String>();
		DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder dombuilder;
		try {
			dombuilder = domfac.newDocumentBuilder();
			Document doc = dombuilder.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
			Element root = doc.getDocumentElement();
			NodeList nodes = root.getChildNodes();

			if (nodes != null) {
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					map.put(node.getNodeName(), node.getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			logger.error("readOneLayerXML error , xml = " + xml, e);
		} catch (SAXException e) {
			logger.error("readOneLayerXML error , xml = " + xml, e);
		} catch (IOException e) {
			logger.error("readOneLayerXML error , xml = " + xml, e);
		}
		return map;
	}

	//
	// /**
	// * java.net实现 HTTP POST方法提交
	// *
	// * @param url
	// * @param paramContent
	// * @return
	// */
	public static Map<String, String> submitPost(String url, String paramContent) {

		StringBuffer responseMessage = new StringBuffer();
		OutputStreamWriter reqOut = null;
		InputStream in = null;
		BufferedReader br = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			java.net.URLConnection connection = new java.net.URL(url).openConnection();
			connection.setDoOutput(true);
			/**
			 * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
			 * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
			 */
			reqOut = new OutputStreamWriter(connection.getOutputStream());
			if (paramContent != null) {
				reqOut.write(paramContent);

			}
			reqOut.flush();
			int charCount = -1;
			in = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			while ((charCount = br.read()) != -1) {
				responseMessage.append((char) charCount);
			}
			map.put("responseMessage", responseMessage.toString());
		} catch (Exception ex) {
			logger.error("submitPost error, url = " + url + " content = " + paramContent, ex);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (reqOut != null) {
					reqOut.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return map;
	}

	/**
	 * 提交GET 请求到服务器
	 * 
	 * @param url
	 * @return
	 */
	public static Map<String, String> authSubmitGetRequest(String url) {

		HttpClient client = new DefaultHttpClient();
		HttpGet post = new HttpGet(url);
		Map<String, String> resultMap = new HashMap<String, String>();
		HttpResponse response;
		try {

			response = client.execute(post);

			resultMap.put("responseMessage", EntityUtils.toString(response.getEntity(), "utf-8"));

			logger.info("end authSubmitGetRequest ,response=" + resultMap.get("responseMessage"));

		} catch (java.net.ConnectException e) {
			logger.error("authSubmitGetRequest 网络异常 ", e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (post != null) {
					post.releaseConnection();
				}

			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		return resultMap;
	}

	/**
	 * 获取媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param media_id
	 *            媒体文件id
	 * @param savePath
	 *            文件在服务器上的存储路径
	 */
	public static String downloadMedia(String accessToken, String mediaId, String savePath) {
		String filePath = null;
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		logger.info("IN downloadMedia requestUrl = " + requestUrl);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			StringBuffer responseMessage = new StringBuffer();

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}

			InputStream in = conn.getInputStream();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8096];
			int len;
			while ((len = in.read(buffer)) > -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			// 判断文件是否有效
			InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
			int charCount = -1;
			while ((charCount = stream1.read()) != -1) {
				responseMessage.append((char) charCount);
			}
			if (responseMessage.indexOf("invalid credential") > 0 || responseMessage.indexOf("invalid media_id") > 0) {
				logger.error("下载媒体文件失败 , responseMessage = " + responseMessage);
				return null;
			}
			stream1.close();
			// 生成文件
			InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
			BufferedInputStream bis = new BufferedInputStream(stream2);
			String filename = conn.getHeaderField("Content-disposition").replace("attachment; filename=\"", "")
					.replace("\"", "");
			// 根据内容类型获取扩展名
			// String fileExt = "jpg";
			FileUtil.makeDir(savePath);
			// 将mediaId作为文件名
			filePath = savePath + filename;

			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;

			while ((size = bis.read(buf)) != -1) {
				responseMessage.append((char) size);
				fos.write(buf, 0, size);
			}
			stream2.close();
			fos.close();
			bis.close();

			conn.disconnect();
			String info = String.format("下载媒体文件成功，filePath=" + filePath);

			logger.info("IN downloadMedia info = " + info);
		} catch (Exception e) {
			filePath = null;
			logger.error("下载媒体文件失败 , savePath = " + savePath, e);
		}
		return filePath;
	}

	/**
	 * 提交GET 请求到服务器
	 * 
	 * @param url
	 * @return
	 */
	public static Map<String, String> downloadMedia(String accessToken, String mediaId) {
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		logger.info("IN downloadMedia requestUrl = " + requestUrl);
		HttpClient client = new DefaultHttpClient();
		HttpGet post = new HttpGet(requestUrl);
		Map<String, String> resultMap = new HashMap<String, String>();
		HttpResponse response;
		try {

			response = client.execute(post);

			// resultMap.put("responseMessage",
			// EntityUtils.toString(response.getStatusLine()));

			logger.info("end downloadMedia ,response toString=" + response.toString());
			Header[] headerList = response.getAllHeaders();
			for (Header h : headerList) {

				logger.info("end headerList ,headervalue=" + h.getValue() + " name=" + h.getName());
			}

			logger.info("end downloadMedia ,response=" + response.getLastHeader("media"));

			logger.info("end downloadMedia ,response=" + resultMap.get("responseMessage"));

		} catch (java.net.ConnectException e) {
			logger.error("downloadMedia 网络异常 ", e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (post != null) {
					post.releaseConnection();
				}

			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		return resultMap;
	}

	/**
	 * 提交 POST 请求到腾讯服务器－官方接口调用
	 * 
	 * @param url
	 *            携带access_token
	 * @param param
	 * @return
	 */
	public static Map<String, String> authSubmitPostRequest(String url, String param) {

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			if (param != null) {
				post.setEntity(new StringEntity(param, "utf-8"));
			}
			HttpResponse response = client.execute(post);

			resultMap.put("responseMessage", EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			logger.error("authSubmitPostRequest ERROR , url = " + url + " ,param = " + param, e);
		} finally {
			try {
				post.releaseConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info(url + "**end authSubmitPostRequest  responseMessage=" + resultMap.get("responseMessage"));
		return resultMap;
	}

}
