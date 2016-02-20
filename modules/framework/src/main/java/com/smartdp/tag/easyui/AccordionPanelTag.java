package com.smartdp.tag.easyui;

/**
 * 可伸缩面板标签
 * 
 * @author pengfenglong
 * 
 */
public class AccordionPanelTag extends PanelTag {
	protected String selected;// 被选择
	protected String onSelect;// 当一个可伸缩面板被选择时触发
	protected String onAdd;// 在一个新面板被添加时触发
	protected String onBeforeRemove;// 在可伸缩面板被移除之前触发，返回false将取消移除
	protected String onRemove;// 在一个可伸缩面板被移除时触发

	public String getCss() {
		return "";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("selected", selected);
		parameters.put("onSelect", onSelect);
		parameters.put("onAdd", onAdd);
		parameters.put("onBeforeRemove", onBeforeRemove);
		parameters.put("onRemove", onRemove);
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getOnSelect() {
		return onSelect;
	}

	public void setOnSelect(String onSelect) {
		this.onSelect = onSelect;
	}

	public String getOnAdd() {
		return onAdd;
	}

	public void setOnAdd(String onAdd) {
		this.onAdd = onAdd;
	}

	public String getOnBeforeRemove() {
		return onBeforeRemove;
	}

	public void setOnBeforeRemove(String onBeforeRemove) {
		this.onBeforeRemove = onBeforeRemove;
	}

	public String getOnRemove() {
		return onRemove;
	}

	public void setOnRemove(String onRemove) {
		this.onRemove = onRemove;
	}

}