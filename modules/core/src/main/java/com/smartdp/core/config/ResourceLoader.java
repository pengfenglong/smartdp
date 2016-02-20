package com.smartdp.core.config;

interface ResourceLoader {
	void loadConfig();
	String[] getSpringConfigs();
	String[] getStrutsConfig();
	String[] getLog4jConfig();
	String[] getPropertiesConfig();
	String[] getInitConfig();

}
