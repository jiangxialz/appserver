package com.maker.app.service.impl;

import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maker.app.action.ResponseVo;
import com.maker.app.common.AppInterface;
import com.maker.app.constant.AppConstants;
import com.maker.app.constant.RetCode;
import com.maker.app.constant.SmsCodeEnum;
import com.maker.app.dao.IPlatSmsCodeDAO;
import com.maker.app.entity.PlatSmsCode;
import com.maker.app.exception.AppException;
import com.maker.app.messager.handler.MessageHandler;
import com.maker.app.messager.handler.SMSMessageHandler;
import com.maker.app.messager.util.SmsUtils;
import com.maker.app.service.IPlatSmsCodeService;
import com.maker.app.util.JSONUtil;
import com.maker.app.util.MessageUtils;

/**
 * 短信验证码业务操作类
 * @author hlz
 * @date 2015-6-4 下午5:17:22
 */
@Service
public class PlatSmsCodeService implements IPlatSmsCodeService{
	
	Logger logger = Logger.getLogger(PlatSmsCodeService.class);
	
	@Autowired
	private IPlatSmsCodeDAO platSmsCodeDAO;
	
	/**
	 * 获取短信验证码
	 * 先保存验证码记录表，如果失败，则不发送短信验证码
	 */
	@Override
	public String getSmsCode(AppInterface ai) {
		
		ResponseVo responseVo = new ResponseVo();
		try {
			String mobile = String.valueOf(ai.getParameterValue("mobile"));
			String smsCode = SmsUtils.createRandom(Boolean.TRUE,6);
		    
		    // 把信息写入验证码记录表
		    Date createTime = new Date();
		    long now = createTime.getTime();
			Date expireTime = new Date(now + AppConstants.VALID_TIME_REGISTER); // 1分钟有效
			
		    PlatSmsCode platSmsCode = new PlatSmsCode();
		    platSmsCode.setUserId(0);
		    platSmsCode.setMobile(mobile);
		    platSmsCode.setSmsCode(smsCode);
		   
		    platSmsCode.setCreateTime(createTime);
		    platSmsCode.setExpireTime(expireTime);
		    platSmsCode.setUseKey(SmsCodeEnum.REGISTER.getUseKey());
		    // 保存记录
		    platSmsCodeDAO.insert(platSmsCode);
		    
		    // 发送短信
			String smsContent = MessageUtils.getText("sms.send.content1") + smsCode + MessageUtils.getText("sms.send.content2");
			MessageHandler handler = new SMSMessageHandler();
		    boolean smsCodeSendFlag = handler.sendMessage(mobile, smsContent);
		    if(!smsCodeSendFlag){
		    	throw new AppException(MessageUtils.getText("front.get.smsCode.fail"));
		    }
		    
		    responseVo.setMessage(MessageUtils.getText("front.operation.success"));
		    
		} catch (AppException appEx) {
			logger.error("获取短信验证码失败！");
			responseVo.setStatus(AppConstants.STATUS_ERROR);
			responseVo.setRetCode(RetCode.ERROR_CUSTOM);
	    	responseVo.setMessage(appEx.getMessage());
	    	
		} catch (Exception e) {
			logger.error("系统异常：" + ExceptionUtils.getStackTrace(e));
			responseVo.setStatus(AppConstants.STATUS_ERROR);
			responseVo.setRetCode(RetCode.ERROR_SYSTEM);
			responseVo.setMessage(MessageUtils.getText("front.operation.fail"));
		}
		return JSONUtil.toJSON(responseVo);
	}
	

}
