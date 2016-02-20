<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<script type="text/javascript">
	var selectMenu = $('#smart-treegrid').treegrid('getSelected');
	$.crud.init({
		onLaodEdit:function(data){
			var parentId;
			if(selectMenu.parentMenu == null){
				parentId = "";
			}else{
				parentId = selectMenu.parentMenu.menuId;
			}
			$('#parentId').val(parentId);
		}
	});
</script>
<sm:validform formid="smart-form" layout="div" dialog="false" action="platform-changeParent-Menu.action">
	<input type="hidden" id="parentId" name="parentId">
	<div style="margin: 15px;">请选择父菜单 : </div>
	<a href="#" class="easyui-linkbutton" onclick="$('#parentId').val('');">主菜单</a>
	<div style="margin-right: 15px;">
		<sm:tree url="platform-tree-Menu.action" onClick="function(node) {
															if(node.id == selectMenu.menuId){
																alert('不能选择自己作为父菜单');
																return;
															}else{
																$('#parentId').val(node.id);
															}}"></sm:tree>
	</div>
</sm:validform>
