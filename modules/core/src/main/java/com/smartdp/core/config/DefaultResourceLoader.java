package com.smartdp.core.config;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultResourceLoader implements ResourceLoader {
	private static final String CONFIG_NAME = "config";
	private static final String STRUTS = "struts";
	private static final String SPRING = "spring";
	private static final String LONG4J = "long4j";
	private static final String PROPERTIES = "properties";
	private static final String INIT = "init";
	private Map<String, List<String>> configMap = new HashMap<String, List<String>>();
	private static DefaultResourceLoader instance = new DefaultResourceLoader();
	private static Logger logger = LoggerFactory
			.getLogger(DefaultResourceLoader.class);

	private DefaultResourceLoader() {
	}

	public static DefaultResourceLoader getInstance() {
		return instance;
	}

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ex) {
		}
		if (cl == null) {
			cl = DefaultResourceLoader.class.getClassLoader();
		}
		return cl;
	}

	public List<File> getConfigFiles() {
		List<File> files = new ArrayList<File>();
		try {
			URL configFileUrl = getDefaultClassLoader().getResource("");
			if (configFileUrl == null) {
				logger.error("请配置config路径");
			} else {
				File configFile = new File(getDefaultClassLoader().getResource(
						"").toURI());
				for (File file : configFile.listFiles()) {
					files.add(file);
				}
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return files;

	}

	@Override
	public void loadConfig() {
		List<File> files = getConfigFiles();
		List<String> strutsFiles = new ArrayList<String>();
		List<String> springFiles = new ArrayList<String>();
		List<String> log4jFiles = new ArrayList<String>();
		List<String> propertiesFiles = new ArrayList<String>();
		List<String> initFiles = new ArrayList<String>();
		for (File f : files) {
			if ("struts.xml".equals(f.getName())) {
				strutsFiles.add(f.getPath());
			} else if ("applicationContext.xml".equals(f.getName())) {
				springFiles.add(f.getPath());
			} else if ("log4j.properties".equals(f.getName())) {
				log4jFiles.add(f.getPath());
			} else if (f.getName().endsWith(".properties")) {
				propertiesFiles.add(f.getPath());
			} else if (f.getName().endsWith("init.xml")) {
				initFiles.add(f.getPath());
			} else {
				System.out.println("no used config:" + f.getName());
			}
		}
		configMap.put(STRUTS, strutsFiles);
		configMap.put(SPRING, springFiles);
		configMap.put(LONG4J, log4jFiles);
		configMap.put(PROPERTIES, propertiesFiles);
		configMap.put(INIT, initFiles);
	}

	public String[] getSpringConfigs() {
		List<String> springFiles = configMap.get(SPRING);
		String[] files = new String[springFiles.size()];
		for (int i = 0; i < springFiles.size(); i++) {
			String file = springFiles.get(i).replace("\\", "/");
			files[i] = file.substring(file.indexOf("/WEB-INF/classes"),
					file.length());
		}
		return files;
	}

	@Override
	public String[] getStrutsConfig() {
		return list2array(configMap.get(STRUTS));
	}

	@Override
	public String[] getLog4jConfig() {
		return list2array(configMap.get(LONG4J));
	}

	@Override
	public String[] getPropertiesConfig() {
		return list2array(configMap.get(PROPERTIES));
	}

	@Override
	public String[] getInitConfig() {
		return list2array(configMap.get(INIT));
	}

	private String[] list2array(List<String> list) {
		String[] configs = new String[list.size()];
		list.toArray(configs);
		return configs;
	}

}
