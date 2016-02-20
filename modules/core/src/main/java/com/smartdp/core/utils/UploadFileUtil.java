/**
 * UploadFileUtil.java
 * com.acpframework.core.util 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0.0		 2011-10-11 		Liqi
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
 */

package com.smartdp.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 上传文件工具类 ClassName:UploadFileUtil
 * 
 * @author Liqi
 * @version 1.0.0
 * @Date 2011-10-11 下午11:18:30
 */
public abstract class UploadFileUtil
{
	/**spring 公共上传文件处理类*/
	private static final CommonsMultipartResolver resolver = new CommonsMultipartResolver();

	/**
	 * 获得上传文件
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            文件名
	 * @return MultipartFile
	 */
	public static MultipartFile getUploadFile(HttpServletRequest request,
			String name)
	{
		return resolver.resolveMultipart(request).getFile(name);
	}

	/**
	 * 是否有上传文件 
	 * @param request HttpServletRequest
	 * @return true为是，否则为false
	 */
	public static boolean isMultipart(HttpServletRequest request)
	{
		return resolver.isMultipart(request);
	}

	/**
	 * 转换为更强大的MultipartHttpServletRequest
	 * @param request HttpServletRequest
	 * @return MultipartHttpServletRequest
	 */
	public static MultipartHttpServletRequest resolveMultipart(
			HttpServletRequest request)
	{
		return resolver.resolveMultipart(request);
	}

	/**
	 * 清除上传文件
	 * @param request HttpServletRequest
	 */
	public static void cleanupMultipart(HttpServletRequest request)
	{
		MultipartHttpServletRequest mRequest = resolveMultipart(request);
		resolver.cleanupMultipart(mRequest);
	}
}
