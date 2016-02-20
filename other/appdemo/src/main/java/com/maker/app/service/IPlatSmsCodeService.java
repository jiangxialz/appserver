package com.maker.app.service;

import com.maker.app.common.AppInterface;

public interface IPlatSmsCodeService {
	
	/**
	 * 获取手机短信验证码
	 * @param mobile
	 * @return
	 */
	public String getSmsCode(AppInterface ai);
	
}
