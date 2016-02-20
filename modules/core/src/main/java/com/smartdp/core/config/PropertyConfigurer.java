package com.smartdp.core.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 读取propertice配置文件
 * @author pengfenglong
 *
 */
public class PropertyConfigurer implements InitializingBean{
	private static Properties props;  
	  
    public static Object get(String name) {  
        return props.get(name);  
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		props = new Properties();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();  
	    Resource[] resources = null;
		try {
			resources = resolver.getResources("classpath*:config-*.properties");
			for(Resource resource : resources){
				props.load(resource.getInputStream());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}  
}
