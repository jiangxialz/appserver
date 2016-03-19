package com.maker.app.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.maker.app.constant.AppConstants;
import com.maker.app.constant.IpRequestNumConstant;
import com.maker.app.exception.AppException;
import com.maker.app.util.MD5SortUtil;
import com.maker.app.util.business.IpRequestNumUtil;
import com.wonder.constant.SplitConstants;
import com.wonder.http.HttpReceiveUtil;
import com.wonder.util.IpUtil;
import com.wonder.xml.XmlUtil;

/**
 * 
 * <请求相关验证过滤器>
 * <建议测试环境关闭，外网环境开启>
 *
 * @author  hlz
 * @date  2016-1-29 上午10:06:55
 */
public class ReqVerifyFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = -2947396490326845497L;
	
	static Logger logger = Logger.getLogger(ReqVerifyFilter.class);
	
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		httpRequest.setCharacterEncoding("UTF-8");
		
		// 正式环境进行验证
		if(!AppConstants.IS_TEST_ENV){
    		/**
             * IP请求频率验证
             */
    		String reqIp = IpUtil.getIpAddr(httpRequest);
            IpRequestNumUtil.verifyRequest(reqIp, IpRequestNumConstant.GLOBALREQ);
            
            // TODO.. 签名验证
            /*if(!verifySign(httpRequest)){
                throw new AppException(MessageUtils.getText("global.req.sign.verify.fail"));
            }*/
		}
		
		chain.doFilter(request, response);
	}
    
    
    /**
     * 签名验证
     * @param request
     * @return
     */
    public static boolean verifySign(HttpServletRequest request){
        boolean flag = true;
        Map<String, String> requestMap = getReqMapParam(request);
        String MD5SignData = MD5SortUtil.signValue(requestMap, "sign", true, "");
        if(!MD5SignData.equals(request.getParameter("sign"))){
            flag = false;
        }
        return flag;
    }
    
    /**
     * <把请求参数转化为Map<String, String>，便于验签>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return Map<String,String> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getReqMapParam(HttpServletRequest request){
        Map<String, String> reqMapParam = new HashMap<String, String>();
        try
        {
            String reqKeyValueParams = HttpReceiveUtil.getParameters(request);
            if(StringUtils.isNotBlank(reqKeyValueParams)){
                String method = request.getMethod();
                if ("get".equalsIgnoreCase(method)) {
                    String[] reqParams = reqKeyValueParams.split(SplitConstants.SPLIT_AND);
                    for(int i=0; i<reqParams.length; i++){
                        String[] aParam = reqParams[i].split("=");
                        reqMapParam.put(aParam[0], aParam[1]);
                    }
                }else {
                    reqMapParam = HttpReceiveUtil.getParametersHashMap(request);
                }
            }else{ // xml/json 参数数据
                String reqTextParam = HttpReceiveUtil.getPostDataByStream(request);
                
                String contentType = request.getContentType();
                if(StringUtils.isNotBlank(contentType)){
                    if("json".equalsIgnoreCase(contentType)){
                        reqMapParam = JSON.parseObject(reqTextParam,Map.class);
                    }else if("xml".equalsIgnoreCase(contentType)){
                        String reqJsonStr = XmlUtil.xml2json(reqTextParam);
                        reqMapParam = JSON.parseObject(reqJsonStr,Map.class);
                    }else {
                        throw new AppException("当前contentType为："+contentType);
                    }
                }else {
                    throw new AppException("当前contentType为空！");
                }
            }
        }
        catch (AppException appEx)
        {
            logger.error("获取请求参数自定义异常：" + appEx.getMessage());
        }
        catch (Exception e)
        {
            logger.error("获取请求参数系统异常：" + ExceptionUtils.getStackTrace(e));
        }
        return reqMapParam;
    }

	
	public void init(FilterConfig config) throws ServletException {}

	
}
