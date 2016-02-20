package com.smartdp.core.tree.web;

import javax.servlet.http.HttpServletRequest;

import com.smartdp.core.tree.support.AbstractTreeModelCreator;

/**
 * 
 * @author pengfenglong
 *
 */
public abstract class AbstractWebTreeModelCreator extends AbstractTreeModelCreator{
	protected HttpServletRequest request = null;
	public void init(HttpServletRequest pRequest){
		this.request = pRequest;
	}
	
	/**
	 * 获取url连接地址，就是在url地址前添加WebContextPath路径. 
	 * @param pUrl
	 * @return
	 */
	protected String getUrl(String pUrl){
		if ( pUrl == null )
			return "";
		if ( pUrl.trim().length() == 0)
			return "";
		if ( request == null )
			throw new IllegalStateException("request is null, you should invoke init() method!");
		return RequestUtil.getUrl(pUrl, request);
	}
}
