package com.smartdp.core.tree.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartdp.core.tree.support.TreeBuilderSupport;

/**
 * 
 * @author pengfenglong
 *
 */
public abstract class AbstractWebTreeBuilder extends TreeBuilderSupport implements WebTreeBuilder {
	
	protected HttpServletRequest request = null;
	
	private static final int DEFAULT_BUFFER_SIZE = 200;
	private final Log log = LogFactory.getLog( this.getClass() );
	
	protected StringBuffer treeScript = null;
	private int bufferSize = DEFAULT_BUFFER_SIZE;
	
	public void init(HttpServletRequest pRequest){
		this.request = pRequest;
	    treeScript = new StringBuffer(bufferSize);		
	}
	
	
	/**
	 * 注意：
	 * 1:必须保证，同一个节点，获取的脚本名称必须相同。
	 *  也就是说,不管是第一次调用，还是第2次，返回的名字都一样
	 *  ，绝对不允许出现随机名称.
	 * 2:这里的命名规则不能再被修改，因为有很多页面依赖这种
	 *   命名方式，如果要修改，只能是写一个之类，覆盖这种命名方法.
	 */
	protected String getNodeScriptName(WebTreeNode pNode){
		return pNode.getId();
	}
	 
	protected boolean isEmpty(final String pUrl){
		if (pUrl == null ){
			return true;
		}
		if ( "".equals(pUrl.trim()) ){
			return true;
		} else {
			return false;
		}
	}
	
	public void clearScript(){
		if ( treeScript != null ){
			treeScript = new StringBuffer(bufferSize);
		}
	}
	public String getTreeScript(){
		String result = treeScript.toString();
		if ( log.isDebugEnabled() ){
			log.debug("script:\n" + result);
		}
		return result;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}
}
