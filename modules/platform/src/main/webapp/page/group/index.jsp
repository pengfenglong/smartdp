<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript">
	$(function(){
		$('.panel-tool').html('<a href="#" style="width:120;font-weight: bolder;" onclick="window.location.href=\'indexTree.jsp\';">切换到树形表格视图</a>');
	});
</script>
<sm:datagrid title="部门管理  -- 数据表格视图" url="platform-list-Group.action" idField="groupId" autotool="add,edit,delete">
	<sm:datagridcolumn field="groupSimpleName" width="1" query="true">简称名</sm:datagridcolumn>
	<sm:datagridcolumn field="groupFullName" width="2" query="true">全称名</sm:datagridcolumn>
	<sm:datagridcolumn field="groupCode" width="1">部门编码</sm:datagridcolumn>
	<sm:datagridcolumn field="groupParent" width="1">父级部门</sm:datagridcolumn>
	<sm:datagridcolumn field="groupAddress1" width="1">部门地址</sm:datagridcolumn>
	<sm:datagridcolumn field="groupTel1" width="1">部门电话</sm:datagridcolumn>
</sm:datagrid>
