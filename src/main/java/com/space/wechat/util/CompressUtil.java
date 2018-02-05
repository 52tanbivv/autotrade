package com.space.wechat.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.space.wechat.framework.exception.BusinessException;

public class CompressUtil {
	private static final int BUFFER_BYTES = 1024;

	/**
	 * 将文件压缩
	 * 
	 * @param sourceFile
	 *            源文件
	 * @param targetFile
	 *            压缩后的文件
	 * @return
	 */
	public static String compressFile(String sourceFile, String targetFile,
			String fileName) {
		ZipOutputStream zos = null;
		BufferedInputStream bis = null;
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(targetFile));
			zos = new ZipOutputStream(bos);
			bis = new BufferedInputStream(new FileInputStream(sourceFile));
			String entryName = new File(sourceFile).getName();
			if (StringUtil.isNotEmpty(fileName)) {
				entryName = fileName;
			}
			ZipEntry firstEntry = new ZipEntry(entryName);
			zos.putNextEntry(firstEntry);
			int length = 0;
			byte[] buf = new byte[BUFFER_BYTES];
			while ((length = bis.read(buf)) > 0) {
				zos.write(buf, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("压缩文件时，出现IO读写错误");
		} finally {
			try {
				zos.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new BusinessException("压缩文件——关闭流 出错");
			}
		}
		return targetFile;
	}
}
