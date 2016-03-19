package com.maker.app.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.maker.app.util.business.IpRequestNumUtil;

public class IpRequestNumQuartz extends QuartzJobBean {
	
	private static Logger logger = Logger.getLogger(IpRequestNumQuartz.class);

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
			IpRequestNumUtil.clearCache();
		} catch (Exception e) {
			logger.error("error",e);
		}

	}

}
