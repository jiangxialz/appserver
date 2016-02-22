package com.maker.app.action.front;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.maker.app.action.FrontBaseAction;
import com.maker.app.action.ResponseVo;
import com.maker.app.common.AppInterface;
import com.maker.app.common.Global;
import com.maker.app.common.SysCache;
import com.maker.app.constant.AppConstants;
import com.maker.app.constant.RetCode;
import com.maker.app.entity.AppSession;
import com.maker.app.exception.AppException;
import com.maker.app.util.JSONUtil;
import com.maker.app.util.MessageUtils;
import com.maker.app.util.business.AppVisitLogUtil;

/**
 * 全局对外请求http处理类
 * @author hlz
 * @date 2015-5-11 下午3:18:01
 */
public class GlobalExternalReqAction extends FrontBaseAction{

	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(GlobalExternalReqAction.class);
	
	public String execute() throws Exception{
		
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		PrintWriter out = super.getWriter();
		
		ResponseVo responseVo = new ResponseVo();
		
		try {
			long start = System.currentTimeMillis();
			
			//得到请求的action
			String action = request.getParameter("action");
			//通过这个action，就可以找到对应的接口配置
			AppInterface appInterface = SysCache.allInterfaces.get(action);
			//如果没有查到，说明这个action不合法，报告给调用者
			if (appInterface == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				throw new AppException(RetCode.ERROR_CUSTOM, MessageUtils.getText("front.params.action.notexist") + ":" + action);
			}
			
			//这里用深度克隆，以便每次调用都有新的接口实体，使得线程安全
			AppInterface ai = appInterface.clone(); 
			//从request得到所有要求的参数，填充到接口实体里去
			ai.fillParameterValues(request, response);
			//校验参数，若校验通过，返回结果为空，若有错，返回参数错误原因。
			String msg = ai.validateParameter();
			if (msg.length() > 0) {
				throw new AppException(RetCode.ERROR_CUSTOM, msg);
			}
			//再解析参数，变成实际需要的类型
			ai.parseValue();
			
			// 是否允许访问
			isAllowAccess(request, ai);
			
			//根据action查找到相应的处理类
			Object service = SysCache.allServices.get(action);
			if(service == null){
				logger.error("the interface service '" + action + "' was undifined...");
			}
			//根据action查找到相应的方法
			Method method = service.getClass().getMethod(SysCache.allMethods.get(action), AppInterface.class);
			//用反射的机制调用查到的方法，得到返回结果
			String json = (String)method.invoke(service, ai); //调用service的指定的方法
			logger.info("result json : " + json);
			
			//把结果返回给调用者
			out.print(json); 
			out.flush();
			long end = System.currentTimeMillis();
			
			// 获取错误信息
			msg = getErrorMsg(json);
			
			// 保存访问日志
			AppVisitLogUtil.addAppVisitLog(service, ai, start, end - start, msg);
			
		} catch (AppException appEx) {
			logger.error(appEx.getMessage());
			responseVo.setStatus(AppConstants.STATUS_ERROR);
			responseVo.setRetCode(appEx.getCode());
			responseVo.setMessage(appEx.getMessage());
			out.println(JSONUtil.toJSON(responseVo));
			
		}catch (Exception e) {
			logger.error("system error : " + ExceptionUtils.getStackTrace(e));
			responseVo.setStatus(AppConstants.STATUS_ERROR);
			responseVo.setRetCode(RetCode.ERROR_SYSTEM);
			responseVo.setMessage(MessageUtils.getText("front.system.error"));
			out.println(JSONUtil.toJSON(responseVo));
		}
		return null;
	}
	
	/**
	 * 从返回的json数据得到错误消息
	 * @param json
	 * @return
	 */
	private static String getErrorMsg(String json) {
		String msg = "";
		if (json.indexOf("ERROR") > -1) {
			int p1 = json.indexOf("message");
			if (p1 > -1) {
				int p2 = json.indexOf("\"", p1+10);
				if (p2 == -1) 
					p2 = json.indexOf("'", p1+10);
				if (p2 != -1)
					msg = json.substring(p1+10, p2);
			}
		}
		return msg;
	}
	
	/**
	 * 是否允许访问
	 * @param request HttpServletRequest对象
	 * @param ai 应用接口对象
	 * @return 若是可以访问，返回true，否则返回false
	 */
	@SuppressWarnings("unchecked")
	private void isAllowAccess(HttpServletRequest request, AppInterface ai) {
		if (ai.getNeedAuth()) {
			String sessionid = ai.getSessionid();
			if (sessionid == null)
				throw new AppException(RetCode.USER_NO_LOGIN, MessageUtils.getText("user.no.login"));
			Map<String, AppSession> appSessionMap = Global.get(Global.APP_SESSION, Map.class);
			AppSession appSession = appSessionMap.get(sessionid);
			if (appSession == null) {
				throw new AppException(RetCode.USER_SID_NOTEXIST, MessageUtils.getText("user.sid.notexist"));
			} else {
				ai.setAppSession(appSession);
			}
		} 
	}
	
}
