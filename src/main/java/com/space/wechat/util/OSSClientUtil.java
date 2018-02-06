package com.space.wechat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectResult;

@Component
@Transactional(readOnly = true)
public class OSSClientUtil {
	public static final String[] audioFileTypes = new String[] { "mp3", "wav", "wma", "mid", "m4a" };

	public static final String[] videoFileTypes = new String[] { "asx", "asf", "mpg", "wmv", "3gp", "mov", "avi", "flv",
			"mp4" };

	public static final String[] imageFileTypes = new String[] { "jpg", "gif", "png", "webp", "bmp", "tiff" };

	public static final String[] OSSimageFileTypes = new String[] { "jpg", "webp", "png", "bmp" };

	public static final String ALI_AK = "0DJchGp6j9IuK8iN";
	public static final String ALI_SK = "ZQqLmDRCX9i3khARyuV6FoGBdFsZlL";
	private static final String BUCKET_NAME = "weixt-static";
	private static final String BUCKET_NAME_EXPORT = "weixt-export";
	// private static final String ENDPOINT =
	// "http://oss-cn-qingdao-internal.aliyuncs.com";
	private static final String ENDPOINT = "http://oss-cn-qingdao.aliyuncs.com";
	public static final String ALI_IMAGE_URL = "http://static.weixiaotong.com.cn/";
	public static final String ALI_IMAGE_URL1 = "https://static.weixiaotong.com.cn";
	public static final String ALI_COMMON_URL = "http://weixt-static.oss-cn-qingdao.aliyuncs.com/";
	public static final String ALI_EXPORT_URL = "http://weixt-export.oss-cn-qingdao.aliyuncs.com/";
	public static final String ALI_EXPORT_WEIXT_URL = "http://weixt-export.weixiaotong.com.cn/";

	// 在上传到服务器的时候，是否在本地服务器也保存一份
	public static Boolean saveLocalFile = false;
	public static String localFileDic = "";

	private static Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);

	private static OSSClient getOSSClient() {
		return new OSSClient(ENDPOINT, ALI_AK, ALI_SK);

	}

	/**
	 * 判断是否属于视频mp3
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean getFileTypeIsVideo(String fileType) {
		boolean isVideo = Arrays.<String>asList(videoFileTypes).contains(fileType);
		return isVideo;
	}

	/**
	 * 判断是否属于视频阿里OSS图片处理支持的格式
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean getFileTypeIsOSSImageSupport(String fileType) {
		boolean isVideo = Arrays.<String>asList(OSSimageFileTypes).contains(fileType);
		return isVideo;
	}

	/**
	 * 判断是否属于视频mp3
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean getFileTypeIsAudio(String fileType) {
		boolean isAudio = Arrays.<String>asList(audioFileTypes).contains(fileType);
		return isAudio;
	}

	/**
	 * 判断是否属于图片
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean getFileTypeIsImage(String fileType) {
		boolean isAudio = Arrays.<String>asList(imageFileTypes).contains(fileType);
		return isAudio;
	}

	/**
	 * 获取文件类型 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName) {
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}

	/**
	 * 通过文件名读取文件，并提交到阿里OSS上，上传文件后将原始文件删除。
	 * 
	 * @param filename
	 * @return 阿里OSS上的文件名
	 */
	public static String putFile(String filename, Boolean compressFlag) throws Exception {
		logger.info("put qiniu filename=" + filename);
		String filetype = FileUtil.getFileType(filename);

		if (filetype != null) {
			filetype = filetype.toLowerCase();
			if (filetype.equals("jpeg")) {
				filetype = "jpg";
			}
		}

		// if (compressFlag == true
		// && (filetype.equals("jpg") || filetype.equals("bmp")
		// || filetype.equals("png") || filetype.equals("gif"))) {
		// // ImgCompressUtil.compressPic(filename);
		// }

		String filekey = FileHashUtil.getSHA1Checksum(filename) + System.currentTimeMillis() + "." + filetype;

		String flvUrl = "";
		String mp4Url = "";
		String result = "";
		if (getFileTypeIsVideo(filetype) && compressFlag) {
			// if (!new File(FfmpegUtil.TEMPPATH + filekey +
			// ".HD.flv").exists()) {
			// FfmpegUtil.encodeToFlvVideo(filename, filekey);
			// }
			// flvUrl = ALI_URL
			// + putOSSFile(FfmpegUtil.TEMPPATH + filekey + ".HD.flv");
			String mp4file = FfmpegUtil.encodeToMp4Video(filename, filekey);
			mp4Url = ALI_COMMON_URL + putOSSFile(mp4file, BUCKET_NAME);

			result = mp4Url;
		} else if (getFileTypeIsOSSImageSupport(filetype)) {

			result = ALI_IMAGE_URL + putOSSFile(filename, BUCKET_NAME);
		} else {
			result = ALI_COMMON_URL + putOSSFile(filename, BUCKET_NAME);
		}
		if (StringUtil.isNotEmpty(result)) {
			cleanFiles(filename, filekey);
			return result;
		} else {
			throw new FilePutErrorException("result：" + result);
		}
	}

	/**
	 * 通过文件名读取文件（主要针对用户需要导出），并提交到阿里OSS上，上传文件后将原始文件删除。
	 * 
	 * @param filename
	 * @return 阿里OSS上的文件名
	 */
	public static String putExportFile(String filename) throws Exception {
		String filetype = FileUtil.getFileType(filename);

		String filekey = FileHashUtil.getSHA1Checksum(filename) + System.currentTimeMillis() + "." + filetype;

		String result = ALI_EXPORT_WEIXT_URL + putOSSFile(filename, BUCKET_NAME_EXPORT);
		if (StringUtil.isNotEmpty(result)) {
			cleanFiles(filename, filekey);
			return result;
		} else {
			throw new FilePutErrorException("result：" + result);
		}
	}

	/**
	 * 通过文件名读取文件（主要针对用户需要导出），并提交到阿里OSS上，上传文件后将原始文件删除。
	 * 注意：本方法将使用原始文件名，而不是hash过的文件名
	 * 
	 * @param filename
	 * @return 阿里OSS上的文件名
	 */
	public static String putExportFileWithOrignName(String filename) throws Exception {
		String filekey = FileUtil.getOrignFileName(filename);

		String result = ALI_EXPORT_WEIXT_URL + putOSSFileWithOrignFileName(filename, BUCKET_NAME_EXPORT);
		if (StringUtil.isNotEmpty(result)) {
			cleanFiles(filename, filekey);
			return result;
		} else {
			throw new FilePutErrorException("result：" + result);
		}
	}

	public static void saveLocalFile(String filename, String hashfilename) {

		try {
			if (saveLocalFile) {
				copyByChannel(filename, localFileDic + "/" + hashfilename);
			}
		} catch (Exception e) {

			e.printStackTrace();
			logger.error("存储本地文件出错", e);
		}
	}

	public static void copyByChannel(String sourceFileName, String targgetFileName) {

		File sourceFile = new File(sourceFileName);
		File targgetFile = new File(targgetFileName);
		if (!sourceFile.exists()) {
			logger.error("源文件不存在！");
			return;
		}
		if (targgetFile.exists()) {
			logger.info("文件已经存在！");
			return;
		}
		int length = 2097152;
		FileInputStream in = null;
		FileOutputStream out = null;
		FileChannel inC = null, outC = null;

		try {
			in = new FileInputStream(sourceFile);
			out = new FileOutputStream(targgetFile);
			inC = in.getChannel();
			outC = out.getChannel();
			ByteBuffer b = null;

			while (true) {
				if (inC.position() == inC.size()) {
					return;
				}
				if ((inC.size() - inC.position()) < length) {
					length = (int) (inC.size() - inC.position());
				} else
					length = 2097152;
				b = ByteBuffer.allocateDirect(length);
				inC.read(b);
				b.flip();
				outC.write(b);
				outC.force(false);
			}
		} catch (Exception e) {
			logger.error("copy file error,", e);
		} finally {
			try {
				if (inC != null) {
					inC.close();
				}
				if (outC != null) {
					outC.close();
				}
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				logger.error("close file error,", e);
			}
		}
	}

	/**
	 * 通过文件名读取文件，并提交到阿里OSS上，上传文件后将原始文件删除。
	 * 
	 * @param filename
	 * @return 阿里OSS上的文件名
	 */
	public static Map putFileAndThumbnail(String filename, Boolean compressFlag) throws Exception {
		logger.info("put qiniu filename=" + filename);
		String filetype = FileUtil.getFileType(filename);

		if (filetype != null) {
			filetype = filetype.toLowerCase();
			if (filetype.equals("jpeg")) {
				filetype = "jpg";
			}
		}

		String filekey = FileHashUtil.getSHA1Checksum(filename) + System.currentTimeMillis() + "." + filetype;
		String mp4Url = "";
		Map<String, String> result = new HashMap<String, String>();
		// 存储文件到本地

		result.put("filetype", filetype);
		if (getFileTypeIsVideo(filetype)) {
			String makeImgbyvideoFilename = FfmpegUtil.makeImgbyvideo(filename, filekey);
			if (compressFlag) {
				String mp4file = FfmpegUtil.encodeToMp4Video(filename, filekey);
				mp4Url = ALI_COMMON_URL + putOSSFile(mp4file, BUCKET_NAME);
				result.put("url", mp4Url);
			} else {
				result.put("url", ALI_COMMON_URL + putOSSFile(filename, BUCKET_NAME));
			}

			if (StringUtil.isNotEmpty(makeImgbyvideoFilename)) {
				File imagefile = new File(makeImgbyvideoFilename);
				if (!imagefile.exists()) {
					Thread.sleep(500);
				}
				if (imagefile.exists()) {
					result.put("picUrl", ALI_IMAGE_URL + putOSSFile(makeImgbyvideoFilename, BUCKET_NAME));
				} else {
					result.put("picUrl", "");
					logger.error("获取缩略图失败：" + makeImgbyvideoFilename);
				}
			} else {
				result.put("picUrl", "");
			}
		} else if (getFileTypeIsOSSImageSupport(filetype)) {
			String url = ALI_IMAGE_URL + putOSSFile(filename, BUCKET_NAME);
			result.put("url", url);
			result.put("picUrl", url);
		} else {
			result.put("url", ALI_COMMON_URL + putOSSFile(filename, BUCKET_NAME));
		}
		if (!result.isEmpty()) {
			cleanFiles(filename, filekey);
			return result;
		} else {
			throw new FilePutErrorException("转码失败！");
		}
	}

	private static void cleanFiles(String filename, String filekey) {
		FileUtil.delDiskFile(null, filename);
		FileUtil.delDiskFile(FfmpegUtil.TEMPPATH, filekey + ".HD.flv");
		FileUtil.delDiskFile(FfmpegUtil.TEMPPATH, filekey + ".HD.mp4");
		FileUtil.delDiskFile(FfmpegUtil.TEMPPATH, filekey + ".bmp");
	}

	public static String putOSSFileWithOrignFileName(String filename, String bucket_name) throws Exception {
		String filetype = getFileType(filename);
		if (filetype != null) {
			filetype = filetype.toLowerCase();
			if (filetype.equals("jpeg")) {
				filetype = "jpg";
			}

		}

		String filekey = FileUtil.getOrignFileName(filename);

		try {
			getFileInfo(filekey, bucket_name);
			return filekey;
		} catch (Exception e) {
		}
		// 初始化OSSClient
		OSSClient client = getOSSClient();

		// 获取指定文件的输入流
		File file = new File(filename);
		InputStream content = new FileInputStream(file);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();

		// 必须设置ContentLength
		meta.setContentLength(file.length());

		// 上传Object.
		PutObjectResult result = client.putObject(bucket_name, filekey, content, meta);

		// 打印ETag
		System.out.println(result.getETag());
		// 上传以后删除文件
		// file.delete();
		System.out.println("文件上传成功，远程文件名：" + filekey + " 本地文件名：" + filename + " 文件大小:" + file.length() / 1024 + "k");
		return filekey;
	}

	public static String putOSSFile(String filename, String bucket_name) throws Exception {
		String filetype = getFileType(filename);
		if (filetype != null) {
			filetype = filetype.toLowerCase();
			if (filetype.equals("jpeg")) {
				filetype = "jpg";
			}

		}

		String filekey = FileHashUtil.getSHA1Checksum(filename) + "." + filetype;

		saveLocalFile(filename, filekey);
		try {
			getFileInfo(filekey, bucket_name);
			return filekey;
		} catch (Exception e) {
		}
		// 初始化OSSClient
		OSSClient client = getOSSClient();

		// 获取指定文件的输入流
		File file = new File(filename);
		InputStream content = new FileInputStream(file);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();

		// 必须设置ContentLength
		meta.setContentLength(file.length());

		// 上传Object.
		PutObjectResult result = client.putObject(bucket_name, filekey, content, meta);

		// 打印ETag
		System.out.println(result.getETag());
		// 上传以后删除文件
		// file.delete();
		System.out.println("文件上传成功，远程文件名：" + filekey + " 本地文件名：" + filename + " 文件大小:" + file.length() / 1024 + "k");
		return filekey;
	}

	/**
	 * 通过文件的HASH值获取阿里OSS文件信息
	 * 
	 * @param hashfilename
	 * @return
	 * @return
	 * @throws Exception
	 */
	public static OSSObject getFileInfo(String key, String bucket_name) throws IOException {

		// 初始化OSSClient
		OSSClient client = getOSSClient();

		// 获取Object，返回结果为OSSObject对象
		return client.getObject(bucket_name, key);

	}

	public static void listObjects(String bucket_name) {

		// 初始化OSSClient
		OSSClient client = getOSSClient();

		// 获取指定bucket下的所有Object信息
		ObjectListing listing = client.listObjects(bucket_name);

		// 遍历所有Object
		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
			System.out.println(objectSummary.getKey());
		}
	}

	public static void main(String[] args) {
		// UploadFile uploadFile = new UploadFile("d:\\ffmpeg\\input\\1111.mp4",
		// "111", "111");
		// try {
		// OSSClientUtil.putFile(uploadFile);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		try {
			System.out.print(putExportFile("/Users/yejianfei/git/json-to-csv/src/com/jsontocsv/aaaa1473572306587.csv"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// copyByChannel("/Users/yejianfei/WORK/share/1.jpg",
		// "/Users/yejianfei/WORK/share/abcde121.jpg");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	/**
	 * 获取通过前端页面直接上传阿里云oss服务器的，相关签名和认证码
	 * 
	 * @return
	 */
	public String getOSSPolicyAndSignature(JSONObject params) {
		Map<String, String> resutlMap = new HashMap<String, String>();

		try {
			OSSClient client = getOSSClient();

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 10);// 有效期10分钟

			PolicyConditions policyConditions = new PolicyConditions();

			policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 104857600);

			String policy = client.generatePostPolicy(calendar.getTime(), policyConditions);

			String signature = client.calculatePostSignature(policy);
			policy = new String(Base64.encodeBase64(policy.getBytes("UTF-8")));

			resutlMap.put("policy", policy);
			resutlMap.put("signature", signature);
			resutlMap.put("OSSAccessKeyId", ALI_AK);
			resutlMap.put("bucket", ALI_IMAGE_URL1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("获取通过前端页面直接上传阿里云oss服务器的，相关签名和认证码 错误:" + e.getMessage());
		}

		return JSON.toJSONString(resutlMap);
	}

}
