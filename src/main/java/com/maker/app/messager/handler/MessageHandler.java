package com.maker.app.messager.handler;

/**
 * 消息发送处理器接口
 * @author hlz
 * @date 2015-6-3 上午10:06:57
 */
public abstract interface MessageHandler {
	
	public abstract boolean sendMessage(String mobile, String smsContent);
}
