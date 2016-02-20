package com.smartdp.tag.easyui;

/**
 * 日期时间组合框标签
 * 
 * @author pengfenglong
 * 
 */
public class DatetimeboxTag extends AbstractUITag {
	protected String showSeconds;// 显示秒
	protected String options;// 返回属性对象。
	protected String spinner;// 返回时间调节器对象。
	protected String setValue;// 设置日期时间组合框的值。

	public String getCss() {
		return "easyui-datetimebox";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("showSeconds", showSeconds);
		parameters.put("options", options);
		parameters.put("spinner", spinner);
		parameters.put("setValue", setValue);
	}

	public String getShowSeconds() {
		return showSeconds;
	}

	public void setShowSeconds(String showSeconds) {
		this.showSeconds = showSeconds;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getSpinner() {
		return spinner;
	}

	public void setSpinner(String spinner) {
		this.spinner = spinner;
	}

	public String getSetValue() {
		return setValue;
	}

	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}
}