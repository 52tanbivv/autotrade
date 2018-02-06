package com.baidu.ueditor.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.space.wechat.util.OSSClientUtil;
import com.space.wechat.util.QiniuUtil;
import com.space.wechat.util.StringUtil;

public class StorageManager {
	public static final int BUFFER_SIZE = 8192;

	public StorageManager() {
	}

	public static State saveBinaryFile(byte[] data, String path) {
		File file = getTmpFile(path);

		State state = valid(file);

		if (!state.isSuccess()) {
			return state;
		}

		File tmpFile = getTmpFile(file.getName());

		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile));
			bos.write(data);
			bos.flush();
			bos.close();
		} catch (IOException ioe) {
			return new BaseState(false, AppInfo.IO_ERROR);
		}

		state = saveTmpFile(tmpFile, file.getName());
		return state;
	}

	public static State saveFileByInputStream(InputStream is, String originFileName, long maxSize) {
		State state = null;

		File tmpFile = getTmpFile(originFileName);

		byte[] dataBuf = new byte[2048];
		BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile),
					StorageManager.BUFFER_SIZE);

			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();

			if (tmpFile.length() > maxSize) {
				tmpFile.delete();
				return new BaseState(false, AppInfo.MAX_SIZE);
			}

			state = saveTmpFile(tmpFile, originFileName);

			if (!state.isSuccess()) {
				tmpFile.delete();
			}

			return state;

		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	public static State saveFileByInputStream(InputStream is, String path) {
		State state = null;

		File tmpFile = getTmpFile("");

		byte[] dataBuf = new byte[2048];
		BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile),
					StorageManager.BUFFER_SIZE);

			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();

			state = saveTmpFile(tmpFile, path);

			if (!state.isSuccess()) {
				tmpFile.delete();
			}

			return state;
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static File getTmpFile(String suffix) {
		File tmpDir = FileUtils.getTempDirectory();
		String tmpFileName = (Math.random() * 10000 + "").replace(".", "") + suffix;
		return new File(tmpDir, tmpFileName);
	}

	private static State saveTmpFile(File tmpFile, String originFileName) {
		State state = null;
		String url = "";
		String picUrl = "";
		try {
			//Map fileAndThum = OSSClientUtil.putFileAndThumbnail(tmpFile.getAbsolutePath(),false);
			url = QiniuUtil.putFile(tmpFile.getAbsolutePath());

//			url = StringUtil.getNullStr(fileAndThum.get("url"));
//			picUrl = StringUtil.getNullStr(fileAndThum.get("picUrl"));
		} catch (IOException e) {
			e.printStackTrace();
			return new BaseState(false, AppInfo.IO_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseState(false, AppInfo.IO_ERROR);
		}

		state = new BaseState(true);
		state.putInfo("size", tmpFile.length());
		state.putInfo("title", originFileName);
		state.putInfo("url", url.replace(OSSClientUtil.ALI_COMMON_URL, OSSClientUtil.ALI_IMAGE_URL));
		state.putInfo("picUrl", picUrl);
		return state;
	}

	private static State valid(File file) {
		File parentPath = file.getParentFile();

		if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
			return new BaseState(false, AppInfo.FAILED_CREATE_FILE);
		}

		if (!parentPath.canWrite()) {
			return new BaseState(false, AppInfo.PERMISSION_DENIED);
		}

		return new BaseState(true);
	}
}
