package com.smartdp.core.plugin;

/**
 * 插件接口
 * @author pengfenglong
 *
 */
public interface Plugin {
	public void run(Object... obj);

    public Boolean install();

    public Boolean uninstall();

    public Boolean upgrade();
}
