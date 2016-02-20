package com.maker.app.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCache implements Cache {

	private static Map<String, Object> cache = new ConcurrentHashMap<String, Object>();
	
	public MemoryCache() {}

	public Object get(String key) {
		return cache.get(key);
	}

	public void put(String key, Object value) {
		cache.put(key, value);
	}

	public void remove(String key) {
		cache.remove(key);
	}

	public void update(String key, Object value) {
		cache.put(key, value);
	}
}
