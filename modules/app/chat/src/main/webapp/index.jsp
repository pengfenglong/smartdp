<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<style type="text/css">
.ke-icon-history {
    background-image: url(../skins/default/default.gif);
    background-position: 0px -304px;
    width: 16px;
    height: 16px;
    position: absolute;
    right: 10px;
}
</style>
<script type="text/javascript" src="/smartdp/framework/plugin/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="js/chat.js"></script>
<script type="text/javascript">
var editor;
KindEditor.ready(function(K) {
	editor = K.create('#content', {
		uploadJson : contextPath+'/chat-uploadFile-Chat.action',
		afterUpload : function(url) {
            alert(url);
   		},
		resizeType : 0,
		allowUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', '|', 'emoticons', 'insertfile'
		]
	});
//	K.ctrl(document.body, 13, function() {
//		subMsg();
//	});
});

function userDblClickRow(rowIndex, rowData){
	if(rowData.userCode != login_user_id){
		createChatWin(rowData);
	}
}

function chatTabsOnSelect(title){
	var user = title.split('/');
	chat_user_id = user[1];
	chat_user_name = user[0];
	$("span:contains("+title+")").removeClass('no-read-tips');
}
</script>
<style type="text/css">
	.message-list{
		margin: 2px;
	}
	.message-detail{
		/**border-bottom: 1px gray solid;*/
		padding: 3px;
		margin:px;
		cursor: pointer;
	}
	.no-readmessage-num{
		color: red;
		font-weight:bolder;
		position: relative;
		top: -4px;
		left: -8px;
		margin: 0 2px;
	}
	.chat-history-user{
		font-weight: bolder;
		width:60px;
		white-space:nowrap;
		text-overflow: ellipsis;
	}
	.chat-history-message{
		color: gray;
		width:110px;
		white-space:nowrap;
		text-overflow: ellipsis;
	}
	.history_over {
		background: none repeat scroll 0 0 #FFF2FF;
	}
	.history_choose {  
    	background: none repeat scroll 0 0 #EAF2FF;  
	}
	.no-read-tips{
		font-weight:bolder;
		color: red;
	}
</style>
</head>
<body>
	<sm:layoutcontainer>
		<sm:layout region="north" height="30px">
			<div style="background-color: #0080FF;color:white;font-weight: bolder;height: 100%;width: 100%;">
				<span style="margin-left: 10px;line-height: 30px;">在线聊天系统<span>(1/10)</span> --- <span id="my-name"></span></span>
			</div>
		</sm:layout>
		<sm:layout region="west" width="200px" split="true" border="true">
			<sm:tabs fit="true">
				<sm:tab title="消息">
					<div class="message-list"></div>
				</sm:tab>
				<sm:tab title="部门">
				<div style="width: 100%;height: 510px;">
				<sm:layoutcontainer>
					<sm:layout region="north" height="200px" split="true">
						<sm:tree id="groupTree" url="chat-groupTree-Chat.action" onClick="function(node) {
											groupId = node.id;
											$('#smart-datagrid').datagrid('load', {
												'groupId' : groupId
											})}">
						</sm:tree>
					</sm:layout>
					<sm:layout region="center">
						<!-- <div id="tb"><input id="ss" class="easyui-searchbox" style="width:150px;"/><div style="float: right;padding: 2px;"><input type="checkbox"/>显示离线</div></div> -->
						<sm:datagrid url="chat-listUser-Chat.action"  toolbar="#tb" pagination="false" fitColumns="true" selectOnCheck="true"
							onDblClickRow="function(rowIndex, rowData){
								userDblClickRow(rowIndex, rowData);
							}">
							<sm:datagridcolumn field="userName" width="1">用户</sm:datagridcolumn>
						</sm:datagrid>
					</sm:layout>
				</sm:layoutcontainer>
				</div>
				</sm:tab>
				<sm:tab title="群组">
					<div style="margin: 2px;">
						<sm:linkbutton iconCls="icon-add" onclick="$.crud.createWindow('创建群组','groups.jsp',670,500);"></sm:linkbutton>
						<sm:linkbutton iconCls="icon-remove"></sm:linkbutton>
					</div>
				</sm:tab>
			</sm:tabs>
		</sm:layout>
		<sm:layout region="center" border="true">
			<sm:layoutcontainer>
		    	<sm:layout region="center" border="true">
		    		<sm:tabs id="usertabs" fit="true" onSelect="function(title){
						chatTabsOnSelect(title);
		    		}"></sm:tabs>
		    	</sm:layout>
		    	<sm:layout region="south" height="200px;">
		    		<textarea style="width: 99.5%;height: 160px;" id="content"></textarea>
		    		<div style="float: left;margin: 5px;"><sm:linkbutton onclick="showHistoryMessage();">消息记录</sm:linkbutton></div>
		    		<div style="float: right;margin: 5px;"><sm:linkbutton>关闭</sm:linkbutton><sm:linkbutton onclick="subMsg();">发送</sm:linkbutton></div>
		    	</sm:layout>
			</sm:layoutcontainer>
		</sm:layout>
	</sm:layoutcontainer>
</body>
</html>
