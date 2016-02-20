package com.maker.app.entity;

import java.sql.Timestamp;

import com.maker.app.util.JSONUtil;

/**
 * 访问日志实体类
 * @author hlz
 * @date 2015-5-5
 */
public class AppVisitLog {
	
	private long id; //LOGID
	private int userId;	//会员ID
	private String ip;	//访问者IP
	private String interfaceName;	//接口名
	private Timestamp visitTime;	//访问时间
	private int elapse;	//耗时
	private int result;	//调用接口结果
	private String errMsg;	//出错的原因
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public Timestamp getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Timestamp visitTime) {
		this.visitTime = visitTime;
	}
	public int getElapse() {
		return elapse;
	}
	public void setElapse(int elapse) {
		this.elapse = elapse;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String toString() {
		return JSONUtil.toJSON(this);
	}
}
