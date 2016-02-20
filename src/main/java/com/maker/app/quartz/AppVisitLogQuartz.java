package com.maker.app.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.maker.app.util.business.AppVisitLogUtil;
/**
 * 访问日志调度
 * @author hlz
 * @date 2015-5-8 下午3:21:58
 */
public class AppVisitLogQuartz extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		//批量保存日志记录
		AppVisitLogUtil.batchSaveAppVisitLogList();
	}

}
