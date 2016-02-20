package com.smartdp.tag.easyui;

/**
 * 菜单按钮标签
 * 
 * @author pengfenglong
 * 
 */
public class MenubuttonTag extends AbstractUITag {
	protected String plain;// 简洁模式
	protected String menu;// 菜单
	protected String duration;// 持续时间
	protected String options;// 返回属性对象。
	protected String disable;// 禁用菜单按钮。
	protected String enable;// 启用菜单按钮。

	public String getCss() {
		return "easyui-menubutton";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("plain", plain);
		parameters.put("menu", "'" + menu + "'");
		parameters.put("duration", "'" + duration + "'");
		parameters.put("options", options);
		parameters.put("disable", disable);
		parameters.put("enable", enable);
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
}