package com.smartdp.core.tree.support;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartdp.core.tree.ITreeNode;
import com.smartdp.core.tree.ITreeModel;
import com.smartdp.core.tree.ITreeModelCreator;
import com.smartdp.core.tree.IUncodable;
import com.smartdp.core.tree.IUserDataUncoder;
import com.smartdp.core.tree.exception.CreateTreeModelException;
import com.smartdp.core.tree.exception.UncodeException;

/**
 * 
 * @author pengfenglong
 *
 */
public abstract class AbstractTreeModelCreator implements ITreeModelCreator {

	private boolean allowMutiRoot = true;
	private final Log log = LogFactory.getLog(getClass());

	public boolean isAllowMutiRoot() {
		return allowMutiRoot;
	} 

	public void setAllowMutiRoot(boolean allowMutiRoot) {
		this.allowMutiRoot = allowMutiRoot;
	}
	
	   public ITreeModel create(Collection pUserDatas) throws CreateTreeModelException{
		   return create(pUserDatas, new IUserDataUncoder(){
			public Object getID(Object pUserData) throws UncodeException {
				IUncodable uncode = (IUncodable)pUserData;
				return uncode.getID();
			}
			public Object getParentID(Object pUserData) throws UncodeException {
				IUncodable uncode = (IUncodable)pUserData;
				return uncode.getParentID();
			}
			   
		   });
	   }
	public ITreeModel create(Collection pUserDatas, IUserDataUncoder pUncoder)
			throws CreateTreeModelException {
		if (pUserDatas == null) {
			return new DefaultTreeModel();
		}
		if ( pUserDatas.isEmpty() ){
			return new DefaultTreeModel();
		}
		if (pUncoder == null) {
			throw new CreateTreeModelException("节点解码器不能为空null");
		}
		DefaultTreeModel result = new DefaultTreeModel();
		Map nodes = new LinkedHashMap();
		Iterator userDatasIterator = pUserDatas.iterator();
		while (userDatasIterator.hasNext()) {
			Object userData = userDatasIterator.next();
			Object id = null;
			try {
				id = pUncoder.getID(userData);
			} catch (UncodeException ex) {
				throw new CreateTreeModelException(ex.getMessage(), ex);
			}
			if ( id == null ){
				throw new CreateTreeModelException("获取用户ID失败，用户对象:" + userData);
			}
			ITreeNode node = null;
			try {
				node = createNode(userData, pUncoder);
			} catch (Exception ex) {
				throw new CreateTreeModelException(ex.getMessage(), ex);
			}
			if ( node == null ){
				log.warn("创建节点失败，用户对象:" + userData);
				continue;
			}
			node.setUserData(userData);
			nodes.put(id, node);//将节点cache起来
		}

		Iterator nodeIterator = nodes.values().iterator();
		while (nodeIterator.hasNext()) {
			ITreeNode node = (ITreeNode) nodeIterator.next();
			Object userData = node.getUserData();
			Object parentId = null;
			try {
				parentId = pUncoder.getParentID(userData);
			} catch (UncodeException ex) {
				throw new CreateTreeModelException(ex.getMessage(), ex);
			}
			ITreeNode parentNode = (ITreeNode) nodes.get(parentId);
			if (parentNode == null) {//跟节点
				result.addRootNode(node);
				continue;
			}
			node.setParent(parentNode);
		}
		
		if (result.getRootNodeCount() == 0) {
			throw new CreateTreeModelException("不存在跟节点");
		}
		
		if ( allowMutiRoot == false ){
			if ( result.getRootNodeCount() > 1 ){
				throw new MultiRootTreeNodeException();
			}
		}
		 return result;
	}
 
	protected abstract ITreeNode createNode(Object pUserData, IUserDataUncoder pUncoder);


}
