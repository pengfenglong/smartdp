<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:datagrid title="数据服务管理" url="report-list-DataService.action" idField="id" autotool="add,edit,delete">
	<sm:datagridcolumn field="name" width="1" query="true">数据服务名称</sm:datagridcolumn>
	<sm:datagridcolumn field="datasource" width="1">数据源</sm:datagridcolumn>
	<sm:datagridcolumn field="executeSql" width="1">执行的SQL</sm:datagridcolumn>
</sm:datagrid>
