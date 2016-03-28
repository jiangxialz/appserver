package com.maker.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.maker.app.util.OptionUtils;
import com.wonder.http.HttpReceiveUtil;

/**
 * 
 * <请求参数日志记录过滤器>
 * <建议测试环境开启，外网环境关闭>
 *
 * @author  hlz
 * @date  2016-1-29 上午10:06:55
 */
public class ReqParamsFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = -2947396490326845497L;
	
	Logger logger = Logger.getLogger(ReqParamsFilter.class);
	
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		httpRequest.setCharacterEncoding("UTF-8");
//      String ua = httpRequest.getHeader("User-Agent");
		
		/**
         *  测试环境进行打印log，外网环境关闭
         */
        if(OptionUtils.getBooleanOption("sysconfig.current.env.isTest")){
    		String reqUrl = httpRequest.getRequestURI();
    		// key/value 参数数据
    		String reqKeyValueParams = HttpReceiveUtil.getParameters(httpRequest);
    		if(StringUtils.isNotBlank(reqKeyValueParams)){
        		String method = httpRequest.getMethod();
        		if ("get".equalsIgnoreCase(method)) {
        		    logger.info("~~~~~~~~~ (GET) url="+reqUrl+"?"+reqKeyValueParams);
        		}else{
        		    logger.info("~~~~~~~~~ (POST) url="+reqUrl+"?"+reqKeyValueParams);
        		}
    		}else{
    			/**
    		     * xml/json 参数数据
    		     * 此处不能接收数据流参数，否则对应的真正的处理类获取不到任何参数（数据流只能获取一次！！！）
    		     * String reqTextParam = HttpReceiveUtil.getPostDataByStream(httpRequest);
    	         * logger.info("~~~~~~~~~ (POST) url= "+reqUrl+"\r\n" + reqTextParam);
    		     */
    		}
		}
		chain.doFilter(request, response);
	}
    
    
	public void init(FilterConfig config) throws ServletException {}

	
}
