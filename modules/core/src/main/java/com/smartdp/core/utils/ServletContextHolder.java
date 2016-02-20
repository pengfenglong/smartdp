/**
 * Copyright (c) 2005-2010 acp.com.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: ServletContextHolder.java 1211 2010-09-10 16:20:45Z liqi $
 */
package com.smartdp.core.utils;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.web.context.ServletContextAware;

/**
 * 以静态变量保存Spring ServletContextHolder, 可在任何代码任何地方任何时候中取出ServletContext.
 * 
 * @author liqi
 */
public class ServletContextHolder implements ServletContextAware, DisposableBean {

	private static ServletContext servletContext = null;

	private static Logger logger = LoggerFactory.getLogger(ServletContextHolder.class);

	/**
	 * 实现ServletContextAware接口, 注入ServletContext到静态变量中.
	 */
	public void setServletContext(ServletContext servletContext) {
		logger.debug("注入ServletContext到ServletContextHolder:" + servletContext);

		if (ServletContextHolder.servletContext != null) {
			logger.warn("ServletContextHolder中的ServletContext被覆盖, 原有servletContext为:"
					+ ServletContextHolder.servletContext);
		}

		ServletContextHolder.servletContext = servletContext; //NOSONAR
	}

	/**
	 * 实现DisposableBean接口,在Context关闭时清理静态变量.
	 */
	@Override
	public void destroy() throws Exception {
		ServletContextHolder.clear();
	}

	/**
	 * 取得存储在静态变量中的ServletContext.
	 */
	public static ServletContext getServletContext() {
		assertContextInjected();
		
		return servletContext;
	}
	
	/**
	 * 获取服务器信息
	 * @return 服务器信息
	 */
	public static String getServerInfo()
	{
	    assertContextInjected();
	    return servletContext.getServerInfo();
	}
	
	/**
	 * 获得上下文路径
	 * @return 上下文路径
	 */
	public static String getContextPath()
	{
	    assertContextInjected();
	    return servletContext.getContextPath();
	}
	
	/**
	 * 获得上下文名称
	 * @return 上下文名称
	 */
	public static String getServletContextName()
	{
	    assertContextInjected();
	    return servletContext.getServletContextName();
	}

	/**
	 * 清除ServletContextHolder中的ServletContext为Null.
	 */
	public static void clear() {
		logger.debug("清除ServletContextHolder中的ServletContext:" + servletContext);
		servletContext = null;
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		if (servletContext == null) {
			throw new IllegalStateException("ServletContext未注入,请在applicationContext.xml中定义ServletContextHolder");
		}
	}
}
