package com.maker.app.constant;

/**
 * 短信验证码用途枚举
 * @author hlz
 * @date 2015-6-4 下午4:34:17
 */
public enum SmsCodeEnum {
	
	REGISTER("register","用户注册");
	
	private String useKey;
	private String name;
	
	/**
	 * @param code 用途关键字
	 * @param name 用途描述
	 */
	private SmsCodeEnum(String useKey,String name){
		 this.useKey = useKey;
		 this.name = name;
	}
	
	public static String getName(String useKey){
		for(SmsCodeEnum smsCodeEnum : SmsCodeEnum.values()){
			if(smsCodeEnum.getUseKey().equals(useKey))
				return smsCodeEnum.getName();
		}
		return null;
	}
	
	public static SmsCodeEnum getSmsCodeEnum(String useKey){
		for(SmsCodeEnum smsCodeEnum : SmsCodeEnum.values()){
			if(smsCodeEnum.getUseKey().equals(useKey))
				return smsCodeEnum;
		}
		return null;
	}
	
	
	public String getUseKey() {
		return useKey;
	}
	public void setUseKey(String useKey) {
		this.useKey = useKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
