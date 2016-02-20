<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<% 
String contextPath = request.getContextPath();
%>
<script type="text/javascript">
<!--
	var contextPath = '<%=contextPath%>';
//-->
</script>
<script type="text/javascript">
$(function(){

	/**获得父分类路径开始*/
	var path = [];
	var select = $('#smart-treegrid').treegrid('getSelected');
	function loop(n){
		if(n == null){
			path.push('根分类');
		}else{
			path.push(n.name);
			var node = $('#smart-treegrid').treegrid('getParent',n.id);
			loop(node);
		}
	 }
	
	function getPath(type){
		var n=0;
		if('create' == type){
			n = 0;
		}else if('edit' == type){
			n = 1;
		}
		loop(select);
		var s = '';
		for(var i=path.length-1;i>=n;i--){
			if(i == n){
				s += path[i];
			}else{
				s += path[i] + ' --> ';
			}
		}
		return s;
	}
	/**获得父分类路径结束*/
	
	
	$.crud.init({
		beforSave:function(){
			$('#siteId').val('1');
			return true;
		},
		//新增时
		onLoadCreate:function(){
			$('#parent').html(getPath('create'));
			if(select==null){
				$('#parentId').remove();
			}else{
				$('#parentId').val(select.id);
			}
			
		},
		onLaodEdit:function(data){
			$('#parent').html(getPath('edit'));
			$('.smart_icon').attr('src',contextPath+$('#icon').val());
			if(select.parent==null){
				$('#parentId').remove();
			}else{
				$('#parentId').val(select.parent.id);
			}
			
		},
		onSuccess:function(){
			$('#smart-treegrid').treegrid('reload');
		}
	});
})

function selectIcon(me){
	$.ajax({
		url:contextPath+'/framework-list-Icon.action',
		success:function(data){
			var s = [];
			for(var i=0;i<data.length;i++){
				s.push('<img class="smart_icon" src="'+contextPath+data[i]+'" />');
			}
			var iconWin = $('<div>').dialog({
				title : '选择图标',
				height: 310,
				width: 455,
				left:$(me).position().left+50,
				top:$(me).position().top+30,
				content : s.join('')
			});
			
			$('.smart_icon').click(function(){
				$(me).attr('src',$(this).attr('src'));
				$('#icon').val($(this).attr('src').split(contextPath)[1]);
				$(me).addClass('smart_icon');
				iconWin.dialog('close');
			});
		}
	});
}


</script>
<style>
	.smart_icon{
		margin:2px;
		background:#778899;
		width:45px;
		height:45px;
		cursor: pointer;
	}
	.smart_icon:hover{
		background:4169E1;
		color:red;
	}
</style>
<sm:validform formid="smart-form" layout="div" dialog="false" action="cms-save-Channel.action">
	<input type="hidden" id="siteId" name="site.id"/>
	<sm:div label="父分类">
		<input type="hidden" id="parentId" name="parent.id"/>
	    <div id="parent"></div>
	 </sm:div>
	<sm:div label="分类名称" need="true">
	    <sm:validinput  datatype="s1-50" name="name"></sm:validinput>
	 </sm:div>
	<sm:div label="排列顺序">
		<sm:validinput  datatype="n1-10" ignore="ignore" name="priority"></sm:validinput>
	 </sm:div>
	<sm:div label="描述">
	    <textarea datatype="s1-255" ignore="ignore" name="description" style="width:260px;height: 80px;"></textarea>
	 </sm:div>
	<sm:div label="图标">
		<img class="smart_icon" src="" onclick="selectIcon(this);" alt="换一个" />
		<input type="hidden" id="icon" name="icon" />
	 </sm:div>
	<sm:div label="是否显示">
	    <sm:select dict="isNeed"  name="isShow"></sm:select>
	 </sm:div>
	<sm:div label="响应类型">
		<select class="easyui-combobox" name="path" style="width:150px;" data-options="onSelect:function(data){
			$('._path').hide();
			if(data.value == 'link'){
				$('#link').show();
			}else if(data.value == 'business'){
				$('#business').show();
			}else if(data.value == 'phone'){
				$('#phone').show();
			}
		}">
			<option value="menu">菜单</option>
	    	<option value="children">子分类</option>
	    	<option value="article">文章</option>
	    	<option value="link">链接</option>
	    	<option value="business">业务模块</option>
	    	<option value="phone">电话</option>
	    	<option value="navigation">导航</option>
	    </select>
	 </sm:div>
	 <div id="link" style="display:none;" class="_path">
	 	<sm:div label="链接地址">
		    <sm:validinput name="link"></sm:validinput>
		</sm:div>
	</div>
	<div id="business" style="display:none;" class="_path">
	 	<sm:div label="业务模块">
		    <sm:validinput name="business"></sm:validinput>
		</sm:div>
	</div>
	<div id="phone" style="display:none;" class="_path">
	 	<sm:div label="电话">
		    <sm:validinput name="phone"></sm:validinput>
		</sm:div>
	</div>
</sm:validform>