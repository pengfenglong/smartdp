package com.smartdp.core.tree.support;

import java.util.Iterator;

import javax.swing.tree.TreeNode;

import com.smartdp.core.utils.JsonUtil;
import com.smartdp.core.tree.ITreeConverter;
import com.smartdp.core.tree.ITreeNode;
import com.smartdp.core.tree.ITreeModel;

public class JsTreeCoverter extends DefaultTreeCoverter {

	
	/**
	 * 重写
	 * @param sb
	 * @param node
	 */
	protected void rebuildJsonString(StringBuffer sb,ITreeNode node) {
		
		if (node.isLeaf()) {
			String temp=JsonUtil.bean2json(node.getUserData());
			temp=temp.substring(1, temp.length()-1);
			sb.append("{");			
			sb.append("\"data\":\"");
			sb.append(node.getName());
			sb.append("\",");
			sb.append("\"attr\":{");
			sb.append(temp);
			sb.append(",\"leaf\":\"true\"");			
			sb.append("}");			
			sb.append("}");
		} else {
			Iterator<ITreeNode> children = node.getChildren();
			
			String temp=JsonUtil.bean2json(node.getUserData());
			temp=temp.substring(1, temp.length()-1);
			sb.append("{");
			sb.append("\"data\":\"");
			sb.append(node.getName());
			sb.append("\",");
			sb.append("\"attr\":{");			
			sb.append(temp);
			sb.append("}");
			sb.append(",\"children\":[");
			while (children.hasNext()) {
				ITreeNode child = children.next();								
				rebuildJsonString(sb,child);
				
				sb.append(",");
			}
			
			sb.append("]}");
			sb.delete(sb.length()-3, sb.length()-2);			
		}
	}  



}
