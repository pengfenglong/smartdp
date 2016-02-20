package com.smartdp.core.tree;

/**
 * 树转换接口，可以将树模型转换成各种需要的数据类型，比如json、xml
 * @author pengfenglong
 *
 */
public interface ITreeConverter {
	
	/**
	 * 树转换成xml
	 * @param treeModel
	 * @return
	 */
	public String tree2XML(ITreeModel treeModel);
	
	/**
	 * 树转换成json
	 * @param treeModel
	 * @return
	 */
	public String tree2Json(ITreeModel treeModel);
	
	/**
	 * 树转换成html
	 * @param treeModel
	 * @return
	 */
	public String tree2Html(ITreeModel treeModel);
}
