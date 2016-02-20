package com.smartdp.core.web.loadpoint;

import java.io.IOException;

import org.apache.commons.lang.xwork.ArrayUtils;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.smartdp.core.config.DefaultResourceLoader;
import com.smartdp.core.exception.ServiceAccessException;

/**
 * log4j加载器
 * @author peng
 *
 */
public class Log4jConfigLoader {
	
	private static final Logger log = LoggerFactory.getLogger(Log4jConfigLoader.class);
	
	public void init(){
		PropertiesFactoryBean loader = new PropertiesFactoryBean();
		try {
			String[] locations = DefaultResourceLoader.getInstance().getLog4jConfig();
			Resource[] resources = new Resource[locations.length];
			for(int i = 0 ; i < locations.length ; i ++ ){
				resources[i] = new FileSystemResource(locations[i]);
			}
			log.info("正在加载日志配置文件   ==> locations = " + ArrayUtils.toString(locations));
			loader.setLocations(resources);
			loader.afterPropertiesSet();
			PropertyConfigurator.configure(loader.getObject());
		} catch (IOException e) {
			log.error("加载日志配置文件错误。", e);
			throw new ServiceAccessException("加载日志配置文件错误。", e);
		}
	}

}
