<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:linkbutton onclick="$.crud.edit('模块分配操作','moduleform.jsp')">模块分配操作</sm:linkbutton>
<sm:treegrid title="菜单管理"  url="platform-treegrid-Menu.action" idField="menuId" treeField="menuName">
	<sm:datagridcolumn field="menuName" width="350">模块菜单名称</sm:datagridcolumn>
	<sm:datagridcolumn field="menuCode" width="350">模块菜单编码</sm:datagridcolumn>
	<sm:datagridcolumn field="menuOrder" width="350">模块菜单排序</sm:datagridcolumn>
</sm:treegrid>
