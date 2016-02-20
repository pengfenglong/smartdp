<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript">
	var groupId;
	var groupName;
	$(function(){
		$('#groupId').val(groupId);
	});
	function groupFormatter(val,row,index){
		return row.groups[0].groupSimpleName;
	}
</script>
<sm:layoutcontainer>
	<sm:layout region="west" width="200px" split="true">
		<sm:panel title="部门"  fit="true">
			<div style="margin: 5px;">
				<a href="#" class="easyui-linkbutton"
					onclick="groupId='';$('#groupTree').tree('collapseAll');$('#smart-datagrid').datagrid('load',{'groupId':''});">全部部门</a>
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
				<div id="mm" class="easyui-menu" style="width: 120px;">
					<div iconCls="icon-add" onclick="createGroup()">新增部门</div>
					<div iconCls="icon-edit" onclick="editGroup()">修改部门</div>
					<div class="menu-sep"></div>
					<div onclick="expandAll()">全部展开</div>
					<div onclick="collapseAll()">全部收缩</div>
				</div>
			</div>
		</sm:panel>
	</sm:layout>
	<sm:layout region="center">
		<sm:datagrid title="用户管理" url="platform-list-User.action" idField="userId" autotool="add,edit,delete">
			<sm:datagridcolumn field="userCode" sortable="true" width="1" query="true">用户号</sm:datagridcolumn>
			<sm:datagridcolumn field="userName" width="1" query="true">用户名称</sm:datagridcolumn>
			<sm:datagridcolumn field="userGender" dict="true" width="1" query="true">性别</sm:datagridcolumn>
			<sm:datagridcolumn field="groupId" width="1" formatter="groupFormatter">所属部门</sm:datagridcolumn>
			<sm:datagridcolumn field="userQq" width="1">QQ</sm:datagridcolumn>
			<sm:datagridcolumn field="userMsn" width="1">MSN</sm:datagridcolumn>
			<sm:datagridcolumn field="status" dict="true" width="1">用户状态</sm:datagridcolumn>
			<sm:datagridcolumn field="userAvidate" width="1">有效期</sm:datagridcolumn>
		</sm:datagrid>
	</sm:layout>
</sm:layoutcontainer>
