package com.space.wechat.util;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.Entry;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

public class QiniuUtil {

	public static final String QINIU_AK = "JHFvEVcb990q5oBPkAr--ycsnYjLmaFawFIGTee7";
	public static final String QINIU_SK = "wyNhZNUPpRgB40zn7pf-C-_GBMarKIPQKNQEF2hG";
	private static final String BUCKET_NAME = "jeff-ye-1978";
	private static final String QINIU_URL = "http://jeff-ye-1978.qiniudn.com/";
	private static final int FILE_EXISTS = 200;
	private static final int FILE_PUT_SUCCESS = 200;

	private static Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

	private static Mac getMac() {
		Config.ACCESS_KEY = QINIU_AK;
		Config.SECRET_KEY = QINIU_SK;
		return new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);

	}

	private static String getUpToken() {

		PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
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

	private static String getMediaUpToken(String fileType) {
		PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
		if (FileUtil.getFileTypeIsAudio(fileType)) {
			putPolicy.asyncOps = "avthumb/mp3";
		} else if (FileUtil.getFileTypeIsVideo(fileType)) {
			putPolicy.asyncOps = "avthumb/mp4";
		}

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

		String filename = "/Users/yejianfei/16f35084fa93eb8ed7fa455a14f347da.jpg";
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
		return OSSClientUtil.putFile(filename, true);
	}

	/**
	 * 如果属于视频，不进行转码
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static String putFileNoFfmpeg(String filename) throws Exception {
		return OSSClientUtil.putFile(filename, false);
	}

	public static String putImageFile(String filename) throws Exception {
		return OSSClientUtil.putFile(filename, false);
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
