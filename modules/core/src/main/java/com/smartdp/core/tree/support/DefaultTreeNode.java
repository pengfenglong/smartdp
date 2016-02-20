package com.smartdp.core.tree.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.smartdp.core.tree.ITreeNode;

/**
 * 
 * @author pengfenglong
 *
 */
public class DefaultTreeNode implements ITreeNode, Serializable{
	private static final long serialVersionUID = 1L;
	private ITreeNode parent;      //父亲节点，跟节点的父亲为null
	private Object userData;  //用户数据
	private List children = new ArrayList();
	private String name;

	
	public DefaultTreeNode(){
		name = "no name";
	}
	
	public DefaultTreeNode(String pName){
		if ( pName == null ){
			throw new NullPointerException("节点名称不能为空null");
		}
		name = pName;
	}
	
	public DefaultTreeNode(String pName, Object pUserData){
		this(pName);
		this.userData = pUserData;
	}
	
	public String toString(){
		if ( name == null ){
			return super.toString();	
		} else {
			return name.toString();
		}		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void detachNode(ITreeNode pNode){
		if ( pNode == null ){
			return;
		}
		//不包含pNode
		if ( children.contains(pNode) == false ){
			return;
		}
		children.remove(pNode);		
		pNode.setParent(null);

	}
	
	public Object getUserData() {
		return userData;
	}
	public void setUserData(Object userData) {
		this.userData = userData;
	}
	public ITreeNode getParent() {
		return parent;
	}
	
	public boolean isRoot(){
		return this.parent == null ? true : false;
	}
	public void setParent(ITreeNode pParentNode) {
		if ( pParentNode == this.parent ){
			return;
		}
		if ( this.parent != null ){//解除跟以前父亲菜单的关系.
			this.parent.detachNode(this);
		}
		if (pParentNode != null) {
			int index = pParentNode.getIndex(this);
			if (index == -1) {
				pParentNode.addNode(this);
			}
		}
		this.parent = pParentNode;
	}
	public void addNode(ITreeNode pNode) {
		if ( pNode == null ){
			return;
		}
		if (children.contains(pNode) == false) {
			children.add(pNode);
			pNode.setParent(this);
		}
	}
	
	public boolean isHaveChildren(){
		return !this.children.isEmpty();
	}


	public Iterator getChildren() {
		return this.children.iterator();
	}

	public boolean isLeaf() {
		return this.children.size() == 0 ? true : false;
	}

	public int getChildCount() {
		return this.children.size();
	}

	public ITreeNode getChildAt(int pChildIndex) {
		return (ITreeNode)this.children.get(pChildIndex);
	}

	public int getIndex(ITreeNode pChildIndex) {
		return this.children.indexOf(pChildIndex);
	}
}
