package com.space.wechat.framework;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.space.wechat.entity.company.MyProgressListener;

public class MyCommonsMultipartResolver extends CommonsMultipartResolver {

	private static final Logger log = Logger
			.getLogger(MyCommonsMultipartResolver.class);

	@Override
	public boolean isMultipart(HttpServletRequest request) {
		String dir = request.getParameter("dir");
		// log.info("isMultipart dir=" + dir);
		if (dir != null) { // kindeditor 上传图片的时候 不进行request 的转换
			return false;
		} else if (request.getRequestURI().contains("/filehandel/imagePreview")) {
			return false;
		} else if (request.getRequestURI().contains(
				"/filehandel/fileuploadMultipart")) {
			return false;
		} else if (request.getRequestURI().contains("/klxx/wlzy/swfupload")) {
			return false;
		} else if (request.getRequestURI().contains("/filehandel/picupload")) {
			return false;
		} else if (request.getRequestURI().contains("/klxx/wlzy/manage/create")) {
			return false;
		} else if (request.getRequestURI().contains("/jyhd/mrcp/picupload")) {
			return false;
		} else if (request.getRequestURI().contains(
				"/mobile/upload_image_by_app")) {
			return false;
		}else if(request.getRequestURI().contains(
				"/klxx/wlzy/wkUpload")){
			return true;
		}
		return super.isMultipart(request);
	}

	@Override
	public MultipartParsingResult parseRequest(HttpServletRequest request)
			throws MultipartException {
		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);
		// 设置监听器
		MyProgressListener progressListener = new MyProgressListener(
				request.getSession());
		fileUpload.setProgressListener(progressListener);
		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload)
					.parseRequest(request);
			return parseFileItems(fileItems, encoding);
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(),
					ex);
		} catch (FileUploadException ex) {
			throw new MultipartException(
					"Could not parse multipart servlet request", ex);
		}
	}

}
