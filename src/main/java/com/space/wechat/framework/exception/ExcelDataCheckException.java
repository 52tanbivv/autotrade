package com.space.wechat.framework.exception;

import java.io.Serializable;

public class ExcelDataCheckException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public ExcelDataCheckException(String errorMsg) {
		super(errorMsg);
	}
}
