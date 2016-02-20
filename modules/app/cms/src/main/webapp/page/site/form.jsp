<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<sm:base type="jquery,easyui"></sm:base>
<% 
String contextPath = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
%>
<script type="text/javascript">
<!--
	var contextPath = '<%=contextPath%>';
	var basePath = '<%=basePath%>';
//-->
</script>
<script type="text/javascript">
	function preview(){
		window.open(basePath+'template/wap/index.html?site=1','newwindow','height=520,width=320,top=50,left=800,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
	}
	$(function(){
		$('.panel-tool').html('<a href="#" style="width:80;font-weight: bolder;" onclick="preview()">微官网预览</a>');
	});
	function release(){
		$.messager.progress({ 
			title:'请稍后', 
			msg:'站点发布中...' 
		}); 
		$.ajax({
			url:contextPath+'/cms-release-Site.action?siteIds=1',
			success:function(data){
				if(data.success){
					$.messager.progress('close');
					$.messager.alert('提示','站点发布成功。');
				}else{
					$.messager.alert('提示','站点发布失败。');
				}
			}
		});
	   }
	
	$(function(){
		$.ajax({
			url:contextPath+'/cms-getById-Site.action?siteId=1',
			success:function(data){
				$('#smart-form').form('load', data);
				$('#url').html(basePath+'template/wap/index.html?site=1');
				//$('#url').attr('href',basePath+'template/wap/index.html?site=1');
				$('#url').click(function(){
					preview();
				});
			}
		});
	})
	function saveSuccess(){
		alert('保存成功');
	}
</script>
<sm:panel title="基本设置" fit="true">
	<sm:validform formid="smart-form" layout="div" dialog="false" action="cms-save-Site.action" callback="saveSuccess">
		<input type="hidden" name="id" value="1"/>
		<sm:div label="名称" need="true">
			<sm:validinput  datatype="s1-100" name="siteName"></sm:validinput>
		</sm:div>
		<sm:div label="标题">
		    <sm:validinput  datatype="s1-80" ignore="ignore" name="title"></sm:validinput>
		 </sm:div>
		<sm:div label="关键字">
		    <sm:validinput  datatype="s1-100" ignore="ignore" name="keywords"></sm:validinput>
		 </sm:div>
		<sm:div label="图文封面">
			<sm:validinput  datatype="s1-100" ignore="ignore"  type="file"></sm:validinput>
		</sm:div>
	
		<sm:div label="描述">
			<textarea datatype="s1-255" ignore="ignore" name="description" style="width:260px;height: 80px;"></textarea>
		</sm:div>
		<sm:div label="首页地址">
			<a id="url" href="#"></a><input type="button" onclick="window.clipboardData.setData($('#url').html());alert('复制成功');" value="复制" />
		 </sm:div>
		 <div style="float:right;margin: 5px;">
			<!-- <sm:linkbutton onclick="preview();">微官网预览</sm:linkbutton>
			 <sm:linkbutton onclick="release();">发布</sm:linkbutton>-->
			 <sm:linkbutton onclick="$('#btn_sub').click();">保存</sm:linkbutton>
		 </div>
	</sm:validform>
</sm:panel>
</body>