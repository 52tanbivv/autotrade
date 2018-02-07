package com.space.wechat.util;


import java.io.File;

import javax.activation.MimetypesFileTypeMap;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.Entry;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

public class QiniuUtil_local {

	public static final String QINIU_AK = "SpQkFKWnTnILrSyuWh7s_15QyoqyIPiB5PTNPOFV";
	public static final String QINIU_SK = "ACZ5oFx9awPys_2Ux0dxz2Tah0PB4-Bl-DUYUmnh";
	private static final String BUCKET_NAME = "enter";
	private static final String QINIU_URL = "http://enter.u.qiniudn.com/";
	private static final int FILE_EXISTS = 200;
	private static final int FILE_PUT_SUCCESS = 200;

	private static Logger logger = LoggerFactory.getLogger(QiniuUtil_local.class);

	private static Mac getMac() {
		Config.ACCESS_KEY = QINIU_AK;
		Config.SECRET_KEY = QINIU_SK;
		return new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);

	}

	private static String getUpToken() {

		PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
		try {
			String upToken=putPolicy.token(getMac());
			return putPolicy.token(getMac());
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取uptoken失败！" + e.getMessage());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取uptoken失败！" + e.getMessage());
		}
	}
	
	private static String getVedioUpToken() {
		PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
		putPolicy.asyncOps="avthumb/mp4";
		try {
			return putPolicy.token(getMac());
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取uptoken失败！" + e.getMessage());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取uptoken失败！" + e.getMessage());
		}
	}
	private static String getAudioUpToken() {
		PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
		putPolicy.asyncOps="avthumb/mp3/ar/44100/ab/32k;avthumb/mp3/aq/6/ar/16000";
		try {
			return putPolicy.token(getMac());
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取uptoken失败！" + e.getMessage());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取uptoken失败！" + e.getMessage());
		}
	}
	public static void main(String[] args) {

		// 请确保该bucket已经存在

		String filename = "/Users/yejianfei/WORK/temp/TEMP/yxf2.jpg";
		try {
			File f = new File(filename);
			System.out.println(new MimetypesFileTypeMap().getContentType(f));
			System.out.print(putFile(filename));
			getFileInfo(filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 通过文件名读取文件，并提交到七牛云存储上，上传文件后将原始文件删除。
	 * 
	 * @param filename
	 * @return 七牛云服务上的文件名
	 * @throws 存储在七牛服务器上文件路径
	 */
	public static String putFile(String filename) throws Exception {
		logger.info("put qiniu filename=" + filename);
		String filetype = FileUtil.getFileType(filename);

		if (filetype != null) {
			filetype = filetype.toLowerCase();
			if (filetype.equals("jpeg")) {
				filetype = "jpg";
			}
		}

		if (filetype.equals("jpg") || filetype.equals("bmp")
				|| filetype.equals("png") || filetype.equals("gif")) {
			ImgCompressUtil.compressPic(filename);
		}

		String filekey = FileHashUtil.getSHA1Checksum(filename) + "."
				+ filetype;
		if (getFileInfo(filekey).getStatusCode() == FILE_EXISTS) {
			return QINIU_URL + filekey;
		}

		File file = new File(filename);
		
		PutExtra extra = new PutExtra();
		PutRet ret = IoApi.putFile(getVedioUpToken(), filekey, file, extra);
		// 上传以后删除文件
		// file.delete();
		logger.warn("文件上传七牛成功，远程文件名：" + filekey + " 本地文件名：" + filename
				+ " 文件大小:" + file.length() / 1024 + "k");
		if (ret.getStatusCode() == FILE_PUT_SUCCESS) {
			return QINIU_URL + ret.getKey();
		} else {
			throw new FilePutErrorException("失败原因：" + ret);
		}
	}

	/**
	 * 通过文件的HASH值获取七牛存储文件信息
	 * 
	 * @param hashfilename
	 * @return
	 * @throws Exception
	 */
	public static Entry getFileInfo(String hashfilename) throws Exception {
		RSClient client = new RSClient(getMac());
		return client.stat(BUCKET_NAME, hashfilename);
	}
	
	
}
