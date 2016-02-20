package com.smartdp.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.smartdp.core.utils.ConvertUtils;
import com.smartdp.tag.select.DictUtils;

public class BaseTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String type = "default";// 加载类型

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			String contexPath = request.getContextPath();
			
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();

			String types[] = type.split(",");
			if (ConvertUtils.isIn("jquery", types)) {
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/jquery/jquery-1.8.3.min.js\"></script>");
			}
			if (ConvertUtils.isIn("cookie", types)) {
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/jquery/jquery.cookie.js\"></script>");
			}
			if (ConvertUtils.isIn("easyui", types)) {
				//sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/tools/jquery.cookie.js\"></script>");
				//sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/tools/changeEasyuiTheme.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/tools/dataformat.js\"></script>");
				sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/easyui/themes/default/easyui.css\" type=\"text/css\"></link>");
				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/easyui/themes/icon.css\" type=\"text/css\"></link>");
				//sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contexPath+"/framework/plugin/accordion/css/accordion.css\">");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/easyui/jquery.easyui.min.1.3.2.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/easyui/locale/easyui-lang-zh_CN.js\"></script>");
				//sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/tools/syUtil.js\"></script>");
			}
			if (ConvertUtils.isIn("DatePicker", types)) {
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/My97DatePicker/WdatePicker.js\"></script>");
			}
			if (ConvertUtils.isIn("jqueryui", types)) {
				//----------------------------------------------------------------
				//update-begin--Author:zhangdaihao  Date:20130205 for：自动补全
//				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/jquery-ui/js/jquery-1.8.3.js\"></script>");
				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.min.css\" type=\"text/css\"></link>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/jquery-ui/js/jquery-ui-1.9.2.custom.min.js\"></script>");
			}
			if (ConvertUtils.isIn("jquerylayout", types)) {
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/jquery-plugs/jquery-layout/jquery.layout.js\"></script>");
			}
			
			if (ConvertUtils.isIn("prohibit", types)) {
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/tools/prohibitutil.js\"></script>");		}
			if (ConvertUtils.isIn("designer", types)) {
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/designer/easyui/jquery-1.7.2.min.js\"></script>");
				sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/designer/easyui/easyui.css\" type=\"text/css\"></link>");
				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/designer/easyui/icon.css\" type=\"text/css\"></link>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/designer/easyui/jquery.easyui.min.1.3.0.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/designer/easyui/locale/easyui-lang-zh_CN.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/tools/syUtil.js\"></script>");
				
				sb.append("<script type=\'text/javascript\' src=\'"+contexPath+"/framework/plugin/jquery/jquery-autocomplete/lib/jquery.bgiframe.min.js\'></script>");
				sb.append("<script type=\'text/javascript\' src=\'"+contexPath+"/framework/plugin/jquery/jquery-autocomplete/lib/jquery.ajaxQueue.js\'></script>");
				sb.append("<script type=\'text/javascript\' src=\'"+contexPath+"/framework/plugin/jquery/jquery-autocomplete/jquery.autocomplete.min.js\'></script>");
				sb.append("<link href=\""+contexPath+"/framework/plugin/designer/designer.css\" type=\"text/css\" rel=\"stylesheet\" />");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/draw2d/wz_jsgraphics.js\"></script>");
				sb.append("<script src=\'"+contexPath+"/framework/plugin/designer/draw2d/mootools.js\'></script>");
				sb.append("<script src=\'"+contexPath+"/framework/plugin/designer/draw2d/moocanvas.js\'></script>");
				sb.append("<script src=\'"+contexPath+"/framework/plugin/designer/draw2d/draw2d.js\'></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/MyCanvas.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/ResizeImage.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/event/Start.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/event/End.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/connection/MyInputPort.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/connection/MyOutputPort.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/connection/DecoratedConnection.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/task/Task.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/task/UserTask.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/task/ManualTask.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/task/ServiceTask.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/gateway/ExclusiveGateway.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/gateway/ParallelGateway.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/boundaryevent/TimerBoundary.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/boundaryevent/ErrorBoundary.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/subprocess/CallActivity.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/task/ScriptTask.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/task/MailTask.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/task/ReceiveTask.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/task/BusinessRuleTask.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/designer.js\"></script>");
				sb.append("<script src=\""+contexPath+"/framework/plugin/designer/mydesigner.js\"></script>");

			}
			if (ConvertUtils.isIn("datatables", types)) {
				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/datatables/css/datatables_style_02.css\" type=\"text/css\"></link>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/datatables/js/jquery.dataTables.min.js\"></script>");
			}
			if (ConvertUtils.isIn("highcharts", types)) {
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Highcharts-2.2.5/js/themes/grid.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Highcharts-2.2.5/js/highcharts.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Highcharts-2.2.5/js/modules/exporting.js\"></script>");
			}
//			if (ConvertUtils.isIn("validform", types)) {
//				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/Validform/css/divfrom.css\" type=\"text/css\"></link>");
//				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/Validform/css/style.css\" type=\"text/css\"></link>");
//				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/Validform/css/tablefrom.css\" type=\"text/css\"></link>");
//				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Validform/js/form.js\"></script>");
//				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Validform/js/Validform_v5.3.1_min.js\"></script>");
//				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Validform/js/Validform_Datatype.js\"></script>");
//				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/Validform/js/datatype.js\"></script>");//金额校验规则
//			}
			if (ConvertUtils.isIn("tools", types)) {
				//----begin -----Author:邢双阳   ---日期：2013-5-14----for：取消lhgDiaglog的风格设置参数，增加页面加载性能-----
				//sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/lhgDialog/lhgdialog.min.js\"></script>");
				//----end -----Author:邢双阳   ---日期：2013-5-14----for：取消lhgDiaglog的风格设置参数，增加页面加载性能-----
				//sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/artDiglog/plugins/iframeTools.js\"></script>");
				//sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/tools/curdtools.js\"></script>");
				//sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/tools/easyuiextend.js\"></script>");
			}
			if (ConvertUtils.isIn("toptip", types)) {
				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/toptip/css/css.css\" type=\"text/css\"></link>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/toptip/manhua_msgTips.js\"></script>");
			}
			if (ConvertUtils.isIn("multiselect2side", types)) {
				sb.append("<link rel=\"stylesheet\" media=\"screen\" href=\""+contexPath+"/framework/plugin//jquery-plugs/multiselect2side/css/jquery.multiselect2side.css\" type=\"text/css\"></link>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/jquery-plugs/multiselect2side/js/jquery.multiselect2side.js\"></script>");
			}
			if (ConvertUtils.isIn("kindeditor", types)) {
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/kindeditor/kindeditor-min.js\"></script>");
			}
			if (ConvertUtils.isIn("syntaxhighlighter", types)) {
				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/SyntaxHighlighter/styles/shCoreDefault.css\" type=\"text/css\"></link>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/SyntaxHighlighter/scripts/shCore.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/SyntaxHighlighter/scripts/shBrushJScript.js\"></script>");
			}
			if (ConvertUtils.isIn("codemirror", types)) {
				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/CodeMirror/lib/codemirror.css\" type=\"text/css\"></link>");
				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/plugin/CodeMirror/theme/eclipse.css\" type=\"text/css\"></link>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/CodeMirror/lib/codemirror.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/CodeMirror/mode/javascript/javascript.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/CodeMirror/keymap/extra.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/CodeMirror/addon/selection/active-line.js\"></script>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/CodeMirror/addon/edit/matchbrackets.js\"></script>");
				
			}
			if (ConvertUtils.isIn("framework", types)) {
				sb.append("<link rel=\"stylesheet\" href=\""+contexPath+"/framework/resources/css/main.css\" type=\"text/css\"></link>");
				sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/resources/js/easy-curd.js\"></script>");
			}
			
			// sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/easyui/myplug/easycurd.js\"></script>");
			// sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/easyui/myplug/mask.js\"></script>");
			// sb.append("<script type=\"text/javascript\" src=\""+contexPath+"/framework/plugin/easyui/myplug/windowControl.js\"></script>");
			
			sb.append("<script type=\"text/javascript\">");
			sb.append("var dictJson = ").append(DictUtils.dictToJsonObject()).append(";");
			sb.append("</script>");
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}
