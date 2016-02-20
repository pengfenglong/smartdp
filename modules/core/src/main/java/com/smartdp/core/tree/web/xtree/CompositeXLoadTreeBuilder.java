package com.smartdp.core.tree.web.xtree;


import com.smartdp.core.templateEngine.Context;
import com.smartdp.core.templateEngine.support.DefaultContext;
import com.smartdp.core.templateEngine.support.StrTemplateUtil;
import com.smartdp.core.tree.ITreeNode;
import com.smartdp.core.tree.exception.BuildTreeException;
import com.smartdp.core.tree.web.WebTreeDynamicNode;
import com.smartdp.core.tree.web.WebTreeNode;


public class CompositeXLoadTreeBuilder extends XLoadTreeBuilder{
	
	/** 
	 * 负责导入Tree所需要的js,css
	 */
	public void buildTreeStart() throws BuildTreeException {
		StringBuffer resouces = new StringBuffer();
		resouces.append("<link type='text/css' rel='stylesheet' href='${xtreeStyle}' />").append(ENTER);
		resouces.append("<script src='${resouceHome}/xtree.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/xloadtree.js'></script>").append(ENTER);		
		resouces.append("<script src='${resouceHome}/xmlextras.js'></script>").append(ENTER);		
		resouces.append("<script src='${resouceHome}/map.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/checkboxTreeItem.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/checkboxXLoadTree.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/radioTreeItem.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/radioXLoadTree.js'></script>").append(ENTER);
		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("xtreeStyle", this.getXtreeStyle());
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	
	private void buildNormalNodeStart(ITreeNode pNode, ITreeNode pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		String nodeProperty = webTreeNode.getNodeProperty();
		if ( WebTreeNode.NONE.equals(nodeProperty) ){
			super.buildNodeStart(pNode, pParentNode, pLevel, pRow);
			return;
		}		
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		
		StringBuffer node = new StringBuffer();
		node.append("   var ${nodeScriptName}=new ${treeItem}(\"${name}\",").
		     append("\"${value}\",\"${action}\",${parent},\"${icon}\",\"${openIcon}\",${checked},${disabled}); ");
		node.append(ENTER);
		
		
		Context context = new DefaultContext(); 
		context.put("nodeScriptName", getNodeScriptName(webTreeNode));		
		if ( WebTreeNode.CHECKBOX.equals(nodeProperty)){
			context.put("treeItem", "WebFXCheckBoxTreeItem");	
		}else if ( WebTreeNode.RADIO.equals(nodeProperty)){
			context.put("treeItem", "WebFXRadioTreeItem");
		}		
		context.put("name", webTreeNode.getName());
		context.put("value", webTreeNode.getValue());
		context.put("action", webTreeNode.getAction());
		context.put("checked", new Boolean(webTreeNode.isSelected()) );
		context.put("disabled", new Boolean(webTreeNode.isDisabled()) );
		context.put("icon", webTreeNode.getIcon());
		context.put("openIcon", webTreeNode.getOpenIcon() );
		
		context.put("parent", parentNodeScriptName);
		
		treeScript.append(StrTemplateUtil.merge(node.toString(), context));
		
	}
	
	public void buildNodeStart(ITreeNode pNode, ITreeNode pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		if ( webTreeNode instanceof WebTreeDynamicNode == false ){
			buildNormalNodeStart(pNode, pParentNode, pLevel, pRow);
			return;
		}
		//build动态节点
		WebTreeDynamicNode dynamicNode = (WebTreeDynamicNode)webTreeNode;
		String nodeProperty = dynamicNode.getNodeProperty();
		StringBuffer nodeTemplate = new StringBuffer();
		Context context = new DefaultContext();		
		if ( WebTreeNode.NONE.equals(nodeProperty) ){
			nodeTemplate.append("   var ${nodeScriptName}=new ${treeItem}(\"${name}\",").
		     append("\"${subTreeURL}\", \"${action}\",${parent},\"${icon}\",\"${openIcon}\"); ");	
  		    nodeTemplate.append(ENTER);
		} else {
			nodeTemplate.append("   var ${nodeScriptName}=new ${treeItem}(\"${name}\",").
		     append("\"${value}\",\"${action}\",\"${subTreeURL}\",${parent},\"${icon}\",\"${openIcon}\",${checked},${disabled});"); 			
 		    nodeTemplate.append(ENTER);			
		}
		
				
		context.put("nodeScriptName", getNodeScriptName(webTreeNode));		
		if ( WebTreeNode.CHECKBOX.equals(nodeProperty)){
			context.put("treeItem", "WebFXLoadCheckBoxTreeItem");	
		}else if ( WebTreeNode.RADIO.equals(nodeProperty)){
			context.put("treeItem", "WebFXLoadRadioTreeItem");
		} else {
			context.put("treeItem", "WebFXLoadTreeItem");			
			
		}
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		context.put("name", webTreeNode.getName());
		context.put("value", webTreeNode.getValue());
		context.put("subTreeURL", dynamicNode.getSubTreeURL());
		context.put("checked", new Boolean(webTreeNode.isSelected()) );
		context.put("disabled", new Boolean(webTreeNode.isDisabled()) );
		context.put("icon", webTreeNode.getIcon());
		context.put("action", webTreeNode.getAction());
		context.put("openIcon", webTreeNode.getOpenIcon() );
		context.put("parent", parentNodeScriptName);
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));
		
	}		
	
}
