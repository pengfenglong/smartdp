package com.smartdp.tag.easyui;

/**
 * 属性表格标签
 * 
 * @author pengfenglong
 * 
 */
public class PropertygridTag extends AbstractUITag {
	protected String showGroup;// 显示分组
	protected String groupField;// 分组字段
	protected String groupFormatter;// 分组格式

	public String getCss() {
		return "easyui-propertygrid";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("showGroup", showGroup);
		parameters.put("groupField", "'" + groupField + "'");
		parameters.put("groupFormatter", "'" + groupFormatter + "'");
	}

	public String getShowGroup() {
		return showGroup;
	}

	public void setShowGroup(String showGroup) {
		this.showGroup = showGroup;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public String getGroupFormatter() {
		return groupFormatter;
	}

	public void setGroupFormatter(String groupFormatter) {
		this.groupFormatter = groupFormatter;
	}
}