/**
 * ExceptionUtils.java
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
/**
 * ClassName:ExceptionUtils
 * 异常工具类
 * @author   Liqi
 * @version  
 * @since    Version 1.0
 * @Date	 2011-8-6		下午05:46:57
 *
 * @see 	 
 */
public abstract class ExceptionUtils
{
	/**
	 * 异常信息通用格式：msg。[name=o]
	 * toExceptionMessage:(这里用一句话描述这个方法的作用)
	 * @param msg 异常说明
	 * @param name 要增加的参数名
	 * @param o 要增加的参数值
	 * @return 格式后的字符串
	 */
	public static String toExceptionMessage(String msg, String name, Object o)
	{
		if (null == o)
		{
			return msg;
		}
		StringBuilder sb = new StringBuilder(47);
		sb.append(msg);
		sb.append("。[");
		sb.append(name);
		sb.append("=");
		sb.append(o.toString());
		sb.append("]");
		return sb.toString();
	}
}

