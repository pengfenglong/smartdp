package com.smartdp.core.tree.exception;

/**
 * 
 * @author pengfenglong
 *
 */
public class BuildTreeException extends TreeException{
	
	private static final long serialVersionUID = 1L;
 
	public BuildTreeException() {
		super("构造树异常");
	}

	public BuildTreeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuildTreeException(String message) {
		super(message);
	}

	public BuildTreeException(Throwable cause) {
		super(cause);
	}
	
	
}
