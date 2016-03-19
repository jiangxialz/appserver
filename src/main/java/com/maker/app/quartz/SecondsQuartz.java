package com.maker.app.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.maker.app.util.MessageUtils;
import com.maker.app.util.OptionUtils;

/**
 * 2秒调度
 * @author lz
 * 2014-11-25 下午2:13:13
 *
 */
public class SecondsQuartz extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		//查询配置文件是否更改
		OptionUtils.reload();
		
		//查询多语言文件是否更改
		MessageUtils.reload();
	}

}
