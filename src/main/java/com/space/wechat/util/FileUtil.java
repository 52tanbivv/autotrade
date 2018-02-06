package com.space.wechat.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.DeferredFileOutputStream;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;

public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static InputStream getInputStream(String strUrl) {
		int nStartPos = 0;
		HttpURLConnection urlconnection = null;
		URL url = null;
		InputStream fis = null;
		try {
			url = new URL(strUrl);
			urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestProperty("User-Agent", "Internet Explorer");
			String sProperty = "bytes=" + nStartPos + "-";

			// 告诉服务器book.rar这个文件从nStartPos字节开始传
			urlconnection.setRequestProperty("RANGE", sProperty);
			urlconnection.connect();
			fis = urlconnection.getInputStream();

			return fis;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
	}

	public static Map<String, Object> writeFile(String strUrl, String folder, String sPath) {
		int nStartPos = 0;
		int nRead = 0;
		BufferedInputStream bis = null;
		HttpURLConnection urlconnection = null;
		URL url = null;
		Map<String, Object> map = new HashMap<String, Object>();
		InputStream fis = null;
		try {
			makeDir(sPath + "/" + folder);
			url = new URL(strUrl);
			urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestProperty("User-Agent", "Internet Explorer");
			String sProperty = "bytes=" + nStartPos + "-";

			// 告诉服务器book.rar这个文件从nStartPos字节开始传
			urlconnection.setRequestProperty("RANGE", sProperty);
			urlconnection.connect();
			long nEndPos = getFileSize(urlconnection);
			map.put("filesize", getFileSize(nEndPos));

			fis = urlconnection.getInputStream();
			map.put("fis", fis);
			bis = new BufferedInputStream(fis);

			String filetype = HttpURLConnection.guessContentTypeFromStream(bis);
			String filename = folder + "/" + StringUtil.getIdGenerator() + "."
					+ filetype.substring(filetype.indexOf("/") + 1);
			RandomAccessFile oSavedFile = new RandomAccessFile(sPath + "/" + filename, "rw");
			byte[] b = new byte[1024];
			// 读取网络文件,写入指定的文件中
			while ((nRead = bis.read(b, 0, 1024)) > 0 && nStartPos < nEndPos) {
				oSavedFile.write(b, 0, nRead);
				nStartPos += nRead;
			}

			urlconnection.disconnect();

			try {
				filename = QiniuUtil.putFile(sPath + filename);

				map.put("filename", filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// 获得文件长度
	public static long getFileSize(HttpURLConnection httpConnection) {
		int nFileLength = -1;
		try {
			int responseCode = httpConnection.getResponseCode();
			if (responseCode >= 400) {
				logger.warn("Error Code : " + responseCode);
				return -2; // -2 represent access is error
			}
			String sHeader;
			for (int i = 1;; i++) {
				sHeader = httpConnection.getHeaderFieldKey(i);
				if (sHeader != null) {
					if (sHeader.equals("Content-Length")) {
						nFileLength = Integer.parseInt(httpConnection.getHeaderField(sHeader));
						break;
					}
				} else
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nFileLength;
	}

	/**
	 * 把输入流的内容，写到硬盘中，主要应用于图片上传的存储、相关支持性文件的上传
	 * 
	 * @param input
	 *            二进制输入流
	 * @param outPath
	 *            文件输出路径
	 * @return
	 */
	public static boolean writeByteFile(InputStream inputStream, String outPath, String fileName) {

		makeDir(outPath);

		File dir = new File(outPath, fileName);// 上传后写入硬盘的文件
		OutputStream outputStream = null;// 输出流写到硬盘
		try {
			outputStream = new FileOutputStream(dir);
		} catch (FileNotFoundException ex) {
			logger.error(ex.getMessage());
		}
		// 读取输入流，并由输出流写入硬盘
		try {
			byte[] b = new byte[1024];
			int len;
			while ((len = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, len);
			}
			outputStream.close();
			inputStream.close();

		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
		// 检查是否写入成功，成功的话给予提示 , 后续应该进行重构，保存和更新分离出来。
		return dir.isFile();

	}

	/**
	 * 创建文件夹;
	 * 
	 * @param sDir
	 *            需要创建的文件夹完整路径
	 * @return 成功创建||已经存在 true 创建失败 false
	 */
	public static boolean makeDir(String sDir) {
		File file = new File(sDir);
		return file.exists() ? true : file.mkdirs();
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
	 * 获取文件类型 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileWithoutType(String fileName) {
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * 获取文件类型 获取上传的文件名,先得到/的位置，再截取从 /的下一个位置到文件的最后.的值，最后得到文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		return fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("."));
	}

	/**
	 * 获取各类文件的,存数据库 如 学校代码＋文件夹类型（logo）/log10001.jpg
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFolder(String folder, String fileName) {
		return folder + "/" + fileName;
	}

	/**
	 * 设定文件存储路径,存数据库 如 logo/log10001.jpg
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getStoreFilePath(String path, String folder) {
		makeDir(path + "/" + folder);// 不存在创建
		return path + folder;
	}

	public static String imagePreview(HttpServletRequest request) throws ServletException, IOException {
		BASE64Encoder encoder = new BASE64Encoder();

		byte[] bs = null;
		long filesize = 0;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		int width = 0;
		int height = 0;
		InputStream inputStream = null;
		ByteArrayOutputStream op = null;
		DeferredFileOutputStream dfo = null;

		try {
			factory.setSizeThreshold(4096);// 设置缓冲,这个值决定了是fileinputstream还是bytearrayinputstream
			// factory.setRepository(new File("d:\\temp"));//设置临时存放目录,默认是new
			ServletFileUpload sfileUpLoad = new ServletFileUpload(factory);
			sfileUpLoad.setSizeMax(20 * 1024 * 1024);// 10M
			List items = sfileUpLoad.parseRequest(request);
			Iterator it = items.iterator();
			// 暂时只考虑单文件
			while (it.hasNext()) {
				FileItem fileItem = (FileItem) it.next();
				if (!fileItem.isFormField()) {
					inputStream = fileItem.getInputStream();
					filesize = fileItem.getSize();
					bs = new byte[(int) filesize];

				} else {
					if (fileItem.getFieldName().equals("width")) {
						dfo = (DeferredFileOutputStream) fileItem.getOutputStream();
						width = Integer.parseInt(new String(dfo.getData()));
					}
					if (fileItem.getFieldName().equals("height")) {
						dfo = (DeferredFileOutputStream) fileItem.getOutputStream();
						height = Integer.parseInt(new String(dfo.getData()));
					}
				}
			}

			// op = ResizeImg(inputStream, width, height);
			// bs = op.toByteArray();
			String imgpath = "data:image/jpeg;base64,";
			String img = encoder.encode(input2byte(inputStream));
			imgpath += img;

			return imgpath;
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dfo != null) {
				dfo.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (op != null) {
				op.close();
			}
		}
		// blankimage
		return "data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==";

	}

	public static final byte[] input2byte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

	// 只对jpg缩放
	public static ByteArrayOutputStream ResizeImg(InputStream inputStream, int maxWidth, int maxHeight)
			throws IOException {

		BufferedImage bufferimage = ImageIO.read(inputStream);
		int curWidth = bufferimage.getWidth();
		int curHeight = bufferimage.getHeight();

		// double desiredRatio =
		// Math.min((double)(maxWidth)/(double)curWidth,(double)maxHeight/(double)curHeight);
		// curWidth = (int) (curWidth*desiredRatio);
		// curHeight = (int)(curHeight*desiredRatio);
		double ratio = 0;
		Image itemp = bufferimage.getScaledInstance(maxWidth, maxHeight, bufferimage.SCALE_SMOOTH);
		// 计算比例
		if ((bufferimage.getHeight() > maxHeight) || (bufferimage.getWidth() > maxWidth)) {
			if (bufferimage.getHeight() > bufferimage.getWidth()) {
				ratio = (new Integer(maxHeight)).doubleValue() / bufferimage.getHeight();
			} else {
				ratio = (new Integer(maxWidth)).doubleValue() / bufferimage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			itemp = op.filter(bufferimage, null);
		}

		BufferedImage bufftmp = new BufferedImage(curWidth, curHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = bufftmp.createGraphics();

		g2D.setColor(Color.white);
		g2D.fillRect(0, 0, curWidth, curHeight);
		// g2D.drawImage(bufferimage, null, 0, 0);

		if (maxWidth == itemp.getWidth(null))
			g2D.drawImage(itemp, 0, (maxHeight - itemp.getHeight(null)) / 2, itemp.getWidth(null),
					itemp.getHeight(null), Color.white, null);
		else
			g2D.drawImage(itemp, (maxWidth - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null),
					Color.white, null);
		g2D.dispose();
		// itemp = bufftmp;

		ByteArrayOutputStream op = new ByteArrayOutputStream();
		// JPEGImageEncoder imageEncoder = JPEGCodec.createJPEGEncoder(op);
		// imageEncoder.encode((BufferedImage)itemp);
		return op;
	}

	public static String fileUploadProcess(List<FileItem> items, String folder, Long orgcode, String fileUploadPath,
			String delete_url) throws IOException {
		JSONArray json = new JSONArray();
		String qiniuUrl = null;
		try {
			int i = 0;
			for (FileItem item : items) {
				if (!item.isFormField()) {
					String fileHashSha1 = FileHashUtil.getSHA1Checksum(item.getInputStream());

					String fileType = FileUtil.getFileType(item.getName());

					String storeFilePath = FileUtil.getStoreFilePath(fileUploadPath, folder) + "/" + fileHashSha1 + "."
							+ fileType;
					System.out.println("****storeFilePath:" + storeFilePath);
					File file = new File(storeFilePath);

					JSONObject jsono = new JSONObject();
					jsono.put("name", item.getName());
					jsono.put("size", item.getSize());
					if (!FileUtil.getFileTypeIsPhoto(fileType)) {
						jsono.put("error", "文件格式不正确：只能上传gif,jpg,jpeg,png,bmp");
						json.put(jsono);

					} else {
						i = i + 1;
						item.write(file);
						qiniuUrl = QiniuUtil.putFile(storeFilePath);
						jsono.put("fileHashSha1", fileHashSha1);
						jsono.put("url", qiniuUrl);
						jsono.put("thumbnail_url", qiniuUrl);
						jsono.put("delete_url", delete_url + "?delfile=" + file.getName());
						jsono.put("delete_type", "GET");
					}

					json.put(jsono);
				}
			}
		} catch (FileUploadException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// writer.write(json.toString());
			// writer.close();
		}
		return json.toString();
	}

	public static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		StringBuilder sb = new StringBuilder();

		String line = null;

		try {

			while ((line = reader.readLine()) != null) {

				sb.append(line + "/n");

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				is.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		return sb.toString();

	}

	public static String fileUploadProcess(List<FileItem> items, String folder, Long orgcode, String fileUploadPath)
			throws IOException {
		String qiniuUrl = null;
		String fileHashSha1 = "";
		String filename = "";
		try {

			for (FileItem item : items) {
				if (item.getFieldName().equals("fileData")) {

					fileHashSha1 = FileHashUtil.getSHA1Checksum(item.getInputStream());

					String imgStr = convertStreamToString(item.getInputStream()).replace("data:image/jpeg;base64,", "");

					// System.out.println(imgStr);

					String fileType = "jpg";
					// FileUtil.getFileType(item.getName())
					filename = fileHashSha1 + "." + fileType;
					String storeFilePath = FileUtil.getStoreFilePath(fileUploadPath, folder) + "/" + filename;
					logger.info("****storeFilePath1:" + storeFilePath);

					GenerateImage(imgStr, storeFilePath);

					qiniuUrl = QiniuUtil.putFile(storeFilePath);

				}
			}
		} catch (Exception e) {
			logger.error("上传文件失败:" + e.getMessage());
			return "{\"errCode\":-1,\"message\":\"上传成功\",\"data\":{\"id\":\"" + fileHashSha1 + "\",\"picId\":\""
					+ filename + "\"}}";
		} finally {
			// writer.write(json.toString());
			// writer.close();
		}
		return "{\"errCode\":0,\"message\":\"上传成功\",\"data\":{\"id\":\"" + fileHashSha1.substring(10)
				+ "\",\"picId\":\"" + qiniuUrl + "\"}}";
	}

	public static String fileUploadProcess(List<FileItem> items, String folder, Long orgcode, String fileUploadPath,
			String delete_url, String needType) throws IOException {
		JSONArray json = new JSONArray();
		String qiniuUrl = null;
		try {
			int i = 0;
			for (FileItem item : items) {
				if (!item.isFormField()) {
					String fileHashSha1 = FileHashUtil.getSHA1Checksum(item.getInputStream());

					String fileType = FileUtil.getFileType(item.getName());
					String storeFilePath = FileUtil.getStoreFilePath(fileUploadPath, folder) + "/" + fileHashSha1 + "."
							+ fileType;

					File file = new File(storeFilePath);
					Long fileSize = item.getSize();
					JSONObject jsono = new JSONObject();
					jsono.put("name", item.getName());
					jsono.put("size", item.getSize());
					if (!FileUtil.getFileTypeIsPhoto(fileType) && needType.compareTo("image") == 0) {
						jsono.put("error", "文件格式不正确：只能上传gif,jpg,jpeg,png,bmp");
						json.put(jsono);
					} else if (!FileUtil.getFileTypeIsMedia(fileType) && needType.compareTo("media") == 0) {
						jsono.put("error", "文件格式不正确：只能上传mp3,wav,wma,mid,m4a,asx,asf,mpg,wmv,3gp,mov,avi,flv,mp4");
						json.put(jsono);
					} else if (fileSize > 51000000) {
						jsono.put("error", "文件不能大于50Mb");
						json.put(jsono);
					} else {
						i = i + 1;
						item.write(file);
						qiniuUrl = QiniuUtil.putFile(storeFilePath);
						jsono.put("fileHashSha1", fileHashSha1);
						jsono.put("url", qiniuUrl);
						jsono.put("thumbnail_url", qiniuUrl);
						jsono.put("delete_url", delete_url + "?delfile=" + file.getName());
						jsono.put("delete_type", "GET");
					}

					json.put(jsono);
				}
			}
		} catch (FileUploadException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// writer.write(json.toString());
			// writer.close();
		}
		return json.toString();
	}

	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			// imgFilePath =
			// "/Users/hbz/work/share/10004/cytd/grittt1122123.jpg";// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String fileUploadProcess(MultipartHttpServletRequest multipartRequest, String folder, Long orgcode,
			String fileUploadPath) throws IOException {
		MultipartFile multipartFile = multipartRequest.getFile("uploadFile");
		InputStream in = null;
		String fileName = null;
		String storeFilePath = null;
		String result = null;
		if (multipartFile != null && !multipartFile.isEmpty()) {
			// 上传文件本身的名字
			try {
				String filetype = FileUtil.getFileType(multipartFile.getOriginalFilename());
				in = multipartFile.getInputStream();
				// FileHashUtil.getSHA1Checksum(in)
				fileName = StringUtil.getIdGenerator() + "." + filetype;

				FileUtil.writeByteFile(in, FileUtil.getStoreFilePath(fileUploadPath, folder), fileName);
				storeFilePath = FileUtil.getFolder(folder, fileName);

				result = QiniuUtil.putFile(fileUploadPath + "/" + storeFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// FileUtil.ResizeImg(inputStream, maxWidth, maxHeight)
		}
		return result;
	}

	public static String fileUploadProcess1(MultipartHttpServletRequest multipartRequest, String folder, Long orgcode,
			String fileUploadPath) throws IOException {
		MultipartFile multipartFile = multipartRequest.getFile("uploadFile1");
		InputStream in = null;
		String fileName = null;
		String storeFilePath = null;
		String result = null;
		if (multipartFile != null && !multipartFile.isEmpty()) {
			// 上传文件本身的名字
			try {
				String filetype = FileUtil.getFileType(multipartFile.getOriginalFilename());
				in = multipartFile.getInputStream();
				// FileHashUtil.getSHA1Checksum(in)
				fileName = StringUtil.getIdGenerator() + "." + filetype;

				FileUtil.writeByteFile(in, FileUtil.getStoreFilePath(fileUploadPath, folder), fileName);
				storeFilePath = FileUtil.getFolder(folder, fileName);

				result = QiniuUtil.putFile(fileUploadPath + "/" + storeFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// FileUtil.ResizeImg(inputStream, maxWidth, maxHeight)
		}
		return result;
	}

	public static String videoUploadProcess(MultipartHttpServletRequest multipartRequest, String folder, Long orgcode,
			String fileUploadPath) throws IOException {
		MultipartFile multipartFile = multipartRequest.getFile("videoUpload");
		InputStream in = null;
		String fileName = null;
		String storeFilePath = null;
		String result = null;
		if (multipartFile != null && !multipartFile.isEmpty()) {
			// 上传文件本身的名字
			try {
				String filetype = FileUtil.getFileType(multipartFile.getOriginalFilename());
				in = multipartFile.getInputStream();
				// FileHashUtil.getSHA1Checksum(in)
				fileName = StringUtil.getIdGenerator() + "." + filetype;

				FileUtil.writeByteFile(in, FileUtil.getStoreFilePath(fileUploadPath, folder), fileName);
				storeFilePath = FileUtil.getFolder(folder, fileName);

				result = QiniuUtil.putFile(fileUploadPath + "/" + storeFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// FileUtil.ResizeImg(inputStream, maxWidth, maxHeight)
		}
		return result;
	}

	private static final String[] imgFileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

	// private static final String[] mediaFileTypes = new String[]
	// {"asx","asf","mpg","wmv","3gp","mov","avi","flv","mp4","mp3","m4a" };

	public static final String[] audioFileTypes = new String[] { "mp3", "wav", "wma", "mid", "m4a" };

	public static final String[] videoFileTypes = new String[] { "asx", "asf", "mpg", "wmv", "3gp", "mov", "avi", "flv",
			"mp4" };

	/**
	 * 判断是否属于图片
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean getFileTypeIsPhoto(String fileType) {
		return Arrays.<String>asList(imgFileTypes).contains(fileType.toLowerCase());
	}

	/**
	 * 判断是否属于视频mp3
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean getFileTypeIsMedia(String fileType) {
		boolean isVideo = getFileTypeIsAudio(fileType.toLowerCase());
		boolean isAudio = getFileTypeIsVideo(fileType.toLowerCase());
		return isVideo || isAudio;
	}

	/**
	 * 判断是否属于视频mp3
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean getFileTypeIsAudio(String fileType) {
		boolean isAudio = Arrays.<String>asList(audioFileTypes).contains(fileType.toLowerCase());
		return isAudio;
	}

	/**
	 * 判断是否属于视频mp3
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean getFileTypeIsVideo(String fileType) {
		boolean isVideo = Arrays.<String>asList(videoFileTypes).contains(fileType.toLowerCase());
		return isVideo;
	}

	public static boolean delDiskFile(String filepath, String filename) {
		File file = new File(filepath, filename);
		if (file.exists()) {
			file.delete(); // TODO:check and report success
		}
		return true;
	}

	public static String getFileSize(Long filesize) {
		DecimalFormat df = new DecimalFormat("###.##");
		float f;
		if (filesize < 1024 * 1024) {
			f = (float) ((float) filesize / (float) 1024);
			return (df.format(new Float(f).doubleValue()) + "KB");
		} else {
			f = (float) ((float) filesize / (float) (1024 * 1024));
			return (df.format(new Float(f).doubleValue()) + "MB");
		}
	}

	public static String fileUploadProcess(String imgStr, String fileUploadPath) throws IOException {
		String qiniuUrl = null;
		String filename = "";

		try {
			String fileType = "jpg";

			filename = StringUtil.getIdGenerator() + "." + fileType;
			String storeFilePath = fileUploadPath + "/" + filename;
			GenerateImage(imgStr, storeFilePath);

			qiniuUrl = QiniuUtil.putFile(storeFilePath);

		} catch (Exception e) {
			logger.error("作业管理-上传文件失败:" + e.getMessage());
		} finally {

		}
		return qiniuUrl;
	}

	/**
	 * 图片转化成base64字符串
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getImageStr(String filePath, String storeFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgFile = "";
		byte[] data = null;
		try {
			String fileType = "jpg";
			String filename = StringUtil.getIdGenerator() + "." + fileType;
			storeFilePath = storeFilePath + "/" + filename;
			imgFile = FileUtil.download(filePath, storeFilePath);
			// 待处理的图片
			InputStream in = null;

			// 读取图片字节数组
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static String download(String filePath, String loadPath) throws Exception {
		// 构造URL
		URL url = new URL(filePath);
		// 打开URL连接
		URLConnection con = url.openConnection();
		// 得到URL的输入流
		InputStream input = con.getInputStream();
		// 设置数据缓冲
		byte[] bs = new byte[1024 * 2];
		// 读取到的数据长度
		int len;
		// 输出的文件流保存图片至本地
		OutputStream os = new FileOutputStream(loadPath);
		while ((len = input.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.close();
		input.close();
		return loadPath;
	}

	/**
	 * 图片上传到七牛上，并返回图片地址.
	 * 
	 * @param params
	 * @param orgCode
	 * @return
	 */
	public static String getQiNiuPhotoPath(String photoUrls, Long orgCode) {
		if (!StringUtils.isNotEmpty(photoUrls)) {
			return "";
		} else {
			String[] arrPhotoUrls = photoUrls.split(",");
			String imgPath = "";
			for (String photoPath : arrPhotoUrls) {
				try {
					String qiNiuPath = StringUtil
							.getNullStr(getMobileUploadQiniuUrl(photoPath, getFileUploadPath(orgCode), orgCode));
					imgPath = qiNiuPath + "," + imgPath;
				} catch (Exception e) {
					throw new RuntimeException("图片上传到七牛失败！ photoid=" + photoPath);
				}
			}
			if (StringUtil.isNotEmpty(imgPath)) {
				imgPath = imgPath.substring(0, imgPath.length() - 1);
			}
			return imgPath;
		}
	}

	public static String getMobileUploadQiniuUrl(String mediaIdOrUrl, String imagePath, Long orgcode) throws Exception {

		if (mediaIdOrUrl.indexOf("http") > -1) {

			if (mediaIdOrUrl.indexOf(OSSClientUtil.ALI_IMAGE_URL) > -1
					|| mediaIdOrUrl.indexOf(OSSClientUtil.ALI_COMMON_URL) > -1) {
				// 如果属于阿里云直接路径
				return mediaIdOrUrl;
			}
			Map<String, Object> map = FileUtil.writeFile(mediaIdOrUrl, UtilConstant.FOLDER_CYTD, imagePath);
			String filename = StringUtil.getNullStr(map.get("filename"));
			// String filesize = StringUtil.getNullStr(map.get("filesize"));
			return filename;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgcode", orgcode);
			map.put("media_id", mediaIdOrUrl);
			map.put("filePath", imagePath);
			// return PushMsgUtil.getWxFile(map);
			return QiniuUtil.putFileNoFfmpeg(getWxFile(map));
		}
	}

	/**
	 * 通过腾讯提供的media_id下载文件包括图片或者语音
	 * 
	 * @param map
	 *            入参 orgcode media_id
	 * @return
	 */
	public static String getWxFile(Map map) {
		Long orgcode = StringUtil.getNullLong(map.get("orgcode"));
		String media_id = StringUtil.getNullStr(map.get("media_id"));
		String filePath = StringUtil.getNullStr(map.get("filePath"));
		// WxXxOrg org = CacheUtil.getOrgInfoByOrgcode(orgcode);
		//
		// String access_token = MemCacheForOnecardUtil.getWxOrg(
		// org.getWxslaveuser()).getAccesstoken();

		String access_token = "";
		return SubmitHttpRequestUtil.downloadMedia(access_token, media_id, filePath);
	}

	public static Map getMobileUploadFileAndThumbnail(String mediaIdOrUrl, String imagePath, Long orgcode)
			throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		if (mediaIdOrUrl.indexOf("http") > -1) {

			if (mediaIdOrUrl.indexOf(OSSClientUtil.ALI_IMAGE_URL) > -1
					|| mediaIdOrUrl.indexOf(OSSClientUtil.ALI_COMMON_URL) > -1) {
				// 如果属于阿里云直接路径
				result.put("url", mediaIdOrUrl);
				result.put("picUrl", mediaIdOrUrl);
				return result;
			}
			Map<String, Object> map = FileUtil.writeFile(mediaIdOrUrl, UtilConstant.FOLDER_CYTD, imagePath);
			String filename = StringUtil.getNullStr(map.get("filename"));
			// String filesize = StringUtil.getNullStr(map.get("filesize"));
			result.put("url", filename);
			result.put("picUrl", filename);
			return result;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgcode", orgcode);
			map.put("media_id", mediaIdOrUrl);
			map.put("filePath", imagePath);
			return null;
			// return PushMsgUtil.getWxFile(map);
			// return OSSClientUtil.putFileAndThumbnail(
			// PushMsgUtil.getWxFile(map), false);
		}

	}

	/**
	 * 图片上传路径.
	 * 
	 * @param orgcde
	 * @return
	 */
	public static String getFileUploadPath(Long orgcode) {
		String uploadRoot = ConfigUtil.getValue("share");
		String fileUploadPath = uploadRoot + "/" + orgcode + "/";
		FileUtil.makeDir(fileUploadPath);
		logger.info("fileUploadPath=" + fileUploadPath);
		return fileUploadPath;
	}

	public static String fileUploadNews(String imgStr, String fileUploadPath) throws IOException {
		String filename = "";
		String storeFilePath = "";
		try {
			String fileType = "jpg";
			String[] imageFile = imgStr.split("base64,");
			filename = StringUtil.getIdGenerator() + "." + fileType;
			storeFilePath = fileUploadPath + filename;
			GenerateImage(imageFile[1], storeFilePath);
		} catch (Exception e) {
			logger.error("广告推广-图片上传文件失败:" + e.getMessage());
		}
		return storeFilePath;
	}

	public static String changeToMp3(String mp3Path, String sourcePath) {
		String targetPath = mp3Path + "msg_audio_" + StringUtil.getIdGenerator() + ".mp3";
		File source = new File(sourcePath);
		File target = new File(targetPath);
		if (!target.exists()) {
			try {
				target.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		AudioAttributes audio = new AudioAttributes();
		Encoder encoder = new Encoder();

		audio.setCodec("libmp3lame");
		audio.setBitRate(new Integer(128000));
		audio.setChannels(new Integer(2));
		audio.setSamplingRate(new Integer(44100));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);

		try {
			encoder.encode(source, target, attrs);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return targetPath;
	}

	public static String createFile(String sourcePath) {
		File file = new File(sourcePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sourcePath;
	}

	/**
	 * 根据本地文件路径将图片转为base64字符串
	 * 
	 * @param filePath
	 * @param storeFilePath
	 * @return
	 */
	public static String getImageBase64Str(String storeFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		try {
			File file = new File(storeFilePath);
			if (!file.exists()) {
				return "";
			}
			// 待处理的图片
			InputStream in = null;

			// 读取图片字节数组
			in = new FileInputStream(storeFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static String getOrignFileName(String fileName) {
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		return fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
	}
}
