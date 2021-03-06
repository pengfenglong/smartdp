package com.smartdp.tag.validform;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class ValidFormTag extends TagSupport {
	protected String formid = "formobj";// 表单FORM ID
	protected Boolean refresh = true;
	protected String callback="$.crud.saveSuccessCallback";// 回调函数
	protected String beforeSubmit;// 提交前处理函数
	protected String btnsub = "btn_sub";// 以ID为标记触发提交事件
	protected String btnreset = "btn_reset";// 以ID为标记触发提交事件
	protected String layout = "div";// 表单布局
	protected String usePlugin;// 外调插件
	protected boolean dialog = true;// 是否是弹出窗口模式
	protected String action;// 表单提交路径
	protected String tabtitle;// 表单选项卡

	public void setTabtitle(String tabtitle) {
		this.tabtitle = tabtitle;
	}

	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}

	public void setBtnsub(String btnsub) {
		this.btnsub = btnsub;
	}

	public void setRefresh(Boolean refresh) {
		this.refresh = refresh;
	}

	public void setBtnreset(String btnreset) {
		this.btnreset = btnreset;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			String contexPath = request.getContextPath();
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			if ("div".equals(layout)) {
				sb.append("<div id=\"content\">");
				sb.append("<div id=\"wrapper\">");
				sb.append("<div id=\"steps\">");
			}
			sb.append("<form id=\"" + formid + "\" action=\"" +contexPath+"/" + action + "\" name=\"" + formid + "\" method=\"post\">");
			if ("btn_sub".equals(btnsub))
				sb.append("<input type=\"hidden\" id=\"" + btnsub + "\" class=\"" + btnsub + "\"/>");
			out.print(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			String contexPath = request.getContextPath();
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			if (layout.equals("div")) {
				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/Validform/css/divfrom.css\" type=\"text/css\"/>");
				if (tabtitle != null)
					sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Validform/js/form.js\"></script>");
			}
			sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/Validform/css/style.css\" type=\"text/css\"/>");
			sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/Validform/css/tablefrom.css\" type=\"text/css\"/>");
			sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Validform/js/Validform_v5.3.1_min.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Validform/js/Validform_Datatype.js\"></script>");
			//----------------------------------------------------------------
			//update-begin--Author:zhangdaihao  Date:20130225 for：金额校验规则
			sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Validform/js/datatype.js\"></script>");
			//update-end--Author:zhangdaihao  Date:20130225 for：金额校验规则
			//----------------------------------------------------------------
			if (usePlugin != null) {
				if (usePlugin.indexOf("jqtransform") >= 0) {
					sb.append("<SCRIPT type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Validform/plugin/jqtransform/jquery.jqtransform.js\"></SCRIPT>");
					sb.append("<LINK rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/Validform/plugin/jqtransform/jqtransform.css\" type=\"text/css\"></LINK>");
				}
				if (usePlugin.indexOf("password") >= 0) {
					sb.append("<SCRIPT type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Validform/plugin/passwordStrength/passwordStrength-min.js\"></SCRIPT>");
				}
			}
			sb.append("<script type=\"text/javascript\">");
			sb.append("$(function(){");
			sb.append("$(\"#" + formid + "\").Validform({");
			sb.append("tiptype:4,");
			sb.append("btnSubmit:\"#" + btnsub + "\",");
			sb.append("btnReset:\"#" + btnreset + "\",");
			sb.append("ajaxPost:true,");
			if (beforeSubmit != null) {
				sb.append("beforeSubmit:function(curform){var tag=false;");
				sb.append("return " + beforeSubmit + "(curform);");

				sb.append("},");
			}
			if (usePlugin != null) {
				StringBuffer passsb = new StringBuffer();
				if (usePlugin.indexOf("password") >= 0) {
					passsb.append("passwordstrength:{");
					passsb.append("minLen:6,");
					passsb.append("maxLen:18,");
					passsb.append("trigger:function(obj,error)");
					passsb.append("{");
					passsb.append("if(error)");
					passsb.append("{");
					passsb.append("obj.parent().next().find(\".Validform_checktip\").show();");
					passsb.append("obj.find(\".passwordStrength\").hide();");
					passsb.append("}");
					passsb.append("else");
					passsb.append("{");
					passsb.append("$(\".passwordStrength\").show();");
					passsb.append("obj.parent().next().find(\".Validform_checktip\").hide();");
					passsb.append("}");
					passsb.append("}");// trigger结尾
					passsb.append("}");// passwordstrength结尾
				}
				StringBuffer jqsb = new StringBuffer();
				if (usePlugin.indexOf("jqtransform") >= 0) {
					if (usePlugin.indexOf("password") >= 0) {
						sb.append(",");
					}
					jqsb.append("jqtransform :{selector:\"select\"}");
				}
				sb.append("usePlugin:{");
				if (usePlugin.indexOf("password") >= 0) {
					sb.append(passsb);
				}
				if (usePlugin.indexOf("jqtransform") >= 0) {
					sb.append(jqsb);
				}
				sb.append("},");
			}
			sb.append("callback:function(data){");
				sb.append("" + callback + "(data);");
			sb.append("}" + "});" + "});" + "</script>");
			sb.append("");
			
			sb.append("</form>");
			if ("div".equals(layout)) {
				sb.append("</div>");
				if (tabtitle != null) {
					String[] tabtitles = tabtitle.split(",");
					sb.append("<div id=\"navigation\" style=\"display: none;\">");
					sb.append("<ul>");
					for (String string : tabtitles) {
						sb.append("<li>");
						sb.append("<a href=\"#\">" + string + "</a>");
						sb.append("</li>");
					}
					sb.append("</ul>");
					sb.append("</div>");
				}
				sb.append("</div></div>");
			}
			out.print(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public void setUsePlugin(String usePlugin) {
		this.usePlugin = usePlugin;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void setBeforeSubmit(String beforeSubmit) {
		this.beforeSubmit = beforeSubmit;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

}
