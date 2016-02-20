<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<script type="text/javascript">
	var selectGroup;
	if($('#smart-treegrid').length > 0){
		selectGroup = $('#smart-treegrid').treegrid('getSelected');
	}else if($('#smart-datagrid').length > 0){
		selectGroup = $('#smart-datagrid').datagrid('getSelected');
	}
	$.crud.init({
		onLoadCreate:function(data){
			if($('#smart-treegrid').length > 0){
				if(selectGroup == null){
					$('#parent').html('根部门');
					$('#parentId').val('');
				}else{
					$('#parent').html(selectGroup.groupSimpleName);
					$('#parentId').val(selectGroup.groupId);
				}
			}
		},
		onLaodEdit:function(data){
			if(selectGroup.parentGroup == null){
				if($('#smart-treegrid').length > 0){
					$('#parent').html('主菜单');
				}
				$('#parentId').val('');
			}else{
				if($('#smart-treegrid').length > 0){
					$('#parent').html(selectGroup.parentGroup.groupSimpleName);
				}else if($('#smart-datagrid').length > 0){
					$('#groupTree').combotree("setValue",selectGroup.parentGroup.groupSimpleName);
				}
				$('#parentId').val(selectGroup.parentGroup.groupId);
			}
			
		}
	});
</script>
<sm:validform formid="smart-form" layout="div" dialog="false" action="platform-save-Group.action">
	<sm:div label="父级部门">
		<input type="hidden" name="parentId" id="parentId"/>
		<span id="parent">	
			<sm:combotree id="groupTree" url="platform-tree-Group.action" onSelect="function(node,o){$('#parentId').val(node.id);}"></sm:combotree>	
		</span>	
	</sm:div>
	<sm:div label="部门简称" checktip="请输入...">
		<sm:validinput datatype="s2-8" name="groupSimpleName"></sm:validinput>
	</sm:div>
	<sm:div label="部门全称" >
		<sm:validinput name="groupFullName"></sm:validinput>
	</sm:div>
	<sm:div label="描述" >
		<sm:validinput  name="memo"></sm:validinput>
	</sm:div>
	<sm:div label="部门编码" checktip="请输入数字、字母、下划线">
		<sm:validinput datatype="s1-10" name="groupCode"></sm:validinput>
	</sm:div>
	<sm:div label="部门地址1" >
		<sm:validinput  name="groupAddress1"></sm:validinput>
	</sm:div>
	<sm:div label="部门地址2" >
		<sm:validinput  name="groupAddress2"></sm:validinput>
	</sm:div>
	<sm:div label="部门电话1" >
		<sm:validinput  name="groupTel1"></sm:validinput>
	</sm:div>
	<sm:div label="部门电话2" >
		<sm:validinput  name="groupTel2"></sm:validinput>
	</sm:div>
	<sm:div label="部门建立时间" >
		<sm:datebox id="groupBeginDate"></sm:datebox>
	</sm:div>
	<sm:div label="部门类型" >
		<sm:validinput  name="groupType"></sm:validinput>
	</sm:div>
	<sm:div label="部门传真" >
		<sm:validinput  name="groupFax"></sm:validinput>
	</sm:div>
	<sm:div label="部门邮编" >
		<sm:validinput  name="groupPostal"></sm:validinput>
	</sm:div>
	<sm:div label="部门注册号" >
		<sm:validinput  name="groupRegNo"></sm:validinput>
	</sm:div>
</sm:validform>