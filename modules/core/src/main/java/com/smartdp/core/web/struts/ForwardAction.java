/**
 * ForwardActionSupport.java
 * com.acpframework.core.web.struts 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0.0		 2011-10-9 		Liqi
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
 */

package com.smartdp.core.web.struts;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

/**
 * 页面跳转类 ClassName:ForwardActionSupport
 * 
 * @author Liqi
 * @version 1.0.0
 * @Date 2011-10-9 下午11:52:36
 */
public class ForwardAction extends BaseActionSupport
{
	/**
	 * （用一句话描述这个变量表示什么）
	 * 
	 * @version Version 1.0
	 */

	private static final long serialVersionUID = 1L;

	private String prefix;

	private String paramterName;

	/**
	 * prefix 前缀
	 * 
	 * @param prefix
	 *            the prefix to set
	 * @since CodingExample Ver 1.0
	 */
	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	/**
	 * paramterName 参数名 如：xx.action?path=test.jsp,就是这个path
	 * 
	 * @param paramterName
	 *            the paramterName to set
	 * @since CodingExample Ver 1.0
	 */
	public void setParamterName(String paramterName)
	{
		this.paramterName = paramterName;
	}

	/**
	 * 路径到页面 如：前缀为/WEB-INF/page 参数名为path 输入地址xx.action?path=test.jsp
	 * 跳转到/WEB-INF/page/test.jsp
	 * 
	 * @return null
	 * @throws IOException
	 */
	public String doForward() throws IOException
	{
		try
		{
			if (StringUtils.isEmpty(prefix) || StringUtils.isEmpty(paramterName))
			{
				return null;
			}
			String path = this.getRequest().getParameter(paramterName);
			String fillPath = prefix + path;
			LOG.warn("Action config parameter is request.");
			this.getRequest().getRequestDispatcher(fillPath).forward(getRequest(), getResponse());
		}
		catch (Exception ex)
		{
			LOG.error("Forward error.", ex);
		}

		return null;
	}

}
