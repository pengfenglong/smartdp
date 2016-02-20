package com.smartdp.tag.easyui;

/**
 * 日期组合框标签
 * 
 * @author pengfenglong
 * 
 */
public class DateboxTag extends AbstractUITag {
	protected String panelWidth;// 面板宽度
	protected String panelHeight;// 面板高度
	protected String currentText;// 当前文本
	protected String closeText;// 关闭
	protected String okText;// OK按钮文本
	protected String disabled;// 禁用
	protected String formatter;// 格式
	protected String parser;// 解析器
	protected String onSelect;// 当用户选择日期时触发。

	public String getCss() {
		return "easyui-datebox";
	}
	
	public String getTagType() {
		return "input";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("panelWidth", "'" + panelWidth + "'");
		parameters.put("panelHeight", "'" + panelHeight + "'");
		parameters.put("currentText", "'" + currentText + "'");
		parameters.put("closeText", "'" + closeText + "'");
		parameters.put("okText", "'" + okText + "'");
		parameters.put("disabled", disabled);
		parameters.put("formatter", "'" + formatter + "'");
		parameters.put("parser", "'" + parser + "'");
		parameters.put("onSelect", onSelect);
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

	public String getCurrentText() {
		return currentText;
	}

	public void setCurrentText(String currentText) {
		this.currentText = currentText;
	}

	public String getCloseText() {
		return closeText;
	}

	public void setCloseText(String closeText) {
		this.closeText = closeText;
	}

	public String getOkText() {
		return okText;
	}

	public void setOkText(String okText) {
		this.okText = okText;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getParser() {
		return parser;
	}

	public void setParser(String parser) {
		this.parser = parser;
	}

	public String getOnSelect() {
		return onSelect;
	}

	public void setOnSelect(String onSelect) {
		this.onSelect = onSelect;
	}
}