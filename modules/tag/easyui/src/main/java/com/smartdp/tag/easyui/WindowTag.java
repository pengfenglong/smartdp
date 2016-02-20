package com.smartdp.tag.easyui;

/**
 * 窗口标签
 * 
 * @author pengfenglong
 * 
 */
public class WindowTag extends PanelTag {
	protected String title;// 标题
	protected String collapsible;// 可折叠
	protected String minimizable;// 最小化
	protected String maximizable;// 最大化
	protected String closable;// 关闭
	protected String closed;// 已关闭
	protected String zIndex;// 堆叠顺序
	protected String draggable;// 可拖放
	protected String resizable;// 可缩放
	protected String shadow;// 阴影
	protected String inline;// 行内显示
	protected String modal;// 遮罩
	protected String window;// 返回窗口对象。

	public String getCss() {
		return "easyui-window";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("title", "'" + title + "'");
		parameters.put("collapsible", collapsible);
		parameters.put("minimizable", minimizable);
		parameters.put("maximizable", maximizable);
		parameters.put("closable", closable);
		parameters.put("closed", closed);
		parameters.put("zIndex", "'" + zIndex + "'");
		parameters.put("draggable", draggable);
		parameters.put("resizable", resizable);
		parameters.put("shadow", shadow);
		parameters.put("inline", inline);
		parameters.put("modal", modal);
		parameters.put("window", window);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCollapsible() {
		return collapsible;
	}

	public void setCollapsible(String collapsible) {
		this.collapsible = collapsible;
	}

	public String getMinimizable() {
		return minimizable;
	}

	public void setMinimizable(String minimizable) {
		this.minimizable = minimizable;
	}

	public String getMaximizable() {
		return maximizable;
	}

	public void setMaximizable(String maximizable) {
		this.maximizable = maximizable;
	}

	public String getClosable() {
		return closable;
	}

	public void setClosable(String closable) {
		this.closable = closable;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public String getZIndex() {
		return zIndex;
	}

	public void setZIndex(String zIndex) {
		this.zIndex = zIndex;
	}

	public String getDraggable() {
		return draggable;
	}

	public void setDraggable(String draggable) {
		this.draggable = draggable;
	}

	public String getResizable() {
		return resizable;
	}

	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

	public String getShadow() {
		return shadow;
	}

	public void setShadow(String shadow) {
		this.shadow = shadow;
	}

	public String getInline() {
		return inline;
	}

	public void setInline(String inline) {
		this.inline = inline;
	}

	public String getModal() {
		return modal;
	}

	public void setModal(String modal) {
		this.modal = modal;
	}

	public String getWindow() {
		return window;
	}

	public void setWindow(String window) {
		this.window = window;
	}
}