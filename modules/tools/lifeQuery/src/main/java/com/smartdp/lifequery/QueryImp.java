package com.smartdp.lifequery;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

public abstract class QueryImp implements Query {

	private static final Logger LOGGER = Logger.getLogger(QueryImp.class);

	public String getEncoding() {
		return "UTF-8";
	}

	/**
	 * http请求
	 * 
	 * @param uri
	 * @return
	 */
	public String http(String uri) {
		StringBuilder sBuff = new StringBuilder();
		try {
			URL url = new URL(uri);
			URLConnection connectionData = url.openConnection();
			connectionData.setConnectTimeout(10000);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connectionData.getInputStream(), getEncoding()));
			String line = null;
			while ((line = br.readLine()) != null)
				sBuff.append(line);
		} catch (SocketTimeoutException e) {
			LOGGER.error("连接超时", e);
			System.out.println("连接超时");
		} catch (FileNotFoundException e) {
			LOGGER.error("加载文件出错", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		String datas = sBuff.toString();
		return datas;

	}

	@Override
	public String getJsonStrResult(String input) {
		return http(input);
	}

	@Override
	public String getXmlStrResult(String input) {
		return http(input);
	}

	@Override
	public JSONObject getJsonObjectResult(String input) {
		String jsoStr = getJsonStrResult(input);
		JSONObject jsonObject = JSONObject.fromObject(jsoStr);
		return jsonObject;
	}

	@Override
	public Map getMapResult(String input) {
		JSONObject jsonObject = getJsonObjectResult(input);
		return (Map) jsonObject;
	}

}
