package com.smartdp.tag.easyui;

/**
 * 选项卡标签
 * 
 * @author pengfenglong
 * 
 */
public class TabsTag extends AbstractUITag {
	protected String width;// 宽度
	protected String height;// 高度
	protected String plain;// 简洁模式
	protected String fit;// 铺满浏览器
	protected String border;// 边框
	protected String scrollIncrement;// 滚动增量
	protected String scrollDuration;// 滚动时间
	protected String tools;// 工具栏
	protected String onLoad;// 当一个选项卡完成ajax远程载入对象时触发。
	protected String onSelect;// 当用户选择一个选项卡时触发。
	protected String onBeforeClose;// 在一个选项卡被关闭之前触发，返回false将取消关闭。
	protected String onClose;// 在用户关闭一个选项卡面板后触发。
	protected String onAdd;// 在一个选项卡面板被添加后触发。
	protected String onUpdate;// 在一个选项卡面板被更新后触发。
	protected String onContextMenu;// 在一个选项卡面板被鼠标右键单击后触发。

	public String getCss() {
		return "easyui-tabs";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("width", "'" + width + "'");
		parameters.put("height", "'" + height + "'");
		parameters.put("plain", plain);
		parameters.put("fit", fit);
		parameters.put("border", border);
		parameters.put("scrollIncrement", "'" + scrollIncrement + "'");
		parameters.put("scrollDuration", "'" + scrollDuration + "'");
		parameters.put("tools", tools);
		parameters.put("onLoad", onLoad);
		parameters.put("onSelect", onSelect);
		parameters.put("onBeforeClose", onBeforeClose);
		parameters.put("onClose", onClose);
		parameters.put("onAdd", onAdd);
		parameters.put("onUpdate", onUpdate);
		parameters.put("onContextMenu", onContextMenu);
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

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
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

	public String getScrollIncrement() {
		return scrollIncrement;
	}

	public void setScrollIncrement(String scrollIncrement) {
		this.scrollIncrement = scrollIncrement;
	}

	public String getScrollDuration() {
		return scrollDuration;
	}

	public void setScrollDuration(String scrollDuration) {
		this.scrollDuration = scrollDuration;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getOnLoad() {
		return onLoad;
	}

	public void setOnLoad(String onLoad) {
		this.onLoad = onLoad;
	}

	public String getOnSelect() {
		return onSelect;
	}

	public void setOnSelect(String onSelect) {
		this.onSelect = onSelect;
	}

	public String getOnBeforeClose() {
		return onBeforeClose;
	}

	public void setOnBeforeClose(String onBeforeClose) {
		this.onBeforeClose = onBeforeClose;
	}

	public String getOnClose() {
		return onClose;
	}

	public void setOnClose(String onClose) {
		this.onClose = onClose;
	}

	public String getOnAdd() {
		return onAdd;
	}

	public void setOnAdd(String onAdd) {
		this.onAdd = onAdd;
	}

	public String getOnUpdate() {
		return onUpdate;
	}

	public void setOnUpdate(String onUpdate) {
		this.onUpdate = onUpdate;
	}

	public String getOnContextMenu() {
		return onContextMenu;
	}

	public void setOnContextMenu(String onContextMenu) {
		this.onContextMenu = onContextMenu;
	}
}