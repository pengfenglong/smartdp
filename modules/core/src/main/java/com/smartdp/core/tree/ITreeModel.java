package com.smartdp.core.tree;

import java.util.Iterator;


/**
 * 树，森林
 * @author pengfenglong
 *
 */
public interface ITreeModel {
	
	/**
	 * 获取跟节点,可以是多个跟节点.
	 * @return
	 */
	public Iterator getRootNodes();
}
