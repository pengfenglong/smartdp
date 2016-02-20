package com.smartdp.tag.easyui;

/**
 * 缩放标签
 * 
 * @author pengfenglong
 * 
 */
public class ResizableTag extends AbstractUITag {
	protected String disabled;// 禁止缩放
	protected String handles;// 句柄
	protected String minWidth;// 最小宽度
	protected String minHeight;// 最小高度
	protected String maxWidth;// 最大宽度
	protected String maxHeight;// maxHeight
	protected String edge;// 边界
	protected String onStartResize;// 当开始缩放时触发。
	protected String onResize;// 在缩放过程中触发，当返回false时, DOM元素将不再缩放。
	protected String onStopResize;// 当缩放停止是触发。

	public String getCss() {
		return "easyui-resizable";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("disabled", disabled);
		parameters.put("handles", "'" + handles + "'");
		parameters.put("minWidth", "'" + minWidth + "'");
		parameters.put("minHeight", "'" + minHeight + "'");
		parameters.put("maxWidth", "'" + maxWidth + "'");
		parameters.put("maxHeight", "'" + maxHeight + "'");
		parameters.put("edge", "'" + edge + "'");
		parameters.put("onStartResize", onStartResize);
		parameters.put("onResize", onResize);
		parameters.put("onStopResize", onStopResize);
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getHandles() {
		return handles;
	}

	public void setHandles(String handles) {
		this.handles = handles;
	}

	public String getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	public String getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(String minHeight) {
		this.minHeight = minHeight;
	}

	public String getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(String maxWidth) {
		this.maxWidth = maxWidth;
	}

	public String getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	public String getOnStartResize() {
		return onStartResize;
	}

	public void setOnStartResize(String onStartResize) {
		this.onStartResize = onStartResize;
	}

	public String getOnResize() {
		return onResize;
	}

	public void setOnResize(String onResize) {
		this.onResize = onResize;
	}

	public String getOnStopResize() {
		return onStopResize;
	}

	public void setOnStopResize(String onStopResize) {
		this.onStopResize = onStopResize;
	}
}