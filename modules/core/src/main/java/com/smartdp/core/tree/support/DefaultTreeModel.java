package com.smartdp.core.tree.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.smartdp.core.tree.ITreeNode;
import com.smartdp.core.tree.ITreeModel;

/**
 * 
 * @author pengfenglong
 *
 */
public class DefaultTreeModel implements ITreeModel{
    
    /**
     * 用户存储树的跟节点
     */
    private List rootNodes = new ArrayList();
    
	public Iterator getRootNodes() {
		return rootNodes.iterator();
	}
	
	public void addRootNode(ITreeNode pRootNode){
		rootNodes.add(pRootNode);
	}

	public int getRootNodeCount(){
		return this.rootNodes.size();
	}
	public ITreeNode getRootNodeAt(int pRootIndex){
	  return (ITreeNode)rootNodes.get(pRootIndex);	
	}
	 public int getRootNodeIndex(ITreeNode node){
		return this.rootNodes.indexOf(node); 
	 }
	public boolean removeRoot(ITreeNode pRootNode){
		return rootNodes.remove(pRootNode);
	}
	
}
