package com.smartdp.tag.easyui;

/**
 * 日历标签
 * 
 * @author pengfenglong
 * 
 */
public class CalendarTag extends AbstractUITag {
	protected String width;// 宽度
	protected String height;// 高度
	protected String fit;// 铺满浏览器
	protected String border;// 边框
	protected String weeks;// 星期代码列表
	protected String months;// 月份代码列表
	protected String year;// 年份
	protected String month;// 月份
	protected String current;// 当前日期
	protected String onSelect;// 当用户选择日期的时候触发。

	public String getCss() {
		return "easyui-calendar";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("width", "'" + width + "'");
		parameters.put("height", "'" + height + "'");
		parameters.put("fit", fit);
		parameters.put("border", border);
		parameters.put("weeks", weeks);
		parameters.put("months", months);
		parameters.put("year", "'" + year + "'");
		parameters.put("month", "'" + month + "'");
		parameters.put("current", "'" + current + "'");
		parameters.put("onSelect", onSelect);
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getFit() {
		return fit;
	}

	public void setFit(String fit) {
		this.fit = fit;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getOnSelect() {
		return onSelect;
	}

	public void setOnSelect(String onSelect) {
		this.onSelect = onSelect;
	}
}