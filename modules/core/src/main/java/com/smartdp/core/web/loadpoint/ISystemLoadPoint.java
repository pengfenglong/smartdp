package com.smartdp.core.web.loadpoint;

/**
 * 加载点
 * @author peng
 *
 */
public interface ISystemLoadPoint {
	
	/**
	 * 启动时
	 */
	void startup();
	
	/**
	 * 销毁时
	 */
	void shutdown();

}
