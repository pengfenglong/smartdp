package com.smartdp.core.tree.support;

import com.smartdp.core.tree.ITreeNode;

/**
 * 采用节点文本进行排序
 * @author pengfenglong
 *
 */
public class DefaultNodeComparator extends AbstractTreeNodeComparator {

	protected Comparable getComparableProperty(ITreeNode pNode) {
		if ( pNode instanceof DefaultTreeNode == false){
			throw new IllegalArgumentException("node type is error, should be WebNode!");
		}
		DefaultTreeNode defaultNode = (DefaultTreeNode)pNode;
		return defaultNode.getName();
	}

}
