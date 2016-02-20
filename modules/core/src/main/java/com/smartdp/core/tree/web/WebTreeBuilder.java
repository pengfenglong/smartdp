package com.smartdp.core.tree.web;

import javax.servlet.http.HttpServletRequest;

import com.smartdp.core.tree.ITreeBuilder;

/**
 * 
 * @author pengfenglong
 *
 */
public interface WebTreeBuilder extends ITreeBuilder{
	public void init(HttpServletRequest pRequest);
	public String getTreeScript();
}
