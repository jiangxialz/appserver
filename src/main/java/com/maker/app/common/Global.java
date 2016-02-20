package com.maker.app.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.maker.app.common.cache.Cache;
import com.maker.app.common.cache.MemoryCache;
import com.maker.app.entity.AppSession;
import com.maker.app.entity.AppVisitLog;

@SuppressWarnings("rawtypes")
public class Global {
	private static Logger logger = Logger.getLogger(Global.class);

	private static Cache cache;

	// 访问日志缓存
	public static final String APP_VISIT_LOG = "APP_VISIT_LOG";
	
	// app会话信息缓存
	public static final String APP_SESSION = "APP_SESSION";
	
	/** ip请求次数控制(ip#操作类型    次数 [15分钟清空一次,不放入数据库]) */
	public static final String IP_REQUEST_NUM_CACHE = "IP_REQUEST_NUM_CACHE";
	
	/** 平台订单锁缓存*/
	public static final String PT_ORDER_LOCK_MAP = "PT_ORDER_LOCK_MAP";

	public static void init() {
		
		// 默认内存
		cache = new MemoryCache();

		// 访问日志缓存(线程安全)
		final List<AppVisitLog> appVisitLogList = Collections.synchronizedList(new ArrayList<AppVisitLog>());
		cache.put(APP_VISIT_LOG, appVisitLogList);
		
		//ip请求次数控制
		final Map<String,Integer> ipRequestNumCache = new ConcurrentHashMap<String,Integer>();
		cache.put(IP_REQUEST_NUM_CACHE, ipRequestNumCache);
		
		// app会话信息
		final Map<String, AppSession> appSession = new ConcurrentHashMap<String, AppSession>();
		cache.put(APP_SESSION, appSession);
		
		logger.info("初始化全局缓存对象 ......");
	}

	/**
	 * 获得缓存中的对象
	 * 
	 * @param key
	 * @return
	 */
	public static <T> T get(String key, Class<T> c) {
		Object o = cache.get(key);
		if (o == null)
			return null;
		return c.cast(o);
	}

	/**
	 * 获得缓存中的对象
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return cache.get(key);
	}

	/**
	 * 获得缓存中Map对象的对象
	 * 
	 * @param key
	 * @param key2
	 * @param c
	 * @return
	 */
	public static <T> T get(String key, String key2, Class<T> c) {
		Map map = get(key, Map.class);
		if (map == null)
			return null;
		try {
			return c.cast(map.get(key2));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获得缓存中Map对象的对象
	 * 
	 * @param key
	 * @param key2
	 * @param c
	 * @return
	 */
	public static <T> T get(String key, Integer key2, Class<T> c) {
		Map map = get(key, Map.class);
		if (map == null)
			return null;
		try {
			return c.cast(map.get(key2));
		} catch (Exception e) {
			return null;
		}
	}
}
