<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:datagrid title="模块操作权限管理" url="platform-list-ModuleOperate.action" idField="operateId" autotool="add,edit,delete">
	<sm:datagridcolumn field="operateName" width="1" query="true">模块操作名称</sm:datagridcolumn>
	<sm:datagridcolumn field="optFunLink" width="1">模块操作功能链接</sm:datagridcolumn>
	<sm:datagridcolumn field="status" dict="true" width="1">模块操作状态</sm:datagridcolumn>
	<sm:datagridcolumn field="memo" dict="true" width="1">备注</sm:datagridcolumn>
</sm:datagrid>
