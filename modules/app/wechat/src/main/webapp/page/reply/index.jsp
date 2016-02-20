<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:datagrid title="关键字回复管理" url="reply-list-Message.action" idField="id" autotool="add,edit,delete">
	<sm:datagridcolumn field="msgType" width="1" query="true">关键字</sm:datagridcolumn>
	<sm:datagridcolumn field="toUserName" width="1">回复类型</sm:datagridcolumn>
	<sm:datagridcolumn field="fromUserName" width="2">回复内容</sm:datagridcolumn>
</sm:datagrid>
