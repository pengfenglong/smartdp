package com.smartdp.core.web.loadpoint;

import javax.servlet.ServletContextEvent;


/**
 * 国际化加载点
 * @author peng
 *
 */
public class InternationalizationLoadPoint implements ISystemLoadPoint{
	
	private ServletContextEvent event;
	public InternationalizationLoadPoint(ServletContextEvent event) {
		this.event = event;
	}
	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
