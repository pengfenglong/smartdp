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
<sm:panel title="默认设置" fit="true">
	<sm:validform formid="smart-form" layout="div" dialog="false" action="cms-save-Site.action" callback="saveSuccess">
		<sm:div label="关注时回复">
			<textarea datatype="s1-255" ignore="ignore" name="description" style="width:260px;height: 80px;"></textarea>
		</sm:div>
		<sm:div label="无匹配回复">
			<textarea datatype="s1-255" ignore="ignore" name="description" style="width:260px;height: 80px;"></textarea>
		</sm:div>
		 <div style="float:right;margin: 5px;">
			 <sm:linkbutton onclick="$('#btn_sub').click();">保存</sm:linkbutton>
		 </div>
	</sm:validform>
</sm:panel>
</body>


