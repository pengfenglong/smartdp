package com.smartdp.core.tree.exception;

/**
 * 
 * @author pengfenglong
 *
 */
public class TreeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TreeException() {
		super("构造树出错!");
	}

	public TreeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TreeException(String message) {
		super(message);
	}

	public TreeException(Throwable cause) {
		super(cause);
	}
}
