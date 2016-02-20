package com.smartdp.tag.easyui;

/**
 * 数字调节器标签
 * 
 * @author pengfenglong
 * 
 */
public class NumberspinnerTag extends AbstractUITag {
	protected String options;// options
	protected String setValue;// setValue

	public String getCss() {
		return "easyui-numberspinner";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("options", "'" + options + "'");
		parameters.put("setValue", "'" + setValue + "'");
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
}