<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<script type="text/javascript">
$(function() {
	
	$.crud.init({
		beforSave:function(){
			var roles = $('#roleCombogrid').combogrid('getValues');
			$('#roleIds').val(roles);
			return true;
		},
		//新增时
		onLoadCreate:function(){
			if(groupId != null){
				$('#groupId').val(groupId);
				$('#groupName').html(groupName);
			}else{
				$('#groupTree').show();
			}
		},
		onLaodEdit:function(data){
			var roles = data.roles;
			var roleArr = [];
			for(var i=0;i<roles.length;i++){
				roleArr.push(roles[i].roleId);
			}
			$('#roleCombogrid').combogrid('setValues',roleArr);
			$('#confirmPassword').val($('#userPassword').val());
			$('#groupId').val(data.groups[0].groupId);
			$('#groupName').html(data.groups[0].groupSimpleName);
		}
	});
});
</script>
<sm:validform formid="smart-form" layout="div" dialog="false" action="platform-save-User.action">
	<sm:div label="所属部门" >
		<input type="hidden" id="groupId" name="groupId">
		<span id="groupName">
			<span id="groupTree" style="display: none">
				<sm:combotree url="platform-tree-Group.action" onSelect="function(node,o){$('#groupId').val(node.id);}"></sm:combotree>
			</span>
		</span>
	</sm:div>
	<sm:div label="用户名称" need="true">
		<sm:validinput datatype="s2-8" name="userName"></sm:validinput>
	</sm:div>
	<sm:div label="用户号" need="true">
		<sm:validinput datatype="s2-8" name="userCode"></sm:validinput>
	</sm:div>
	<sm:div label="密码" need="true" >
		<sm:validinput type="password" datatype="s6-16" id="userPassword" name="userPassword" errormsg="密码范围在6~16位之间！"></sm:validinput>
	</sm:div>
	<sm:div label="确认密码" need="true">
		<sm:validinput type="password" datatype="*" recheck="userPassword" id="confirmPassword" name="confirmPassword" errormsg="您两次输入的账号密码不一致！"></sm:validinput>
	</sm:div>
	<sm:div label="所属角色" >
		<input type="hidden" id="roleIds" name="roleIds">
		<sm:combogrid id="roleCombogrid" url="platform-listAll-Role.action"  idField="roleId"  textField="roleName" multiple="true"   
		  columns="[[{field:'ck',checkbox:true},{field:'roleName',titlsm:'角色名称',width:1},{field:'memo',titlsm:'描述',width:1}]]"></sm:combogrid>
	</sm:div>
	<sm:div label="用户状态" >
		<sm:select dict="validState"  name="status"></sm:select>
	</sm:div>
	<sm:div label="性别" >
		<sm:select dict="sex"  name="userGender"></sm:select>
	</sm:div>
	<sm:div label="职位" >
		<sm:validinput datatype="s1-20" ignore="ignore" name="userPosition"></sm:validinput>
	</sm:div>
	<sm:div label="用户有效期" >
		<sm:datebox name="userAvidate"></sm:datebox>
	</sm:div>
	<sm:div label="用户邮箱" >
		<sm:validinput datatype="e" ignore="ignore" name="userEmail"></sm:validinput>
	</sm:div>
	<sm:div label="手机号码" >
		<sm:validinput datatype="m" ignore="ignore" name="userMobile"></sm:validinput>
	</sm:div>
	<sm:div label="手机短号" >
		<sm:validinput datatype="n1-4" ignore="ignore" name="userMobile2"></sm:validinput>
	</sm:div>
	<sm:div label="家庭地址" >
		<sm:validinput datatype="s1-100" ignore="ignore" name="userAddress"></sm:validinput>
	</sm:div>
	<sm:div label="QQ" >
		<sm:validinput datatype="s4-15" ignore="ignore" name="userQq"></sm:validinput>
	</sm:div>
	<sm:div label="MSN" >
		<sm:validinput datatype="s4-15" ignore="ignore" name="userMsn"></sm:validinput>
	</sm:div>
	<sm:div label="备注" >
		<sm:validinput type="areatext" datatype="s1-500" ignore="ignore" name="memo"></sm:validinput>
	</sm:div>
</sm:validform>