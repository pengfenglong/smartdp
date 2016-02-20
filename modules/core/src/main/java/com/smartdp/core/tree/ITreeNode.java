package com.smartdp.core.tree;

import java.util.Iterator;

/**
 * 树节点
 * @author pengfenglong
 *
 */
public interface ITreeNode  {

	public ITreeNode getParent();
	public void setParent(ITreeNode pParent);
	
	public void setUserData(Object pUserData);
	public Object getUserData();
	public Iterator getChildren();
	
	
	public void detachNode(ITreeNode pNode);
	public void addNode(ITreeNode pNode);
	
	
	 public boolean isLeaf();
	 public boolean isRoot();
	 public int getChildCount();
	 public ITreeNode getChildAt(int pChildIndex);
	 public int getIndex(ITreeNode node);
	 public String getName();

	
}
