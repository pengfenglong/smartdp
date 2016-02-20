package com.smartdp.core.web.listen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.smartdp.core.config.DefaultResourceLoader;
import com.smartdp.core.web.loadpoint.ISystemLoadPoint;
import com.smartdp.core.web.loadpoint.InternationalizationLoadPoint;
import com.smartdp.core.web.loadpoint.Log4jConfigLoadPoint;
import com.smartdp.core.web.loadpoint.PropertiesLoadPoint;
import com.smartdp.core.web.loadpoint.SpringContextLoadPoint;

/**
 * 系统上下文监听器
 * 
 * @author peng
 * 
 */
public class SystemContextLoaderListener implements ServletContextListener {

	protected final Log log = LogFactory.getLog(getClass());
	// 加载点
	protected List<ISystemLoadPoint> loadPoints = new ArrayList<ISystemLoadPoint>();

	public void contextInitialized(ServletContextEvent event) {
		DefaultResourceLoader.getInstance().loadConfig();
		initLoader(event);
		for (ISystemLoadPoint loadPoint : loadPoints) {
			// 启动加载点
			loadPoint.startup();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {

		for (ISystemLoadPoint loadPoint : loadPoints) {
			// 销毁加载点
			loadPoint.shutdown();
		}
	}

	/**
	 * 初始化默认加载点
	 * 
	 * @param event
	 */
	public void initLoader(ServletContextEvent event) {
		try {
			String[] initFiles = DefaultResourceLoader.getInstance()
					.getInitConfig();
			SAXReader reader = new SAXReader();
			for (String initFile : initFiles) {
				Document doc = reader.read(new FileInputStream(URLDecoder
						.decode(initFile, "UTF-8")));
				String[] loadPointClasses = doc.selectSingleNode(
						"/config/loadpoint").getText().split(",");
				for(String loadPointClass : loadPointClasses){
					Class clazz = Class.forName(loadPointClass.trim());
					Constructor constructor = clazz
							.getConstructor(ServletContextEvent.class);
					ISystemLoadPoint loadPoint = (ISystemLoadPoint) constructor
							.newInstance(event);
					loadPoints.add(loadPoint);
				}
			}
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		} catch (SecurityException e) {
			log.error(e);
		} catch (IllegalArgumentException e) {
			log.error(e);
		} catch (DocumentException e) {
			log.error(e);
		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (NoSuchMethodException e) {
			log.error(e);
		} catch (InstantiationException e) {
			log.error(e);
		} catch (IllegalAccessException e) {
			log.error(e);
		} catch (InvocationTargetException e) {
			log.error(e);
		}
		addLoader(event);
	}

	/**
	 * 子类实现
	 */
	protected void addLoader(ServletContextEvent event) {

	}

}
