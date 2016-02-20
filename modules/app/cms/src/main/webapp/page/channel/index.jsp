<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<script>
	function _format(d){
		if(d){
			return '<img style="background:#778899;" width="35px" height="35px" src="'+contextPath+d+'"/>';
		}
	}
	function preview(){
		window.open('http://localhost:8080/smartdp/template/wap/index.html?site=1','newwindow','height=520,width=320,top=50,left=800,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
	}
	$(function(){
		//$('.panel-tool').html('<a href="#" style="width:80;font-weight: bolder;" onclick="preview()">微官网预览</a>');
	});
</script>
<sm:treegrid title="分类管理" url="cms-treegrid-Channel.action" idField="id" treeField="name" autotool="add,edit,delete">
	<sm:datagridcolumn field="name" width="1">栏目名称</sm:datagridcolumn>
	<sm:datagridcolumn field="priority" width="1">排列顺序</sm:datagridcolumn>
	<sm:datagridcolumn field="isShow" width="1">是否显示</sm:datagridcolumn>
	<sm:datagridcolumn field="icon" width="1" formatter="_format">图标</sm:datagridcolumn>
</sm:treegrid>