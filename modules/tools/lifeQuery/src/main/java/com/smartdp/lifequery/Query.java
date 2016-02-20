package com.smartdp.lifequery;

import java.util.Map;

import net.sf.json.JSONObject;

public interface Query {

	/**
	 * 获得json字符串结果
	 * 
	 * @param input
	 * @return
	 */
	public String getJsonStrResult(String input);

	/**
	 * 获得xml字符串结果
	 * 
	 * @param input
	 * @return
	 */
	public String getXmlStrResult(String input);

	/**
	 * 获得json对象结果
	 * 
	 * @param input
	 * @return
	 */
	public JSONObject getJsonObjectResult(String input);

	/**
	 * 获得map结果
	 * 
	 * @param input
	 * @return
	 */
	public Map getMapResult(String input);

}
