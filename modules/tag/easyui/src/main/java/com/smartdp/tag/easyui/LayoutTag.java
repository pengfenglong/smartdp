package com.smartdp.tag.easyui;

/**
 * 布局面板标签
 * 
 * @author pengfenglong
 * 
 */
public class LayoutTag extends AbstractUITag {
	protected String title;// 标题
	protected String width;// 宽度
	protected String height;// 高度
	protected String region;// 区域
	protected String border;// 边框
	protected String split;// 分隔条
	protected String iconCls;// 图标CSS类
	protected String href;// 超链接
	protected String resize;// 设置布局面板的尺寸大小。
	protected String panel;// 返回特定的布局面板，'region'参数的可取值为：'north','south','east','west','center'。
	protected String collapse;// 折叠特定的布局面板，'region'参数的可取值为：'north','south','east','west'。
	protected String expand;// 延伸特定的布局面板，'region'参数的可取值为：'north','south','east','west'。

	public String getCss() {
		return "";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("title", "'" + title + "'");
		parameters.put("width", "'" + width + "'");
		parameters.put("height", "'" + height + "'");
		parameters.put("region", "'" + region + "'");
		parameters.put("border", border);
		parameters.put("split", split);
		parameters.put("iconCls", "'" + iconCls + "'");
		parameters.put("href", "'" + href + "'");
		parameters.put("resize", resize);
		parameters.put("panel", panel);
		parameters.put("collapse", collapse);
		parameters.put("expand", expand);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getResize() {
		return resize;
	}

	public void setResize(String resize) {
		this.resize = resize;
	}

	public String getPanel() {
		return panel;
	}

	public void setPanel(String panel) {
		this.panel = panel;
	}

	public String getCollapse() {
		return collapse;
	}

	public void setCollapse(String collapse) {
		this.collapse = collapse;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
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