package com.maker.app.action;

import com.maker.app.constant.AppConstants;
import com.maker.app.constant.RetCode;

/**
 * HTTP请求返回通知结果
 * @author hlz
 * @date 2015-5-11 下午2:30:03
 */
public class ResponseVo {
	
	private String status=AppConstants.STATUS_OK;
	private int retCode = RetCode.SUCCESS;
	private String message;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getRetCode() {
		return retCode;
	}
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
