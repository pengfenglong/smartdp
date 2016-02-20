/**
 * ClassUtils.java
 * com.acpframework.core.util
 *
 * Function： TODO 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0		 2011-7-24 		T410i
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
*/

package com.smartdp.core.utils;

import org.apache.commons.lang.xwork.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

/**
 * ClassName:ClassUtils
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   liqi
 * @version  
 * @since    Version 1.0
 * @Date	 2011-7-24		下午09:48:48
 *
 * @see 	 
 */
public abstract class ClassUtil extends ClassUtils
{
	private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);
	/**
	 * 返回默认的类加载器，为类路径加载
	 * @return 类加载器
	 */
	public static ClassLoader getDefaultClassLoader()
	{
		ClassLoader cl = null;
		cl = Thread.currentThread().getContextClassLoader();
		if (null == cl)
		{
			cl = ClassUtil.class.getClassLoader();
		}
		return cl;
	}
	
	/**
	 * 转换类名称为类
	 * @param names 类名称
	 * @return 类数组
	 */
	public static Class<?>[] parseClassString(String[] names)
	{
		if (ArrayUtils.isEmpty(names))
		{
			return null;
		}
		Class<?>[] classes = new Class<?>[names.length];
		for (int i = 0; i < names.length; i++)
		{
			try
			{
				classes[i] = forName(names[i], getDefaultClassLoader());
			}
			catch (ClassNotFoundException e)
			{
				log.warn("类没有找到，[class=" + names[i]);
			}
			catch (LinkageError e)
			{
				log.warn("类连接错误，[class=" + names[i]);
				
			}
		}
		return classes;
	}
}

