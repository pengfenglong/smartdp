package com.smartdp.tag.easyui;

/**
 * 布局面板标签
 * 
 * @author pengfenglong
 * 
 */
public class LayoutContainerTag extends AbstractUITag {

	protected String type = "body";// 类型(body.div)
	protected String width;// 类型(body.div)
	protected String height;// 类型(body.div)

	public String getCss() {
		return "easyui-layout";
	}

	public String getTagType() {
		return type;
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("width", "'" + width + "'");
		parameters.put("height", "'" + height + "'");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}