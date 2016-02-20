package com.smartdp.tag.easyui;

/**
 * 进度条标签
 * 
 * @author pengfenglong
 * 
 */
public class ProgressbarTag extends AbstractUITag {
	protected String width;// 宽度
	protected String value;// 值
	protected String text;// 文本
	protected String onChange;// 当进度发生改变时触发。

	public String getCss() {
		return "easyui-progressbar";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("width", "'" + width + "'");
		parameters.put("value", "'" + value + "'");
		parameters.put("text", "'" + text + "'");
		parameters.put("onChange", onChange);
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}
}