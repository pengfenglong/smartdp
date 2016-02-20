package com.smartdp.tag.easyui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

public abstract class AbstractUITag extends TagSupport {

	protected String id;
	protected String name;
	protected Map parameters = new HashMap();
	protected Map attributes = new HashMap();
	protected String getContextPath() {
		String contextPath = ((HttpServletRequest) pageContext.getRequest())
				.getContextPath();
		return contextPath;
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

	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.println("</" + getTagType() + ">");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	public String resolveParams() {
		populateParams();
		StringBuffer s = new StringBuffer();
		for (Object o : parameters.keySet()) {
			if (parameters.get(o) != null
					&& !"'null'".equals(parameters.get(o))) {
				s.append(o).append(":").append(parameters.get(o)).append(",");
			}
		}
		String option = "";
		if (s.length() > 1) {
			option = s.substring(0, s.length() - 1);
		}

		return option;
	}
	
	public String resolveAttrs(){
		populateAttributes();
		StringBuffer s = new StringBuffer();
		for (Object o : attributes.keySet()) {
			if (attributes.get(o) != null
					&& !"'null'".equals(attributes.get(o))) {
				s.append(" "+o+"=\"").append(attributes.get(o)).append("\"");
			}
		}
		return s.toString();
	}

	public String getTagType() {
		return "div";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void populateAttributes() {
		attributes.put("id", id);
		attributes.put("name", name);
		attributes.put("class", getCss());
	}

	public void populateParams() {
	}

	public String getCss() {
		return "";
	}
}
