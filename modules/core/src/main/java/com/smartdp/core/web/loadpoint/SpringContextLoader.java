package com.smartdp.core.web.loadpoint;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.util.Assert;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.smartdp.core.config.DefaultResourceLoader;

/**
 * spring上下文加载点
 * @author peng
 *
 */
public class SpringContextLoader extends ContextLoader{
	
	private ServletContextEvent event;
	
	public SpringContextLoader(ServletContextEvent event){
		this.event = event;
	}

	
	/**
	 * 重写ContextLoader的createWebApplicationContext方法
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected WebApplicationContext createWebApplicationContext(
			ServletContext servletContext, ApplicationContext parent)
			throws BeansException {

		Class contextClass = determineContextClass(servletContext);
		if (!ConfigurableWebApplicationContext.class
				.isAssignableFrom(contextClass)) {
			throw new ApplicationContextException("Custom context class ["
					+ contextClass.getName() + "] is not of type ["
					+ ConfigurableWebApplicationContext.class.getName() + "]");
		}

		ConfigurableWebApplicationContext wac = (ConfigurableWebApplicationContext) BeanUtils
				.instantiateClass(contextClass);
		wac.setParent(parent);
		wac.setServletContext(servletContext);
		
		String[] locations = DefaultResourceLoader.getInstance().getSpringConfigs();
		
		Assert.notNull(locations, "Spring没有配置");
		wac.setConfigLocations(locations);
		wac.refresh();
		return wac;
	}
	

}
