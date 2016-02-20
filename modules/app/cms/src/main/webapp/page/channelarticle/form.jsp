<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<script type="text/javascript">
	$(function() {
		var editor;
		$.getScript(contextPath+'/framework/plugin/kindeditor/kindeditor-min.js', function() {
			KindEditor.basePath = contextPath+'/framework/plugin/kindeditor/';
			editor = KindEditor.create('#_content',{
				resizeType : 0,
				allowPreviewEmoticons : false,
				allowImageUpload : false,
				items : [
					'source', '|','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
					'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
					'insertunorderedlist', '|', 'emoticons', 'image', 'link','media'],
				afterChange:function(){
					//$('#_content').html(editor.html());
				}
			});
		});
		
		$.crud.init({
			beforSave:function(){
				$('#h_content').val(editor.html());
				return true;
			},
			//新增时
			onLoadCreate:function(){
				$('#channelId').val(channelId);
				$('#channelName').html(channelName);
			},
			onLaodEdit:function(data){
				var select = $('#smart-datagrid').datagrid('getSelected');
				channelId = select.channel.id;
				$('#channelId').val(channelId);
				$('#channelName').html(select.channel.name);
				$('#_content').html($('#h_content').val());
			},
			onSuccess:function(){
				$.crud.createNote('保存成功');
				$('#smart-datagrid').datagrid('load',{
					'channelId' : channelId
				});
				$('#smart-datagrid').datagrid('clearSelections');
				$('#smart-datagrid').datagrid('uncheckAll');
				$('.datagrid-header-check input').attr('checked',false);
			}
		});
	});
</script>
<sm:validform formid="smart-form" layout="div" dialog="false" action="cms-save-Article.action">
	<sm:div label="分类">
		<input type="hidden" id="channelId" name="channel.id" />		
	    <div id="channelName"></div>
	 </sm:div>
	<sm:div label="标题" need="true">
		<sm:validinput id="title" datatype="*1-100" name="title"></sm:validinput>
	</sm:div>
	<sm:div label="关键字">
	    <sm:validinput  datatype="*1-50" ignore="ignore" name="shortTitle"></sm:validinput>
	</sm:div>
 	<sm:div label="排序">
	    <sm:validinput  datatype="n1-5" ignore="ignore" name="sort"></sm:validinput>
	</sm:div>
 	<sm:div label="图文封面">
	    <sm:validinput  ignore="ignore" type="file"></sm:validinput>
	</sm:div>
	<sm:div label="简介">
		<textarea datatype="*1-255" ignore="ignore" name="description"style="width:50%;height: 80px;"></textarea>
	 </sm:div>
	 <!-- 

	<sm:div label="是否置顶">
	 </sm:div>
	<sm:div label="是否推荐">
	 </sm:div>
	<sm:div label="状态(0:草稿;1:审核中;2:已审核;3:回收站)" need="true">
	 </sm:div>
	<sm:div label="副标题">
	    <sm:validinput  datatype="s1-100" ignore="ignore" name="subTitle"></sm:validinput>
	 </sm:div>
	<sm:div label="来源">
	    <sm:validinput  datatype="s1-50" ignore="ignore" name="origin"></sm:validinput>
	 </sm:div>
	<sm:div label="来源链接">
	    <sm:validinput  datatype="s1-50" ignore="ignore" name="originUrl"></sm:validinput>
	 </sm:div>

	<sm:div label="是否允许评论">
	 </sm:div>
	<sm:div label="指定模板">
	    <sm:validinput  datatype="s1-100" ignore="ignore" name="tplContent"></sm:validinput>
	 </sm:div>
	  -->
	<sm:div label="文章内容" need="true">
		<input type="hidden" id="h_content" name="content" datatype="*1-2147483647"/>
	    <textarea id="_content" style="width:85%;height: 250px;" class="inputxt"></textarea>
	 </sm:div>
</sm:validform>