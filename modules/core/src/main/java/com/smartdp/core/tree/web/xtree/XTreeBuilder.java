/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 欢迎加入 E3平台联盟QQ群:21523645 
 */
package com.smartdp.core.tree.web.xtree;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartdp.core.templateEngine.Context;
import com.smartdp.core.templateEngine.support.DefaultContext;
import com.smartdp.core.templateEngine.support.StrTemplateUtil;
import com.smartdp.core.tree.ITreeNode;
import com.smartdp.core.tree.exception.BuildTreeException;
import com.smartdp.core.tree.web.AbstractWebTreeBuilder;
import com.smartdp.core.tree.web.WebTreeNode;

/**
 * 这是XTree的Builder,负责构造XTree
 * @author pengfenglong
 *
 */
public class XTreeBuilder extends AbstractWebTreeBuilder{
	
	private final Log log = LogFactory.getLog(XTreeBuilder.class);
	
	public static final String DEFAULT_BEHAVIOR = "classic";
	private String behavior =  DEFAULT_BEHAVIOR;
	private boolean usePersistence = false;
	/**
	 * xtree.css文件,要是完整路径
	 */
	private String xtreeStyle;

	private String resourceHome;
	
	
    /**
     * 跟节点是否展开
     */
    private boolean rootExpand = true; 

	
	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
	
	/**
	 * 负责导入Tree所需要的js,css
	 */
	public void buildTreeStart() throws BuildTreeException {
		StringBuffer resouces = new StringBuffer();
		resouces.append("<link type='text/css' rel='stylesheet' href='${xtreeStyle}' />").append(ENTER);		
		resouces.append("<script src='${resouceHome}/xtree.js'></script>").append(ENTER);
		
		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("xtreeStyle", getXtreeStyle());
		
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	
	public void buildRootNodeStart(ITreeNode pRootNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pRootNode instanceof WebTreeNode == false){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode type");
		}
		treeScript.append("<script language='javascript'>").append(ENTER);
		WebTreeNode node = (WebTreeNode)pRootNode;
		StringBuffer nodeTemplate = new StringBuffer();
		
		nodeTemplate.append("webFXTreeConfig.usePersistence = ${usePersistence};").append(ENTER);
		nodeTemplate.append("webFXTreeConfig.setImagePath(\"${imagePath}\");").append(ENTER);
		nodeTemplate.append("   var ${nodeScriptName}=new WebFXTree(\"${text}\",").
		     append("\"${action}\",\"${behavior}\",\"${icon}\",\"${openIcon}\",${open} ); ");	
		nodeTemplate.append(ENTER);
		
		Context context = new DefaultContext(); 
		context.put("usePersistence", new Boolean( this.isUsePersistence()) );
		context.put("nodeScriptName", getNodeScriptName(node));
		context.put("text", node.getName());	
		context.put("behavior", this.behavior);
		context.put("imagePath", this.getResourceHome() + "/images/");
		context.put("action", node.getAction());		
        context.put("icon", node.getIcon() );
		context.put("openIcon", node.getOpenIcon() );
		context.put("open", this.rootExpand);

		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));		
	}
	
		
	public void buildRootNodeEnd(ITreeNode pRootNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pRootNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		StringBuffer temp = new StringBuffer();
		temp.append("   document.write(${rootScript});").append(ENTER);
		temp.append("</script>").append(ENTER);
		Context context = new DefaultContext(); 
		context.put("rootScript", getNodeScriptName((WebTreeNode)pRootNode));
		treeScript.append(StrTemplateUtil.merge(temp.toString(), context));		   
	}
	

	public void buildNodeStart(ITreeNode pNode, ITreeNode pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		 
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		
		StringBuffer nodeTemplate = new StringBuffer();
		nodeTemplate.append("   var ${nodeScriptName}=new WebFXTreeItem(\"${text}\",").
		     append("\"${action}\",${parent},\"${icon}\",\"${openIcon}\"); ");	
		nodeTemplate.append(ENTER);

		Context context = new DefaultContext(); 
		context.put("nodeScriptName", getNodeScriptName(webTreeNode));
		context.put("text", webTreeNode.getName());
		context.put("action", webTreeNode.getAction());
		context.put("icon", webTreeNode.getIcon());
		context.put("openIcon", webTreeNode.getOpenIcon() );
		context.put("parent", parentNodeScriptName);
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));
		
	}


	public String getResourceHome() {
		if ( resourceHome == null ){
			return this.request.getContextPath() + "/framework/e3/tree/xtree";
		} else {
			return resourceHome;
		}
	}

	public void setResourceHome(String resourceHome) {
		this.resourceHome = resourceHome;
	}

	public boolean isUsePersistence() {
		return usePersistence;
	}

	public void setUsePersistence(boolean usePersistence) {
		this.usePersistence = usePersistence;
	}

	public String getXtreeStyle() {
		if ( xtreeStyle == null ){
			return getResourceHome() + "/xtree.css";
		}
		return xtreeStyle;
	}

	public void setXtreeStyle(String xtreeStyle) {
		this.xtreeStyle = xtreeStyle;
	}

	/**
	 * @deprecated use isRootExpand
	 * @return
	 */
	public boolean isOpen() {
		return rootExpand;
	}

	/**
	 * @deprecated use setRootExpand
	 * @param open
	 */
	public void setOpen(boolean open) {
		this.rootExpand = open;
	}

	public boolean isRootExpand() {
		return rootExpand;
	}

	public void setRootExpand(boolean rootExpand) {
		this.rootExpand = rootExpand;
	}


}
