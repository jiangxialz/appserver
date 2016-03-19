package com.maker.app.dao;

import com.maker.app.entity.PlatSmsCode;

public interface IPlatSmsCodeDAO {
	
	public int insert(PlatSmsCode platSmsCode);
	
	/**
	 * 查找该手机号注册操作生成的未使用的注册码
	 * @param mobile
	 * @param smsCode
	 * @return
	 */
	public PlatSmsCode getPlatSmsCode(String mobile, String smsCode);
	
	/**
	 * 更新
	 * @param platSmsCode
	 * @return
	 */
	public int update(PlatSmsCode platSmsCode);
}
