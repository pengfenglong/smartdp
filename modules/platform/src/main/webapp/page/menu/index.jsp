<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript">
	$(function(){
		$('.panel-tool').html('<a href="#" style="width:120;font-weight: bolder;" onclick="window.location.href=\'indexTree.jsp\';">切换到树形表格视图</a>');
	});
</script>
<sm:datagrid title="菜单管理  -- 数据表格视图" url="platform-list-Menu.action" idField="menuId"  autotool="add,edit,delete">
	<sm:datagridcolumn field="menuName" width="1" query="true">菜单名称</sm:datagridcolumn>
	<sm:datagridcolumn field="menuCode" width="1">菜单编码</sm:datagridcolumn>
	<sm:datagridcolumn field="menuUrl" width="1">菜单地址</sm:datagridcolumn>
	<sm:datagridcolumn field="menuOrder" width="1">菜单排序</sm:datagridcolumn>
</sm:datagrid>
