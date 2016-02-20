package com.smartdp.core.web.loadpoint;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartdp.core.config.DefaultResourceLoader;

/**
 * 属性文件加载点
 * @author peng
 *
 */
public class PropertiesLoadPoint implements ISystemLoadPoint {

	protected final Log log = LogFactory.getLog(getClass());
	
	private ServletContextEvent event;
	
	public PropertiesLoadPoint(ServletContextEvent event) {
		this.event = event;
	}
	@Override
	public void startup() {
		log.info("开始加载属性文件...");
		String[] locations =DefaultResourceLoader.getInstance().getPropertiesConfig();
	}

	@Override
	public void shutdown() {
	}

}
