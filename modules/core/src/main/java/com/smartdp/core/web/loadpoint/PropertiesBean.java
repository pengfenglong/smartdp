/**
 * PropertiesBean.java
 * com.acpframework.core.loader.support 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0.0		 2011-10-1 		Liqi
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
*/

package com.smartdp.core.web.loadpoint;

import java.util.Map;
import java.util.Properties;

/**
 * 属性文件类
 * 这个类用于存放在系统加载时的properties
 * ClassName:PropertiesBean
 *
 * @author   Liqi
 * @version  1.0.0 
 * @Date	 2011-10-1		下午9:48:10
 * @see ACPPropertyPlaceholderConfigurer#resetProperties()	 
 */
public class PropertiesBean
{
	/**可以存储到内存中的properties*/
	private static Properties propertiesCache = new Properties();
	
	public static void setProperties(Properties properties)
	{
		propertiesCache = properties;
	}
	
	public static void clear()
	{
		propertiesCache.clear();
	}
	
	public static Object put(Object key, Object value)
	{
		return propertiesCache.put(key, value);
	}
	
	public static void putAll(Map m)
	{
		propertiesCache.putAll(m);
	}
	
	public static void remove(Object key)
	{
		propertiesCache.remove(key);
	}
	
	public static boolean isEmpty()
	{
		return propertiesCache.isEmpty();
	}
	
	public static int size()
	{
		return propertiesCache.size();
	}
	
	public static Object getProperty(Object key)
	{
		return propertiesCache.get(key);
	}
	
	
}

