package com.maker.app.vo;

public class AppSessionVo {
	
	// 会话id
	private String sid;
	
	public AppSessionVo(String sessionId){
		this.sid = sessionId;
	}
	

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	

}
