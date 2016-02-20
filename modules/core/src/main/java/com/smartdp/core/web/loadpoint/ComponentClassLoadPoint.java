package com.smartdp.core.web.loadpoint;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartdp.core.classloader.ComponentLoaderManager;

/**
 * 类加载器加载点
 * 
 * @author peng
 * 
 */
public class ComponentClassLoadPoint implements ISystemLoadPoint {

	protected final Log log = LogFactory.getLog(getClass());

	private ServletContextEvent event;

	public ComponentClassLoadPoint(ServletContextEvent event) {
		this.event = event;
	}

	public void startup() {
		log.info("开始设置类加载器...");
		Thread.currentThread().setContextClassLoader(
				ComponentLoaderManager.getComponentClassLoader(event
						.getServletContext()));
	}

	public void shutdown() {
	}

}
