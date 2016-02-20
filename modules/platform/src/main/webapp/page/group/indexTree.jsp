<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript">
	$(function(){
		$('.panel-tool').html('<a href="#" style="width:120;font-weight: bolder;" onclick="window.location.href=\'index.jsp\';">切换到数据表格视图</a>');
	});
</script>
<sm:treegrid title="部门管理  -- 树形表格视图"  url="platform-treegrid-Group.action" idField="groupId" treeField="groupSimpleName" autotool="add,edit,delete">
	<sm:datagridcolumn field="groupSimpleName" width="200">部门名</sm:datagridcolumn>
	<sm:datagridcolumn field="groupType" width="200">部门类型</sm:datagridcolumn>
	<sm:datagridcolumn field="groupCode" width="200">部门编码</sm:datagridcolumn>
</sm:treegrid>
