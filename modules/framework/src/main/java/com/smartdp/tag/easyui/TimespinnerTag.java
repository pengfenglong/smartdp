package com.smartdp.tag.easyui;

/**
 * 时间调节器标签
 * 
 * @author pengfenglong
 * 
 */
public class TimespinnerTag extends AbstractUITag {
	protected String separator;// 分隔符
	protected String showSeconds;// 显示秒数
	protected String highlight;// 高亮
	protected String options;// 返回属性对象。
	protected String setValue;// 设置时间调节器的值。
	protected String getHours;// 获取当前小时的值。
	protected String getMinutes;// 获取当前分钟的值。
	protected String getSeconds;// 获取当前秒钟的值。

	public String getCss() {
		return "easyui-timespinner";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("separator", "'" + separator + "'");
		parameters.put("showSeconds", showSeconds);
		parameters.put("highlight", "'" + highlight + "'");
		parameters.put("options", options);
		parameters.put("setValue", setValue);
		parameters.put("getHours", getHours);
		parameters.put("getMinutes", getMinutes);
		parameters.put("getSeconds", getSeconds);
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getShowSeconds() {
		return showSeconds;
	}

	public void setShowSeconds(String showSeconds) {
		this.showSeconds = showSeconds;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getSetValue() {
		return setValue;
	}

	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}

	public String getGetHours() {
		return getHours;
	}

	public void setGetHours(String getHours) {
		this.getHours = getHours;
	}

	public String getGetMinutes() {
		return getMinutes;
	}

	public void setGetMinutes(String getMinutes) {
		this.getMinutes = getMinutes;
	}

	public String getGetSeconds() {
		return getSeconds;
	}

	public void setGetSeconds(String getSeconds) {
		this.getSeconds = getSeconds;
	}
}