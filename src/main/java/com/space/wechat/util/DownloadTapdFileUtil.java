package com.space.wechat.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTapdFileUtil {
	public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置超时间为3秒
		conn.setConnectTimeout(3 * 1000);
		// 防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("authority", "file.tapd.cn");
		conn.setRequestProperty("method", "GET");
		conn.setRequestProperty("path", "/tfl//captures/2017-07/tapd_20024831_base64_1500010859_37.png");
		conn.setRequestProperty("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		conn.setRequestProperty("scheme", "https");
		conn.setRequestProperty("scheme", "https");
		conn.setRequestProperty("accept-encoding", "gzip, deflate, br");
		conn.setRequestProperty("accept-language", "zh-CN,zh;q=0.8,en;q=0.6");
		conn.setRequestProperty("cache-control", "no-cache");
		conn.setRequestProperty("cookie",
				"pgv_pvi=2963597312; track_assignment_by_role=developer; CAKEPHP=bj8f977rnvq6fbht8hk44m8a93; t_u=68fd5ec58b8e750c39b8f83613c42bd5dae8d9bc14af0dbb0aa28c699a0630fbeda1e965fbfa66a1%7C1");

		conn.setRequestProperty("pragma", "no-cache");
		conn.setRequestProperty("upgrade-insecure-requests", "1");
		conn.setRequestProperty("user-agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		// 得到输入流
		InputStream inputStream = conn.getInputStream();
		// 获取自己数组
		byte[] getData = readInputStream(inputStream);

		// 文件保存位置
		File saveDir = new File(savePath);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		File file = new File(saveDir + File.separator + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(getData);
		if (fos != null) {
			fos.close();
		}
		if (inputStream != null) {
			inputStream.close();
		}

		System.out.println("info:" + url + " download success");

	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	public static void main(String[] args) {
		try {
			downLoadFromUrl("https://file.tapd.cn/tfl//captures/2017-07/tapd_20024831_base64_1500010859_37.png",
					"1.png", "/Users/yejianfei/Documents/mygo/");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
