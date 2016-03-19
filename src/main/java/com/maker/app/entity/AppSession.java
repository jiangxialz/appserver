package com.maker.app.entity;

import java.sql.Timestamp;
//
//import com.maker.app.vo.UserInfoVo;

/**
 * APP 会话信息
 * @author hlz
 * @date 2015-5-5
 */
public class AppSession {
	
	private String sessionId; //当前会话ID
	private int userId;	//会员ID
	private Timestamp createTime; //创建时间
	private Timestamp lastUpdate; //最后访问时间
	private String ip;	//IP
	
//	private UserInfoVo userInfoVo;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
//	public UserInfoVo getUserInfoVo() {
//		return userInfoVo;
//	}
//	public void setUserInfoVo(UserInfoVo userInfoVo) {
//		this.userInfoVo = userInfoVo;
//	}
	
	
}
