package com.smartdp.tag.easyui;

/**
 * 调节器标签
 * 
 * @author pengfenglong
 * 
 */
public class SpinnerTag extends AbstractUITag {
	protected String width;// 宽度
	protected String value;// 值
	protected String min;// 最小值
	protected String max;// 最大值
	protected String increment;// 增量
	protected String editable;// 可编辑
	protected String disabled;// 禁用
	protected String spin;// 调节函数
	protected String onSpinUp;// 当用户点击增加按钮时触发。
	protected String onSpinDown;// 当用户点击减少按钮时触发。

	public String getCss() {
		return "easyui-spinner";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("width", "'" + width + "'");
		parameters.put("value", "'" + value + "'");
		parameters.put("min", "'" + min + "'");
		parameters.put("max", "'" + max + "'");
		parameters.put("increment", "'" + increment + "'");
		parameters.put("editable", editable);
		parameters.put("disabled", disabled);
		parameters.put("spin", "'" + spin + "'");
		parameters.put("onSpinUp", onSpinUp);
		parameters.put("onSpinDown", onSpinDown);
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getSpin() {
		return spin;
	}

	public void setSpin(String spin) {
		this.spin = spin;
	}

	public String getOnSpinUp() {
		return onSpinUp;
	}

	public void setOnSpinUp(String onSpinUp) {
		this.onSpinUp = onSpinUp;
	}

	public String getOnSpinDown() {
		return onSpinDown;
	}

	public void setOnSpinDown(String onSpinDown) {
		this.onSpinDown = onSpinDown;
	}
}