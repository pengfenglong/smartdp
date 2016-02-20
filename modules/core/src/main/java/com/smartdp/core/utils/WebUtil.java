/**
 * WebUtil.java
 * com.acpframework.core.util 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0.0		 2011-10-10 		Liqi
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
 */

package com.smartdp.core.utils;

import java.io.FileNotFoundException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.xwork.ArrayUtils;
import org.springframework.util.Assert;
import org.springframework.web.util.WebUtils;

/**
 * web工具类 ClassName:WebUtil
 * 
 * @author Liqi
 * @version 1.0.0
 * @Date 2011-10-10 上午1:11:32
 */
public abstract class WebUtil extends WebUtils
{
	/**
	 * 获得请求的值
	 * @param request ServletRequest
	 * @param name 参数名
	 * @param defaultValue 默认值
	 * @return 值
	 */
	public static String getParameter(ServletRequest request, String name, String defaultValue)
	{
		Assert.notNull(request, "ServletRequest must not be null");
		String v = request.getParameter(name);
		if (StringUtils.isEmpty(v))
		{
			return defaultValue;
		}
		return v;
	}

	/**
	 * 获得请求的值
	 * @param request ServletRequest
	 * @param name 参数名
	 * @param defaultValue 默认值
	 * @return 值
	 */
	public static int getParameter(ServletRequest request, String name, int defaultValue)
	{
		Assert.notNull(request, "ServletRequest must not be null");
		String v = request.getParameter(name);
		if (StringUtils.isEmpty(v))
		{
			return defaultValue;
		}
		return TypeConvertUtils.toInt(v);
	}

	/**
	 * 获得请求的值
	 * @param request ServletRequest
	 * @param name 参数名
	 * @param defaultValue 默认值
	 * @return 值
	 */
	public static double getParameter(ServletRequest request, String name, double defaultValue)
	{
		Assert.notNull(request, "ServletRequest must not be null");
		String v = request.getParameter(name);
		if (StringUtils.isEmpty(v))
		{
			return defaultValue;
		}
		return TypeConvertUtils.toDouble(v);
	}

	/**
	 * 获得请求的值
	 * @param request ServletRequest
	 * @param name 参数名
	 * @param defaultValue 默认值
	 * @return 值
	 */
	public static long getParameter(ServletRequest request, String name, long defaultValue)
	{
		Assert.notNull(request, "ServletRequest must not be null");
		String v = request.getParameter(name);
		if (StringUtils.isEmpty(v))
		{
			return defaultValue;
		}
		return TypeConvertUtils.toLong(v);
	}

	/**
	 * 获得请求的值数组
	 * @param request ServletRequest
	 * @param name 参数名
	 * @param defaultValue 默认值
	 * @return 值数组
	 */
	public static String[] getParameterValues(ServletRequest request, String name, String[] defaultValues)
	{
		Assert.notNull(request, "ServletRequest must not be null");
		String[] vs = request.getParameterValues(name);
		if (ArrayUtils.isEmpty(vs))
		{
			return defaultValues;
		}
		return vs;
	}

	/**
	 * 获得请求的值数组
	 * @param request ServletRequest
	 * @param name 参数名
	 * @param defaultValue 默认值
	 * @return 值数组
	 */
	public static int[] getParameterValues(ServletRequest request, String name, int[] defaultValues)
	{
		Assert.notNull(request, "ServletRequest must not be null");
		String[] vs = request.getParameterValues(name);
		if (ArrayUtils.isEmpty(vs))
		{
			return defaultValues;
		}
		return TypeConvertUtils.toInt(vs);
	}

	/**
	 * 获得请求的值数组
	 * @param request ServletRequest
	 * @param name 参数名
	 * @param defaultValue 默认值
	 * @return 值数组
	 */
	public static double[] getParameterValues(ServletRequest request, String name, double[] defaultValues)
	{
		Assert.notNull(request, "ServletRequest must not be null");
		String[] vs = request.getParameterValues(name);
		if (ArrayUtils.isEmpty(vs))
		{
			return defaultValues;
		}
		return TypeConvertUtils.toDouble(vs);
	}

	/**
	 * 获得请求的属性值
	 * @param request ServletRequest
	 * @param name 参数名
	 * @param defaultValue 默认值
	 * @return 属性值
	 */
	public static String getAttribute(ServletRequest request, String name, String defaultValue)
	{
		Assert.notNull(request, "ServletRequest must not be null");
		Object obj = request.getAttribute(name);
		String v = null;
		if (obj != null)
		{
			v = String.valueOf(obj);
		}
		if (StringUtils.isEmpty(v))
		{
			v = defaultValue;
		}
		return v;
	}

	/**
	 * 获得真实路径
	 * @param request HttpServletRequest
	 * @param path 相对路径
	 * @return 绝对路径
	 * @throws FileNotFoundException 文件找不到异常
	 */
	public static String getRealPath(HttpServletRequest request, String path) throws FileNotFoundException
	{
		return getRealPath(request.getSession().getServletContext(), path);
	}
}
