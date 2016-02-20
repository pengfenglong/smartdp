package com.smartdp.tag.easyui;

/**
 * 组合树标签
 * 
 * @author pengfenglong
 * 
 */
public class CombotreeTag extends AbstractUITag {
	protected String editable;// 可编辑
	protected String options;// 返回属性对象。
	protected String tree;// 返回树形菜单对象。
	protected String loadData;// 载入本地树形菜单数据。
	protected String reload;// 再次向远程树形菜单数据发起请求。
	protected String clear;// 清除组件值。
	protected String setValues;// 设置组合框的值，参数values是一个数组。
	protected String setValue;// 设置组合框的值。

	public String getCss() {
		return "easyui-combotree";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("editable", editable);
		parameters.put("options", options);
		parameters.put("tree", tree);
		parameters.put("loadData", loadData);
		parameters.put("reload", reload);
		parameters.put("clear", clear);
		parameters.put("setValues", setValues);
		parameters.put("setValue", setValue);
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public String getLoadData() {
		return loadData;
	}

	public void setLoadData(String loadData) {
		this.loadData = loadData;
	}

	public String getReload() {
		return reload;
	}

	public void setReload(String reload) {
		this.reload = reload;
	}

	public String getClear() {
		return clear;
	}

	public void setClear(String clear) {
		this.clear = clear;
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
}