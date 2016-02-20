package com.smartdp.tag.easyui;

/**
 * 搜索框标签
 * 
 * @author pengfenglong
 * 
 */
public class SearchboxTag extends AbstractUITag {
	protected String width;// 宽度
	protected String propmt;// 提醒
	protected String value;// 值
	protected String menu;// 菜单
	protected String searcher;// 搜索器

	public String getCss() {
		return "easyui-searchbox";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("width", "'" + width + "'");
		parameters.put("propmt", "'" + propmt + "'");
		parameters.put("value", "'" + value + "'");
		parameters.put("menu", "'" + menu + "'");
		parameters.put("searcher", "'" + searcher + "'");
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getPropmt() {
		return propmt;
	}

	public void setPropmt(String propmt) {
		this.propmt = propmt;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getSearcher() {
		return searcher;
	}

	public void setSearcher(String searcher) {
		this.searcher = searcher;
	}

}