package com.maker.app.listener;

import java.nio.charset.Charset;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.maker.app.common.Global;
import com.maker.app.common.SpringService;
import com.maker.app.common.SysCache;
/**
 * 自定义监听器
 * @author lz
 * 2014-11-24 下午2:40:47
 *
 */
public class InitSystemListener implements ServletContextListener {
	
	private static Logger logger = Logger.getLogger(InitSystemListener.class);
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
	    
		final String defaultCharset = Charset.defaultCharset().toString();//本地编码
		logger.info("本地编码：" + defaultCharset);
		
		ServletContext servletContext = servletContextEvent.getServletContext();
		// 初始化Spring
		SpringService.setServletContext(servletContext);
		
		// 加载接口定义信息
		SysCache.load();
		
		// 初始化缓存信息
		Global.init();
		
	} 
	
	/**
	 * 销毁
	 * @param servletContextEvent
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
		try {
			SchedulerFactory schedFact = new StdSchedulerFactory();
			schedFact.getScheduler().shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}

}
