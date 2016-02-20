<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:linkbutton onclick="alert('test');">测试</sm:linkbutton>
<sm:datagrid title="参数管理" url="platform-list-Parameter.action" idField="paraId" autotool="add,edit,delete">
	<sm:datagridcolumn field="paraKeyName" width="1" query="true">数参键名</sm:datagridcolumn>
	<sm:datagridcolumn field="paraName" width="1" query="true">数参名</sm:datagridcolumn>
	<sm:datagridcolumn field="paraType" width="1">数参类型</sm:datagridcolumn>
	<sm:datagridcolumn field="paraValue" width="1">数参值</sm:datagridcolumn>
</sm:datagrid>
