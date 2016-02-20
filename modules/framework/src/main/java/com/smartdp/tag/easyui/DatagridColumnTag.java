package com.smartdp.tag.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

public class DatagridColumnTag extends AbstractUITag {
	protected String title;// 标题
	protected String field;// 字段
	protected String width;// 宽度
	protected String rowspan;// 跨行数
	protected String colspan;// 跨列数
	protected String align;// 对其方式
	protected String sortable;// 可排序
	protected String resizable;// 缩放
	protected String hidden;// 隐藏
	protected String checkbox;// 复选框
	protected String formatter;// 格式化
	protected String styler;// 样式
	protected String sorter;// 排序器
	protected String editor;// 编辑器
	protected String dict;// 数据字典
	protected String query = "false";// 是否为查询字段
	protected String matchType = "LIKES";// 查询类型

	public String getTagType() {
		return "th";
	}

	public String getCss() {
		return "";
	}
	
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			StringBuffer start = new StringBuffer();
			start.append("<").append(getTagType());
			start.append(resolveAttrs());
			start.append(" data-options=\"").append(resolveParams()).append(
					"\">");
			out.println(start.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_BODY_INCLUDE;
	}


	public void populateParams() {
		super.populateParams();
		parameters.put("title", "'" + title + "'");
		parameters.put("field", "'" + field + "'");
		parameters.put("width", width);
		parameters.put("rowspan", "'" + rowspan + "'");
		parameters.put("colspan", "'" + colspan + "'");
		parameters.put("align", "'" + align + "'");
		parameters.put("sortable", sortable);
		parameters.put("resizable", resizable);
		parameters.put("hidden", hidden);
		parameters.put("checkbox", checkbox);
		parameters.put("formatter", formatter);
		parameters.put("styler", "'" + styler + "'");
		parameters.put("sorter", "'" + sorter + "'");
		parameters.put("editor", editor);
		if(!StringUtils.isEmpty(dict)){
			parameters.put("formatter","$.crud.columnFormat");
		}
		if("true".equals(query)){
			JspWriter out = pageContext.getOut();
			StringBuffer script = new StringBuffer();
			script.append("<script>$(function(){var fieldName = $('td[field=").append(field).append("]').text();");
			script.append("var _input = '<input name=\"filter_").append(matchType).append("_").append(field).append("\" />';");
			script.append("if($('input[name=filter_").append(matchType).append("_").append(field).append("]').length == 0){");
			script.append("	   $('#condition_area').append(fieldName + ': ' + _input);");
			script.append("}");
			script.append("});</script>");
			try {
				out.print(script);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getRowspan() {
		return rowspan;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public String getColspan() {
		return colspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

	public String getResizable() {
		return resizable;
	}

	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getStyler() {
		return styler;
	}

	public void setStyler(String styler) {
		this.styler = styler;
	}

	public String getSorter() {
		return sorter;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getDict() {
		return dict;
	}

	public void setDict(String dict) {
		this.dict = dict;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	
}