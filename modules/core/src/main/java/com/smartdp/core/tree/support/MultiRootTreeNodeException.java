package com.smartdp.core.tree.support;

import com.smartdp.core.tree.exception.CreateTreeModelException;


/**
 * 
 * @author pengfenglong
 *
 */
public class MultiRootTreeNodeException extends CreateTreeModelException{
 
	private static final long serialVersionUID = 1L;

	public MultiRootTreeNodeException() {
		super("存在多个跟节点");
	}

	public MultiRootTreeNodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MultiRootTreeNodeException(String message) {
		super(message);
	}

	public MultiRootTreeNodeException(Throwable cause) {
		super(cause);
	}


}
