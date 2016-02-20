package com.smartdp.tag.easyui;

/**
 * 自定义组合框标签
 * 
 * @author pengfenglong
 * 
 */
public class ComboTag extends AbstractUITag {
	protected String width;// 宽度
	protected String panelWidth;// 下拉框宽度
	protected String panelHeight;// 下拉框高度
	protected String multiple;// 可多选
	protected String separator;// 分隔条
	protected String editable;// 可编辑
	protected String disabled;// 禁用
	protected String hasDownArrow;// 下拉图标
	protected String value;// 值
	protected String delay;// 延迟
	protected String keyHandler;// 按键助手
	protected String onShowPanel;// 当显示下拉面板的时候触发。
	protected String onHidePanel;// 当隐藏下拉面板的时候触发。
	protected String onChange;// 当组合框的值发生改变时触发。

	public String getCss() {
		return "easyui-combo";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("width", "'" + width + "'");
		parameters.put("panelWidth", "'" + panelWidth + "'");
		parameters.put("panelHeight", "'" + panelHeight + "'");
		parameters.put("multiple", multiple);
		parameters.put("separator", "'" + separator + "'");
		parameters.put("editable", editable);
		parameters.put("disabled", disabled);
		parameters.put("hasDownArrow", hasDownArrow);
		parameters.put("value", "'" + value + "'");
		parameters.put("delay", "'" + delay + "'");
		parameters.put("keyHandler", keyHandler);
		parameters.put("onShowPanel", onShowPanel);
		parameters.put("onHidePanel", onHidePanel);
		parameters.put("onChange", onChange);
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getPanelWidth() {
		return panelWidth;
	}

	public void setPanelWidth(String panelWidth) {
		this.panelWidth = panelWidth;
	}

	public String getPanelHeight() {
		return panelHeight;
	}

	public void setPanelHeight(String panelHeight) {
		this.panelHeight = panelHeight;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getHasDownArrow() {
		return hasDownArrow;
	}

	public void setHasDownArrow(String hasDownArrow) {
		this.hasDownArrow = hasDownArrow;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	public String getKeyHandler() {
		return keyHandler;
	}

	public void setKeyHandler(String keyHandler) {
		this.keyHandler = keyHandler;
	}

	public String getOnShowPanel() {
		return onShowPanel;
	}

	public void setOnShowPanel(String onShowPanel) {
		this.onShowPanel = onShowPanel;
	}

	public String getOnHidePanel() {
		return onHidePanel;
	}

	public void setOnHidePanel(String onHidePanel) {
		this.onHidePanel = onHidePanel;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}
}