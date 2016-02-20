package com.smartdp.tag.easyui;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 布局面板标签
 * 
 * @author pengfenglong
 * 
 */
public class LayoutBodyTag extends TagSupport {
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.println("<body class=\"easyui-layout\">");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.println("</body>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

}