<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript">
	$(function(){
		$('.panel-tool').html('<a href="#" style="width:120;font-weight: bolder;" onclick="window.location.href=\'index.jsp\';">切换到数据表格视图</a>');
	});
</script>
<sm:linkbutton onclick="$.crud.edit('移动菜单','treeSelect.jsp');">移动</sm:linkbutton>
<sm:treegrid title="菜单管理   -- 树形表格视图"  url="platform-treegrid-Menu.action" idField="menuId" treeField="menuName" autotool="add,edit,delete">
	<sm:datagridcolumn field="menuName" width="1">菜单名称</sm:datagridcolumn>
	<sm:datagridcolumn field="menuCode" width="1">菜单编码</sm:datagridcolumn>
	<sm:datagridcolumn field="menuUrl" width="1">菜单地址</sm:datagridcolumn>
	<sm:datagridcolumn field="menuOrder" width="1">菜单排序</sm:datagridcolumn>
</sm:treegrid>
