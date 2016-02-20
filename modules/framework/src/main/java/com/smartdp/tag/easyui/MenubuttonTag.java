package com.smartdp.tag.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * 菜单按钮标签
 * 
 * @author pengfenglong
 * 
 */
public class MenubuttonTag extends AbstractUITag {
	protected String plain;// 简洁模式
	protected String menu;// 菜单
	protected String duration;// 持续时间
	protected String options;// 返回属性对象。
	protected String disable;// 禁用菜单按钮。
	protected String enable;// 启用菜单按钮。

	public String getCss() {
		return "easyui-menubutton";
	}
	
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			StringBuffer start = new StringBuffer();
			start.append("<a href=\"#\" id=\"mb\" style=\"font-weight: bolder;\">主菜单</a>");
			start.append("<div id=\"mm\"></div>");
			start.append("<style type=\"text/css\">a{text-decoration:none;color:black;}</style>");
			start.append("<script type=\"text/javascript\">");
			start.append("	var temp = '';");
			start.append("	function createMenu(data){");
			start.append("		var _href = '#';");
			start.append("		if(data.menuUrl != null && data.menuUrl != ''){_href = data.menuUrl;}");
			start.append("		if(data.menus.length == 0){");
			start.append("			temp += '<div iconCls=\"icon-default\"><a target=\"mainframe\" href=\"'+_href+'\">'+data.menuName+'</a></div>';");
			start.append("		}else{");
			start.append("			temp += '<div>';");
			start.append("			temp += '	<span><a target=\"mainframe\" href=\"'+_href+'\">'+data.menuName+'</a></span>';");
			start.append("			temp += '	<div>';");
			start.append("			for(var i=0,n=data.menus.length;i<n;i++){");
			start.append("				createMenu(data.menus[i])");
			start.append("			}");
			start.append("			temp += '	</div>';");
			start.append("			temp += '</div>';");
			start.append("		}");
			start.append("	}");
			start.append("	$.ajax({");
			start.append("		  url: \""+getContextPath()+"/platform-listAll-Menu.action\",");
			start.append("		  dataType:\"json\",");
			start.append("		  success: function(data){");
			start.append("			   for(var i=0;i<data.length;i++){");
			start.append("				   if(data[i].parentMenu == null){");
			start.append("					   createMenu(data[i]);");
			start.append("				   }");
			start.append("			   }");
			start.append("			   $('#mm').append(temp);");
			start.append("			   $('#mb').menubutton({  ");
			start.append("				    menu: '#mm'  ");
			start.append("				}); ");
			start.append("			   $('#mm').menu({  ");
			start.append("					onClick:function(item){");
			start.append("						 $('#mainframe').attr('src',$(this).attr(item.name));"); 
			start.append("					}  ");
			start.append("			   }); ");
			start.append("		  }");
			start.append("	});");
			start.append("</script>");
			out.println(start.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_BODY_INCLUDE;
	}
	
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("plain", plain);
		parameters.put("menu", "'" + menu + "'");
		parameters.put("duration", "'" + duration + "'");
		parameters.put("options", options);
		parameters.put("disable", disable);
		parameters.put("enable", enable);
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
}