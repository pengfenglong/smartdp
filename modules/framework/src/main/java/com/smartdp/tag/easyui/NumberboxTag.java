package com.smartdp.tag.easyui;

/**
 * 数字验证框标签
 * 
 * @author pengfenglong
 * 
 */
public class NumberboxTag extends AbstractUITag {
	protected String disabled;// 禁用
	protected String min;// 最小值
	protected String max;// 最大值
	protected String precision;// 精度
	protected String disable;// 禁用输入框。
	protected String enable;// 启用输入框。
	protected String fix;// 将输入框的值修正为一个有效值。

	public String getCss() {
		return "easyui-numberbox";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("disabled", disabled);
		parameters.put("min", "'" + min + "'");
		parameters.put("max", "'" + max + "'");
		parameters.put("precision", "'" + precision + "'");
		parameters.put("disable", disable);
		parameters.put("enable", enable);
		parameters.put("fix", fix);
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
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

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getFix() {
		return fix;
	}

	public void setFix(String fix) {
		this.fix = fix;
	}
}