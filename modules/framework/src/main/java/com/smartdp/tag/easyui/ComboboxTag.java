package com.smartdp.tag.easyui;

/**
 * 可装载组合框标签
 * 
 * @author pengfenglong
 * 
 */
public class ComboboxTag extends AbstractUITag {
	protected String valueField;// 值域
	protected String textField;// 文本域
	protected String mode;// 模式
	protected String url;// 超链接地址
	protected String method;// 方法
	protected String data;// 数据
	protected String filter;// 过滤器
	protected String formatter;// 格式
	protected String onLoadSuccess;// 当远程数据载入成功时触发。
	protected String onLoadError;// 当远程数据载入错误时触发。
	protected String onSelect;// 当用户选择一个列表项时触发。
	protected String onUnselect;// 当用户取消选择一个列表项时触发。

	public String getCss() {
		return "easyui-combobox";
	}
	
	public String getTagType() {
		return "input";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("valueField", "'" + valueField + "'");
		parameters.put("textField", "'" + textField + "'");
		parameters.put("mode", "'" + mode + "'");
		parameters.put("url", "'" +getContextPath() + "/" + url + "'");
		parameters.put("method", "'" + method + "'");
		parameters.put("data", data);
		parameters.put("filter", "'" + filter + "'");
		parameters.put("formatter", "'" + formatter + "'");
		parameters.put("onLoadSuccess", onLoadSuccess);
		parameters.put("onLoadError", onLoadError);
		parameters.put("onSelect", onSelect);
		parameters.put("onUnselect", onUnselect);
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getTextField() {
		return textField;
	}

	public void setTextField(String textField) {
		this.textField = textField;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getOnLoadSuccess() {
		return onLoadSuccess;
	}

	public void setOnLoadSuccess(String onLoadSuccess) {
		this.onLoadSuccess = onLoadSuccess;
	}

	public String getOnLoadError() {
		return onLoadError;
	}

	public void setOnLoadError(String onLoadError) {
		this.onLoadError = onLoadError;
	}

	public String getOnSelect() {
		return onSelect;
	}

	public void setOnSelect(String onSelect) {
		this.onSelect = onSelect;
	}

	public String getOnUnselect() {
		return onUnselect;
	}

	public void setOnUnselect(String onUnselect) {
		this.onUnselect = onUnselect;
	}
}