package com.smartdp.tag.validform;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

public class InputTag extends TagSupport {

	protected String id;
	protected String name;
	protected String type;
	protected String _class;
	protected String value;
	protected String datatype;
	protected String style;
	protected String errormsg;
	protected String plugin;
	protected String ignore;
	protected String recheck;

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append("<input ");
		if (!StringUtils.isEmpty(id)) {
			sb.append("id=\"").append(id).append("\" ");
		}
		if (!StringUtils.isEmpty(name)) {
			sb.append("name=\"").append(name).append("\" ");
		}
		if (!StringUtils.isEmpty(type)) {
			sb.append("type=\"").append(type).append("\" ");
		} else {
			sb.append("type=\"text\" ");
		}
		if (!StringUtils.isEmpty(_class)) {
			sb.append("class=\"").append(_class).append("\" ");
		} else {
			sb.append("class=\"inputxt\" ");
		}
		if (!StringUtils.isEmpty(value)) {
			sb.append("value=\"").append(value).append("\" ");
		}
		if (!StringUtils.isEmpty(datatype)) {
			sb.append("datatype=\"").append(datatype).append("\" ");
		}
		if (!StringUtils.isEmpty(style)) {
			sb.append("style=\"").append(style).append("\" ");
		}
		if (!StringUtils.isEmpty(errormsg)) {
			sb.append("errormsg=\"").append(errormsg).append("\" ");
		}
		if (!StringUtils.isEmpty(plugin)) {
			sb.append("plugin=\"").append(plugin).append("\" ");
		}
		if (!StringUtils.isEmpty(ignore)) {
			sb.append("ignore=\"").append(ignore).append("\" ");
		}
		if (!StringUtils.isEmpty(recheck)) {
			sb.append("recheck=\"").append(recheck).append("\" ");
		}
		sb.append(" />");
		try {
			out.println(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}

		return EVAL_PAGE;

	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getPlugin() {
		return plugin;
	}

	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}

	public String getIgnore() {
		return ignore;
	}

	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}

	public String getRecheck() {
		return recheck;
	}

	public void setRecheck(String recheck) {
		this.recheck = recheck;
	}

}
