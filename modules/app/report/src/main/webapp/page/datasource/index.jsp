<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:linkbutton onclick="alert('test');">测试连接</sm:linkbutton>
<sm:datagrid title="数据源管理" url="report-list-Datasource.action" idField="id" autotool="add,edit,delete">
	<sm:datagridcolumn field="name" width="1" query="true">数据源名称</sm:datagridcolumn>
	<sm:datagridcolumn field="dsType" width="1">类型</sm:datagridcolumn>
	<sm:datagridcolumn field="dsProperties" width="1">jdbc属性</sm:datagridcolumn>
</sm:datagrid>
