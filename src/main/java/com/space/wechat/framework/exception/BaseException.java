package com.space.wechat.framework.exception;

/**
 * 异常处理基类
 * 
 * @author wm
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -5154615570448990996L;

	protected String errorCode; // 错误代码(属性表文件中定义其message)
	protected String errorMsg; // 错误信息(For debug)
	protected Object[] parameters; // 参数列表(错误代码对应的message需要的参数列表)
	protected Throwable errorCause; // 错误发生原因(原始Exception)
	protected String errorDetail; // 错误详细信息

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorCode
	 * @param parameters
	 * @param e
	 */
	public BaseException(String errorMsg, String errorDetail, String errorCode,
			Object[] parameters) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorDetail = errorDetail;
		this.parameters = parameters;
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorCode
	 * @param parameters
	 * @param e
	 */
	public BaseException(String errorMsg, String errorDetail, String errorCode,
			Object[] parameters, Throwable e) {
		super(errorMsg, e);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorDetail = errorDetail;
		this.errorCause = e;
		this.parameters = parameters;
	}

	public Throwable getErrorCause() {
		return errorCause;
	}

	public void setErrorCause(Throwable errorCause) {
		this.errorCause = errorCause;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

}
