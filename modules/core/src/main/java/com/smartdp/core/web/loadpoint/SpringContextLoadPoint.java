package com.smartdp.core.web.loadpoint;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Spring上下文加载点
 * 
 * @author peng
 * 
 */
public class SpringContextLoadPoint implements ISystemLoadPoint {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private ServletContextEvent event;

	private SpringContextLoader loader = new SpringContextLoader(event);

	public SpringContextLoadPoint(ServletContextEvent event) {
		this.event = event;
	}

	public void startup() {
		log.info("开始加载spring配置文件...");
		//调用SpringContextLoader中重写的closeWebApplicationContext方法
		loader.initWebApplicationContext(event.getServletContext());
	}

	public void shutdown() {
		//调用ContextLoader中的closeWebApplicationContext方法
		loader.closeWebApplicationContext(event.getServletContext());
	}

}
