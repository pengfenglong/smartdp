package com.smartdp.wechat.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存
 * @author pengfenglong
 *
 */
public class Cache {
	/**
	 * 缓存实例的容器
	 */
	private static Map<String, Object> map = new HashMap<String, Object>();

	public static Cache instance = new Cache();

	/**
	 * 私有化构造方法
	 */
	private Cache() {
	}

	public static Cache getInstance() {
		return instance;
	}

	public void set(String key, Object value) {
		map.put(key, value);
	}

	public Object get(String key) {
		return map.get(key);
	}
	
}