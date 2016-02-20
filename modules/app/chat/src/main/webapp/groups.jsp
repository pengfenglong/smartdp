<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<style>
 #selectusers p{
	border-bottom: 1px gray dashed;
	margin: 4px 20px;
	padding: 2px;
 }
  #selectusers p span{
    float:right;
	color: blue;
	margin-right: 5px;
	cursor: pointer;
 }
</style>
<script type="text/javascript">
	$.crud.init({
		beforSave:function(){
			var users = [];
			$('#selectusers p').each(function(){
				users.push($(this).attr('id'));
			});
			$('#users').val(users.join(','));
		}
	});
	function selectUser(rowIndex, rowData){
		var flag = true;
		$('#selectusers p').each(function(){
			if($(this).text().indexOf(rowData.userName) != -1){
				flag = false;
			}
		});
		if(flag){
			$('#selectusers').append('<p id="'+rowData.userCode+'">'+rowData.userName+'<span>X</span></p>');
		}
		$('#selectusers p span').click(function(){
			$(this).parent().remove();
		});
	}
</script>
<sm:validform formid="smart-form" layout="div" dialog="false" action="chat-saveGroups-Chat.action">
<div style="width: 98%;height: 98%">
<sm:layoutcontainer>
	<sm:layout region="north" height="45px">
		<div style="padding: 5px;">
		<sm:div label="群组名称">
			<sm:validinput name="name" style="width:250px;" datatype="*1-30"></sm:validinput>
		</sm:div>
		</div>
	</sm:layout>
	<sm:layout region="west" split="true" width="180px">
		<sm:panel title="选择部门" fit="true">
		<sm:tree id="groupTree" url="chat-groupTree-Chat.action" onClick="function(node) {
				groupId = node.id;
				$('#groups-users-datagrid').datagrid('load', {
					'groupId' : groupId
				})}">
		}">
		</sm:tree>
		</sm:panel>
	</sm:layout>
	<sm:layout region="center">
		<sm:datagrid title="选择用户" id="groups-users-datagrid" url="chat-listUser-Chat.action" toolbar="#toolb" pagination="false" fitColumns="false" selectOnCheck="true"
			onDblClickRow="function(rowIndex, rowData){
				selectUser(rowIndex, rowData);
			}">
			<sm:datagridcolumn field="userName" width="200">用户名称</sm:datagridcolumn>
		</sm:datagrid>
	</sm:layout>
	<sm:layout region="east" split="true" width="220px">
	<sm:panel title="已选用户" fit="true">
	<input type="hidden" id="users" name="users"/>
	<div id="selectusers"></div>
	</sm:panel>
	</sm:layout>
</sm:layoutcontainer>
</div>
</sm:validform>
