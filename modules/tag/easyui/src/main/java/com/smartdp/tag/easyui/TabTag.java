package com.smartdp.tag.easyui;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TabTag extends AbstractUITag {
	private String title;// 标题
	private String content;// 内容
	private String href;// 超链接
	private String cache;// 缓存
	private String iconCls;// 图标CSS类
	private String width;// 宽度
	private String height;// 高度
	private boolean closable;// 可关闭
	private boolean selected;// 默认选项卡

	public String getCss() {
		return "";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("width", "'" + width + "'");
		parameters.put("height", "'" + height + "'");
		parameters.put("title", "'" + title + "'");
		parameters.put("content", "'" + content + "'");
		parameters.put("href", "'" + href + "'");
		parameters.put("cache", "'" + cache + "'");
		parameters.put("iconCls", "'" + iconCls + "'");
		parameters.put("selected", selected);
		parameters.put("closable", closable);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getCache() {
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
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

	public boolean isClosable() {
		return closable;
	}

	public void setClosable(boolean closable) {
		this.closable = closable;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}