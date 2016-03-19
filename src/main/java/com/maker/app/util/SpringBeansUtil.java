package com.maker.app.util;

import com.maker.app.common.SpringService;
import com.maker.app.service.IAppVisitLogService;

/**
 * spring服务定义
 * @author hlz
 * @date 2015-5-8 上午11:54:17
 */
public abstract class SpringBeansUtil {

	protected static IAppVisitLogService appVisitLogService = (IAppVisitLogService)SpringService.getBean("appVisitLogService");
	
}
