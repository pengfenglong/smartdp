package com.smartdp.tag.easyui;

/**
 * 可伸缩面板标签
 * 
 * @author pengfenglong
 * 
 */
public class AccordionTag extends AbstractUITag {
	protected String width;// 宽度
	protected String height;// 高度
	protected String fit;// 铺满浏览器
	protected String border;// 边框
	protected String animate;// 动画效果

	public String getCss() {
		return "easyui-accordion";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("width", "'" + width + "'");
		parameters.put("height", "'" + height + "'");
		parameters.put("fit", fit);
		parameters.put("border", border);
		parameters.put("animate", animate);
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

	public String getFit() {
		return fit;
	}

	public void setFit(String fit) {
		this.fit = fit;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getAnimate() {
		return animate;
	}

	public void setAnimate(String animate) {
		this.animate = animate;
	}
}