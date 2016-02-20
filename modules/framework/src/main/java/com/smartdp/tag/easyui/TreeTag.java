package com.smartdp.tag.easyui;

import org.apache.commons.lang.StringUtils;

/**
 * 树形菜单标签
 * 
 * @author pengfenglong
 * 
 */
public class TreeTag extends AbstractUITag {
	protected String lazy = "true";// 是否延迟加载
	protected String url;// 超链接
	protected String method;// 方法
	protected String animate;// 动画
	protected String checkbox;// 复选框
	protected String cascadeCheck;// 级联选择
	protected String onlyLeafCheck;// 只选叶子节点
	protected String dnd;// 拖放
	protected String data;// 数据
	protected String onClick;// 当用户点击节点时触发，node参数包含如下属性： id：节点id。
								// text：显示在节点上的文本。 checked：节点是否被选择。
								// attributes：节点的自定义属性。 target：被点击的目标DOM对象。
	protected String onDblClick;// 当用户双击节点时触发。
	protected String onBeforeLoad;// 在请求载入数据之前触发，返回false将取消载入。
	protected String onLoadSuccess;// 当数据载入成功时触发。
	protected String onLoadError;// 当数据载入失败时触发，arguments参数跟jQuery.ajax的'error'函数一样。
	protected String onBeforeExpand;// 在节点打开之前触发，返回false将取消打开。
	protected String onExpand;// 在节点被打开时触发。
	protected String onBeforeCollapse;// 在节点被关闭之前触发，返回false将取消关闭。
	protected String onCollapse;// 当节点被关闭时触发。
	protected String onCheck;// 当用户点击复选框时触发。
	protected String onBeforeSelect;// 在节点被选择之前触发，返回false将取消选择。
	protected String onSelect;// 当节点被选择时触发。
	protected String onContextMenu;// 当节点被鼠标右键点击时触发。
	protected String onDrop;// 当节点位置被（拖动）更换时触发。 target：DOM对象，需要被拖动动的目标节点。
							// source：原始节点。
							// point：指明拖动方式，可选值：'append'，'top'或者'bottom'。
	protected String onBeforeEdit;// 在编辑节点之前触发。
	protected String onAfterEdit;// 在编辑节点之后触发。
	protected String onCancelEdit;// 当取消编辑时触发。
	protected String parentIdField = "parentId";// 父ID字段
	protected String param;// 参数
	
	public String getCss() {
		return "easyui-tree";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("method", "'" + method + "'");
		parameters.put("animate", animate);
		parameters.put("checkbox", checkbox);
		parameters.put("cascadeCheck", cascadeCheck);
		parameters.put("onlyLeafCheck", onlyLeafCheck);
		parameters.put("dnd", dnd);
		parameters.put("data", data);
		parameters.put("onClick", onClick);
		parameters.put("onDblClick", onDblClick);
		parameters.put("onLoadSuccess", onLoadSuccess);
		parameters.put("onLoadError", onLoadError);
		parameters.put("onBeforeExpand", onBeforeExpand);
		parameters.put("onExpand", onExpand);
		parameters.put("onBeforeCollapse", onBeforeCollapse);
		parameters.put("onCollapse", onCollapse);
		parameters.put("onCheck", onCheck);
		parameters.put("onBeforeSelect", onBeforeSelect);
		parameters.put("onSelect", onSelect);
		parameters.put("onContextMenu", onContextMenu);
		parameters.put("onDrop", onDrop);
		parameters.put("onBeforeEdit", onBeforeEdit);
		parameters.put("onAfterEdit", onAfterEdit);
		parameters.put("onCancelEdit", onCancelEdit);
		
		if("true".equals(lazy)){
			StringBuffer paramS = new StringBuffer("");
			if(!StringUtils.isEmpty(param)){
				String[] pas = param.split(",");
				for(String pa : pas){
					String[] s = pa.split(":");
					paramS.append("+'&").append(s[0]).append("='+").append(s[1]);
				}
			}
			String _s = "function(node,o){$(this).tree('options').url = '"
					+ getContextPath() + "/" + url + "?" + parentIdField + "=";
			parameters.put("url", "'"+getContextPath() + "/" + url + "?" + parentIdField + "='"+paramS);
			if(StringUtils.isEmpty(onBeforeExpand)){
				parameters.put("onBeforeExpand", _s + "'+node.id"+paramS+";}");
			}else{
				parameters.put("onBeforeLoad", onBeforeLoad);
			}
		}else{
			parameters.put("url", "'" + url + "'");
			parameters.put("onBeforeLoad", onBeforeLoad);
		}
		
	}
	
	public String getLazy() {
		return lazy;
	}

	public void setLazy(String lazy) {
		this.lazy = lazy;
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

	public String getAnimate() {
		return animate;
	}

	public void setAnimate(String animate) {
		this.animate = animate;
	}

	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	public String getCascadeCheck() {
		return cascadeCheck;
	}

	public void setCascadeCheck(String cascadeCheck) {
		this.cascadeCheck = cascadeCheck;
	}

	public String getOnlyLeafCheck() {
		return onlyLeafCheck;
	}

	public void setOnlyLeafCheck(String onlyLeafCheck) {
		this.onlyLeafCheck = onlyLeafCheck;
	}

	public String getDnd() {
		return dnd;
	}

	public void setDnd(String dnd) {
		this.dnd = dnd;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getOnDblClick() {
		return onDblClick;
	}

	public void setOnDblClick(String onDblClick) {
		this.onDblClick = onDblClick;
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

	public String getOnCheck() {
		return onCheck;
	}

	public void setOnCheck(String onCheck) {
		this.onCheck = onCheck;
	}

	public String getOnBeforeSelect() {
		return onBeforeSelect;
	}

	public void setOnBeforeSelect(String onBeforeSelect) {
		this.onBeforeSelect = onBeforeSelect;
	}

	public String getOnSelect() {
		return onSelect;
	}

	public void setOnSelect(String onSelect) {
		this.onSelect = onSelect;
	}

	public String getOnContextMenu() {
		return onContextMenu;
	}

	public void setOnContextMenu(String onContextMenu) {
		this.onContextMenu = onContextMenu;
	}

	public String getOnDrop() {
		return onDrop;
	}

	public void setOnDrop(String onDrop) {
		this.onDrop = onDrop;
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

	public String getParentIdField() {
		return parentIdField;
	}

	public void setParentIdField(String parentIdField) {
		this.parentIdField = parentIdField;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
}