package com.smartdp.core.tree.support;

import java.util.Iterator;

import javax.swing.tree.TreeNode;

import com.smartdp.core.utils.JsonUtil;
import com.smartdp.core.tree.ITreeConverter;
import com.smartdp.core.tree.ITreeNode;
import com.smartdp.core.tree.ITreeModel;

public class DefaultTreeCoverter implements ITreeConverter {

	public String tree2Html(ITreeModel treeModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public String tree2Json(ITreeModel treeModel) {
		
		Iterator<TreeNode> nodes = treeModel.getRootNodes();
		StringBuffer sb=new StringBuffer();			
		sb.append("[");	
		while(nodes.hasNext()){
			rebuildJsonString(sb,(ITreeNode)nodes.next());
			sb.append(",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		
		
//		String temp=sb.toString();		
//		int x=temp.indexOf("children");		
//		temp=temp.substring(x+10);
//		temp=temp.substring(0,temp.length()-2);
//		return temp;
		return sb.toString();
	}
	
	
	/**
	 * 递归函数，重组字符串，注释部分为根据不同的前台数据进行个性处理。
	 * @param sb
	 * @param node
	 */
	protected void rebuildJsonString(StringBuffer sb,ITreeNode node) {
		
		if (node.isLeaf()) {
			String temp=JsonUtil.bean2json(node.getUserData());
			temp=temp.substring(1, temp.length()-1);
			sb.append("{");			
			sb.append(temp);
			sb.append(",\"leaf\":\"true\"");
			//sb.append(",/"parent/":/"").append(((TreeNode)node.getParent()).getId()).append("/"");			
			sb.append("}");
		} else {
			Iterator<ITreeNode> children = node.getChildren();
			
			String temp=JsonUtil.bean2json(node.getUserData());
			temp=temp.substring(1, temp.length()-1);
			sb.append("{");			
			sb.append(temp);			
//			if(node.getParent()!=null) {
//				sb.append(",/"parent/":/"").append(((TreeNode)node.getParent()).getId()).append("/"");
//			}
			
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
	


	public String tree2XML(ITreeModel treeModel) {
		// TODO Auto-generated method stub
		return null;
	}



}
