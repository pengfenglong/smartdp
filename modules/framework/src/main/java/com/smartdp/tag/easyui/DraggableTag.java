package com.smartdp.tag.easyui;

/**
 * 一般拖动标签
 * 
 * @author pengfenglong
 * 
 */
public class DraggableTag extends AbstractUITag {
	protected String proxy;// 替代
	protected String revert;// 复原
	protected String cursor;// 指针
	protected String deltaX;// 水平增量，X轴
	protected String deltaY;// 垂直增量，y轴
	protected String handle;// 句柄
	protected String disabled;// 停止拖动
	protected String edge;// 边缘
	protected String axis;// 拖动轴
	protected String onBeforeDrag;// 在拖动之前触发，返回false将取消拖动。
	protected String onStartDrag;// 当目标对象开始被拖动时触发。
	protected String onDrag;// 在拖动过程中触发，当不能再拖动时返回false。
	protected String onStopDrag;// 当拖动停止时触发。

	public String getCss() {
		return "easyui-draggable";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("proxy", "'" + proxy + "'");
		parameters.put("revert", revert);
		parameters.put("cursor", "'" + cursor + "'");
		parameters.put("deltaX", "'" + deltaX + "'");
		parameters.put("deltaY", "'" + deltaY + "'");
		parameters.put("handle", "'" + handle + "'");
		parameters.put("disabled", disabled);
		parameters.put("edge", "'" + edge + "'");
		parameters.put("axis", "'" + axis + "'");
		parameters.put("onBeforeDrag", onBeforeDrag);
		parameters.put("onStartDrag", onStartDrag);
		parameters.put("onDrag", onDrag);
		parameters.put("onStopDrag", onStopDrag);
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getRevert() {
		return revert;
	}

	public void setRevert(String revert) {
		this.revert = revert;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public String getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(String deltaX) {
		this.deltaX = deltaX;
	}

	public String getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(String deltaY) {
		this.deltaY = deltaY;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	public String getAxis() {
		return axis;
	}

	public void setAxis(String axis) {
		this.axis = axis;
	}

	public String getOnBeforeDrag() {
		return onBeforeDrag;
	}

	public void setOnBeforeDrag(String onBeforeDrag) {
		this.onBeforeDrag = onBeforeDrag;
	}

	public String getOnStartDrag() {
		return onStartDrag;
	}

	public void setOnStartDrag(String onStartDrag) {
		this.onStartDrag = onStartDrag;
	}

	public String getOnDrag() {
		return onDrag;
	}

	public void setOnDrag(String onDrag) {
		this.onDrag = onDrag;
	}

	public String getOnStopDrag() {
		return onStopDrag;
	}

	public void setOnStopDrag(String onStopDrag) {
		this.onStopDrag = onStopDrag;
	}
}