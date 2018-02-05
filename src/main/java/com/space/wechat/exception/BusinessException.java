package com.space.wechat.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务处理异常类
 * 
 * @author wm
 *
 */
public class BusinessException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory
			.getLogger(BusinessException.class);

	/**
	 * Constructor
	 * 
	 * @param errorCode
	 */
	public BusinessException(String errorMsg) {

		super(errorMsg, null, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param errorCode
	 * @param parameters
	 */
	public BusinessException(String errorCode, Object[] parameters) {
		super(null, null, errorCode, parameters);
	}

	/**
	 * Constructor
	 * 
	 * @param errorCode
	 * @param e
	 */
	public BusinessException(String errorMsg, Throwable e) {
		super(errorMsg, null, null, null, e);
	}

	/**
	 * Constructor
	 * 
	 * @param errorCode
	 * @param parameters
	 * @param e
	 */
	public BusinessException(String errorCode, Object[] parameters, Throwable e) {
		super(null, null, errorCode, parameters, e);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorCode
	 */
	public BusinessException(String errorMsg, String errorCode) {
		super(errorMsg, null, errorCode, null);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorCode
	 * @param parameters
	 */
	public BusinessException(String errorMsg, String errorCode,
			Object[] parameters) {
		super(errorMsg, null, errorCode, parameters);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorCode
	 * @param parameters
	 * @param e
	 */
	public BusinessException(String errorMsg, String errorCode,
			Object[] parameters, Throwable e) {
		super(errorMsg, null, errorCode, parameters, e);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorDetail
	 * @param errorCode
	 */
	public BusinessException(String errorMsg, String errorDetail,
			String errorCode) {
		super(errorMsg, errorDetail, errorCode, null);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorDetail
	 * @param errorCode
	 * @param e
	 */
	public BusinessException(String errorMsg, String errorDetail,
			String errorCode, Throwable e) {
		super(errorMsg, errorDetail, errorCode, null, e);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorDetail
	 * @param errorCode
	 * @param parameters
	 */
	public BusinessException(String errorMsg, String errorDetail,
			String errorCode, Object[] parameters) {
		super(errorMsg, errorDetail, errorCode, parameters, null);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorDetail
	 * @param errorCode
	 * @param parameters
	 * @param e
	 */
	public BusinessException(String errorMsg, String errorDetail,
			String errorCode, Object[] parameters, Throwable e) {
		super(errorMsg, errorDetail, errorCode, parameters, e);
	}
}
