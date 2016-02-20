package com.smartdp.tag.easyui;

/**
 * 简单载入器标签
 * 
 * @author pengfenglong
 * 
 */
public class EasyloaderTag extends AbstractUITag {
	protected String modules;// 模块
	protected String locales;// 多语言
	protected String base;// 很目录
	protected String theme;// 主题名称
	protected String css;// 层叠样式表
	protected String locale;// 自定义语言
	protected String timeout;// 超时
	protected String onProgress;// 当一个模块成功载入时触发。
	protected String onLoad;// 当一个模块及其所有依赖关系（可以理解为载入该模块所需要的其他模块、属性、方法等）载入时触发。

	public void populateParams() {
		super.populateParams();
		parameters.put("modules", modules);
		parameters.put("locales", locales);
		parameters.put("base", "'" + base + "'");
		parameters.put("theme", "'" + theme + "'");
		parameters.put("css", css);
		parameters.put("locale", "'" + locale + "'");
		parameters.put("timeout", "'" + timeout + "'");
		parameters.put("onProgress", onProgress);
		parameters.put("onLoad", onLoad);
	}

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public String getLocales() {
		return locales;
	}

	public void setLocales(String locales) {
		this.locales = locales;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getOnProgress() {
		return onProgress;
	}

	public void setOnProgress(String onProgress) {
		this.onProgress = onProgress;
	}

	public String getOnLoad() {
		return onLoad;
	}

	public void setOnLoad(String onLoad) {
		this.onLoad = onLoad;
	}
}