/**
 * Dom4jUtil.java
 * com.acpframework.core.util
 *
 * Function： TODO 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0		 2011-8-6 		T410i
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
*/

package com.smartdp.core.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import com.smartdp.core.exception.ServiceAccessException;

/**
 * ClassName:Dom4jUtil
 * dom4j工具类
 * @author   Liqi
 * @version  
 * @since    Version 1.0
 * @Date	 2011-8-6		下午05:15:46
 *
 * @see 	 
 */
public abstract class XMLUtils
{
	static SAXReader saxr = new SAXReader(); 
	/**
	 * 通过表达式的方式来解析XML，并返回一个列表信息
	 * 注意：这个解析路径是相对的，一般在classpath下，使用时请务必小心。
	 * @param xmlSourcePath XML资源路径，这个路径是相对的，一般在classpath下
	 * @param xpathExpression 表达式（如//resources/resource说明根节点resources下的所有resource）
	 * @return 列表信息（如resource的所有值）
	 */
	@SuppressWarnings("unchecked")
	public static List<String> parseXML(String xmlSourcePath, String xpathExpression)
	{
		Assert.notNull(xmlSourcePath, ExceptionUtils.toExceptionMessage("XML的路径不能为空", "xmlSourcePath", xmlSourcePath));
		Assert.notNull(xpathExpression, ExceptionUtils.toExceptionMessage("XML的路径公式不能为空", "xpathExpression", xpathExpression));
		try
		{
			SAXReader saxr = new SAXReader();
			Document document = saxr.read(ResourceUtils.getURL(xmlSourcePath));
			return document.selectNodes(xpathExpression);
		}
		catch (FileNotFoundException e)
		{
			throw new ServiceAccessException(ExceptionUtils.toExceptionMessage("文件不存在", "xmlSourcePath", xmlSourcePath));
		}
		catch (DocumentException e)
		{
			throw new ServiceAccessException(ExceptionUtils.toExceptionMessage("解析XML文件异常，请联系系统管理员", "xmlSourcePath", xmlSourcePath));
		}
	}
	
	/**
	 * 通过表达式来获得document中的节点列表。
	 * @param document document
	 * @param xpathExpression 表达式（如//resources/resource说明根节点resources下的所有resource）
	 * @return 列表信息（如resource的所有值）
	 */
	@SuppressWarnings("rawtypes")
	public static List getDocumentNodes(Document document, String xpathExpression)
	{
		return document.selectNodes(xpathExpression);
	}
	
	/**
	 * 获得DOCUMENT节点的所有值
	 * @param document
	 * @param xpathExpression
	 * @see getDocumentNodes(Document document, String xpathExpression)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getDocumentNodesValue(Document document, String xpathExpression)
	{
		List<String> listStr = new ArrayList<String>();
		List list = getDocumentNodes(document, xpathExpression);
		Iterator it = list.iterator();
		while(it.hasNext())
		{
			DefaultElement de = (DefaultElement)it.next();
			listStr.add(de.getText());
		}
		return listStr;
	}
	
	/**
	 * 获得XML的文件描述信息
	 * @param xpath 这个路径是上下文的绝对路径
	 * @return Document
	 */
	public static Document getDocument(String xpath)
	{
		try
		{
			return saxr.read(xpath);
		}
		catch (DocumentException e)
		{
			throw new ServiceAccessException(ExceptionUtils.toExceptionMessage("读取XML文件异常", "xpath", xpath));
		}
	}
}

