package com.maker.app.util.business;

import java.util.Map;

import com.maker.app.common.Global;
import com.maker.app.constant.IpRequestNumConstant;
import com.maker.app.exception.AppException;
import com.maker.app.util.MessageUtils;
import com.maker.app.util.OptionUtils;

/**
 * IP请求次数控制
 * @author hlz
 * @date 2015-5-29 下午6:33:16
 */
public class IpRequestNumUtil {
	
	/**
	 * 验证请求,15分钟清空
	 * @param ip
	 * @param operationType
	 * @throws Exception 
	 */
	public static void verifyRequest(String ip,String operationType) {
		
		 String key = ip + "#" + operationType;
		 Map<String,Integer> ipRequestNumCache = Global.get(Global.IP_REQUEST_NUM_CACHE, Map.class);
		 Integer num = ipRequestNumCache.get(key);
		 if(num==null){
			 num = 0;
		 }
		 ipRequestNumCache.put(key, num+1);
		 
		 // http请求次数控制
		 if(operationType.equals(IpRequestNumConstant.GLOBALREQ)){
			 // 限制次数
			 int limitNum = Integer.valueOf(OptionUtils.getDefaultOption("global.req.limit.num", "300"));
			 if(num > limitNum){
				 throw new AppException(MessageUtils.getText("global.req.ip.num.abnormal"));
			 }
		 }
		 
	}
	
	
	/**
	 * 清理缓存
	 */
	public static void clearCache(){
		Map<String,Integer> ipRequestNumCache = Global.get(Global.IP_REQUEST_NUM_CACHE, Map.class);
		ipRequestNumCache.clear();
	}
	

}
