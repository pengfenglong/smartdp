package com.smartdp.tag.easyui;

/**
 * 拖动至容器标签
 * 
 * @author pengfenglong
 * 
 */
public class DroppableTag extends AbstractUITag {
	protected String accept;// accept
	protected String onDragEnter;// 当可拖动元素被拖入目标容器的时候触发，参数source是被拖动的DOM元素。
	protected String onDragOver;// 当可拖动元素被拖至某个元素之上的时候触发，参数source是被拖动的DOM元素。
	protected String onDragLeave;// 当可拖动元素被拖离目标容器的时候触发，参数source是被拖动的DOM元素。
	protected String onDrop;// 当可拖动元素被拖入目标容器并放置的时候触发，参数source是被拖动的DOM元素。

	public String getCss() {
		return "easyui-droppable";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("accept", "'" + accept + "'");
		parameters.put("onDragEnter", onDragEnter);
		parameters.put("onDragOver", onDragOver);
		parameters.put("onDragLeave", onDragLeave);
		parameters.put("onDrop", onDrop);
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getOnDragEnter() {
		return onDragEnter;
	}

	public void setOnDragEnter(String onDragEnter) {
		this.onDragEnter = onDragEnter;
	}

	public String getOnDragOver() {
		return onDragOver;
	}

	public void setOnDragOver(String onDragOver) {
		this.onDragOver = onDragOver;
	}

	public String getOnDragLeave() {
		return onDragLeave;
	}

	public void setOnDragLeave(String onDragLeave) {
		this.onDragLeave = onDragLeave;
	}

	public String getOnDrop() {
		return onDrop;
	}

	public void setOnDrop(String onDrop) {
		this.onDrop = onDrop;
	}
}