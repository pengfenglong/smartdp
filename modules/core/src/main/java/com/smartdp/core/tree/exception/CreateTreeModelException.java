package com.smartdp.core.tree.exception;

/**
 * 
 * @author pengfenglong
 *
 */
public class CreateTreeModelException extends TreeException{
	
	private static final long serialVersionUID = 1L;

	public CreateTreeModelException() {
		super("创建树异常");
	}

	public CreateTreeModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreateTreeModelException(String message) {
		super(message);
	}

	public CreateTreeModelException(Throwable cause) {
		super(cause);
	}

}
