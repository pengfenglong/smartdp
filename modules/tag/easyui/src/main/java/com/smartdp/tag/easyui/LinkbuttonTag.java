package com.smartdp.tag.easyui;

/**
 * 链接按钮标签
 * 
 * @author pengfenglong
 * 
 */
public class LinkbuttonTag extends AbstractUITag {
	protected String id;// ID
	protected String disabled;// 禁用
	protected String plain;// 简洁模式
	protected String text;// 文本
	protected String iconCls;// 图标CSS类
	protected String options;// 返回属性对象。
	protected String disable;// 禁用按钮。
	protected String enable;// 启用按钮。

	public String getCss() {
		return "easyui-linkbutton";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("id", "'" + id + "'");
		parameters.put("disabled", disabled);
		parameters.put("plain", plain);
		parameters.put("text", "'" + text + "'");
		parameters.put("iconCls", "'" + iconCls + "'");
		parameters.put("options", options);
		parameters.put("disable", disable);
		parameters.put("enable", enable);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
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