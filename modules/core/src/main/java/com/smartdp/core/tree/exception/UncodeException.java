package com.smartdp.core.tree.exception;

/**
 * 
 * @author pengfenglong
 *
 */
public class UncodeException extends CreateTreeModelException{

	private static final long serialVersionUID = 1L;

	public UncodeException() {
		super("给节点解码异常");
	} 

	public UncodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UncodeException(String message) {
		super(message);
	}

	public UncodeException(Throwable cause) {
		super(cause);
	}
}
