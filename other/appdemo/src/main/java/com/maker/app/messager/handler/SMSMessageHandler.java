package com.maker.app.messager.handler;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.maker.app.util.OptionUtils;
import com.wonder.exception.CustomException;
import com.wonder.http.HttpClientSendUtil;

/***
 * 短信发送处理类
 * @author hlz
 * @date 2015-6-3 上午11:50:47
 */
public class SMSMessageHandler implements MessageHandler {
	
	Logger logger = Logger.getLogger(SMSMessageHandler.class);

	@Override
	public boolean sendMessage(String mobile, String smsContent) {
		if (StringUtils.isNotBlank(mobile)
				&& StringUtils.isNotBlank(smsContent)) {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("account", OptionUtils.getOption("account"));
			params.put("password", OptionUtils.getOption("password"));
			params.put("mobile", mobile);
			params.put("content", smsContent);
			try {
				String submitResult = HttpClientSendUtil.postRequest(OptionUtils.getOption("smsUrl"),params, "http");
				Document doc = DocumentHelper.parseText(submitResult);
				Element root = doc.getRootElement();
				
				String code = root.elementText("code"); // 返回值为2时，表示提交成功
				String msg = root.elementText("msg"); // 提交结果描述
				String smsid = root.elementText("smsid"); // 仅当提交成功后，此字段值才有意义（消息ID）
				
				logger.info("sms messager return : code="+code+"&msg="+msg+"&smsid="+smsid);
				
				if ("2".equals(code)) {
					return true;
				}
			} catch (CustomException e1) {
				logger.error("custom exception : " + e1.getMessage());
			}catch (Exception e) {
				logger.error("system exception : " + ExceptionUtils.getStackTrace(e));
			}
		}
		return false;
	}
	
}
