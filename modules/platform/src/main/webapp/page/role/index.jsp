<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:datagrid title="角色管理" url="platform-list-Role.action" idField="roleId" autotool="add,edit,delete">
	<sm:datagridcolumn field="roleName" width="1" query="true">角色名称</sm:datagridcolumn>
	<sm:datagridcolumn field="memo" width="1">角色描述</sm:datagridcolumn>
	<sm:datagridcolumn field="status" dict="true" width="1">用户状态</sm:datagridcolumn>
</sm:datagrid>
