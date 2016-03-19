package com.maker.app.constant;


/**
 * app常量
 * @author hlz
 * @date 2015-5-5
 */
public class AppConstants {
    
    /**
     * 区分测试和正式环境，此参数只在linux环境下有效
     */
    public static boolean IS_TEST_ENV = false;
	
	public static final String CHARACTER_ENCODING = "UTF-8";
	
	/**
	 *  会话id
	 */
	public static final String _SESSIONID = "_sessionid";
	/**
	 *  访问ip
	 */
	public static final String _IP = "_ip";
	
	/**
	 * 批量插入日志的条数
	 */
	public static final int LOG_BATCH_SIZE = 50 ; 
	
	/**
	 * app http请求返回status常亮定义
	 */
	public static final String STATUS_OK = "OK";
	public static final String STATUS_ERROR = "ERROR";
	
	/**
	 * app http请求接口调用结果
	 */
	public static final int RESULT_1 = 1;
	public static final int RESULT_2 = 2;
	
	/***
	 * 是否有效
	 */
	public static final boolean VALID = true;
	public static final boolean INVALID = false;
	
	/*************** 模型相关常量 ***************/
	
	
	/************ 短信验证码 ***************/
	
	/**
	 *  注册验证码有效时间
	 */
	public static final long VALID_TIME_REGISTER = 1000L * 60 * 60;
	
	/**
	 * 验证码是否使用
	 */
	public static final boolean USE_TRUE = true;
	public static final boolean USE_FALSE = false;
	
   
}
