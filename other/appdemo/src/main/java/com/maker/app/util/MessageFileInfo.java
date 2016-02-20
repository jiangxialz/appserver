package com.maker.app.util;

import java.util.Properties;

/**
 * 多语言文件信息
 * 
 * @author amin
 * 
 */
public class MessageFileInfo {

	private Properties properties;// 文件
	private long lastmodifytime;

	public MessageFileInfo() {
		super();
	}

	public MessageFileInfo(Properties properties, long lastmodifytime) {
		super();
		this.properties = properties;
		this.lastmodifytime = lastmodifytime;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public long getLastmodifytime() {
		return lastmodifytime;
	}

	public void setLastmodifytime(long lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}

}
