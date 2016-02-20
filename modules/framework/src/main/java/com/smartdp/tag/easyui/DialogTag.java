package com.smartdp.tag.easyui;

/**
 * 对话窗口标签
 * 
 * @author pengfenglong
 * 
 */
public class DialogTag extends WindowTag {
	protected String title;// 标题
	protected String collapsible;// 可折叠
	protected String minimizable;// 最小化
	protected String maximizable;// 最大化
	protected String resizable;// 可缩放
	protected String toolbar;// 工具栏
	protected String buttons;// 按钮

	public String getCss() {
		return "easyui-dialog";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("title", "'" + title + "'");
		parameters.put("collapsible", collapsible);
		parameters.put("minimizable", minimizable);
		parameters.put("maximizable", maximizable);
		parameters.put("resizable", resizable);
		parameters.put("toolbar", toolbar);
		parameters.put("buttons", buttons);
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

	public String getResizable() {
		return resizable;
	}

	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

	public String getToolbar() {
		return toolbar;
	}

	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

	public String getButtons() {
		return buttons;
	}

	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

}