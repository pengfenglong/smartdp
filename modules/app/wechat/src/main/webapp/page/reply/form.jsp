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


</script>
<sm:validform formid="smart-form" layout="div" dialog="false" action="cms-save-Channel.action">
	<sm:div label="关键字" need="true">
	    <sm:validinput  datatype="s1-50" name="name"></sm:validinput>
	 </sm:div>
	<sm:div label="回复类型">
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
			<option value="text">文本</option>
	    	<option value="imagetext">图文</option>
	    	<option value="voice">语音</option>
	    </select>
	 </sm:div>
	 <sm:div label="回复内容">
		<textarea datatype="s1-255" ignore="ignore" name="description" style="width:260px;height: 80px;"></textarea>
	</sm:div>
	 <sm:div label="回复语音">
		 <sm:validinput type="file"></sm:validinput>
	</sm:div>
	 <sm:div label="图文消息">
	 	<div style="border: 1px red solid;width: 400px;height: 400px;">
	 		
	 	
	 	</div>
		 
	</sm:div>
</sm:validform>