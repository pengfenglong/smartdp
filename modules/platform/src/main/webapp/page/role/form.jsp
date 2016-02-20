<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<script type="text/javascript">
var roleId = '';
$(function() {
	$.crud.init({
		beforSave:function(){
			var menus = [];
			var operates = [];
			for(var i=0;i<$('#menuAndOperateTree').tree('getChecked').length;i++){
				var node = $('#menuAndOperateTree').tree('getChecked')[i];
				if(node.iconCls){
					var parentId = $('#menuAndOperateTree').tree('getParent',node.target).id;
					operates.push(parentId + ':' + node.id);
				}else{
					menus.push(node.id);
				}
			}
			$('#privis').val(menus.join('-')+';'+operates.join('-'));
		},
		onLoadCreate:function(data){
			loadTree();
		},
		onLaodEdit:function(data){
			roleId = data.roleId;
			loadTree();
		}
	});
	function loadTree(){
		 $('#menuAndOperateTree').tree({  
			 checkbox:true,
	         url: '<%=request.getContextPath()%>/platform-listMenuAndOperate-Menu.action?parentId=&roleId='+ roleId,
	         onBeforeExpand:function(node,o){
	        	 $(this).tree('options').url = '<%=request.getContextPath()%>/platform-listMenuAndOperate-Menu.action?parentId='+node.id+'&roleId='+ roleId;
	         }
	     }); 
	}
});
</script>
<sm:validform formid="smart-form" layout="div" dialog="false" action="platform-save-Role.action">
	<input type="hidden" id="privis" name="privis"/>
	<sm:div label="角色名称" need="true">
		<sm:validinput datatype="s2-8" name="roleName"></sm:validinput>
	</sm:div>
	<sm:div label="角色描述" need="true">
		<sm:validinput  datatype="s2-8" name="memo"></sm:validinput>
	</sm:div>
	<sm:div label="权限设置" need="true">
		<sm:tabs>
			<sm:tab title="模块菜单权限">
				<div id="tree" style="height: 400px;overflow: auto;">
					<!--<sm:tree id="menuAndOperateTree" param="roleId:roleId"  checkbox="true" url="platform-listMenuAndOperate-Menu.action"></sm:tree>-->
					<div id="menuAndOperateTree"></div>
				</div>
			</sm:tab>
			<sm:tab title="URL访问权限"></sm:tab>
		</sm:tabs>
	</sm:div>
</sm:validform>
