package com.smartdp.tag.easyui;

/**
 * 组合表格标签
 * 
 * @author pengfenglong
 * 
 */
public class CombogridTag extends AbstractUITag {
	protected String loadMsg;// 载入时信息
	protected String idField;// id字段
	protected String textField;// 文本域
	protected String mode;// 模式
	protected String filter;// 过滤器
	protected String options;// 返回属性对象。
	protected String grid;// 返回数据表格对象。
	protected String setValues;// 设置组合框的值，参数values是一个数组。
	protected String setValue;// 设置组合框的值
	protected String clear;// 清除组合框的值。
	protected String panelWidth = "200";
	protected String multiple;
	protected String url;
	protected String method = "get";
	protected String columns;
	protected String fitColumns = "true";
	protected String width = "150";

	public String getCss() {
		return "easyui-combogrid";
	}

	public String getTagType() {
		return "select";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("loadMsg", "'" + loadMsg + "'");
		parameters.put("idField", "'" + idField + "'");
		parameters.put("textField", "'" + textField + "'");
		parameters.put("mode", "'" + mode + "'");
		parameters.put("filter", "'" + filter + "'");
		parameters.put("options", options);
		parameters.put("grid", grid);
		parameters.put("setValues", setValues);
		parameters.put("setValue", setValue);
		parameters.put("clear", clear);
		parameters.put("panelWidth", panelWidth);
		parameters.put("multiple", multiple);
		parameters.put("url", "'" + getContextPath() + "/" + url + "'");
		parameters.put("method", "'" + method + "'");
		parameters.put("columns", columns);
		parameters.put("fitColumns", fitColumns);
		parameters.put("width", width);
	}

	public String getLoadMsg() {
		return loadMsg;
	}

	public void setLoadMsg(String loadMsg) {
		this.loadMsg = loadMsg;
	}

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
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

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public String getSetValues() {
		return setValues;
	}

	public void setSetValues(String setValues) {
		this.setValues = setValues;
	}

	public String getSetValue() {
		return setValue;
	}

	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}

	public String getClear() {
		return clear;
	}

	public void setClear(String clear) {
		this.clear = clear;
	}

	public String getPanelWidth() {
		return panelWidth;
	}

	public void setPanelWidth(String panelWidth) {
		this.panelWidth = panelWidth;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
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

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getFitColumns() {
		return fitColumns;
	}

	public void setFitColumns(String fitColumns) {
		this.fitColumns = fitColumns;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

}