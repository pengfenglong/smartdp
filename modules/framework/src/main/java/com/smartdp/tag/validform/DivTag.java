package com.smartdp.tag.validform;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

public class DivTag extends TagSupport {

	protected String label;
	protected String checktip;
	protected String need;

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();

		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<div class=\"form\">");
			sb.append("<label class=\"Validform_label\">");
			if (!StringUtils.isEmpty(need)) {
				sb.append("<span class=\"need\">*</span>");
			}else{
				sb.append("<span class=\"need\">&nbsp;</span>");
			}
			sb.append(label).append(" : ");
			sb.append("</label>");
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
			StringBuffer sb = new StringBuffer();
			sb.append("<span class=\"Validform_checktip\">");
			if (!StringUtils.isEmpty(checktip)) {
				sb.append(checktip);
			}
			sb.append("</span></div>");
			out.println(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getChecktip() {
		return checktip;
	}

	public void setChecktip(String checktip) {
		this.checktip = checktip;
	}

	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}

}
