package com.maker.app.entity;

import java.util.Date;

/**
 * 短信验证码实体类
 * @author hlz
 * @date 2015-6-4 下午4:20:26
 */
public class PlatSmsCode {
	
	private int id;
	// 用户id
	private int userId;
	// 手机号
	private String mobile;
	// 验证码
	private String smsCode;
	// 生成时间
	private Date createTime;
	// 过期时间
	private Date expireTime;
	// 是否使用(1:是：0否)，默认为0
	private boolean isUse;
	// 使用时间
	private Date useTime;
	// 用途
	private String useKey;
	// 备注
	private String remark;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public boolean getIsUse() {
		return isUse;
	}
	public void setIsUse(boolean isUse) {
		this.isUse = isUse;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public String getUseKey() {
		return useKey;
	}
	public void setUseKey(String useKey) {
		this.useKey = useKey;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
