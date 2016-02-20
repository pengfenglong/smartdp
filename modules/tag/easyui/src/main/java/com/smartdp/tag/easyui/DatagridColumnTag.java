package com.smartdp.tag.easyui;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.smartdp.framework.tag.select.DictList;
import com.smartdp.framework.tag.select.DictType;
import com.smartdp.framework.tag.select.DictUtils;

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

	public String getTagType() {
		return "th";
	}

	public String getCss() {
		return "";
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
		parameters.put("formatter", "'" + formatter + "'");
		parameters.put("styler", "'" + styler + "'");
		parameters.put("sorter", "'" + sorter + "'");
		parameters.put("editor", editor);
		if(!StringUtils.isEmpty(dict)){
			StringBuffer _s = new StringBuffer();
			_s.append("function(val,row,index){" );
					
			_s.append("alert(val);");	
			_s.append("}");
			
			List<DictType> dictTypes = DictUtils.getDictTypes();
			parameters.put(formatter, _s.toString());
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
	
}