package com.smartdp.core.web.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.ng.HostConfig;
import org.apache.struts2.dispatcher.ng.InitOperations;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.smartdp.core.config.DefaultResourceLoader;

public class Struts2InitOperations extends InitOperations{
	
    public Dispatcher initDispatcher( HostConfig filterConfig ) {
        Dispatcher dispatcher = createDispatcher(filterConfig);
        dispatcher.init();
        return dispatcher;
    }
	   
    private Dispatcher createDispatcher( HostConfig filterConfig ) {
		Map<String, String> params = new HashMap<String, String>();
		for (Iterator e = filterConfig.getInitParameterNames(); e.hasNext();) {
			String name = (String) e.next();
			String value = filterConfig.getInitParameter(name);
			params.put(name, value);
		}

		//获得所有组件的struts配置
		String[] locations = DefaultResourceLoader.getInstance().getStrutsConfig();
		Assert.notNull(locations, "Struts没有配置");
		String locationsStr = StringUtils.arrayToDelimitedString(locations, ",");
		params.put("config",locationsStr);
		return new Dispatcher(filterConfig.getServletContext(), params);
    }

}
