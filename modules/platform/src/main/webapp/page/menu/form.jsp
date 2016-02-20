<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<script type="text/javascript">
	var selectMenu;
	if($('#smart-treegrid').length > 0){
		selectMenu = $('#smart-treegrid').treegrid('getSelected');
	}else if($('#smart-datagrid').length > 0){
		selectMenu = $('#smart-datagrid').datagrid('getSelected');
	}
	$.crud.init({
		onLoadCreate:function(data){
			if($('#smart-treegrid').length > 0){
				if(selectMenu == null){
					$('#parent').html('主菜单');
					$('#parentId').val('');
				}else{
					$('#parent').html(selectMenu.menuName);
					$('#parentId').val(selectMenu.menuId);
				}
			}
		},
		onLaodEdit:function(data){
			if(selectMenu.parentMenu == null){
				if($('#smart-treegrid').length > 0){
					$('#parent').html('主菜单');
				}
				$('#parentId').val('');
			}else{
				if($('#smart-treegrid').length > 0){
					$('#parent').html(selectMenu.parentMenu.menuName);
				}else if($('#smart-datagrid').length > 0){
					$('#menuTree').combotree("setValue",selectMenu.parentMenu.menuName);
				}
				$('#parentId').val(selectMenu.parentMenu.menuId);
			}
			
		}
	});
</script>
<sm:validform formid="smart-form" layout="div" dialog="false" action="platform-save-Menu.action">
	<sm:div label="父级菜单">
		<input type="hidden" id="parentId" name="parentMenu.menuId">
		<span id="parent">
			<sm:combotree id="menuTree" url="platform-tree-Menu.action" onSelect="function(node,o){$('#parentId').val(node.id);}"></sm:combotree>
		</span>
	</sm:div>
	<sm:div label="菜单名称" >
		<sm:validinput datatype="s2-8"  name="menuName"></sm:validinput>
	</sm:div>
	<sm:div label="菜单编码" >
		<sm:validinput datatype="s2-8" name="menuCode"></sm:validinput>
	</sm:div>
	<sm:div label="菜单等级" >
		<sm:validinput datatype="n1-3" ignore="ignore"  name="menuLevel"></sm:validinput>
	</sm:div>
	<sm:div label="菜单排序" >
		<sm:validinput datatype="n1-3" ignore="ignore"  name="menuOrder"></sm:validinput>
	</sm:div>
	<sm:div label="菜单打开方式" >
		<sm:validinput datatype="n1-3" ignore="ignore" name="menuIframe"></sm:validinput>
	</sm:div>
	<sm:div label="菜单地址" >
		<sm:validinput datatype="s2-8" name="menuUrl" style="width:300px;"></sm:validinput>
	</sm:div>
</sm:validform>
