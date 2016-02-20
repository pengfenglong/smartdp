<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<script>
$(function() {
	$.crud.init({
		beforSave:function(){
			upload();
			return false;
		}
	})
});
</script>
<sm:validform formid="smart-form" layout="div" dialog="false" action="cms-save-Template.action">
	<sm:div label="模板编码" need="true">
	    <sm:validinput  datatype="s1-255" id="code" name="code"></sm:validinput>
	 </sm:div>
	<sm:div label="板模名称" need="true">
		<sm:validinput  datatype="s1-255" name="name"></sm:validinput>
	 </sm:div>
	<sm:div label="模板文件">
	    <sm:upload uploader="/cms-upload-Template.action" multi="false" auto="false" extend="*.rar;*.zip" formData="code" onUploadSuccess="$('#btn_sub').click();$('#smart-datagrid').datagrid('load');"></sm:upload>
	 </sm:div>
</sm:validform>

