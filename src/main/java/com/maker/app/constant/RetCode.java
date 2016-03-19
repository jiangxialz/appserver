package com.maker.app.constant;
/**
 * 接口返回状态码
 * @author hlz
 * @date 2015-8-19 下午5:18:27
 */
public class RetCode {
	
	/******** 系统全局 *********/
	// 成功
	public static final int SUCCESS = 0;
	// 操作失败，自定义异常
	public static final int ERROR_CUSTOM = 1000;
	// 操作失败，系统异常
	public static final int ERROR_SYSTEM = 9000;
	
	/******** 用户模块 ********/
	// 没有登录
	public static final int USER_NO_LOGIN = 2000;
	// 该用户会话信息不存在（错误或已失效）
	public static final int USER_SID_NOTEXIST = 2001;
	// 两次输入的密码不一致
	public static final int USER_PW_DIFFER = 2002;
	// 该手机号已经注册
	public static final int USER_MOBILE_REGISTERED = 2003;
	// 该账号已经登录
	public static final int USER_MOBILE_ONLINE = 2004;
	// 验证码已超时
	public static final int USER_SMSCODE_OVERTIME = 2005;
	// 验证码输入错误
	public static final int USER_SMSCODE_ERROR = 2006;
	// 用户名或密码错误
	public static final int USER_LOGIN_FAIL = 2007;
	// 原始密码输入错误
	public static final int USER_MODIFY_PWOLD_ERROR = 2008;
	// 用户不存在
	public static final int USER_LOGINNAME_NOTEXIST = 2009;
	// 注销失败
	public static final int USER_LOGOUT_FAIL = 2010;
	
}
