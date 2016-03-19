package com.maker.app.exception;

/**
 * 自定义异常
 * @author hlz
 * @date 2015-6-1 下午4:40:44
 */
public class AppException extends RuntimeException {

	private static final long serialVersionUID = -6881307451092057815L;

	protected int code;
	
	public AppException(String message){
		super(message);
	}
	
	public AppException(int code, String message){
		super(message);
		this.code = code;
	}
	
	public AppException(int code, String message, Throwable t){
		super(message, t);
		this.code = code;
	}
	
	/**
	 * 出于性能考虑， 复写此方法。
	 */
	public Throwable fillInStackTrace() {
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
