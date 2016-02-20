<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>在线聊天系统</title>
<link href="images/chat.css" rel="stylesheet" media="screen" type="text/css" />
</head>
<body>
<sm:layoutcontainer>
	<sm:layout region="north" border="true" height="40">
		在线聊天系统
	</sm:layout>
	<sm:layout region="west" split="true" width="180px">
		<sm:tabs>
			<sm:tab title="部门">
			<sm:tree id="groupTree" url="platform-tree-Group.action" onClick="function(node) {
										groupId = node.id;
										groupName = node.text;
										$('#smart-datagrid').datagrid('load', {
											'groupId' : groupId
										})}" onContextMenu="function(e, node) {
										e.preventDefault();
										$(this).tree('select', node.target);
										$('#mm').menu('show', {
											left : e.pageX,
											top : e.pageY
										});
									}">
				</sm:tree>
			</sm:tab>
			<sm:tab title="消息"></sm:tab>
		</sm:tabs>
	</sm:layout>
	<sm:layout region="center">
		<sm:layoutcontainer>
			<sm:layout region="west" width="180px">
				<sm:datagrid title="用户" url="platform-list-User.action" idField="userId" toolbar="#a" pagination="false" selectOnCheck="true">
					<sm:datagridcolumn field="userName" width="2">用户名称</sm:datagridcolumn>
				</sm:datagrid>
			</sm:layout>
			<sm:layout region="center">
				
			</sm:layout>
		</sm:layoutcontainer>
	</sm:layout>
</sm:layoutcontainer>
</body>
</html>
