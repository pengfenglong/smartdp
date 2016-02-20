package com.smartdp.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ToolbarTag extends TagSupport {
	protected String id = "toolbar";
	protected String _class = "toolbar";
	
	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();

		try {
			
			StringBuffer sb = new StringBuffer();
			sb.append("<div id=\"");
			sb.append(id);
			sb.append("\" class=\"");
			sb.append(_class);
			sb.append("\">");
			out.println(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}

		return EVAL_PAGE;

	}
	
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.println("</div>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}
	
	

}
