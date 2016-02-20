package com.maker.app.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maker.app.constant.AppConstants;
import com.maker.app.entity.AppSession;
import com.wonder.util.IpUtil;

public class AppInterface {
	private String name;
	private boolean needAuth;
	private List<Parameter> parameters;
	private Map<String, Object> parameterMap = new HashMap<String, Object>();
	private AppSession appSession;
	
	/**
	 * 填充参数
	 * @param request
	 */
	public void fillParameterValues(HttpServletRequest request, HttpServletResponse response) {
		if (parameters != null && parameters.size() > 0) {
			for (Parameter param : parameters) {
				param.setValue(request.getParameter(param.getName()));
			}
		}
		/**
		 * 原生app没有cookie，cookie是相对于浏览器而言
  		String sessionid = getCookieValue(request, "JSESSIONID");
		if (sessionid == null || sessionid.length() == 0)
			sessionid = request.getSession().getId();
		parameterMap.put(AppConstants._SESSIONID, sessionid);
		*/
		parameterMap.put(AppConstants._IP, IpUtil.getIpAddr(request));
	}
	
	public String getIp() {
		return (String)parameterMap.get(AppConstants._IP);
	}
	
	public String getSessionid() {
		return (String)parameterMap.get(AppConstants._SESSIONID);
	}
	
	/**
	 * 校验所有声明的参数，如果合法，返回空串，否则返回参数错误原因
	 * @return 校验结果
	 */
	public String validateParameter() {
		StringBuilder sb = new StringBuilder();
		if (parameters != null && parameters.size() > 0) {
			for (Parameter param : parameters) {
				String msg = param.validate();
				if (!"OK".equals(msg))
					sb.append(msg + "；");
			}
			if (sb.length() > 0)
				sb.deleteCharAt(sb.length()-1);//删除最后一个分号
		}
		return sb.toString();
	}
	
	/**
	 * 把所有的参数转换成声明的类型
	 */
	public void parseValue() {
		if (parameters != null && parameters.size() > 0) {
			for (Parameter param : parameters) 
				parameterMap.put(param.getName(), param.parseValue());
		}
	}
	
	public Object getParameterValue(String paramName) {
		return parameterMap.get(paramName);
	}
	
	public Object setParameterValue(String paramName, Object value) {
		return parameterMap.put(paramName, value);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public AppInterface clone() { //深度克隆
		AppInterface ai = new AppInterface();
		ai.setName(name);
		ai.setNeedAuth(needAuth);
		List<Parameter> list = new ArrayList<Parameter>();
		for (Parameter param : parameters) 
			list.add(param.clone());
		ai.setParameters(list);
		return ai;
	}
	
	public String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0)
			return null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name))
				return cookie.getValue();
		}
		return null;
	}

	public boolean getNeedAuth() {
		return needAuth;
	}

	public void setNeedAuth(boolean needAuth) {
		this.needAuth = needAuth;
	}

	public AppSession getAppSession() {
		return appSession;
	}

	public void setAppSession(AppSession appSession) {
		this.appSession = appSession;
	}
}