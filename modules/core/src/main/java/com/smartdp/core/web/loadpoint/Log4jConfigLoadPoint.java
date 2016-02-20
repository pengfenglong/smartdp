package com.smartdp.core.web.loadpoint;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;


/**
 * 日志加载点
 * 
 * @author peng
 * 
 */
public class Log4jConfigLoadPoint implements ISystemLoadPoint {
	
	protected final Log log = LogFactory.getLog(getClass());
	private ServletContextEvent event;
	public Log4jConfigLoadPoint(ServletContextEvent event) {
		this.event = event;
	}
	public void startup() {
		log.info("开始加载log4j文件。");
		Log4jConfigLoader loader = new Log4jConfigLoader();
		loader.init();
	}

	public void shutdown() {
		LogManager.shutdown();
	}

}
