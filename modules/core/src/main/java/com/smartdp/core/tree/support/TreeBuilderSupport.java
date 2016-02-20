package com.smartdp.core.tree.support;

import com.smartdp.core.tree.ITreeNode;
import com.smartdp.core.tree.ITreeBuilder;
import com.smartdp.core.tree.exception.BuildTreeException;

/**
 * 
 * @author pengfenglong
 *
 */
public abstract class TreeBuilderSupport implements ITreeBuilder{

	//换行符
	protected static final String ENTER = "\r\n";
	

	public void buildNodeEnd(ITreeNode pNode, ITreeNode pParentNode, int pLevel, int pRow) throws BuildTreeException {
	}

	public void buildRootNodeEnd(ITreeNode pRootNode, int pLevel, int pRow) throws BuildTreeException {
	}

	public void buildTreeEnd() throws BuildTreeException {
	}
	

	public void buildTreeStart() throws BuildTreeException {
	
	}

}
