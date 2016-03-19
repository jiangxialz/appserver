package com.maker.app.common;


import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Spring服务
 */

public class SpringService {
	
	private static ServletContext servletContext;
	private static WebApplicationContext webApplictionContext;
	
	public static Object getBean(String name){
		if ( webApplictionContext==null ) return null;
		return webApplictionContext.getBean(name);
	}
	
	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext) {
		SpringService.servletContext = servletContext;
		SpringService.webApplictionContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}

//	public static ApplicationContext getApplicationContext() {
//		ApplicationContext ctx = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
//		//ApplicationContext ctx = new FileSystemXmlApplicationContext("../webapps/War/WEB-INF/applicationContext.xml");
//		return ctx;
//	}
	
}
