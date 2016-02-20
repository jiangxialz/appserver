package com.maker.app.util.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.maker.app.common.AppInterface;
import com.maker.app.common.Global;
import com.maker.app.constant.AppConstants;
import com.maker.app.entity.AppSession;
import com.maker.app.entity.AppVisitLog;
import com.maker.app.util.SpringBeansUtil;

/**
 * app访问日志工具类
 * @author hlz
 * @date 2015-5-8 上午11:56:21
 */
public class AppVisitLogUtil extends SpringBeansUtil{
	
	private static Logger logger = Logger.getLogger(AppVisitLogUtil.class);
	
	/**
	 * 从缓存中访问日志缓存
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<AppVisitLog> getAppVisitLogListByCache() {
		List<AppVisitLog> list = Global.get(Global.APP_VISIT_LOG, List.class);
		return list;
	}

	/**
	 * 新增访问操作记录
	 * @param service
	 * @param ai
	 * @param start
	 * @param elapse
	 * @param msg
	 */
	public static void addAppVisitLog(Object service, AppInterface ai, long start, long elapse, String msg) {
		String ip = ai.getIp();
		AppVisitLog avl = new AppVisitLog();
		AppSession as = ai.getAppSession();
		if (as != null) {
			avl.setUserId(as.getUserId());
		}
		
		avl.setElapse((int)elapse);
		avl.setErrMsg(msg);
		avl.setInterfaceName(ai.getName());
		avl.setIp(ip);
		
		avl.setVisitTime(new Timestamp(start));
		avl.setResult(msg.length() == 0 ? AppConstants.RESULT_1 : AppConstants.RESULT_2);
		
		logger.info("AppVisitLogUtil addAppVisitLog : " + avl);
		
		//加入缓存中
		getAppVisitLogListByCache().add(avl);
		
	}

	/**
	 * 批量保存日志记录，供调度使用
	 * @return 保存的记录条数
	 */
	public static int batchSaveAppVisitLogList() {
		
		int k = 0;
		try {
			
			List<AppVisitLog> appVisitLogList = getAppVisitLogListByCache();
			List<AppVisitLog> toInsertList = new ArrayList<AppVisitLog>();
	
			while (appVisitLogList.size() > 0) {
				// 先删除，再加入，防止并发导致重复加入
				AppVisitLog ol = appVisitLogList.remove(0);
				toInsertList.add(ol);
				k++;
				// 满条数后，批量插入
				if (toInsertList.size() >= AppConstants.LOG_BATCH_SIZE) {
					appVisitLogService.addList(toInsertList);
					toInsertList = new ArrayList<AppVisitLog>();
				}
			}
			// insert剩余的
			if (toInsertList.size() > 0) {
				appVisitLogService.addList(toInsertList);
				toInsertList.clear();
			}
		
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		logger.info("AppVisitLogUtil batchSaveAppVisitLogList size : " + k);
		
		return k;
	}

}
