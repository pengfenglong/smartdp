package com.smartdp.core.tree;

import java.util.Collection;

import com.smartdp.core.tree.exception.CreateTreeModelException;


/**
 * 将业务数据构造成ITreeModel
 * @author pengfenglong
 *
 */
public interface ITreeModelCreator {

	/**
	 * 创建树模型
	 * @param pUserDatas 业务数据,至少要存在一个跟节点（不存在父亲节点的节点)
	 *                   要求集合元素读必须实现IUncodable接口                     
	 * @return 返回根节点.
	 * @throws CreateTreeModelException 如果集合元素没有实现IUncodable接口，会抛出
	 *                                  ClassCastException异常
	 */
   public ITreeModel create(Collection pUserDatas) throws CreateTreeModelException;
	
	/**
	 * 创建树模型 
	 * @param pUserDatas 业务数据,至少要存在一个跟节点（不存在父亲节点的节点)
	 * @param pUncoder   解码器，对每个业务数据进行解码，返回主键对象和父亲主键对象.
	 * @return 返回根节点.
	 * @throws CreateTreeModelException
	 */
  public ITreeModel create(Collection pUserDatas, IUserDataUncoder pUncoder) throws CreateTreeModelException;
}
