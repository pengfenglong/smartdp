package com.smartdp.tag.easyui;

/**
 * 树形表格标签
 * 
 * @author pengfenglong
 * 
 */
public class TreegridTag extends AbstractUITag {
	protected String treeField;// 树形字段
	protected String animate;// 动画
	protected String onClickRow;// 当用户点击节点时触发 。
	protected String onDblClickRow;// 当用户双击节点时触发 。
	protected String onBeforeLoad;// 在请求载入数据之前触发，返回false将取消载入。
	protected String onLoadSuccess;// 当数据载入成功时触发。
	protected String onLoadError;// 当数据载入失败时触发，arguments参数和jQuery.ajax的'error'函数一样。
	protected String onBeforeExpand;// 在节点打开之前触发 ，返回false将取消打开。
	protected String onExpand;// 当节点打开时触发。
	protected String onBeforeCollapse;// 在节点关闭之前触发，返回false将取消关闭。
	protected String onCollapse;// 当节点关闭时触发。
	protected String onContextMenu;// 当节点被鼠标右键点击时触发 。
	protected String onBeforeEdit;// 当用户开始编辑节点时触发。
	protected String onAfterEdit;// 当用户结束编辑时触发。
	protected String onCancelEdit;// 当用户取消编辑节点时触发。

	public String getCss() {
		return "easyui-treegrid";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("treeField", "'" + treeField + "'");
		parameters.put("animate", animate);
		parameters.put("onClickRow", onClickRow);
		parameters.put("onDblClickRow", onDblClickRow);
		parameters.put("onBeforeLoad", onBeforeLoad);
		parameters.put("onLoadSuccess", onLoadSuccess);
		parameters.put("onLoadError", onLoadError);
		parameters.put("onBeforeExpand", onBeforeExpand);
		parameters.put("onExpand", onExpand);
		parameters.put("onBeforeCollapse", onBeforeCollapse);
		parameters.put("onCollapse", onCollapse);
		parameters.put("onContextMenu", onContextMenu);
		parameters.put("onBeforeEdit", onBeforeEdit);
		parameters.put("onAfterEdit", onAfterEdit);
		parameters.put("onCancelEdit", onCancelEdit);
	}

	public String getTreeField() {
		return treeField;
	}

	public void setTreeField(String treeField) {
		this.treeField = treeField;
	}

	public String getAnimate() {
		return animate;
	}

	public void setAnimate(String animate) {
		this.animate = animate;
	}

	public String getOnClickRow() {
		return onClickRow;
	}

	public void setOnClickRow(String onClickRow) {
		this.onClickRow = onClickRow;
	}

	public String getOnDblClickRow() {
		return onDblClickRow;
	}

	public void setOnDblClickRow(String onDblClickRow) {
		this.onDblClickRow = onDblClickRow;
	}

	public String getOnBeforeLoad() {
		return onBeforeLoad;
	}

	public void setOnBeforeLoad(String onBeforeLoad) {
		this.onBeforeLoad = onBeforeLoad;
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

	public String getOnBeforeExpand() {
		return onBeforeExpand;
	}

	public void setOnBeforeExpand(String onBeforeExpand) {
		this.onBeforeExpand = onBeforeExpand;
	}

	public String getOnExpand() {
		return onExpand;
	}

	public void setOnExpand(String onExpand) {
		this.onExpand = onExpand;
	}

	public String getOnBeforeCollapse() {
		return onBeforeCollapse;
	}

	public void setOnBeforeCollapse(String onBeforeCollapse) {
		this.onBeforeCollapse = onBeforeCollapse;
	}

	public String getOnCollapse() {
		return onCollapse;
	}

	public void setOnCollapse(String onCollapse) {
		this.onCollapse = onCollapse;
	}

	public String getOnContextMenu() {
		return onContextMenu;
	}

	public void setOnContextMenu(String onContextMenu) {
		this.onContextMenu = onContextMenu;
	}

	public String getOnBeforeEdit() {
		return onBeforeEdit;
	}

	public void setOnBeforeEdit(String onBeforeEdit) {
		this.onBeforeEdit = onBeforeEdit;
	}

	public String getOnAfterEdit() {
		return onAfterEdit;
	}

	public void setOnAfterEdit(String onAfterEdit) {
		this.onAfterEdit = onAfterEdit;
	}

	public String getOnCancelEdit() {
		return onCancelEdit;
	}

	public void setOnCancelEdit(String onCancelEdit) {
		this.onCancelEdit = onCancelEdit;
	}
}