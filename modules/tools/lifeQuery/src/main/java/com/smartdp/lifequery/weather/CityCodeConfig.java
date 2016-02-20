package com.smartdp.lifequery.weather;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 城市和编码设置
 * 
 * @author pengfenglong
 * 
 */
public class CityCodeConfig {
	
	private static final Logger LOGGER = Logger.getLogger(CityCodeConfig.class);

	private static CityCodeConfig instance;

	public static Map<String, String> cityAndCodeMap = new HashMap<String, String>();

	static {
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/com/smartdp/lifequery/weather/city_code.properties"));
			//props.load(CityCodeConfig.class.getResourceAsStream("/com/smartdp/lifequery/weather/city_code.properties"));

			for (Object key : props.keySet()) {
				String city = new String(
						((String) props.getProperty((String) key))
								.getBytes("ISO8859-1"),
						"UTF-8");
				String code = (String) key;
				cityAndCodeMap.put(city, code);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error("城市编码配置文件没有找到", e);
		} catch (IOException e) {
			LOGGER.error("城市编码配置文件读取异常", e);
		}
	}

	private CityCodeConfig() {

	}

	public static CityCodeConfig getInstance() {
		if (instance == null) {
			instance = new CityCodeConfig();
		}
		return instance;
	}

	public static void main(String[] args) {
		System.out.println(CityCodeConfig.getInstance().cityAndCodeMap.get("深圳"));
	}

}
