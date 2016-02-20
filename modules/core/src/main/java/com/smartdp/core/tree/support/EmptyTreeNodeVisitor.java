package com.smartdp.core.tree.support;

import com.smartdp.core.tree.ITreeNode;
import com.smartdp.core.tree.ITreeNodeVisitor;


/**
 * 
 * @author pengfenglong
 *
 */
public class EmptyTreeNodeVisitor implements ITreeNodeVisitor{

	public boolean visit(ITreeNode pNode) {
		return true;
	}

}
