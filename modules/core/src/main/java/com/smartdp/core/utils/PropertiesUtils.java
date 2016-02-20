/**
 * Copyright (c) 2005-2010 acp.com.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: PropertiesUtils.java 1211 2010-09-10 16:20:45Z calvinxiu $
 */
package com.smartdp.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * Properties Util函数.
 * 
 * @author calvin
 */
public class PropertiesUtils {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入.
	 * 文件路径使用Spring Resource格式, 文件编码使用UTF-8.
	 * 
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 */
	public static Properties loadProperties(String... resourcesPaths) throws IOException {
		Properties props = new Properties();

		for (String location : resourcesPaths) {

			logger.debug("Loading properties file from:" + location);

			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
			} catch (IOException ex) {
				logger.info("Could not load properties from classpath:" + location + ": " + ex.getMessage());
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}
		return props;
	}
	
	/**
	 * 转换字符串为properties
	 * 如jdbc.driverClassName=com.mysql.jdbc.Driver;jdbc.url=jdbc:mysql://
	 * localhost:3306/bme;jdbc.username=admin;jdbc.password=admin;
	 * @param pro 字符串
	 * @param separator 分符串
	 * @return Properties
	 */
	public static Properties toProperties(String pro, String separator)
	{
		Properties properties = new Properties();
		String[] propertyArray = pro.split(separator);
		for (int i =0; i < propertyArray.length; i++)
		{
			String property = propertyArray[i];
			if (property.indexOf("=") == -1)
			{
				continue;
			}
			String[] pros = property.split("=");
			if (pros == null) 
			{
                continue;
            }
			if (pros.length == 1)
			{
			    properties.put(pros[0], "");
			}
			else if (pros.length == 2)
			{
			    properties.put(pros[0], pros[1]);
			}
			else
			{
			    String pros2 = "";
			    for (int k = 1;k < pros.length;k++){
			        pros2 += pros[k];
			        if (k != pros.length-1)
			        {
			            pros2+="=";
			        }
			    }
			    properties.put(pros[0], pros2);
			}
		}
		return properties;
	}
}
