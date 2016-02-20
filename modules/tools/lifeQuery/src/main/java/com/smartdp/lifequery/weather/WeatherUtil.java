package com.smartdp.lifequery.weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * 得到未来6天的天气(含今天)
 * 
 * @author pengfenglong
 * 
 */
public class WeatherUtil {
	
	private static final Logger LOGGER = Logger.getLogger(WeatherUtil.class);

	public static String getWeather(String cityCode) throws IOException,
			NullPointerException {
		StringBuilder sb = new StringBuilder();
		// 解析本机ip地址

		// 连接中央气象台的API
		URL url = new URL("http://m.weather.com.cn/atad/" + cityCode + ".html");
		URLConnection connectionData = url.openConnection();
		connectionData.setConnectTimeout(10000);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connectionData.getInputStream(), "UTF-8"));

			String line = null;
			while ((line = br.readLine()) != null)
				sb.append(line);
		} catch (SocketTimeoutException e) {
			LOGGER.error("连接超时", e);
			System.out.println("连接超时");
		} catch (FileNotFoundException e) {
			LOGGER.error("加载文件出错", e);
		}
		String datas = sb.toString();

		JSONObject jsonData = JSONObject.fromObject(datas);
		// System.out.println(jsonData.toString());
		JSONObject info = jsonData.getJSONObject("weatherinfo");

		// 得到1到6天的天气情况
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 1; i <= 6; i++) {
			// 得到未来6天的日期
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, i - 1);
			Date date = cal.getTime();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("city", info.getString("city").toString());// 城市
			map.put("date_y", sf.format(date));// 日期
			map.put("week", getWeek(cal.get(Calendar.DAY_OF_WEEK)));// 星期
			map.put("fchh", info.getString("fchh").toString());// 发布时间
			map.put("weather", info.getString("weather" + i).toString());// 天气
			map.put("temp", info.getString("temp" + i).toString());// 温度
			map.put("wind", info.getString("wind" + i).toString());// 风况
			map.put("fl", info.getString("fl" + i).toString());// 风速
			map.put("index", info.getString("index").toString());// 今天的穿衣指数
			map.put("index_uv", info.getString("index_uv").toString());// 紫外指数
			map.put("index_tr", info.getString("index_tr").toString());// 旅游指数
			map.put("index_co", info.getString("index_co").toString());// 舒适指数
			map.put("index_cl", info.getString("index_cl").toString());// 晨练指数
			map.put("index_xc", info.getString("index_xc").toString());// 洗车指数
			map.put("index_d", info.getString("index_d").toString());// 天气详细穿衣指数
			list.add(map);
		}
		StringBuffer sBuff = new StringBuffer();
		// 控制台打印出天气
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> wMap = list.get(j);
			sBuff.append(
					wMap.get("date_y") + " " + wMap.get("week") + " "
							+ wMap.get("weather") + " " + wMap.get("temp"))
					.append("\n");
		}
		return sBuff.toString();

	}

	private static String getWeek(int iw) {
		String weekStr = "";
		switch (iw) {
		case 1:
			weekStr = "星期天";
			break;
		case 2:
			weekStr = "星期一";
			break;
		case 3:
			weekStr = "星期二";
			break;
		case 4:
			weekStr = "星期三";
			break;
		case 5:
			weekStr = "星期四";
			break;
		case 6:
			weekStr = "星期五";
			break;
		case 7:
			weekStr = "星期六";
			break;
		default:
			break;
		}
		return weekStr;
	}

	public static void main(String[] args) {
		try {
			System.out.println(getWeather("101010100")); // 101010100(北京)就是你的城市代码
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}