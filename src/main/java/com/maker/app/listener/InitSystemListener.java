package com.maker.app.listener;

import java.net.InetAddress;
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
import com.maker.app.common.SystemConfig;
import com.maker.app.constant.AppConstants;
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
		
		// 获取当前环境
		getCurrentEnv();
	} 
	
    /**
     * <获取本机IP，以判断是否测试环境> 
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void getCurrentEnv()
    {
        try
        {
            // 获得本机IP(服务器上获取的是内网IP)
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress().toString();
            if(SystemConfig.isLinux()){
                if (ip != null && (ip.endsWith(".99"))){
                    AppConstants.IS_TEST_ENV = true;
                }else {
                    AppConstants.IS_TEST_ENV = false;
                }
                logger.info("local ip address:" + ip + " & local currentEnv is : " + (AppConstants.IS_TEST_ENV == true?"test" : "online"));
            }else {
                AppConstants.IS_TEST_ENV = true;
            }
        } catch (Exception e){
            logger.info("getCurrentEnv error ： " + e);
        }
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
