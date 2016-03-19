package com.maker.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wonder.util.IpUtil;

@SuppressWarnings({"serial"})
public class FrontBaseAction  extends ActionSupport{
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	public final String getRequestIp() {
		return IpUtil.getIpAddr(getRequest());
	}

	public PrintWriter getWriter() {
		try {
			this.getResponse().setContentType("text/html;charset=utf-8");
			return this.getResponse().getWriter();
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * 获取请求参数
	 */
	protected HashMap<String,Object> getRequestParams(){
		return getRequestParams(ServletActionContext.getRequest());
	}
	
	/**
	 * 获取请求参数
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public HashMap<String,Object> getRequestParams(HttpServletRequest request){
		HashMap<String, Object> paramss = new HashMap<String, Object>();
		Enumeration paramNames = request.getParameterNames();
		while ((paramNames != null) && (paramNames.hasMoreElements())) {
			String paramName = (String) paramNames.nextElement();
			String[] values = request.getParameterValues(paramName);
			if ((values == null) || (values.length == 0))
				continue;
			if (values.length == 1) {
				paramss.put(paramName, values[0]);
			} else {
				for (int i = 0; i < values.length; i++) {
					paramss.put(paramName, values[i]);
				}
			}
		}
		return paramss;
	}
	
}
