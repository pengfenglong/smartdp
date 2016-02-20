package com.smartdp.core.exception;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 * @author calvin
 */
public class ServiceAccessException extends BaseException {

	private static final long serialVersionUID = 3583566093089790852L;

	public ServiceAccessException() {
		super();
	}

	public ServiceAccessException(String message) {
		super(message);
	}

	public ServiceAccessException(Throwable cause) {
		super(cause);
	}

	public ServiceAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
